package natureGame.framework.input;

import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import sun.plugin.javascript.navig.Anchor;

import java.util.List;

public class MyInput implements Input {

    ClickHandler clickHandler;

    public MyInput(AnchorPane root, float scaleX, float scaleY) {
        clickHandler = new SimpleClickHandler(root, scaleX, scaleY);
    }

    @Override
    public List<ClickEvent> getClickEvents() {
        return clickHandler.getClickEvents();
    }

    @Override
    public void getKeyEvents() {

    }
}
