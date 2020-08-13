package natureGame.framework.input;


import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.util.List;

public interface ClickHandler extends EventHandler<MouseEvent> {
    public boolean isClickDown(int pointer);
    public int getClickX(int pointer);
    public int getClickY(int pointer);
    public List<ClickEvent> getClickEvents();
}
