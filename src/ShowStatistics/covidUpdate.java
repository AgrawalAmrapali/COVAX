package ShowStatistics;


import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;


public class covidUpdate {

    public Label total, discharged, timer;
    public Label deaths, locate, countryD,DistrictData;
    public Button Country;
    public Button State,getDistrict;
    public TextField stateName,stateN,districtName;
    public Button gobackbutton1;

    Thread t;

    public void getState(ActionEvent actionEvent) {
        String stateN = stateName.getText();
        ResponseHandler obj = new ResponseHandler();
        String nstr = obj.getStatedata(stateN);
        locate.setText(nstr);

    }

    public void getCountry(ActionEvent actionEvent) {

        ResponseHandler obj = new ResponseHandler();
        String countryData = obj.getCountryData();
        countryD.setText(countryData);

    }

//    public void getDistrictData(ActionEvent actionEvent)
//    {
//        String name1=stateN.getText();
//        String name2=districtName.getText();
//        DistrictResponse obj=new DistrictResponse();
//        String str=obj.getDistrictRes(name1,name2);
//        DistrictData.setText(str);
//
//    }

    public void goback1(ActionEvent actionEvent){

        Stage stage = (Stage) gobackbutton1.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Main/admin.fxml"));


        } catch ( IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root,800,600));


    }

}