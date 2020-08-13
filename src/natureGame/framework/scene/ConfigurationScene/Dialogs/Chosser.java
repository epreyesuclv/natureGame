package natureGame.framework.scene.ConfigurationScene.Dialogs;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import natureGame.Util;

import java.util.List;

public class Chosser extends GridPane {
    private Stage stage;
    private double xOffset;
    private double yOffset;


    public Chosser(List<String> list, ImageView view) {
        super();
        initializate();
        for (int i = 0; i < list.size(); i++) {
            Canvas canvas = new Canvas(100, 100);
            canvas.getGraphicsContext2D().drawImage(Util.getImage(list.get(i)), 0, 0);
            int finalI = i;
            canvas.setOnMouseClicked(event -> view.setImage(Util.getImage(list.get(finalI))));
            this.add(canvas, i / 3, i % 3);//OnMouseClicked
            if (i == list.size() - 1) {
                Canvas exit = new Canvas(80, 80);
                exit.setOnMouseClicked(event -> stage.close());
                exit.getGraphicsContext2D().drawImage(Util.exit, 0, 0);
                this.add(exit, list.size() / 3, list.size() % 3);

            }
        }
    }

    public void initializate() {

        this.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        this.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
