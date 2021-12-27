package SelfAssessment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Assess3 implements Initializable {
    public Button gobackbutton2;
    @FXML
    private RadioButton q4rb1;
    @FXML private RadioButton q4rb2;
    @FXML private RadioButton q4rb3;
    @FXML private ToggleGroup q4;

    @FXML private RadioButton q5yes;
    @FXML private RadioButton q5no;
    @FXML private ToggleGroup q5;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        q4 = new ToggleGroup();
        this.q4rb1.setToggleGroup(q4);
        this.q4rb2.setToggleGroup(q4);
        this.q4rb3.setToggleGroup(q4);

        q5 = new ToggleGroup();
        this.q5yes.setToggleGroup(q5);
        this.q5no.setToggleGroup(q5);
    }
    boolean assessResponse[] = new boolean[3];
    public void clickListener(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);

        if(q4rb1.isSelected()){
            assessResponse[0] = true;
        }
        else
            assessResponse[0]=false;
        if(q4rb2.isSelected())
        {
            assessResponse[1] = true;

        }
        else
            assessResponse[1]=false;
        if (q5yes.isSelected()){
            assessResponse[2] = true;
        }
        else
            assessResponse[2] = false;


ScoreCalc obj=new ScoreCalc();
long result=obj.assessQues4and5(assessResponse);


            alert.setTitle("Result");
            alert.setHeaderText("");
            if(result<=20)
            alert.setContentText("Risk: "+result+" % (Low)");
            if(result>=20 && result<=40 )
            alert.setContentText("Risk: "+result+" % (Moderate)");
            if(result>=40 && result<=60)
            alert.setContentText("Risk: "+result+" % (High)");
            if(result>=60)
                alert.setContentText("Risk: "+result+" % (Very High)");
            alert.showAndWait();


        }


    public void goBack2(ActionEvent actionEvent) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Main/admin.fxml"));
        Parent root = null;
        try {
            root = loader.load();
            Stage stage = (Stage) gobackbutton2.getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.setTitle("Dashboard");
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

