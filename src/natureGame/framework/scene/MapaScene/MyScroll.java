package natureGame.framework.scene.MapaScene;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;

public class MyScroll extends ScrollPane {

    public MyScroll() {
    }

    public MyScroll(Node content) {
        super(content);
    }

    public ObservableList<Node> getChildren() {
        return super.getChildren();
    }
}
