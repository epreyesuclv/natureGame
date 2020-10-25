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
import natureGame.framework.fileIO.Assets;
import natureGame.framework.fileIO.FileIO;
import natureGame.framework.fileIO.MyFileIO;
import natureGame.framework.fileIO.Settings;
import natureGame.framework.game.Game;
import natureGame.framework.graphics.Graphics;
import natureGame.framework.graphics.MyGraphics;
import natureGame.framework.graphics.MyRoot;
import natureGame.framework.graphics.Render;
import natureGame.framework.scene.ConfigurationScene.ConfigurationController;
import natureGame.framework.scene.MapaScene.MyScroll;
import natureGame.framework.scene.MenuScene.Menu;
import natureGame.framework.screen.GameScreen;
import natureGame.framework.screen.LoadingScreen;
import natureGame.framework.screen.Screen;

import java.io.IOException;

/**
 * clase principal, donde se implementan la interface Game del framework utilizado en la aplicacion
 * aqui tambien se encuentra el hilo principal de javafx
 */
//elieser
public class MyGame extends Application implements Game {


    private static AnchorPane anchor;
    private static MyRoot root;
    Graphics graphics;
    Audio audio;
    FileIO fileIO;
    Screen screen;
    Render render;
    Canvas canvas;

    private Scene gameScene = null;
    private Scene menu = null;
    private Scene configuration = null;
    private double xOffset;
    private double yOffset;

    public static AnchorPane getRoot() {
        return root;
    }


    private static Stage primaryStage;

    //devuelve el Stage principal
    public static Stage getPrimaryStage() {
        return primaryStage;

    }

    //hilo principal de javafx
    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        primaryStage.setWidth(1200);
        primaryStage.setHeight(700);
        Settings.init();
        Settings.initDUMMYList();
        // MediaPlayer media = new MediaPlayer(new Media(new File("src/natureGame/Assets/test.mp3").toURI().toString()));
        //media.play();
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        loadMenuImages();
        showMenu();
        primaryStage.show();
    }

    //carga las imagenes de la primera pantalla
    private void loadMenuImages() {
        Graphics g = getGraphics();
        Assets.fondoCaricatura = g.newPixmap("natureGame/Assets/Images/Otros/FondoCaricatura.jpg", 1200, 720);
        Assets.fondoDia = g.newPixmap("natureGame/Assets/Images/Otros/fondoDia.jpg", 1200, 720);
        Assets.fondoNoche = g.newPixmap("natureGame/Assets/Images/Otros/fondoNoche.jpg", 1200, 720);
        Assets.atras = g.newPixmap("natureGame/Assets/Images/Otros/atras.png", 90, 90);
        Assets.X = g.newPixmap("natureGame/Assets/Images/Otros/xrosa.png", 90, 90);
        Assets.configuration = g.newPixmap("natureGame/Assets/Images/Otros/configuration.png", 90, 90);
        Assets.gamepad = g.newPixmap("natureGame/Assets/Images/Otros/gamepadrosa.png", 90, 90);
        Assets.fondoActual = g.newPixmap("natureGame/Assets/Images/Otros/fondonew.jpg", 1200, 720);

        Assets.piedra = g.newPixmap("natureGame/Assets/Images/Resources/Piedra/piedra4.png", 0);
        Assets.planta = g.newPixmap("natureGame/Assets/Images/Resources/Planta/elmejor.png", 0);
        Assets.conejo = g.newPixmap("natureGame/Assets/Images/Resources/Conejo/Conejo1.png", 0);
        Assets.serpiente = g.newPixmap("natureGame/Assets/Images/Resources/Serpiente/serpiente1.png", 0);
        Assets.lechuza = g.newPixmap("natureGame/Assets/Images/Resources/Hedwig/lechuza1.png", 0);
        Assets.buitre = g.newPixmap("natureGame/Assets/Images/Resources/Buitre/buitre1.png", 0);


    }

    //ejecuta la clase q carga las imagenes de los elementos
    public void load() {
        screen = getStartScreen();
        screen.update(0);

        primaryStage.show();
    }

    //metodo q se ejecuta antes de start por el hilo de javafx
    @Override
    public void init() throws Exception {
        canvas = new Canvas(1200, 700);
        fileIO = new MyFileIO(this);
        render = new Render(this, canvas);
        anchor = render.anchor;
        root = new MyRoot(1200, 700);
        MyScroll scroll = new MyScroll();
        scroll.setContent(anchor);
        root.getChildren().add(scroll);
        graphics = new MyGraphics(canvas, this);
        gameScene = new Scene(root);
        initDragg(anchor);
    }

    //muestra la pantalla de menu
    public void showMenu() {
        stopRender();
        Menu m = new Menu(this, canvas);
        menu = new Scene(m);
        primaryStage.setScene(menu);
    }

    //muestra la pantalla de configuraciones
    public void showConfiguration() throws IOException {
        FXMLLoader loader = new FXMLLoader(MyGame.class.getResource("framework/scene/ConfigurationScene/configuration.fxml"));
        loader.load();
        Parent root = loader.getRoot();
        ConfigurationController controller = loader.getController();
        configuration = new Scene(root);
        controller.setMyGame(this, root);
        initDragg(root);
        primaryStage.setScene(configuration);

    }

    //es usado para q la pantalla se pueda  arrastrar desde cualkier lugar
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

    //hilo principal
    public static void main(String[] args) {
        launch(args);
    }


    //bloquea la Stage principal
    public void blockStage() {
        primaryStage.getScene().getRoot().setDisable(true);
    }

    //desbloquea la Stage principal
    public void unblockStage() {
        primaryStage.getScene().getRoot().setDisable(false);
    }

    public Scene getConfiguration() {
        return configuration;
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

    //lo siguiente son las implementaciones de de los metodos de la clase abstracta Game
    @Override
    public Graphics getGraphics() {
        return graphics;
    }


    @Override
    public void changeSize(int x, int y) {
        render.changeSize(x, y);

    }

    public Screen getStartScreen() {
        return new LoadingScreen(this);
    }

    //comienza la simulacion
    public void startRendering() {
        primaryStage.setScene(gameScene);
        setScreen(new GameScreen(this));
        render.resume();
    }

    public void stopRender() {
        render.pause();
    }

    //cambia el fondo del menu
    public void changeBackground(int i) {
        switch (i) {
            case 0:
                Assets.fondoActual = Assets.fondoDia;
                break;
            case 1:
                Assets.fondoActual = Assets.fondoNoche;
                break;
            case 2:
                Assets.fondoActual = Assets.fondoCaricatura;
                break;
            default:
                load();
        }

    }

    //cambia el tamahno de las casillas en la simulacion
    public void changeScreen(int i) {
        switch (i) {
            case 0:
                Settings.setZoom();
                break;
            case 1:
                Settings.setNormal();
                break;
            case 2:
                Settings.setMini();
                break;
            default:
                load();
        }
    }
}
