package natureGame.model;

import natureGame.framework.fileIO.Settings;

/**
 * esta clase es utilizada para representar un elemento movil en la naturaleza
 **/
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
    public int x;//posicion en metapixeles; 1 metapixel = 90/6 pixeles
    public int y;//posicion en metapixeles; 1 metapixel = 90/6 pixeles

    //constructor
    public Animal(int x, int y, int refer) {
        super(x, y, refer);
        vida = 0;
        reproduccionCounter = 0;
        isMoving = 0;
        this.x = getX() * Settings.IMAGE_BOUNDS;
        this.y = getY() * Settings.IMAGE_BOUNDS;
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
    // calcula la direccion en q debe moverse y la guarda en 'direccX' y 'direccy'
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
        return keepMoving();
    }

    //se usa cuando el animal esta en movimiento,calcula la posicion esacta en pixeles en q debe estar el animal
    public boolean keepMoving() {

        if (isMoving++ < 6) {
            x += direccX * Settings.IMAGE_BOUNDS / 6;
            y += direccY * Settings.IMAGE_BOUNDS / 6;
            return false;
        } else {
            super.setX(x / Settings.IMAGE_BOUNDS);
            super.setY(y / Settings.IMAGE_BOUNDS);
            isMoving = 0;
            if (twice) {
                twice = false;
                if (dx == getX() && dy == getY()) return true;
                return move(dx, dy);
            }
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
}



