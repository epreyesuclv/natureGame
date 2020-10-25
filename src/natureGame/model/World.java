package natureGame.model;

import natureGame.framework.fileIO.Settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//ivett
public class World {
    public List<Animal> vivos;
    public int[] buffervivos;
    public int[] buffermuertos;
    public int[] bufferTotal;
    public List<Animal> muertos;
    public Animal currentAnimal;
    private List<Animal> inmovilList;
    private boolean gameOver = false;
    public int[][] mapa;
    public int[][] mapa2;
    int nextIndex;
    private int growCounter = 0;

    //ivett
    public World() {
        vivos = Settings.list;
        buffervivos = new int[10];
        buffermuertos = new int[10];
        bufferTotal = new int[10];
        mapa = new int[Settings.x][Settings.y];
        mapa2 = new int[Settings.x][Settings.y];

        muertos = new ArrayList<>();
        inmovilList = Settings.inmoviles;
        currentAnimal = vivos.get(vivos.size() - 1);
        nextIndex = vivos.size() - 1;
        for (int i = 0; i < vivos.size(); i++) {
            buffervivos[vivos.get(i).getRefer()]++;

        }
        for (int i = 0; i < inmovilList.size(); i++) {
            buffervivos[inmovilList.get(i).getRefer()]++;
        }
        updateMapa();
        inmovilList = null;
    }

    //ivett
    public void update(float deltaTime) {
        if (gameOver) return;
        if (currentAnimal.getIsMoving() != 0) {
            if (currentAnimal.keepMoving()) {
                mapa[currentAnimal.getX()][currentAnimal.getY()] = currentAnimal.getRefer();
                next();
            }
        } else {

            if (muere(currentAnimal)) {
                buffervivos[currentAnimal.getRefer()]--;
                buffermuertos[currentAnimal.getRefer()]++;
                next();
                return;
            }
            if (reproduccion(currentAnimal))
                buffervivos[currentAnimal.getRefer()]++;

            int r = alimentacion(currentAnimal);
            if (r != 0) {
                buffervivos[r]--;
                buffermuertos[r]++;
            }
            if (growPlant())
                buffervivos[2]++;
        }
    }

    //ivett
    private boolean growPlant() {
        if (growCounter++ < 9) return false;
        List<Pos> list = new ArrayList<>();
        for (int i = 0; i < Settings.x; i++)
            for (int j = 0; j < Settings.y; j++) {
                if (mapa[i][j] == 0 && mapa2[i][j] == 0) list.add(new Pos(i, j, 0));
            }
        if (list.isEmpty()) {
            gameOver = true;
            return false;
        }
        Random rand = new Random();
        int index = rand.nextInt(list.size());
        Pos pos = list.get(index);
        mapa2[pos.getX()][pos.getY()] = 2;
        growCounter = 0;
        return true;
    }

    //ivett
    public void updateMapa() {
        for (Animal a : vivos) {
            mapa[a.getX()][a.getY()] = a.getRefer();
        }
        for (Animal i : inmovilList) {
            mapa2[i.getX()][i.getY()] = i.getRefer();
        }
    }

    //ivett
    private void next() {
        nextIndex -= 1;
        if (nextIndex == -1) nextIndex = vivos.size() - 1;
        currentAnimal = vivos.get(nextIndex);
    }

    //ivett
    private void relocate(int indexRemoved) {
        if (indexRemoved < vivos.indexOf(currentAnimal)) nextIndex--;
    }

    //lis
    public void esAdyacente1(Animal a) {//es para facilitar el trabajo en el metodo alimentacion en una casilla
        if (a.getRefer() == 6) {
            esAdyacente2(a);
            return;
        }
        ArrayList<Pos> l = new ArrayList<>();
        for (int i = a.getX() - 1; i <= a.getX() + 1; i++) {//moverme por las filas del mapa
            for (int j = a.getY() - 1; j <= a.getY() + 1; j++) //moverme por las columnas
                if (i >= 0 && i < mapa.length && j >= 0 && j < mapa[0].length) {
                    if (mapa[i][j] == 0 && mapa2[i][j] == 0) {//para encontrar un espacio vacio
                        l.add(new Pos(i, j, 0));
                    } else if (mapa2[i][j] == 1 && (a.getRefer() == 5 || a.getRefer() == 6)) {
                        l.add(new Pos(i, j, 1));
                    }
                }
        }
        if (!l.isEmpty()) {
            Random r = new Random();
            int n = r.nextInt(l.size());
            Pos p = l.get(n);
            actualizarMapa(p, a);
        }
    }

    //lis
    public void esAdyacente2(Animal a) {  //es para facilitar el trabajo en el metodo alimentacion en una casilla
        ArrayList<Pos> l = new ArrayList<>();
        for (int i = a.getX() - 2; i <= a.getX() + 2; i++) {//moverme por las filas del mapa
            for (int j = a.getY() - 2; j <= a.getY() + 2; j++) //moverme por las columnas
                if (i >= 0 && i < mapa.length && j >= 0 && j < mapa[0].length) {
                    if (mapa[i][j] == 0 && mapa2[i][j] == 0) {//para encontrar un espacio vacio
                        l.add(new Pos(i, j, 0));
                    } else if (mapa2[i][j] == 1 && (a.getRefer() == 5 || a.getRefer() == 6)) {
                        l.add(new Pos(i, j, 1));
                    }
                }
        }

        if (!l.isEmpty()) {
            Random r = new Random();
            int n = r.nextInt(l.size());
            Pos p = l.get(n);
            actualizarMapa(p, a);
        }
    }

