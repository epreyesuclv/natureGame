package natureGame.framework.scene.ConfigurationScene;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import natureGame.MyGame;
import natureGame.framework.fileIO.Settings;
import natureGame.framework.scene.ConfigurationScene.Dialogs.ErrorDialog;
import natureGame.model.Animal;
import org.controlsfx.control.ToggleSwitch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import static natureGame.framework.fileIO.Settings.inmoviles;

public class ConfigurationController {
    private MyGame myGame;

    @FXML
    public ToggleSwitch tswich;
    @FXML
    public TextField textFieldpiedras, textFieldplantas, textFieldconejos, textFieldserpientes, textFieldlechuzas, textFieldbuitres, ancho, largo;
    @FXML
    public ImageView imageViewpiedra, imageViewplanta, imageViewconejo, imageViewserpiente, imageViewlechuza, imageViewbuitre;

    private double xOffset;
    private double yOffset;

    private boolean check() throws IOException {
        TextField[] textFieldList = new TextField[]{null, textFieldpiedras, textFieldplantas, textFieldconejos, textFieldserpientes, textFieldlechuzas, textFieldbuitres, ancho, largo};

        int[] cantidades = new int[10];
        try {
            for (int i = 1; i < 9; i++) {
                cantidades[i] = Integer.parseInt(textFieldList[i].getText());
            }
        } catch (NumberFormatException | NullPointerException e) {

            showError("Hay campos mal");
            return false;
        }


        int casillas = cantidades[7] * cantidades[8];
        int safecasillas = casillas / 3;
        int sum = 0;
        for (int i = 1; i < 7; i++) sum += cantidades[i];
        if (sum > safecasillas) {
            showError("La cantidad de elementos en el sistema deben ser menor del 30% de la cantidad de casillas para un buen desarrollo en el sistema");
            return false;
        }

        for (int i = 1; i < 7; i++) {
            if (cantidades[i] < 2) {
                showError("La cantidad de animales debe ser minimo 2");

                return false;
            }
        }


        if (cantidades[7] < 30 || cantidades[8] < 30) {
            showError("La cantidad de casillas debe ser minimo 30x30");
            return false;
        }
        save(cantidades);
        return true;

    }


    public void exit() throws IOException {
        if (tswich.isSelected()) {
            if (check())
                myGame.showMenu();
            else {
                tswich.setSelected(false);
            }
        } else myGame.showMenu();

    }

    private void save(int[] cantidades) {
        Settings.x = cantidades[7];
        Settings.y = cantidades[8];
        Settings.list = new ArrayList<>();
        inmoviles = new ArrayList<>();
        int[][] map = new int[Settings.x][Settings.y];
        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < cantidades[i]; j++) {
                Random rand = new Random();
                int x = Math.abs(rand.nextInt() % Settings.x);
                int y = Math.abs(rand.nextInt() % Settings.y);
                while (map[x][y] != 0) {
                    x = Math.abs(rand.nextInt() % Settings.x);
                    y = Math.abs(rand.nextInt() % Settings.y);
                }
                if (i == 1 || i == 2)
                    Settings.inmoviles.add(new Animal(x, y, i));
                else
                    Settings.list.add(new Animal(x, y, i));
                map[x][y] = i;
            }
        }


    }

    public void showError(String text) throws IOException {
        FXMLLoader loader = new FXMLLoader(MyGame.class.getResource("framework/scene/ConfigurationScene/Dialogs/errorDialog.fxml"));
        loader.load();
        ErrorDialog controller = loader.getController();

        controller.setText(text);


        Stage dialogStage = new Stage();
        Scene scene = new Scene(loader.getRoot());

        dialogStage.setScene(scene);
        dialogStage.initStyle(StageStyle.TRANSPARENT);
        dialogStage.initModality(Modality.WINDOW_MODAL);
        dialogStage.initOwner(myGame.getPrimaryStage());
        controller.setStage(dialogStage);

        myGame.blockStage();
        dialogStage.showAndWait();
        myGame.unblockStage();
    }
    public void setMyGame(MyGame myGame) {
        this.myGame = myGame;
    }
}
