package natureGame.model;

import javafx.scene.image.Image;
import natureGame.Util;
import natureGame.framework.fileIO.Settings;

import java.util.List;

public class Animal extends Pos {
    boolean twice = false;
    int dx;
    int dy;
    private int vida;//veces q no se ha alimentado
    private int reproduccionCounter;
    private boolean b;
    int isMoving;
    private int direccX;
    private int direccY;
    public int x;//posicion en metapixeles; 1 metapixel = 90/6 pixeles
    public int y;//posicion en metapixeles; 1 metapixel = 90/6 pixeles


    public Animal(int x, int y, int refer) {
        super(x, y, refer);
        vida = 0;
        reproduccionCounter = 0;
        isMoving = 0;
        this.x = getX() * Settings.IMAGE_BOUNDS;
        this.y = getY() * Settings.IMAGE_BOUNDS;
    }


    public int contar() {
        return vida++;
    }


    public void seAlimento() {
        vida = 0;
    }


    public int contadorDTurnos() {
        return ++reproduccionCounter;
    }

    public void reset() {
        reproduccionCounter = 0;
    }

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


    public void moveTwice(int dx, int dy) {
        twice = true;
        this.dx = dx;
        this.dy = dy;
        move(dx, dy);
    }

    public int getIsMoving() {
        return isMoving;
    }

    public int getDireccX() {
        return direccX;
    }
}



