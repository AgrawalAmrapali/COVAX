package UserLogin;

import Helper.ConnectionProvider;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.URL;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.Date;
import java.util.ResourceBundle;

public class AccountDetails implements Initializable {

    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private String phoneno;
    private String email;
    public TextField mail;
    public TextField number;
    public Button Addmember;
    public Button download;
    public Button BookDose,logout;
    @FXML
    private TableView<Members> table;
    ObservableList<Members> oblist = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Members, Integer> col_id;
    @FXML
    private TableColumn<Members, String> col_name;
    @FXML
    private TableColumn<Members, String> col_YOB;
    @FXML
    private TableColumn<Members, Integer> col_Dose1;
    @FXML
    private TableColumn<Members, Integer> col_Dose2;
    @FXML
    private TableColumn<Members, String> col_Vaccine;

    public void setPhoneNo(String phoneno) {
        this.phoneno = phoneno;
        number.setText(phoneno);
        mail.setText("NULL");
        this.email = null;

    }

    public void setEmail(String email) {
        this.email = email;
        this.phoneno = "";
        mail.setText(email);
        number.setText("NULL");

    }

    public void refresh(ActionEvent actionEvent) {
        out.println("UserDashBoard");
        if (phoneno.equals("")) {
            out.println("email");
            out.println(email);
        } else {
            out.println("phoneno");
            out.println(phoneno);
        }
        try {
            //Condition to check if further rows are present in the Database
            oblist.clear();
            while (in.readLine().equals("MORE")) {


                int Member_ID = Integer.parseInt(in.readLine());
                String Member_Name = in.readLine();
                String YOB = in.readLine();
                int Dose1 = Integer.parseInt(in.readLine());
                int Dose2 = Integer.parseInt(in.readLine());
                String Vaccine = in.readLine();
                //System.out.println(Member_Name);


                //Addig all tje data on the onservable list
                oblist.add(new Members(Member_ID, Member_Name, YOB, Dose1, Dose2, Vaccine));
            }

            col_id.setCellValueFactory(new PropertyValueFactory<>("Member_ID"));
            col_name.setCellValueFactory(new PropertyValueFactory<>("Member_Name"));
            col_YOB.setCellValueFactory(new PropertyValueFactory<>("YOB"));
            col_Dose1.setCellValueFactory(new PropertyValueFactory<>("Dose1"));
            col_Dose2.setCellValueFactory(new PropertyValueFactory<>("Dose2"));
            col_Vaccine.setCellValueFactory(new PropertyValueFactory<>("Vaccine"));


            table.setItems(oblist);

        } catch (Exception e) {
            e.printStackTrace();

        }


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }

    //setting the socket connection.
    public void setConnection(Socket client, BufferedReader in, PrintWriter out) {
        this.client = client;
        this.in = in;
        this.out = out;
    }

    public void deleteListener(ActionEvent actionEvent) throws Exception {
        if(!table.getSelectionModel().isEmpty()) {
            out.println("DELETE_MEMBER");

            try {
                int id = table.getSelectionModel().getSelectedItem().getMember_ID();
                out.println(id);
                if (phoneno.equals("")) {

                    out.println(email);
                } else {

                    out.println(phoneno);
                }
                Members selectedItem = table.getSelectionModel().getSelectedItem();
                table.getItems().remove(selectedItem);


            } catch (Exception e) {
                System.out.println("not done");

            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Alert");
            alert.setContentText("Choose Member");
            alert.showAndWait();
        }
    }
    public void addmember(ActionEvent actionEvent)
    {
        if(phoneno.equals(""))
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserLogin/addMember.fxml"));
                Parent root = loader.load();
                AddMember user = loader.getController();
                user.setConnection(client, in, out);
                user.setEmail(email);

                Stage stage = (Stage) Addmember.getScene().getWindow();
                stage.setScene(new Scene(root, 800, 600));
                stage.setTitle("Add Member");
                stage.show();
                System.out.println("Add Member");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserLogin/addMember.fxml"));
                Parent root = loader.load();
                AddMember user = loader.getController();
                user.setPhoneNo(phoneno);
                user.setConnection(client, in, out);
                Stage stage = (Stage) Addmember.getScene().getWindow();
                stage.setScene(new Scene(root, 800, 600));
                stage.setTitle("Add Member");
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }
    public void onClickDownload(ActionEvent actionEvent) throws IOException {

        //if the code Textfield is empty, an alert box will be shown.



            //Sending a request to server to execute the download function
        if(!table.getSelectionModel().isEmpty()) {
            out.println("DOWNLOAD");
            //retrieve the filecode for code textfield.
            int id = table.getSelectionModel().getSelectedItem().getMember_ID();
            out.println(id);


            String response = in.readLine();
            if (response.equals("true")) {

                Label label = new Label("Hurray .............The file has downloaded");
                Popup popup = new Popup();
                label.setStyle("-fx-background-color:white;"
                        + " -fx-font-size: " + 15 + ";"
                        + " -fx-padding: 10px;"
                        + " -fx-background-radius: 6;");
                popup.getContent().add(label);
                label.setMinWidth(200);
                label.setMinHeight(60);
                popup.setAutoHide(true);
                popup.show(download.getScene().getWindow());
            }
            //if the downloaded file is already present in the system, this alert box will be shown
            else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Information Dialog");
                alert.setHeaderText("Alert");
                alert.setContentText("File name already exists in downloads or File not available ");
                alert.showAndWait();
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Alert");
            alert.setContentText("Choose Member");
            alert.showAndWait();
        }

    }
    public void BookSlot(ActionEvent actionEvent)
    {
        if(!table.getSelectionModel().isEmpty()) {
            int id = table.getSelectionModel().getSelectedItem().getMember_ID();
            if (phoneno.equals("")) {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserLogin/BookDose.fxml"));
                    Parent root = loader.load();
                    BookDose user = loader.getController();
                    user.setConnection(client, in, out);
                    user.setEmail(email);
                    user.setMember(id);

                    Stage stage = (Stage) BookDose.getScene().getWindow();
                    stage.setScene(new Scene(root, 800, 600));
                    stage.setTitle("Book Dose");
                    stage.show();
                    System.out.println("Add Member");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserLogin/BookDose.fxml"));
                    Parent root = loader.load();
                    BookDose user = loader.getController();
                    user.setPhoneNo(phoneno);
                    user.setConnection(client, in, out);
                    user.setMember(id);
                    Stage stage = (Stage) BookDose.getScene().getWindow();
                    stage.setScene(new Scene(root, 800, 600));
                    stage.setTitle("Book Dose");
                    stage.show();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Alert");
            alert.setContentText("Choose Member");
            alert.showAndWait();
        }

       // out.println(id);
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
