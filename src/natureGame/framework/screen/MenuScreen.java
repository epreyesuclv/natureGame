package natureGame.framework.screen;

import natureGame.MyGame;

public class MenuScreen extends Screen {

    private final MyGame myGame;

    public MenuScreen(MyGame game) {
        super(game);
        myGame = game;
        myGame.showMenu();

    }

    @Override
    public void update(float deltaTime) {

    }

    private void exit() {
        System.exit(0);
    }


    @Override
    public void present(float deltaTime) {
      /*  Graphics g = game.getGraphics();
        g.drawPixmap(Assets.configuration, 1000, 550);
        g.drawPixmap(Assets.gamepad, 20, 550);
        g.drawPixmap(Assets.X, 1000, 20);
*/
    }

 /*   private boolean inBounds(ClickEvent event, int x, int y, int width, int height) {
        if (event.x > x && event.x < x + width - 1 &&
                event.y > y && event.y < y + height - 1)
            return true;
        else
            return false;
    }*/
}
