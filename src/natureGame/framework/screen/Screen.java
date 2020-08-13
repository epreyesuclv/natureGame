package natureGame.framework.screen;


import natureGame.framework.game.Game;

public abstract class Screen {
    Game game;

    public Screen(Game game) {
        this.game = game;
    }

    public void update(float deltaTime) {

    }
    public void present(float deltaTime) {
    }

    public void pause() {
    }

    public void resume() {
    }

    public void dispose() {
    }
}
