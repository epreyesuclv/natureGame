package natureGame.framework.scene.MapaScene;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import natureGame.model.Animal;
import natureGame.model.World;
import natureGame.MyGame;
import natureGame.Util;

import java.io.IOException;
import java.util.List;


/*public class Mapa extends AnchorPane {
    private final ScrollPane scrollPane;
    double IMAGE_BOUNDS = Util.IMAGE_BOUNDS;
    World world;
    MyGame myGame;
    boolean isPlay = true;
    Statistics statTotal = null,
            statVivos = null,
            statDeath = null;
    public Canvas master;


    public Mapa(MyGame myGame, ScrollPane scroll) {
        super();
        this.myGame = myGame;
        scrollPane = scroll;
        try {
            init();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        createMenu();

    }

    private void createMenu() {
        MenuItem pause = new MenuItem("pause");
        pause.setOnAction(event -> {
            if (isPlay) {
                pause.setText("play");
                world.pause();
            } else {
                pause.setText("pause");
                try {
                    world.resume();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        MenuItem exit = new MenuItem("exit");
        exit.setOnAction(e -> {
            world.stop();
            myGame.showMenu();
        });
        MenuItem checkVivios = new MenuItem("Vivos");
        checkVivios.setOnAction(e -> {
            if (statVivos == null) {
                try {
                    statVivos = showStatistics(Util.vivos, "Vivos");
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
                    statTotal = showStatistics(Util.total, "Total");
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
                    statDeath = showStatistics(Util.muertos, "Muertos");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
            if (!statDeath.stage.isShowing())
                statDeath.stage.show();
        });
        Menu statistics = new Menu("statistics", null, checkMuertos, checkTotal, checkVivios);
        Menu menu = new Menu("game", null, pause, exit);
        getChildren().add(new MenuBar(menu, statistics));


    }

    private Statistics showStatistics(List<Animal> list, String titulo) throws IOException {
        FXMLLoader loader = new FXMLLoader(MyGame.class.getResource("Graphics/MapaScene/barChart.fxml"));
        loader.load();
        Statistics controller = loader.getController();
        Stage stage = new Stage();

        stage.setScene(new Scene(loader.getRoot()));
        stage.initOwner(myGame.getPrimaryStage());
        stage.getIcons().add(Util.getImage("natureGame/Assets/Images/Otros/pie-chart.jpg"));

        controller.setStage(stage);
        controller.update();
        controller.setTitle(titulo);

        stage.show();

        return controller;
    }

    public void init() throws InterruptedException {

        int width = (int) (Util.sizex * IMAGE_BOUNDS);
        int heigth = (int) (Util.sizey * IMAGE_BOUNDS);

   /*     Pos pos = Util.calculateAspectRatio(Util.sizex, Util.sizey);

        double width = pos.getX() * IMAGE_BOUNDS;
        double heigth = pos.getY() * IMAGE_BOUNDS;
        this.setHeight(heigth);
        this.setWidth(width);

        int x = Math.abs(pos.getX() - Util.sizex) / 2;
        int y = Math.abs(pos.getY() - Util.sizey) / 2;

        Canvas canvas = new Canvas(width, heigth);
        for (int i = 0; i < Util.sizex; i++) {
            for (int j = 0; j < Util.sizey; j++) {
              /*  if ((i < x) || (i >= (x + Util.sizex)) || ((j < y) || j >= (y + Util.sizey))) {
                    canvas.getGraphicsContext2D().drawImage(Util.terreno, i * IMAGE_BOUNDS, j * Util.IMAGE_BOUNDS);
                    canvas.getGraphicsContext2D().drawImage(arbol, i * IMAGE_BOUNDS, j * Util.IMAGE_BOUNDS);
                } else
                canvas.getGraphicsContext2D().drawImage(Util.terreno, i * IMAGE_BOUNDS, j * Util.IMAGE_BOUNDS);

            }
        }
        getChildren().add(canvas);
        createComponent(canvas);
        myGame.initDragg(this);
        world = new World(this);
        world.start();
    }

    private void createComponent(Canvas mapCanvas) {
        for (Animal e : Util.initList
        ) {
            if (e.getPos().getRefer() == 1) mapCanvas.getGraphicsContext2D().drawImage(e.getImage(), 0, 0);
            else {
                Canvas canvas = new Canvas(IMAGE_BOUNDS, IMAGE_BOUNDS);
                canvas.getGraphicsContext2D().drawImage(e.getImage(), 0, 0);
                e.setCanvas(canvas);
                draw(canvas, e.getx() * IMAGE_BOUNDS, e.gety() * IMAGE_BOUNDS);
            }
        }
        master = mapCanvas;
    }

    public void draw(Canvas canvas, double x, double y) {
        if (!getChildren().contains(canvas))
            getChildren().add(canvas);
        canvas.setTranslateX(x + IMAGE_BOUNDS);
        canvas.setTranslateY(y + IMAGE_BOUNDS);

    }


}*/
