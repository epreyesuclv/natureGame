package natureGame.framework.input;

import java.util.List;

public interface Input {
    List<ClickEvent> getClickEvents();

    void getKeyEvents();
}
