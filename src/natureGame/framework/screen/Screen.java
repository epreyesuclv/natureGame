package natureGame.framework.screen;
/**
 * clase de la q extienden todas las Screen
 * describe sus comportamienos generales
 */

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
