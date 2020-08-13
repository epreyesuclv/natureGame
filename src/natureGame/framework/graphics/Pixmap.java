package natureGame.framework.graphics;

import javafx.scene.image.Image;

public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public int getFormat();

    public void dispose();

    public Image getImage();

}
