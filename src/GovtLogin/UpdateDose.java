package GovtLogin;

import Entities.Vaccinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

public class UpdateDose {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    Vaccinator vacc;
    public TextField member_id;
    public RadioButton dose1,dose2;
    public Button submit,back;

    public void setConnection(Socket client, BufferedReader in, PrintWriter out) {
        this.client = client;
        this.in = in;
        this.out = out;
    }
    public void setVaccinator(Vaccinator v)
    {
        this.vacc=v;
    }
    public void updatedose(javafx.event.ActionEvent actionEvent)
    {   if((dose1.isSelected()||dose2.isSelected())&&!(member_id.getText().equals(""))) {
        out.println("updatedose");
        out.println(vacc.getPhone_id());
        out.println(member_id.getText());
        if (dose1.isSelected())
            out.println("dose1");
        else
            out.println("dose2");
    }
    else
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Information Dialog");
        alert.setHeaderText("Alert");
        alert.setContentText("Write member id and choode dose");
        alert.showAndWait();
    }
    }
    public void back(ActionEvent actionEvent)
    {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/GovtLogin/option.fxml"));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Option user = loader.getController();
        user.setConnection(client, in, out);
        user.setVaccinator(vacc);

        Stage stage = (Stage) back.getScene().getWindow();
        stage.setScene(new Scene(root, 800, 600));
        stage.setTitle("Update Dose");
        stage.show();
    }

}
