package Dashboard;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Graph {
    public Button graphconfirmed;
    public Button graphrecovered;
    public Button graphdeath;
    public Button updatebydate;
    public Button backto;

    public void graphConfirmed(ActionEvent actionEvent)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("graphCountry.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Stage stage = (Stage) graphconfirmed.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void graphRecovered(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("graphRecovered.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Stage stage = (Stage) graphrecovered.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void graphDeath(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("graphDeaths.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Stage stage = (Stage) graphdeath.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void updatebyDate(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("updatesbydate.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Stage stage = (Stage) updatebydate.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void backTo(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main/admin.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Stage stage = (Stage) backto.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
