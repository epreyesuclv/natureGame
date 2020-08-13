package natureGame.framework.graphics;

import javafx.scene.layout.AnchorPane;

public class MyRoot extends AnchorPane {
    public MyRoot(int x, int y) {
        changeSize(x, y);

    }

    public void changeSize(int x, int y) {
        setWidth(x);
        setHeight(y);
    }

}
