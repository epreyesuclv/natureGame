package natureGame.framework.screen;
/**
 * Es la clase q se encarga de toda la actualizacion de los datos y el dibujado en la pantalla
 */

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;
import natureGame.MyGame;
import natureGame.framework.fileIO.Assets;
import natureGame.framework.fileIO.Settings;
import natureGame.framework.graphics.Graphics;
import natureGame.framework.graphics.Pixmap;
import natureGame.framework.scene.MapaScene.Statistics;
import natureGame.model.World;

import java.io.IOException;

import static natureGame.MyGame.getPrimaryStage;
import static natureGame.MyGame.getRoot;
import static natureGame.framework.fileIO.Settings.IMAGE_BOUNDS;

public class GameScreen extends Screen {
    private World world;
    private Statistics statTotal;
    private Statistics statVivos;
    private Statistics statDeath;
    private float updateTick = 0;
    private int[] cant = new int[10];


    enum GameState {
        Running,
        Paused,
        GameOver
    }

    MyGame myGame;
    GameState state;

    public GameScreen(MyGame game) {
        super(game);
        myGame = game;
        world = new World();
        state = GameState.Running;

        game.changeSize(Settings.x * IMAGE_BOUNDS, Settings.y * IMAGE_BOUNDS);
        createMenu();

    }

    @Override
    public void update(float deltaTime) {
        if (state == GameState.Running) updateRunnig(deltaTime);
        if (state == GameState.Paused) updatePause(deltaTime);
        if (state == GameState.GameOver) updateGameOver(deltaTime);
    }

    //si la simulacion se termina se va a la pantalla de menu
    private void updateGameOver(float deltaTime) {
        world = null;
        statDeath = null;
        statTotal = null;
        statVivos = null;
        game.getGraphics().clear(0);
        myGame.showMenu();

    }

    private void updatePause(float deltaTime) {

    }

    //aki se llama el metodo World.update() q es el modelo utilizado en la simulacion y se actualiza las estadisticas
    // si se estan mostrando en pantalla
    private void updateRunnig(float deltaTime) {
        world.update(deltaTime);
        updateTick += deltaTime;

        if (updateTick > 0.5f) {
            updateTick -= 0.5f;
            if (statVivos != null) {
                statVivos.updateElement(world.buffervivos);

            }
            if (statDeath != null) {
                statDeath.updateElement(world.buffermuertos);
            }
            if (statTotal != null) {
                for (int i = 0; i < 7; i++)
                    world.bufferTotal[i] = world.buffervivos[i] + world.buffermuertos[i];

                statTotal.updateElement(world.bufferTotal);
            }
        }
    }

    //metodo q segun el estado en q este la simulacion actualiza los graficos de cierta manera
    @Override
    public void present(float deltaTime) {
        if (state == GameState.Running) presentRunnig(deltaTime);
        if (state == GameState.Paused) presentPause(deltaTime);
        if (state == GameState.GameOver) presentGameOver(deltaTime);
    }

    private void presentPause(float deltaTime) {
    }

