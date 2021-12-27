package Main;

import UserLogin.AccountDetails;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Admin {
    public Button user,official,dashboard;
    public Button selfAssessment;
    public Button news;
    public Button stats;


    public void showUser(ActionEvent actionEvent)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserLogin/userLogin.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Stage stage = (Stage) user.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void showOfficial(ActionEvent actionEvent)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GovtLogin/govtLogin.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Stage stage = (Stage) official.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void shownews(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/News/news.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Stage stage = (Stage) news.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 800));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showDashboard(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Dashboard/graph.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Stage stage = (Stage) dashboard.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showAssessment(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/SelfAssessment/Assess1.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Stage stage = (Stage) dashboard.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showstats(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/ShowStatistics/covidUpdate.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Stage stage = (Stage) stats.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
