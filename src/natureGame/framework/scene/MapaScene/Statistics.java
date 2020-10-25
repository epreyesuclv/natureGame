package natureGame.framework.scene.MapaScene;
/**
 * es la clase controladora de el fxml pieChart.fxml,
 * se encarga de mostrar las estadisticas que el usuario halla pulsado
 */


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Statistics {
    @FXML
    private PieChart pieChart;
    @FXML
    Label plantasLabel;
    @FXML
    Label conejosLabel;
    @FXML
    Label serpientesLabel;
    @FXML
    Label lechuzasLabel;
    @FXML
    Label buitresLabel;
    private Label[] labels = new Label[5];
    private PieChart.Data[] datas = new PieChart.Data[5];
    private ObservableList<String> list = FXCollections.observableArrayList();
    private int[] cant = new int[10];
    public Stage stage;
    private boolean firstTime = true;

    @FXML
    private void initialize() {
        this.list.addAll("Plantas", "Conejos", "Serpientes", "Lechuza", "Buitre");
    }

    //actualiza los datos de los animales en la grafica
    public void updateElement(int[] buffer) {
        if (firstTime) {
            firstTime = false;
            datas = new PieChart.Data[]{
                    new PieChart.Data(list.get(0), 0),
                    new PieChart.Data(list.get(1), 0),
                    new PieChart.Data(list.get(2), 0),
                    new PieChart.Data(list.get(3), 0),
                    new PieChart.Data(list.get(4), 0)};
            pieChart.getData().addAll(datas);
            labels = new Label[]{
                    plantasLabel,
                    conejosLabel,
                    serpientesLabel,
                    lechuzasLabel,
                    buitresLabel};

        }
        for (int i = 0; i < 5; i++) {
            datas[i].setPieValue(buffer[i + 2]);
            labels[i].setText(list.get(i) + ": " + buffer[i + 2]);

        }
    }

    public void setTitle(String s) {
        pieChart.setTitle(s);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
