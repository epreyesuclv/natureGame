package natureGame.framework.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import natureGame.framework.fileIO.Settings;
import natureGame.framework.game.Game;

/**
 * implementa la interface Graphics
 * es una herramienta para el trabajo con los graficos de la aplicacio
 */
public class MyGraphics implements Graphics {
    Image image;
    Canvas canvas;
    Game game;

    public MyGraphics(Canvas canvas, Game game) {
        this.canvas = canvas;
        this.game = game;
    }

    //crea una nuevo Pixmap a partir de una direccion y un tamanho deseado
    @Override
    public Pixmap newPixmap(String fileName, int xbound, int ybound) {
        image = new Image(fileName, xbound, ybound, false, false);
        return new MyPixmap(image);
    }

    //crea un nuevo Pixmap a partir de una direccion con un tamanho estandar
    @Override
    public Pixmap newPixmap(String fileName, int format) {
        return newPixmap(fileName, Settings.IMAGE_BOUNDS, Settings.IMAGE_BOUNDS);
    }

    //dibuja en la pantalla una la imagen incrustada en el pixmap pasado por parametro
    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y) {
        canvas.getGraphicsContext2D().drawImage(pixmap.getImage(), x, y);
    }

    //usados para el dibujo basico
    @Override
    public void drawPixel(int x, int y, int color) {
    }

    @Override
    public void drawLine(int x, int y, int x2, int y2, int color) {

    }

    @Override
    public void drawRect(int x, int y, int width, int height, int color) {

    }

    @Override
    public int getWidth() {
        return (int) canvas.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) canvas.getHeight();
    }

    //limpia la pantalla
    @Override
    public void clear(int color) {
        canvas.getGraphicsContext2D().clearRect(0, 0, getHeight(), getHeight());
    }

}