package GovtLogin;

import Entities.Vaccinator;
import Helper.ConnectionProvider;
import UserLogin.AccountDetails;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class getGovtLogin {
    Vaccinator vacc=null;
    Socket client;
    BufferedReader in;
    PrintWriter out;
    @FXML
    Button Login;
    public TextField PhoneNo;
    public PasswordField Password;
    public getGovtLogin(){
        try {
            client = new Socket("localhost", 9806);
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
            System.out.println("Connected to server");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
   public  void getGovt(javafx.event.ActionEvent actionEvent) {
       if (PhoneNo.getText().equals("") || Password.getText().equals("")) {
           Alert alert = new Alert(Alert.AlertType.INFORMATION);
           alert.setTitle("Information Dialog");
           alert.setHeaderText("Alert");
           alert.setContentText("Phone number and Password  cannot be empty");


           alert.showAndWait();

       } else {
           try {
               String phone = PhoneNo.getText();
               String pass = Password.getText();
               getGovtLogin gl = new getGovtLogin();
               try{
                   out.println("govtOfficial");
                  out.println(phone);
                  out.println(pass);
                  String message=in.readLine();
                   if(message.equals("find"))
                   {
                       vacc=new Vaccinator();
                       vacc.setPhone_id(phone);
                       vacc.setPassword(pass);

                       try {
                           FXMLLoader loader = new FXMLLoader(getClass().getResource("/GovtLogin/AddDose.fxml"));
                           Parent root = loader.load();
                           AddDosse user = loader.getController();
                           user.setConnection(client, in, out);
                           user.setVaccinator(vacc);

                           Stage stage = (Stage) Login.getScene().getWindow();
                           stage.setScene(new Scene(root, 500, 500));
                           stage.setTitle("Add Dose");
                           stage.show();
                           System.out.println("You are logged in");
                       } catch (IOException e) {
                           e.printStackTrace();
                       }



                   }
                   else
                   {
                       Alert alert = new Alert(Alert.AlertType.INFORMATION);
                       alert.setTitle("Information Dialog");
                       alert.setHeaderText("Alert");
                       alert.setContentText("Phone number or Password may be incorrect");


                       alert.showAndWait();

                   }



               }catch(Exception e)
               {
                   e.printStackTrace();
               }


           } catch (Exception ex) {

               ex.printStackTrace();
           }
       }
   }



}
