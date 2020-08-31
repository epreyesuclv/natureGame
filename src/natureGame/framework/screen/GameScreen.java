package natureGame.framework.screen;

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

    private void updateGameOver(float deltaTime) {
        world = null;
        statDeath = null;
        statTotal = null;
        statVivos = null;
        game.setScreen(new MenuScreen((MyGame) game));
    }

    private void updatePause(float deltaTime) {

    }

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

    @Override
    public void present(float deltaTime) {
        if (state == GameState.Running) presentRunnig(deltaTime);
        if (state == GameState.Paused) presentPause(deltaTime);
        if (state == GameState.GameOver) presentGameOver(deltaTime);
    }

    private void presentPause(float deltaTime) {

    }

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

        g.drawPixmap(animation, world.currentAnimal.x, world.currentAnimal.y);

    }

    private void presentGameOver(float deltaTime) {
        game.setScreen(new MenuScreen((MyGame) game));
    }

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

    private Statistics showStatistics(String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(MyGame.class.getResource("framework/scene/MapaScene/barChart.fxml"));
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
