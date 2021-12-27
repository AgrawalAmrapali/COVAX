package GovtLogin;

import Entities.Vaccinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class Option {
    Vaccinator vacc=null;
    Socket client;
    BufferedReader in;
    PrintWriter out;
    public Button addDose,update,logout;
    public void setConnection(Socket client, BufferedReader in, PrintWriter out) {
        this.client = client;
        this.in = in;
        this.out = out;
    }
    public void setVaccinator(Vaccinator v)
    {
        this.vacc=v;
    }
    public void addDose(ActionEvent actionEvent)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GovtLogin/AddDose.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        AddDosse user = loader.getController();
        user.setConnection(client, in, out);
        user.setVaccinator(vacc);

        Stage stage = (Stage) addDose.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 600));
        stage.setTitle("Add Dose");
        stage.show();
        System.out.println("You are logged in");
    }
    public void updateDetails(ActionEvent actionEvent)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GovtLogin/updateDose.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        UpdateDose user = loader.getController();
        user.setConnection(client, in, out);
        user.setVaccinator(vacc);

        Stage stage = (Stage) update.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 600));
        stage.setTitle("Update Dose");
        stage.show();
    }
    public void logout(ActionEvent actionEvent)
    {
        try{

            //Sending message to server to logout
            out.println("LOGOUT");

            //closing the socket connection
            client.close();

            //Loading the main/homeoage.
            Parent root = FXMLLoader.load(getClass().getResource("/Main/admin.fxml"));
            Stage stage = (Stage) logout.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
