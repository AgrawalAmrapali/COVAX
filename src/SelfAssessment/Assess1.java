package SelfAssessment;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.stage.Stage;

import java.io.IOException;

public class Assess1 {
    public CheckBox q1none;
    public CheckBox q1loss;
    public CheckBox q1fever;
    public CheckBox q1diff;
    public CheckBox q1tired;
    public CheckBox q1cough;
    @FXML private Button secondQues;


    boolean assessResponse[] = new boolean[6];

    public void goToSecondQues(ActionEvent actionEvent) {
        ScoreCalc obj=new ScoreCalc();
        assessResponse[0] = q1cough.isSelected();
        assessResponse[1] = q1diff.isSelected();
        assessResponse[2] = q1tired.isSelected();
        assessResponse[3] = q1fever.isSelected();
        assessResponse[4] = q1loss.isSelected();
        assessResponse[5] = q1none.isSelected();
        obj.assessQues1(assessResponse);

        Stage stage = (Stage) secondQues.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("assess2.fxml"));
        } catch ( IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root,800,600));
    }










}
