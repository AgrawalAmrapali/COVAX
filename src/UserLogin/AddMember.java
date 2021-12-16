package UserLogin;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class AddMember implements Initializable {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private String phoneno;
    private String email;
    public TextField MemName;
    public TextField IdProof;
    public TextField Gen;
    public TextField Age;
    private String message;



    public void setPhoneNo(String phoneno) {
        this.phoneno = phoneno;
        this.email = "";

    }

    public void setEmail(String email) {
        this.email = email;
        this.phoneno = "";

    }
    public void setConnection(Socket client, BufferedReader in, PrintWriter out) {
        this.client = client;
        this.in = in;
        this.out = out;
    }
    @FXML
    ListView fileBox;
    File file;
    public void BrowseClicked(javafx.event.ActionEvent actionEvent) {
        FileChooser fc = new FileChooser();

        file = fc.showOpenDialog(null);

        if(file != null){
            fileBox.getItems().add(file.getName());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    public void uploadClicked(javafx.event.ActionEvent actionEvent) {

        //alert box shown if no file has been choosen to upload.
        String mem=MemName.getText();
        String Id=IdProof.getText();
        String g=Gen.getText();
        if (fileBox.getItems().isEmpty()||mem.equals("")||Id.equals("")||g.equals("")) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Alert");
            alert.setContentText("Some field are are empty");


            alert.showAndWait();
        }
        else {
            try {
                //Requesting the server to execute theupload() function,
                out.println("UPLOAD");

                //If the uploaded file is correct
                if (file != null) {
                    if (phoneno.equals("")) {
                        //out.println("email");
                        out.println(email);
                    } else {
                       // out.println("phoneno");
                        out.println(phoneno);
                    }
                    String fileName = file.getName();
                    out.println(fileName);
                    out.println(file.getAbsolutePath());
                    out.println(mem);
                    out.println(Id);
                    out.println(g);
                    out.println(Age.getText());





                    //Response from the server
                    message = in.readLine();

                    //on receiving correct response, pop up is shown.
                    if (!message.isEmpty()) {
                        Label label = new Label("Hurray .............The file has uploaded");
                        Popup popup = new Popup();
                        label.setStyle("-fx-background-color:white;"
                                + " -fx-font-size: " + 15 + ";"
                                + " -fx-padding: 10px;"
                                + " -fx-background-radius: 6;");
                        popup.getContent().add(label);
                        label.setMinWidth(200);
                        label.setMinHeight(60);
                        popup.setAutoHide(true);

                        //Uploader is shown the File Code window that holds the code to be used for downloads.

                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
