package natureGame.framework.fileIO;

import natureGame.model.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Settings {
    private static final int NORMAL_BOUNDS = 60;
    private static final int ZOOM_BOUNDS = 90;
    private static final int MINI_BOUNDS = 42;
    public static int IMAGE_BOUNDS;
    public static int x = 0;
    public static int y = 0;
    public static List<Animal> list = new ArrayList<>(20);
    public static List<Animal> inmoviles = new ArrayList<>(20);
    public static final int FPS = 200;

    public static void initDUMMYList() {
        IMAGE_BOUNDS = MINI_BOUNDS;
        int[] cantidades = {0, 20, 30, 30, 20, 20, 20, 40, 40};
        x = cantidades[7];
        y = cantidades[8];
        int[][] map = new int[x][y];
        for (int i = 1; i < 7; i++)
            for (int j = 0; j < cantidades[i]; j++) {
                Random rand = new Random();
                int xx = Math.abs(rand.nextInt() % x);
                int yy = Math.abs(rand.nextInt() % y);
                while (map[xx][yy] != 0) {
                    xx = Math.abs(rand.nextInt() % x);
                    yy = Math.abs(rand.nextInt() % y);
                }
                if (i == 1 || i == 2)
                    inmoviles.add(new Animal(xx, yy, i));
                else {
                    list.add(new Animal(xx, yy, i));
                }
                map[xx][yy] = i;
            }

    }


    public static void setMini() {
        IMAGE_BOUNDS = MINI_BOUNDS;
    }

    public static void setNormal() {
        IMAGE_BOUNDS = NORMAL_BOUNDS;
    }

    public static void setZoom() {
        IMAGE_BOUNDS = ZOOM_BOUNDS;
    }

}
