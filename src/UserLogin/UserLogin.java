package UserLogin;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


import javax.mail.*;
import javax.mail.internet.*;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.io.File;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;

import java.util.Properties;
import java.util.Random;

public class UserLogin {
    public Button Login;
    public Button Sender;
    public TextField PhoneNo;
    public TextField Email;
    public PasswordField otp;
    private int OTP;
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;


    public UserLogin(){
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
    public void LoginListener(ActionEvent actionEvent) {
        if ( otp.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Alert");
            alert.setContentText(" otp  cannot be empty");


            alert.showAndWait();

        } else {
            if (Integer.parseInt(otp.getText()) == OTP) {
                {   if(PhoneNo.getText().equals(""))
                {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserLogin/accountDetails.fxml"));
                        Parent root = loader.load();
                        AccountDetails user = loader.getController();
                        user.setConnection(client, in, out);
                        user.setEmail(Email.getText());

                        Stage stage = (Stage) Login.getScene().getWindow();
                        stage.setScene(new Scene(root, 500, 500));
                        stage.setTitle("Dashboard");
                        stage.show();
                        System.out.println("You are logged in");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                    else
                {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserLogin/accountDetails.fxml"));
                        Parent root = loader.load();
                        AccountDetails user = loader.getController();
                        user.setPhoneNo(PhoneNo.getText());
                        user.setConnection(client, in, out);
                        Stage stage = (Stage) Login.getScene().getWindow();
                        stage.setScene(new Scene(root, 500, 500));
                        stage.setTitle("Dashboard");
                        stage.show();
                        System.out.println("You are logged in");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }


                }
            } else {
                System.out.println("Ypu are not logged in");
            }
        }

    }

    public void OTPSender(ActionEvent actionEvent) {
        if (PhoneNo.getText().equals("")&&Email.getText().equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Alert");
            alert.setContentText("Phone number And Email both  cannot be empty");


            alert.showAndWait();

        } else if(Email.getText().equals("")){


            Random ran = new Random();
            OTP = ran.nextInt(999999);
            String message = "This is your OTP" + OTP;
            try {
                message = URLEncoder.encode(message, "UTF-8");
                String APIKey = "Xqdyw5bB7svNuHDUj6mnAI1Vz4ZxWCERMcSkp39fQtKY2gOGTLhzcvxqm9bjVMkHJCdKIFAYf3aE1iGQ";
                String myUrl = "https://www.fast2sms.com/dev/bulkV2?authorization="+APIKey+"&variables_values="+OTP+"&route=otp&numbers="+PhoneNo.getText();
                URL url = new URL(myUrl);
                HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                con.setRequestMethod("GET");
                con.setRequestProperty("User-Agent", "Mozzilla/5.0");
                con.setRequestProperty("cache-control", "no-cache");
                System.out.println("Wait.....");
                System.out.print(con.getResponseCode());
                StringBuffer respose = new StringBuffer();
                BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                while (true) {
                    String line = br.readLine();
                    if (line == null)
                        break;
                    respose.append(line);
                }
                System.out.println(respose);


            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else if(PhoneNo.getText().equals(""))
        {
            String host="smtp.gmail.com";

            //get the system properties
            Properties properties = System.getProperties();
            System.out.println("PROPERTIES "+properties);

            //setting important information to properties object

            //host set
            properties.put("mail.smtp.host", host);
            properties.put("mail.smtp.port","465");
            properties.put("mail.smtp.ssl.enable","true");
            properties.put("mail.smtp.auth","true");

            //Step 1: to get the session object..

            Session session=Session.getInstance(properties, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {

                    return new PasswordAuthentication("rituagrawal20071999@gmail.com","9649726949");
                }



            });

            session.setDebug(true);

            //Step 2 : compose the message [text,multi media]
            MimeMessage m = new MimeMessage(session);

            try {

                //from email
                var em = "agrawalamrapali@gmail.com" ;
                m.setFrom();

                //adding recipient to message
                m.addRecipient(Message.RecipientType.TO, new InternetAddress(Email.getText()));

                //adding subject to message
                String subject = "This is your OTP" ;
                m.setSubject(subject);


                //adding text to message
                Random ran = new Random();
                OTP = ran.nextInt(999999);
                String message = "This is your OTP" + OTP;
                m.setText(message);

                //send

                //Step 3 : send the message using Transport class
                Transport.send(m);

                System.out.println("Sent success...................");


            }catch (Exception e) {
                e.printStackTrace();
            }

        }

        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Alert");
            alert.setContentText("Phone number And Email use only one");


            alert.showAndWait();
        }

    }
}
