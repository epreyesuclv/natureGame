package natureGame.framework.graphics;
/**
 * comportamiento q debe tener la clase q gestiona las imagenes
 */

import javafx.scene.image.Image;

public interface Pixmap {
    public int getWidth();

    public int getHeight();

    public int getFormat();

    public void dispose();

    public Image getImage();

}
