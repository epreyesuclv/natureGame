package natureGame.framework.graphics;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import natureGame.MyGame;
import natureGame.framework.fileIO.Settings;

public class Render {
    MyGame game;
    public MyRoot anchor;
    AnimationTimer renderThread = null;
    volatile boolean running = false;
    Canvas buffer;
    boolean isLock = false;
    private long startTime;


    public Render(MyGame game, Canvas buffer) {
        this.game = game;
        this.buffer = buffer;
        anchor = new MyRoot(1200, 720);
        anchor.getChildren().add(buffer);


        renderThread = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - startTime > 1_000_000_000 / Settings.FPS)
                    if (!isLock && running) {
                        lock();
                        float deltaTime = (System.nanoTime() - startTime) / 1000000000.0f;
                        startTime = System.nanoTime();
                        game.getCurrentScreen().update(deltaTime);
                        game.getCurrentScreen().present(deltaTime);
                        unlock();
                    }
            }

        };
    }


    public void resume() {
        running = true;
        startTime = System.nanoTime();
        renderThread.start();
    }

    public void pause() {
        running = false;
        renderThread.stop();
    }


    private void lock() {
        isLock = true;
    }

    private void unlock() {
        isLock = false;
    }

    public void changeSize(int x, int y) {
        anchor.changeSize(x, y);
        buffer.setWidth(x);
        buffer.setHeight(y);
    }

    public void changeSize(double width, double height) {
        changeSize((int) width, (int) height);
    }

    public void stop() {
        running = false;
        renderThread.stop();
        renderThread = null;
    }
}
