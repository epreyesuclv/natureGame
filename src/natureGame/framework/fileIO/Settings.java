package natureGame.framework.fileIO;

import natureGame.model.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * clase q cambia las configuraciones de la aplicacion
 */

public class Settings {
    private static final int NORMAL_BOUNDS = 60;
    private static final int ZOOM_BOUNDS = 90;
    private static final int SLOW = 15;
    private static final int FAST = 60;
    private static final int NORMAL_SPEED = 30;
    public static int FPS;
    public static int IMAGE_BOUNDS;
    public static int x = 0;
    public static int y = 0;
    public static List<Animal> list = new ArrayList<>(20);//lista inicial de animales
    public static List<Animal> inmoviles = new ArrayList<>(20);//lista inicial de objetos inmoviles
    private static final int MINI_BOUNDS = 30;

    //inicia las listas por defecto
    public static void initDUMMYList() {
        IMAGE_BOUNDS = MINI_BOUNDS;
        FPS = SLOW;
        int[] cantidades = {0, 20, 20, 20, 20, 20, 20, 40, 40};
        x = cantidades[7];
        y = cantidades[8];
        int[][] map = new int[x][y];
        for (int i = 1; i < 7; i++)
            for (int j = 0; j < cantidades[i]; j++) {
                Random rand = new Random();
                int xx = Math.abs(rand.nextInt() % x);
                int yy = Math.abs(rand.nextInt() % y);
                while (map[xx][yy] != 0) {
                    xx++;
                    if (xx == x) {
                        xx = 0;
                        yy++;
                    }
                    if (yy == y)
                        yy = 0;
                }

                if (i == 1 || i == 2)
                    inmoviles.add(new Animal(xx, yy, i));
                else {
                    list.add(new Animal(xx, yy, i));
                }
                map[xx][yy] = i;
            }

    }

    /**
     * usado para cambiar el tamanho de las casillas
     **/
    public static void setMini() {
        IMAGE_BOUNDS = MINI_BOUNDS;
    }

    public static void setNormal() {
        IMAGE_BOUNDS = NORMAL_BOUNDS;
    }

    public static void setZoom() {
        IMAGE_BOUNDS = ZOOM_BOUNDS;
    }

    public static void setSlow() {
        FPS = SLOW;
    }

    public static void setfast() {
        FPS = FAST;
    }

    public static void setNormalSpeed() {
        FPS = NORMAL_SPEED;
    }
}
