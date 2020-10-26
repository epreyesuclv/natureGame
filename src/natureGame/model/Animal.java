package natureGame.model;

/**
 * esta clase es utilizada para representar un elemento movil en la naturaleza
 **/
//ivett
public class Animal extends Pos {
    /**
     * estos atributos son especificos para los buitres
     **/
    private boolean twice = false;//es usado para saber si se tiene q mover dos casillas
    private int dx;
    private int dy;
    /**
     * atributos basicos de todos lo elementos moviles del sitema
     **/
    private int vida;//veces q no se ha alimentado
    private int reproduccionCounter;

    private int isMoving;
    private int direccX;
    private int direccY;

    //constructor
    public Animal(int x, int y, int refer) {
        super(x, y, refer);
        vida = 0;
        reproduccionCounter = 0;
        isMoving = 0;
    }

    //se usa en el metodo de "alimentacion" n la clase World para saber cuando debe morir el animal
    public int contar() {
        return vida++;
    }

    //se usa en el metodo de "alimentacion",se dispara cuando el animal se alimenta
    public void seAlimento() {
        vida = 0;
    }

    //para saber cuando debe reproducirce el animal
    public int contadorDTurnos() {
        return ++reproduccionCounter;
    }

    //se dispara cuando el animal se reproduce
    public void reset() {
        reproduccionCounter = 0;
    }

    //se usa cuando el animal comienza a moverse a otra casilla,
    // calcula la direccion en q debe moverse y la guarda en 'direccX' y 'direccY'
    public boolean move(int dx, int dy) {

        if (getX() - dx > 0) {
            direccX = -1;
        } else if (getX() - dx < 0) {
            direccX = 1;
        } else direccX = 0;
        if (getY() - dy > 0) {
            direccY = -1;
        } else if (getY() - dy < 0) {
            direccY = 1;
        } else direccY = 0;
        setX(getX() + direccX);
        setY(getY() + direccY);
        return keepMoving();
    }

    public void finishMove() {
        isMoving = 6;
    }

    //se usa para cambiar el estado de movimiento cuando el animal se esta moviendo
    public boolean keepMoving() {
        if (isMoving++ < 6) {
            return false;
        } else {
            isMoving = 0;
            if (twice) {
                twice = false;
                if (dx == getX() && dy == getY()) return true;
                return move(dx, dy);
            }
            System.out.println(isMoving);
            direccY = 0;
            direccX = 0;
            return true;
        }
    }

    //si el animal se tiene q mover dos pasos se usa este metodo
    public void moveTwice(int dx, int dy) {
        twice = true;
        this.dx = dx;
        this.dy = dy;
        move(dx, dy);
    }

    //devuelve la variable isMoving,es usado para hacer las animaciones
    public int getIsMoving() {
        return isMoving;
    }

    //devuelve la variable direccX,es usado para hacer las animaciones
    public int getDireccX() {
        return direccX;
    }

    public int getDireccY() {
        return direccY;
    }
}



