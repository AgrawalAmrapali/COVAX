package Dashboard;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.time.LocalDate;

public class Updatesbydate {
    @FXML private Button gobackbutton;
    @FXML
    private DatePicker chooseDate;
    @FXML private Label displayStats;
    @FXML private BarChart dateGraph;
    @FXML private NumberAxis yAxis;
    @FXML private CategoryAxis xAxis;

    public void showData(ActionEvent actionEvent) {
        LocalDate f = chooseDate.getValue();
        String s = f.toString();

        DateResponse obj = new DateResponse();
        long stats[] = obj.returnDateStat(s);

        //display statistics in label
        displayStats.setText("Date: " + f + "\n" +
                "Total confirmed cases: " + stats[1]+ "\n" +
                "Total active cases: " + stats[2] + "\n" +
                "Recovered: " + stats[3] + "\n" +
                "Deaths: " + stats[4]);

        //Enter chart details
        dateGraph.setTitle(s);
        XYChart.Series dateStats = new XYChart.Series();

        dateStats.setName("COVID 19 Statistics of India");

        dateStats.getData().add(new XYChart.Data("Confirmed",stats[1]));
        dateStats.getData().add(new XYChart.Data("Active",stats[2]));
        dateStats.getData().add(new XYChart.Data("Recovered",stats[3]));
        dateStats.getData().add(new XYChart.Data("Deaths",stats[4]));

        dateGraph.getData().addAll(dateStats);
    }


    public void goBack(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("graph.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Stage stage = (Stage) gobackbutton.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
