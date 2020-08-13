package natureGame.framework.graphics;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import natureGame.framework.fileIO.Settings;
import natureGame.framework.game.Game;

public class MyGraphics implements Graphics {
    Image image;
    Canvas canvas;
    Paint paint;
    Game game;

    public MyGraphics(Canvas canvas, Game game) {
        this.canvas = canvas;
        this.game = game;
    }

    @Override
    public Pixmap newPixmap(String fileName, int xbound, int ybound) {
        image = new Image(fileName, xbound, ybound, true, false);
        return new MyPixmap(image);
    }

    @Override
    public Pixmap newPixmap(String fileName, int format) {
        return newPixmap(fileName, Settings.IMAGE_BOUNDS, Settings.IMAGE_BOUNDS);
    }

    @Override
    public void drawPixmap(Pixmap pixmap, int x, int y) {
        canvas.getGraphicsContext2D().drawImage(pixmap.getImage(), x, y);
    }

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

    @Override
    public void clear(int color) {
        canvas.getGraphicsContext2D().clearRect(0, 0, getHeight(), getHeight());
    }

}