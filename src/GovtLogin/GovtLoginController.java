package GovtLogin;

import Entities.Vaccinator;
import Helper.ConnectionProvider;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class GovtLoginController {
    public Button Login;
    public TextField PhoneNo;
    public PasswordField Password;
}



   /* public void LoginListener(javafx.event.ActionEvent actionEvent) {
        if (PhoneNo.getText().equals("") || Password.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Alert");
            alert.setContentText("Phone number and Password  cannot be empty");


            alert.showAndWait();

        } else {
            try {
                String phone=PhoneNo.getText();
                String pass=Password.getText();
                getGovtLogin gl=new getGovtLogin();

                Vaccinator vacc=gl.getGovt(phone,pass);
                if(vacc==null)
                {

                }
                else
                {
                   System.out.println(vacc.getVaccinator_id());

                }

            } catch (Exception ex) {

                ex.printStackTrace();
            }
        }
    }
}*/

