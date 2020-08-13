package natureGame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import natureGame.framework.audio.Audio;
import natureGame.framework.fileIO.FileIO;
import natureGame.framework.fileIO.MyFileIO;
import natureGame.framework.fileIO.Settings;
import natureGame.framework.game.Game;
import natureGame.framework.graphics.Graphics;
import natureGame.framework.graphics.MyGraphics;
import natureGame.framework.graphics.Render;
import natureGame.framework.input.Input;
import natureGame.framework.input.MyInput;
import natureGame.framework.scene.ConfigurationScene.ConfigurationController;
import natureGame.framework.scene.MapaScene.MyScroll;
import natureGame.framework.scene.MenuScene.Menu;
import natureGame.framework.screen.GameScreen;
import natureGame.framework.screen.LoadingScreen;
import natureGame.framework.screen.Screen;
import natureGame.model.Animal;

import java.io.IOException;
import java.util.List;

public class MyGame extends Application implements Game {


    private static AnchorPane anchor;
    Graphics graphics;
    Audio audio;
    FileIO fileIO;
    Input input;
    Screen screen;
    Render render;
    Canvas canvas;

    private Scene gameScene = null;
    private Scene menu = null;
    private Scene configuration = null;
    private double xOffset;
    private double yOffset;


    public Scene getConfiguration() {
        return configuration;
    }


    private static Stage primaryStage;

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        primaryStage.setWidth(1200);
        primaryStage.setHeight(700);
        Settings.initDUMMYList();
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        screen = getStartScreen();
        screen.update(0);

        primaryStage.show();
    }

    @Override
    public void init() throws Exception {
        canvas = new Canvas(1200, 700);
        fileIO = new MyFileIO(this);
        render = new Render(this, canvas);
        anchor = render.anchor;
        MyScroll scroll = new MyScroll();
        scroll.setContent(anchor);
        input = new MyInput(anchor, 1, 1);
        graphics = new MyGraphics(canvas, this);
        gameScene = new Scene(scroll);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;

    }

    public void showMenu() {
        Menu m = new Menu(this, canvas);
        menu = new Scene(m);
        primaryStage.setScene(menu);
    }

    public void showConfiguration() throws IOException {
        FXMLLoader loader = new FXMLLoader(MyGame.class.getResource("framework/scene/ConfigurationScene/configuration.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        ConfigurationController controller = loader.getController();
        configuration = new Scene(root);
        controller.setMyGame(this);
        primaryStage.setScene(configuration);

    }

    public void initDragg(Parent p) {
        p.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        p.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

    }


    public static void main(String[] args) {
        launch(args);
    }

    public List<Animal> getList() {
        return Util.initList;
    }

    public void blockStage() {
        primaryStage.getScene().getRoot().setDisable(true);
    }

    public void unblockStage() {
        primaryStage.getScene().getRoot().setDisable(false);
    }

    @Override
    public Graphics getGraphics() {
        return graphics;
    }

    @Override
    public Audio getAudio() {
        return audio;
    }

    @Override
    public FileIO getFileIO() {
        return fileIO;
    }

    @Override
    public Input getInput() {
        return input;
    }

    @Override
    public void setScreen(Screen screen) {

        if (screen == null)
            throw new IllegalArgumentException("Screen must not be null");
        this.screen.pause();
        this.screen.dispose();
        screen.resume();
        screen.update(0);
        this.screen = screen;

    }

    @Override
    public Screen getCurrentScreen() {
        return screen;
    }

    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }


    @Override
    public void changeSize(int x, int y) {
        render.changeSize(x, y);

    }

    public static AnchorPane getRoot() {
        return anchor;
    }

    public void startRendering() {
        primaryStage.setScene(gameScene);
        setScreen(new GameScreen(this));
        render.resume();

    }

    public void fullScreen() {
        primaryStage.setFullScreen(true);
        canvas.setHeight(primaryStage.getHeight());
        canvas.setWidth(primaryStage.getWidth());
        render.changeSize(primaryStage.getWidth(), primaryStage.getHeight());
    }

    public void normalScreen() {
        primaryStage.setFullScreen(false);
        primaryStage.setHeight(1200);
        primaryStage.setWidth(700);
    }
}
