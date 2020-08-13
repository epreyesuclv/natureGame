package natureGame.framework.input;

import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import natureGame.framework.pool.Pool;
import natureGame.framework.pool.PoolObjectFactory;
import sun.plugin.javascript.navig.Anchor;

import java.util.ArrayList;
import java.util.List;

public class SimpleClickHandler implements ClickHandler {
    float scalex;
    float scaley;
    boolean isClicked;
    int clickX;
    int clickY;
    Pool<ClickEvent> clickEventPool;
    List<ClickEvent> clickEvents = new ArrayList<>();
    List<ClickEvent> clickEventsBuffer = new ArrayList<>();

    public SimpleClickHandler(AnchorPane root, float scalex, float scaley) {
        this.scalex = scalex;
        this.scaley = scaley;
        root.setOnMouseClicked(this);
        root.setOnMousePressed(this);
        root.setOnMouseReleased(this);

        PoolObjectFactory factory = () -> new ClickEvent();
        clickEventPool = new Pool<>(factory, 50);
    }

    @Override
    public boolean isClickDown(int pointer) {
        synchronized (this) {
            if (pointer == 0) {
                return isClicked;
            } else
                return false;
        }
    }

    @Override
    public int getClickX(int pointer) {
        synchronized (this) {
            return clickX;
        }
    }

    @Override
    public int getClickY(int pointer) {
        synchronized (this) {
            return clickY;
        }
    }

    @Override
    public List<ClickEvent> getClickEvents() {
        synchronized (this) {
            for (ClickEvent e : clickEvents)
                clickEventPool.free(e);

            clickEvents.clear();
            clickEvents.addAll(clickEventsBuffer);
            clickEventsBuffer.clear();
            return clickEvents;
        }
    }

    @Override
    public void handle(MouseEvent event) {
        synchronized (this) {

            ClickEvent clickEvent = clickEventPool.newObject();
            if (event.getEventType() == MouseEvent.MOUSE_PRESSED) {
                clickEvent.type = ClickEvent.CLICK_DOWN;
                isClicked = true;
            }





            if (event.getEventType() == MouseEvent.MOUSE_RELEASED) {
                clickEvent.type = ClickEvent.CLICK_UP;
                isClicked = false;
            }
            if (event.getEventType() == MouseEvent.MOUSE_CLICKED) {
                clickEvent.type = ClickEvent.CLICK_UP;
                isClicked = false;
            }
            clickEvent.x = clickX = (int) (event.getSceneX() * scalex);
            clickEvent.y = clickY = (int) (event.getSceneY() * scaley);

            clickEventsBuffer.add(clickEvent);
        }

    }
}
