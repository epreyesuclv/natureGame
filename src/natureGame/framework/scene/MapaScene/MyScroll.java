package natureGame.framework.scene.MapaScene;
/**
 * es el root de la simulacion, es para q la simulacion se le pueda hacer scroll
 */

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;


public class MyScroll extends ScrollPane {

    public MyScroll() {
        setMaxSize(1200, 700);
    }

    public MyScroll(Node content) {
        super(content);
    }

    public ObservableList<Node> getChildren() {
        return super.getChildren();
    }
}
