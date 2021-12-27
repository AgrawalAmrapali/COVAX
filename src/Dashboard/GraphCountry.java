package Dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GraphCountry implements Initializable {

    @FXML
    private BarChart activeCases;
    @FXML
    private CategoryAxis allDates;
    @FXML
    private NumberAxis numberOfCases;
    @FXML
    private Button gobackbutton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GraphResponse obj = new GraphResponse();
        String arr[] = obj.XaxisRes();
        Long brr[] = obj.YaxisRes();

        XYChart.Series currentStats = new XYChart.Series<>();
        currentStats.setName("Current Active cases Statistics of India");

        for (int i = 0; i < 230; i++) {
            currentStats.getData().add(new XYChart.Data(arr[i], brr[i]));

        }

        activeCases.getData().addAll(currentStats);
    }
    public void gobackGraph(ActionEvent actionEvent) {
        Stage stage = (Stage) gobackbutton.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("graph.fxml"));


        } catch ( IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root,800,600));
    }


}
