package natureGame.framework.graphics;

/**
 * interface principal de los graficos
 **/
public interface Graphics {

    Pixmap newPixmap(String fileName, int xbound, int ybound);

    Pixmap newPixmap(String pixmap, int format);

    void drawPixmap(Pixmap pixmap, int x, int y);

    void drawPixel(int x, int y, int color);

    void drawLine(int x, int y, int x2, int y2, int color);

    void drawRect(int x, int y, int width, int height, int color);


    int getWidth();

    int getHeight();

    void clear(int color);
}
