package natureGame.framework.input;

public class ClickEvent {

    public static final int CLICK_DOWN = 0;
    public static final int CLICK_UP = 1;
    public static final int CLICK_DRAGGED = 2;
    public static final int ENTERED = 3;
    public int type;
    public int x, y;
    public int pointer;
}
