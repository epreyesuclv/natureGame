package natureGame.framework.graphics;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import natureGame.MyGame;
import natureGame.framework.fileIO.Settings;

/**
 * es la clase q gestiona la simulacion,tanto los graficos como la logica mediate la creacion de un hilo en segundo plano
 * q se encarga de llamar los metodos update() y present() de la clase GameScreen,la cual es la encargada de
 * actualizar la parte logica y la parte visual respectivamente.
 */
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

        //para crear el hilo en seggundo plano se utilizo la clase de java AnimationTimer
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

    //inicia el segundo plano
    public void resume() {
        running = true;
        startTime = System.nanoTime();
        renderThread.start();
    }

    //detiene el hilo
    public void pause() {
        running = false;
        renderThread.stop();
    }

    //bloquea para q no se ejecuten multiples instancias del hilo
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

    public void stop() {
        running = false;
        renderThread.stop();
        renderThread = null;
    }
}