    //lis
    public boolean muere(Animal a) {
        boolean isDead = false;
        switch (a.getRefer()) {
            case 3://conejo pasa 3 Utils sin comer
                if (a.contar() == 2)
                    isDead = true;
                break;
            case 4:
                if (a.contar() == 3)
                    isDead = true;
                break;
            case 5:
            case 6:
                if (a.contar() == 4)
                    isDead = true;
                break;
        }
        if (isDead) {
            mapa2[a.getX()][a.getY()] = 7;
            mapa[a.getX()][a.getY()] = 0;
            vivos.remove(a);
            muertos.add(a);
            return true;
        }
        return false;
    }

    //lis
    public boolean reproduccion(Animal a) {
        switch (a.getRefer()) {
            case 3:
                if (a.contadorDTurnos() == 2) {
                    a.reset();
                    posicionHijo(a);
                    return true;
                }
                break;
            case 4:
                if (a.contadorDTurnos() == 3) {
                    a.reset();
                    posicionHijo(a);
                    return true;
                }
                break;
            case 5:
            case 6:
                if (a.contadorDTurnos() == 4) {
                    a.reset();
                    posicionHijo(a);
                    return true;
                }
                break;
        }
        return false;
    }

    //lis
    private boolean posicionHijo(Animal a) {
        ArrayList<Pos> l = new ArrayList<>();
        for (int i = a.getX() - 1; i <= a.getX() + 1; i++) {//moverme por las filas del mapa
            for (int j = a.getY() - 1; j <= a.getY() + 1; j++)//moverme por las columnas
                if (i >= 0 && i < mapa.length && j >= 0 && j < mapa[0].length) {
                    if (mapa[i][j] == 0 && mapa2[i][j] == 0) {//para encontrar un espacio vacio
                        l.add(new Pos(i, j, 0));
                    } else if (mapa2[i][j] == 1 && (a.getRefer() == 5 || a.getRefer() == 6)) {
                        l.add(new Pos(i, j, 1));
                    }
                }
        }
        if (!l.isEmpty()) {
            Random r = new Random();
            int n = r.nextInt(l.size());
            Pos p = l.get(n);
            mapa[p.getX()][p.getY()] = a.getRefer();
            vivos.add(new Animal(p.getX(), p.getY(), a.getRefer()));
            return true;
        }

        return false;
    }

    //ivett
    public int alimentacion(Animal a) {
        ArrayList<Pos> alimento = new ArrayList<>();
        for (int i = a.getX() - 1; i <= a.getX() + 1; i++) {//moverme por las filas del mapa
            for (int j = a.getY() - 1; j <= a.getY() + 1; j++) {
                if (i >= 0 && i < mapa.length && j >= 0 && j < mapa[0].length) {
                    switch (a.getRefer()) {
                        case 3:
                            if (mapa2[i][j] == 2) {
                                alimento.add(new Pos(i, j, 2));
                            }
                            break;
                        case 4:
                            if (mapa[i][j] == 3) {
                                alimento.add(new Pos(i, j, 3));
                            }
                            break;
                        case 5:
                            if (mapa[i][j] == 4 || mapa[i][j] == 3) {

                                if (mapa[i][j] == 4)
                                    alimento.add(new Pos(i, j, 4));
                                else
                                    alimento.add(new Pos(i, j, 3));
                            }
                            break;
                    }
                }

            }

        }
        if (a.getRefer() == 6)
            for (int i = a.getX() - 2; i <= a.getX() + 2; i++)
                for (int j = a.getY() - 2; j <= a.getY() + 2; j++)
                    if (i >= 0 && i < mapa.length && j >= 0 && j < mapa[0].length) {
                        if (mapa2[i][j] == 7) {
                            alimento.add(new Pos(i, j, 7));
                        }
                    }
        Animal animal = new Animal(0, 0, 0);
        if (alimento.size() != 0) {
            Random ran = new Random();
            Pos po = null;
            int bound = alimento.size();
            int n = ran.nextInt(bound);
            po = alimento.get(n);

            if (a.getRefer() == 3 || a.getRefer() == 6) {
                mapa2[po.getX()][po.getY()] = 0;
                if (po.getRefer() == 2) {
                    animal = new Animal(po.getX(), po.getY(), 2);
                    muertos.add(animal);
                }
            }

            if (a.getRefer() == 4 || a.getRefer() == 5) {
                animal = buscar(po);
                relocate(vivos.indexOf(animal));
                vivos.remove(animal);
            }
            actualizarMapa(po, a);
            a.seAlimento();
        } else
            esAdyacente1(a);

        return animal.getRefer();

    }

    //ivett
    public void actualizarMapa(Pos p, Animal a) {
        mapa[a.getX()][a.getY()] = 0;
        mapa[p.getX()][p.getY()] = 0;
        if (a.getRefer() == 6) a.moveTwice(p.getX(), p.getY());
        else a.move(p.getX(), p.getY());
    }

    //ivett
    public Animal buscar(Pos p) {
        for (Animal e : vivos) {
            if (e.equals(p)) {
                return e;
            }
        }
        return null;

    }

}

