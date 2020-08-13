package natureGame.framework.scene.MapaScene;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import natureGame.model.Animal;
import natureGame.model.Pos;
import natureGame.Util;

import java.util.ArrayList;
import java.util.List;

/*public class Animation {
    public static int FPS = 30;
    List<Image> animationList = new ArrayList<>();
    private long ANIMATOR_CONSTANT;
    private MyAnimationTimer animator;
    Mapa mapa;
    boolean stop = false;

    public Animation(Mapa mapa) {
        this.mapa = mapa;
    }

    public void makeAnimation(Animal e, Pos pos) {

        Pos salida = e.getPos();
        boolean back = true;
        int x;
        int y;
        if (salida.getX() - pos.getX() > 0) {
            animationList = e.getImageListLeft();
            x = -1;
            back = true;
        } else if (salida.getX() - pos.getX() < 0) {
            animationList = e.getImagesListRigth();
            x = 1;
            back = false;
        } else x = 0;


        if (salida.getY() - pos.getY() > 0) {
            y = -1;
        } else if (salida.getY() - pos.getY() < 0) {
            y = 1;
        } else y = 0;

        ANIMATOR_CONSTANT = System.nanoTime();
        animator = new MyAnimationTimer(
                x,
                y,
                5,
                1_000_000_000,
                ANIMATOR_CONSTANT,
                e.getCanvas(),
                Util.IMAGE_BOUNDS,
                pos
        );
        animator.start();
        while (!stop) {
        }
        animator.stop();

    }

    public class MyAnimationTimer extends AnimationTimer {
        private final Canvas canvas;
        private final long ANIMATOR_CONSTANT;
        private final long x;
        private final long y;
        private final long amountCycle;
        private final long totalTime;
        private final double translate;
        private final Pos initPos;
        private long deltaTime = -1;
        private long LAST_FRAME;


        public MyAnimationTimer(long x, long y, long amountCycle, long totalTime, long ANIMATOR_CONSTANT, Canvas canvas, double translate, Pos initPos) {
            this.ANIMATOR_CONSTANT = ANIMATOR_CONSTANT;
            this.x = x;
            this.y = y;
            this.amountCycle = amountCycle;
            this.totalTime = totalTime;
            LAST_FRAME = ANIMATOR_CONSTANT;
            this.canvas = canvas;
            this.translate = translate;
            this.initPos = initPos;

        }

        @Override
        public void handle(long now) {

            if (deltaTime >= totalTime) {
                stop = true;
                this.stop();
            }
            
            if (now - LAST_FRAME > 1_000_000_000 / FPS) {
                LAST_FRAME = now;

                deltaTime = now - ANIMATOR_CONSTANT;

                int X = (int) (((double) deltaTime / totalTime * x) * translate + initPos.getX());

                int Y = (int) (((double) deltaTime / totalTime * y) * translate + initPos.getY());

                int id = (int) ((double) (deltaTime % ((long) ((double) totalTime / amountCycle))) /
                        (((double) totalTime / amountCycle) / animationList.size()));
                canvas.getGraphicsContext2D().drawImage(animationList.get(id), 0, 0);

                mapa.draw(canvas, X, Y);

            }

        }
    }

}*/

