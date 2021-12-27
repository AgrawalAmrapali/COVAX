package Dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GraphDeaths implements Initializable {

    @FXML private BarChart deathGraph;
    public Button backButton;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GraphResponse2 obj = new GraphResponse2();
        String arr[] = obj.XaxisResult();
        Long brr[] = obj.YaxisDeaths();

        XYChart.Series currentStats = new XYChart.Series<>();
        currentStats.setName("Death Statistics of India");

        for (int i = 0; i<230; i++){
            currentStats.getData().add(new XYChart.Data(arr[i], brr[i]));
        }

        deathGraph.getData().addAll(currentStats);
    }
    public void back(ActionEvent actionEvent) {
        Stage stage = (Stage) backButton.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("graph.fxml"));


        } catch ( IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root,800,600));
    }
}
