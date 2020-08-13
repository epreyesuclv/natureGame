package natureGame.framework.scene.MapaScene;

import javafx.scene.chart.PieChart;

public class Statistics2 {
    PieChart pieChart = new PieChart();

    public void init() {
        pieChart.getData().addAll(new PieChart.Data("perros", 10), new PieChart.Data("gatos", 20));
    }
}
