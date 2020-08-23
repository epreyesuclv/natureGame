package natureGame;

/**
 * @author Elieser
 */


import javafx.scene.image.Image;
import natureGame.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class Util {

    public static ArrayList<Image> buitreLeft = new ArrayList<>();
    public static ArrayList<Image> buitreRigth = new ArrayList<>();
    public static ArrayList<Image> conejoLeft = new ArrayList<>();
    public static ArrayList<Image> conejoRigth = new ArrayList<>();
    public static ArrayList<Image> lechuzaLeft = new ArrayList<>();
    public static ArrayList<Image> lechuzaRigth = new ArrayList<>();
    public static ArrayList<Image> serpienteLeft = new ArrayList<>();
    public static ArrayList<Image> serpienteRigth = new ArrayList<>();

    public static Image planta, buitre, conejo, lechuza, serpiente, piedra, huesos, terreno, gamePad, configuration, exit, X;

    public static double IMAGE_BOUNDS = 80;

    public static List<Animal> initList = new ArrayList<>();
    public static List<Animal> total = new ArrayList<>();
    public static List<Animal> vivos = new ArrayList<>();
    public static List<Animal> muertos = new ArrayList<>();

    public static int sizex = 50;
    public static int sizey = 30;

    private static String resources = "natureGame/Assets/Images/Resources/";
    public static String sBuitre = resources + "Buitre/";
    public static String sConejo = resources + "Conejo/";
    public static String sLechuza = resources + "Hedwig/";
    public static String sHuesos = resources + "Huesos/";
    public static String sPiedra = resources + "Piedra/";
    public static String sPlanta = resources + "Planta/";
    public static String sTerreno = resources + "Terreno/";
    public static String sSerpiente = resources + "Serpiente/";

    public static Image arbol = new Image(sPlanta + "planta3.png");

    public static int[][] mapa = new int[100][100];
  /*  public static ArrayList<Pos> adyacentes(int[][] map, int x, int y) {
        ArrayList<Pos> l = new ArrayList<>();
        for (int i = x - 1; i <= x + 1; i++)
            for (int j = y - 1; j <= y + 1; j++)
                if (i != x || j != y)
                    l.add(new Pos(i, j, map[i][j]));

        return l;
    }*/

    public static void initBounds(double x, double y) {
    }

    public static void loadImages() {
        buitreLeft.add(new Image(sBuitre + "buitre1.png"));
        buitreLeft.add(new Image(sBuitre + "buitre2.png"));
        buitreLeft.add(new Image(sBuitre + "buitre3.png"));

        buitreRigth.add(new Image(sBuitre + "buitre4.png"));
        buitreRigth.add(new Image(sBuitre + "buitre5.png"));
        buitreRigth.add(new Image(sBuitre + "buitre6.png"));

/*
        conejoLeft.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        conejoLeft.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        conejoLeft.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        conejoLeft.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        conejoLeft.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        conejoRigth.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        conejoRigth.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        conejoRigth.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        conejoRigth.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        conejoRigth.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));

        lechuzaLeft.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        lechuzaLeft.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        lechuzaLeft.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        lechuzaLeft.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        lechuzaLeft.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        lechuzaRigth.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        lechuzaRigth.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        lechuzaRigth.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        lechuzaRigth.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        lechuzaRigth.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));

        serpienteLeft.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        serpienteLeft.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        serpienteLeft.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        serpienteLeft.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        serpienteLeft.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        serpienteRigth.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        serpienteRigth.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        serpienteRigth.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        serpienteRigth.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
        serpienteRigth.add(new Image("natureGame/Assets/Images/Resources/Buitre/buitre1"));
*/
        gamePad = new Image("natureGame/Assets/Images/Otros/gamepad-console.jpg");
        configuration = new Image("natureGame/Assets/Images/Otros/configuration.png");
        exit = new Image("natureGame/Assets/Images/Otros/atras.png", 80, 80, true, true);
        X = new Image("natureGame/Assets/Images/Otros/x.png", 80, 80, true, true);

        buitre = new Image("natureGame/Assets/Images/Resources/Buitre/buitre1.png", 100, 100, true, true);
        conejo = new Image(sConejo + "conejo2.png", 100, 100, true, true);
        huesos = new Image("natureGame/Assets/Images/Resources/Huesos/huesos.png", 100, 100, true, true);

        serpiente = new Image("natureGame/Assets/Images/Resources/Serpiente/Serpiente2.png", 100, 100, true, true);
        lechuza = new Image("natureGame/Assets/Images/Resources/Hedwig/hedwig1.png", 100, 100, true, true);

        terreno = new Image("natureGame/Assets/Images/Resources/Terreno/terreno.png", IMAGE_BOUNDS, IMAGE_BOUNDS, true, true);
        piedra = new Image("natureGame/Assets/Images/Resources/Piedra/piedra1.png", 100, 100, true, true);


    }

    public static Image getImage(String url) {
        if (url.endsWith(".jpg") || url.endsWith(".png")) return new Image(url, 100, 100, true, true);
        return new Image(url + ".png", 100, 100, true, true);

    }



    public static Image getImage(int i) {
        switch (i) {
            case 0:
                return terreno;
            case 1:
                return piedra;
            case 2:
                return planta;
            case 3:
                return conejo;
            case 4:
                return serpiente;
            case 5:
                return lechuza;
            case 6:
                return buitre;
        }
        return terreno;
    }

    /*private static int returnNextmultipler(int n, int m) {
        for (int i = n; i < m + n; i++) {
            if (i % m == 0) return i;
        }
        return 0;

    }

    public static Pos calculateAspectRatio(double width, double heigth) {
        int max = (int) Math.max(heigth, width);
        int ratioHeigth;
        int ratioWidth;
        if (max == heigth) {
            ratioHeigth = returnNextmultipler(max, 3);
            ratioWidth = (ratioHeigth * 5) / 3;
        } else {
            ratioWidth = returnNextmultipler(max, 5);
            ratioHeigth = (ratioWidth * 3) / 5;
        }
        return new Pos(ratioWidth + 2, ratioHeigth + 2, 0);
    }*/
}
