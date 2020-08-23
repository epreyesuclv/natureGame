package natureGame.framework.fileIO;

import natureGame.framework.graphics.Pixmap;

public class Assets {

    public static Pixmap atras;
    public static Pixmap X;
    public static Pixmap configuration;
    public static Pixmap gamepad;

    public static Pixmap pieChart;
    public static Pixmap guardar;

    public static Pixmap buitre;
    public static Pixmap buitre2;
    public static Pixmap buitre3;
    public static Pixmap buitre4;
    public static Pixmap buitre5;
    public static Pixmap buitre6;
    public static Pixmap[] buitres = {buitre, buitre2, buitre3, buitre4, buitre5, buitre6};
    public static Pixmap conejo;
    public static Pixmap conejo2;
    public static Pixmap conejo3;
    public static Pixmap conejo4;
    public static Pixmap conejo5;
    public static Pixmap conejo6;
    public static Pixmap[] conejos = {conejo, conejo2, conejo3, conejo4, conejo5, conejo6};
    public static Pixmap lechuza;
    public static Pixmap lechuza2;
    public static Pixmap lechuza3;
    public static Pixmap lechuza4;
    public static Pixmap lechuza5;
    public static Pixmap lechuza6;
    public static Pixmap[] lechuzas = {lechuza, lechuza2, lechuza3, lechuza4, lechuza5, lechuza6};
    public static Pixmap piedra;
    public static Pixmap planta;
    public static Pixmap serpiente;
    public static Pixmap serpiente2;
    public static Pixmap serpiente3;
    public static Pixmap serpiente4;
    public static Pixmap serpiente5;
    public static Pixmap serpiente6;
    public static Pixmap[] serpientes = {serpiente6, serpiente2, serpiente3, serpiente4, serpiente5, serpiente6};
    public static Pixmap terreno;
    public static Pixmap huesos;
    public static Pixmap fondoDia;
    public static Pixmap fondoNoche;
    public static Pixmap fondoCaricatura;
    public static Pixmap music_on;
    public static Pixmap music_off;
    public static Pixmap sfx_on;
    public static Pixmap sfx_off;
    public static Pixmap fondoActual;

    public static void load() {
        buitres = new Pixmap[]{buitre, buitre2, buitre3, buitre4, buitre5, buitre6};
        conejos = new Pixmap[]{conejo, conejo2, conejo3, conejo4, conejo5, conejo6};
        lechuzas = new Pixmap[]{lechuza, lechuza2, lechuza3, lechuza4, lechuza5, lechuza6};
        serpientes = new Pixmap[]{serpiente6, serpiente2, serpiente3, serpiente4, serpiente5, serpiente6};
    }
}
