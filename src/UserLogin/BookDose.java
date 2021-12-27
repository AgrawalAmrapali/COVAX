package UserLogin;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.sql.Date;

public class BookDose {
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;
    private String phoneno;
    private String email;
    @FXML
    private int id;
    @FXML
    private TableView<Slots> table;
    ObservableList<Slots> oblist = FXCollections.observableArrayList();
    @FXML
    private TableColumn<Slots, Integer> slot_id;
    @FXML
    private TableColumn<Slots, Date> date;
    @FXML
    private TableColumn<Slots, Integer> age;
    @FXML
    private TableColumn<Slots, String> city;
    @FXML
    private TableColumn<Slots, Integer> col_Dose1;
    @FXML
    private TableColumn<Slots, Integer> col_Dose2;
    @FXML
    private TableColumn<Slots, String> col_Vaccine;
    @FXML
    private TableColumn<Slots,Integer>slot;
    public RadioButton state,cityw,district;
    public TextField idd;
    public Button back;


    public void setConnection(Socket client, BufferedReader in, PrintWriter out) {
        this.client = client;
        this.in = in;
        this.out = out;
    }


    public void setPhoneNo(String phoneno) {
        this.phoneno = phoneno;
        this.email = null;

    }

    public void setEmail(String email) {
        this.email = email;
        this.phoneno = "";


    }
    public void setMember(int id)
    {
        this.id=id;
    }
    public void book(ActionEvent actionEvent)
    {
        if(!table.getSelectionModel().isEmpty()) {
            out.println("Book");
            int sid = table.getSelectionModel().getSelectedItem().getSlot_id();
            out.println(sid);
            out.println(id);
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Alert");
            alert.setContentText("Choose Slot");
            alert.showAndWait();
        }


    }
    public void refresh(ActionEvent actionEvent) {
        if((state.isSelected()||cityw.isSelected()||district.isSelected())&&!(idd.getText().equals(""))) {
            out.println("ShowSlots");
            if (state.isSelected())
                out.println("state");
            if (cityw.isSelected())
                out.println("city");
            if (district.isSelected())
                out.println("district");
            String me = idd.getText();
            out.println(me);


            try {
                //Condition to check if further rows are present in the Database
                oblist.clear();
                while (in.readLine().equals("MORE")) {

                    int slot_id = Integer.parseInt(in.readLine());
                    Date date = Date.valueOf((in.readLine()));
                    int age = Integer.parseInt(in.readLine());
                    System.out.println(age);
                    String city = in.readLine();
                    int Dose1 = Integer.parseInt(in.readLine());
                    int Dose2 = Integer.parseInt(in.readLine());
                    String Vaccine = in.readLine();
                    int slot = Integer.parseInt(in.readLine());
                    //System.out.println(Member_Name);


                    //Addig all tje data on the onservable list
                    oblist.add(new Slots(slot_id, date, age, city, Dose1, Dose2, Vaccine, slot));
                }
                slot_id.setCellValueFactory(new PropertyValueFactory<>("slot_id"));
                date.setCellValueFactory(new PropertyValueFactory<>("date"));
                age.setCellValueFactory(new PropertyValueFactory<>("age"));
                city.setCellValueFactory(new PropertyValueFactory<>("city"));
                col_Dose1.setCellValueFactory(new PropertyValueFactory<>("dose1"));
                col_Dose2.setCellValueFactory(new PropertyValueFactory<>("dose2"));
                col_Vaccine.setCellValueFactory(new PropertyValueFactory<>("name"));
                slot.setCellValueFactory(new PropertyValueFactory<>("slot"));


                table.setItems(oblist);

            } catch (Exception e) {
                e.printStackTrace();

            }
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Information Dialog");
            alert.setHeaderText("Alert");
            alert.setContentText("Choose state,district or city and Enter the city name");
            alert.showAndWait();
        }


    }
    public void back(ActionEvent actionEvent)
    {
        if(phoneno.equals(""))
        {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/UserLogin/accountDetails.fxml"));
                Parent root = loader.load();
                AccountDetails user = loader.getController();
                user.setConnection(client, in, out);
                user.setEmail(email);

                Stage stage = (Stage) back.getScene().getWindow();
                stage.setScene(new Scene(root, 800, 600));
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
                user.setPhoneNo(phoneno);
                user.setConnection(client, in, out);
                Stage stage = (Stage) back.getScene().getWindow();
                stage.setScene(new Scene(root, 800, 600));
                stage.setTitle("Dashboard");
                stage.show();
                System.out.println("You are logged in");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
    }

