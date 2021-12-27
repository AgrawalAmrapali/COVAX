package GovtLogin;

import Entities.Vaccinator;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;
/*centerId
        vaccineId
        Dose
        age
        datefor
        timefor
        submit*/
public class AddDosse implements Initializable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    Vaccinator vacc;
    public TextField vaccineId;
    public TextField centerId;
    public RadioButton Dose1,Dose2,age1,age2,slot1,slot2,slot3;
    public DatePicker datefor;
    public TextField value;
    public Button back;
    public void setConnection(Socket client, BufferedReader in, PrintWriter out) {
        this.client = client;
        this.in = in;
        this.out = out;
    }
    public void setVaccinator(Vaccinator v)
    {
        this.vacc=v;
    }
    public void addingmem(javafx.event.ActionEvent actionEvent)
    {
        if(!centerId.getText().equals("")&&!vaccineId.getText().equals("")&&(Dose1.isSelected()||Dose2.isSelected())&&datefor.getValue()!=null&&(age1.isSelected()||age2.isSelected())&&(slot1.isSelected()||slot2.isSelected()||slot3.isSelected())&&!value.getText().equals("")) {
            out.println("AddSlot");
            out.println(vacc.getPhone_id());
            out.println(centerId.getText());
            out.println(vaccineId.getText());
            if (Dose1.isSelected())
                out.println("dose1");
            else
                out.println("dose2");
            if (age1.isSelected())
                out.println("18");
            else
                out.println("45");
            if (slot1.isSelected())
                out.println("1");
            else if (slot2.isSelected())
                out.println("2");
            else
                out.println("3");
            out.println(value.getText());
            out.println(datefor.getValue());
            try {
                String message = in.readLine();
                if (message.equals("done")) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Alert");
                    alert.setContentText("Hurray Dose details have been updated");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Information Dialog");
                    alert.setHeaderText("Alert");
                    alert.setContentText("Something Went Wrong");
                    alert.showAndWait();
                }

            } catch (IOException e) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Alert");
                alert.setContentText("Something Went Wrong");
                alert.showAndWait();
                e.printStackTrace();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Alert");
            alert.setContentText("Some fields are empty");
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