    //si la simulacion esta activa se dibujan todos los elementos del sistema en su posicion excepto el q se esta moviendo,
    // pues ese se dibuja segun en la posicion exacta en q se encuentra, se carga la imagen q le corresponde a la animaciona de dicho animal
    //  y esa es la q se dibuja
    private void presentRunnig(float deltaTime) {
        Graphics g = game.getGraphics();
        g.clear(0);

        for (int i = 0; i < Settings.x; i++)
            for (int j = 0; j < Settings.y; j++) {
                g.drawPixmap(Assets.terreno, i * IMAGE_BOUNDS, j * IMAGE_BOUNDS);
                switch (world.mapa2[i][j]) {
                    case 1:
                        g.drawPixmap(Assets.piedra, i * IMAGE_BOUNDS, j * IMAGE_BOUNDS);
                        break;
                    case 2:
                        g.drawPixmap(Assets.planta, i * IMAGE_BOUNDS, j * IMAGE_BOUNDS);
                        break;
                    case 7:
                        g.drawPixmap(Assets.huesos, i * IMAGE_BOUNDS, j * IMAGE_BOUNDS);
                }
                switch (world.mapa[i][j]) {
                    case 3:
                        g.drawPixmap(Assets.conejo, i * IMAGE_BOUNDS, j * IMAGE_BOUNDS);
                        break;
                    case 4:
                        g.drawPixmap(Assets.serpiente, i * IMAGE_BOUNDS, j * IMAGE_BOUNDS);
                        break;
                    case 5:
                        g.drawPixmap(Assets.lechuza, i * IMAGE_BOUNDS, j * IMAGE_BOUNDS);
                        break;
                    case 6:
                        g.drawPixmap(Assets.buitre, i * IMAGE_BOUNDS, j * IMAGE_BOUNDS);
                        break;
                }


            }

        Pixmap animation;
        Pixmap[] animationList;
        int rigth = 0;
        switch (world.currentAnimal.getRefer()) {
            case 3:
                animationList = Assets.conejos;
                break;
            case 4:
                animationList = Assets.serpientes;
                break;
            case 5:
                animationList = Assets.lechuzas;
                break;
            case 6:
                animationList = Assets.buitres;
                break;
            default:
                throw new IllegalStateException("Valor inesperado: " + world.currentAnimal.getRefer());
        }
        if (world.currentAnimal.getDireccX() == 1) rigth = 3;
        animation = animationList[(world.currentAnimal.getIsMoving() % 3) + rigth];

        System.out.println("moving" + world.currentAnimal.getIsMoving());
        System.out.println(world.currentAnimal.getX() + "xy" + world.currentAnimal.getY() + " " + (world.currentAnimal.getX() - (world.currentAnimal.getDireccX() * world.currentAnimal.getIsMoving()) * IMAGE_BOUNDS / 6));
        int x = world.currentAnimal.getX() * IMAGE_BOUNDS - (world.currentAnimal.getDireccX() * (7 - world.currentAnimal.getIsMoving())) * IMAGE_BOUNDS / 6;
        int y = world.currentAnimal.getY() * IMAGE_BOUNDS - (world.currentAnimal.getDireccY() * (7 - world.currentAnimal.getIsMoving())) * IMAGE_BOUNDS / 6;
        g.drawPixmap(animation, x, y);

    }

    private void presentGameOver(float deltaTime) {
        myGame.showMenu();
    }

    //se crea el menu de opciones de la parte superior derecha con sus eventos click asociados
    private void createMenu() {
        MenuItem pause = new MenuItem("pause");
        pause.setOnAction(event -> {
            if (state == GameState.Running) {
                pause.setText("play");
                state = GameState.Paused;
            } else {
                pause.setText("pause");
                state = GameState.Running;
            }
        });
        MenuItem exit = new MenuItem("exit");
        exit.setOnAction(e -> {
            state = GameState.GameOver;
        });
        MenuItem checkVivios = new MenuItem("Vivos");
        checkVivios.setOnAction(e -> {
            if (statVivos == null) {
                try {
                    statVivos = showStatistics("Vivos");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (!statVivos.stage.isShowing()) statVivos.stage.show();
        });
        MenuItem checkTotal = new CheckMenuItem("Total");
        checkTotal.setOnAction(e -> {
            if (statTotal == null) {
                try {
                    statTotal = showStatistics("Total");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (!statTotal.stage.isShowing())
                statTotal.stage.show();
        });
        MenuItem checkMuertos = new MenuItem("Muertos");
        checkMuertos.setOnAction(e -> {
            if (statDeath == null) {
                try {
                    statDeath = showStatistics("Muertos");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (!statDeath.stage.isShowing())
                statDeath.stage.show();
        });
        Menu statistics = new Menu("statistics", null, checkMuertos, checkTotal, checkVivios);
        Menu menu = new Menu("game", null, pause, exit);
        getRoot().getChildren().add(new MenuBar(menu, statistics));


    }

    //metodo q se encarga de ejecutar la pantalla de estadisticas
    private Statistics showStatistics(String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(MyGame.class.getResource("framework/scene/MapaScene/pieChart.fxml"));
        loader.load();
        Statistics controller = loader.getController();
        Stage stage = new Stage();

        stage.setScene(new Scene(loader.getRoot()));
        stage.initOwner(getPrimaryStage());
        stage.getIcons().add(Assets.pieChart.getImage());

        controller.setStage(stage);
        controller.setTitle(titulo);
        stage.show();

        return controller;
    }
}
