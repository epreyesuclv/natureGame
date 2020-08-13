package natureGame.framework.scene.ConfigurationScene.Dialogs;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.FileNotFoundException;


public class ErrorDialog {
    @FXML
    Label text;
    @FXML
    ImageView image;
    @FXML
    Button btnAceptar;
    private Stage stage;
    private double xOffset;
    private double yOffset;

    public void setText(String text) throws FileNotFoundException {
        this.text.setText(text);
       /* InputStream input = new FileInputStream(new File("E:\\Programing\\Work\\Proyecto final\\src\\natureGame\\Assets\\font\\Monster AG.ttf"));
        Font font = Font.loadFont(input, 14);
        this.text.setFont(font);*/
    }

    public void setImage(Image image) {
        this.image.setImage(image);
    }

    public void exit() throws Throwable {
        stage.close();
    }

    public void initializate() {
        AnchorPane root = (AnchorPane) stage.getScene().getRoot();
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - xOffset);
            stage.setY(event.getScreenY() - yOffset);
        });

    }

    public void setStage(Stage dialogStage) {
        this.stage = dialogStage;
        initializate();

    }
}
