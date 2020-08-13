package natureGame.framework.graphics;

import javafx.scene.image.Image;

public class MyPixmap implements Pixmap {
   public Image image;


    public MyPixmap(Image image) {
        this.image = image;
    }

    @Override
    public int getWidth() {
        return (int) image.getWidth();
    }

    @Override
    public int getHeight() {
        return (int) image.getHeight();
    }

    @Override
    public int getFormat() {
        return 0;
    }

    @Override
    public void dispose() {
        image.cancel();
    }

    public Image getImage() {
        return image;
    }
}
