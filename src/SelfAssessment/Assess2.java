package SelfAssessment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Assess2 implements Initializable{

    @FXML
    private RadioButton q3yes;
    @FXML private RadioButton q3no;
    @FXML private ToggleGroup q3;

    boolean assessResponse;
    public Button thirdQues;
    public CheckBox q2none;
    public CheckBox q2diab;
    public CheckBox q2hyper;
    public CheckBox q2lung;
    public CheckBox q2kidney;
    public CheckBox q2heart;
    private boolean arrque2[]=new boolean[6];

    public void goToThirdQues(ActionEvent actionEvent) {
        ScoreCalc obj = new ScoreCalc();
         if(q2diab.isSelected())
          arrque2[0]=true;
        if(q2hyper.isSelected())
            arrque2[1]=true;
        if(q2lung.isSelected())
            arrque2[2]=true;
        if(q2kidney.isSelected())
            arrque2[3]=true;
        if(q2heart.isSelected())
            arrque2[4]=true;
        if(q2none.isSelected())
            arrque2[5]=true;



        if(q3yes.isSelected()){
            assessResponse = true;

        }
        obj.assessQues2and3(assessResponse,arrque2);

        Stage stage = (Stage) thirdQues.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("assess3.fxml"));
        } catch ( IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root,800,600));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        q3 = new ToggleGroup();
        this.q3yes.setToggleGroup(q3);
        this.q3no.setToggleGroup(q3);

    }
}
