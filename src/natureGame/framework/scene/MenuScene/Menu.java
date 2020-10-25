package natureGame.framework.scene.MenuScene;
/**
 * el root de la pantalla de Menu
 * en ella se gestionan todos los eventos click asociados a esta pantalla
 */

import javafx.scene.canvas.Canvas;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import natureGame.MyGame;
import natureGame.framework.fileIO.Assets;
import natureGame.framework.fileIO.Settings;

import java.io.IOException;

public class Menu extends AnchorPane {
    MyGame myGame;
    Canvas canvas;

    public Menu(MyGame myGame, Canvas canvas) {
        super();
        this.myGame = myGame;
        this.canvas = canvas;
        init();
    }

    private void init() {
        //menu de opciones para cambiarle el tamanho a las casillas de los animales en la simulanion
        MenuItem zoomScreen = new MenuItem("Zoom");
        zoomScreen.setOnAction(e -> {
            myGame.changeScreen(0);
        });
        MenuItem normalScreen = new MenuItem("Normal");
        normalScreen.setOnAction(e -> {
            myGame.changeScreen(1);
        });

        MenuItem miniScreen = new MenuItem("Mini");
        miniScreen.setOnAction(e -> {
            myGame.changeScreen(2);
        });


        MenuItem fast = new MenuItem("Fast");
        fast.setOnAction(e -> {
            Settings.setfast();
        });
        MenuItem normalSpeed = new MenuItem("Normal");
        normalSpeed.setOnAction(e -> {
            Settings.setNormalSpeed();
        });

        MenuItem slow = new MenuItem("slow");
        slow.setOnAction(e -> {
            Settings.setSlow();
        });


        javafx.scene.control.Menu screen = new javafx.scene.control.Menu("Pantalla", null, zoomScreen, normalScreen, miniScreen);
        javafx.scene.control.Menu fps = new javafx.scene.control.Menu("FPS", null, fast, normalSpeed, slow);
//los demas botones y sus eventos click asosiados
        setWidth(1200);
        setHeight(720);
        Canvas fondo = new Canvas(1200, 720);
        fondo.getGraphicsContext2D().drawImage(Assets.fondoActual.getImage(), 0, 0);
        getChildren().add(fondo);
        Canvas out = new Canvas(90, 90);
        out.getGraphicsContext2D().drawImage(Assets.X.getImage(), 0, 0);
        out.setTranslateY(20);
        out.setTranslateX(1050);
        getChildren().add(out);
        out.setOnMouseClicked((MouseEvent event) -> {
            MyGame.getPrimaryStage().close();
            System.exit(0);
        });


        Canvas configuration = new Canvas(90, 90);
        configuration.getGraphicsContext2D().drawImage(Assets.configuration.getImage(), 0, 0);
        configuration.setTranslateX(1050);
        configuration.setTranslateY(550);
        configuration.setOnMouseClicked(event -> {
            try {
                myGame.showConfiguration();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        getChildren().add(configuration);
        Canvas play = new Canvas(90, 90);
        play.getGraphicsContext2D().drawImage(Assets.gamepad.getImage(), 0, 0);
        play.setTranslateX(20);
        play.setTranslateY(550);
        play.setOnMouseClicked(event -> {
            myGame.load();
            myGame.startRendering();
        });
        getChildren().add(play);
        myGame.initDragg(this);
        getChildren().add(new MenuBar(screen, fps));

    }

}
