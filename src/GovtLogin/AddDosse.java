package GovtLogin;

import Entities.Vaccinator;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

import java.awt.*;
import java.io.BufferedReader;
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
        out.println("AddSlot");
        out.println(vacc.getPhone_id());
        out.println(centerId.getText());
        out.println(vaccineId.getText());
        if(Dose1.isSelected())
        out.println("dose1");
        else
            out.println("dose2");
        if(age1.isSelected())
            out.println("18");
        else
            out.println("45");
        if(slot1.isSelected())
            out.println("1");
        else if(slot2.isSelected())
            out.println("2");
        else
            out.println("3");
        out.println(value.getText());
        out.println(datefor.getValue());



    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
