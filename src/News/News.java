package News;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URL;

public class News {


    public Button gobackbutton3;


    public void goBack3(ActionEvent actionEvent) {
        Stage stage = (Stage) gobackbutton3.getScene().getWindow();
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("/Main/admin.fxml"));


        } catch ( IOException e) {
            e.printStackTrace();
        }
        stage.setScene(new Scene(root,800,600));
    }

    public void clickListener(ActionEvent actionEvent) {
        openWebPage("https://www.covid19india.org/");
    }

    public static boolean openWebPage(URI uri){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop():null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            try{
                desktop.browse(uri);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void openWebPage(String urlString){
        try{
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        }catch (Exception e){
            e.printStackTrace();
        }
    }



    public void clickWhoadvice(ActionEvent actionEvent) {
        openWebPage("https://www.who.int/emergencies/diseases/novel-coronavirus-2019/advice-for-public");
    }

    public static boolean openWHOPage(URI uri){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop():null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            try{
                desktop.browse(uri);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void openWHOPage(String urlString){
        try{
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickDosanddonts(ActionEvent actionEvent) {
        openWebPage("https://www.healthline.com/health/coronavirus-prevention");
    }

    public static boolean openDosanddonts(URI uri){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop():null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            try{
                desktop.browse(uri);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void openDosanddonts(String urlString){
        try{
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickAfterpositive(ActionEvent actionEvent) {
        openWebPage("https://www.onemedical.com/blog/live-well/positive-covid-19-now-what");
    }

    public static boolean openAfterpositive(URI uri){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop():null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            try{
                desktop.browse(uri);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void openAfterpositive(String urlString){
        try{
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clickFoodcovid(ActionEvent actionEvent) {
        openWebPage("https://www.euro.who.int/en/health-topics/health-emergencies/coronavirus-covid-19/publications-and-technical-guidance/food-and-nutrition-tips-during-self-quarantine");
    }

    public static boolean openFoodcovid(URI uri){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop():null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            try{
                desktop.browse(uri);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void openFoodcovid(String urlString){
        try{
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickEverythingcovid(ActionEvent actionEvent) {
        openWebPage("https://coronavirus.medium.com/");
    }

    public static boolean openEverythingcovid(URI uri){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop():null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            try{
                desktop.browse(uri);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void openEverythingcovid(String urlString){
        try{
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickWorldcovid(ActionEvent actionEvent) {
        openWebPage("https://coronavirus.medium.com/");
    }

    public static boolean openWorldcovid(URI uri){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop():null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            try{
                desktop.browse(uri);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void openWorldcovid(String urlString){
        try{
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickCovidtesting(ActionEvent actionEvent) {
        openWebPage("https://www.cdc.gov/coronavirus/2019-ncov/testing/index.html");
    }

    public static boolean opencovidtesting(URI uri){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop():null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            try{
                desktop.browse(uri);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void opencovidtesting(String urlString){
        try{
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickEverything(ActionEvent actionEvent) {
        openWebPage("https://www.cdc.gov/coronavirus/2019-ncov/testing/index.html");
    }

    public static boolean openeveryything(URI uri){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop():null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            try{
                desktop.browse(uri);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void openeverything(String urlString){
        try{
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickfaq(ActionEvent actionEvent) {
        openWebPage("https://www.cdc.gov/coronavirus/2019-ncov/testing/index.html");
    }

    public static boolean openfaq(URI uri){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop():null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            try{
                desktop.browse(uri);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void openfaq(String urlString){
        try{
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickcovidolder(ActionEvent actionEvent) {
        openWebPage("https://www.who.int/teams/social-determinants-of-health/covid-19");
    }

    public static boolean opencovidolder(URI uri){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop():null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            try{
                desktop.browse(uri);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void opencovidolder(String urlString){
        try{
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clickoldtips(ActionEvent actionEvent) {
        openWebPage("https://www.who.int/teams/social-determinants-of-health/covid-19");
    }

    public static boolean openoldtips(URI uri){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop():null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            try{
                desktop.browse(uri);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void openoldtips(String urlString){
        try{
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickcovidgoi(ActionEvent actionEvent) {
        openWebPage("https://home.kpmg/xx/en/home/insights/2020/04/india-government-and-institution-measures-in-response-to-covid.html");
    }

    public static boolean opengoi(URI uri){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop():null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            try{
                desktop.browse(uri);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void opengoi(String urlString){
        try{
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void clickEconomicindia(ActionEvent actionEvent) {
        openWebPage("https://en.m.wikipedia.org/wiki/Economic_impact_of_the_COVID-19_pandemic_in_India");
    }

    public static boolean openeconomicindia(URI uri){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop():null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            try{
                desktop.browse(uri);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void openeconomicindia(String urlString){
        try{
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickworldeco(ActionEvent actionEvent) {
        openWebPage("https://www.bbc.com/news/amp/business-51706225");
    }

    public static boolean openworldeco(URI uri){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop():null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            try{
                desktop.browse(uri);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void openworldeco(String urlString){
        try{
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    public void clickmental(ActionEvent actionEvent) {
        openWebPage("https://www.ncbi.nlm.nih.gov/pmc/articles/PMC7189395/");
    }

    public static boolean openmental(URI uri){
        Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop():null;
        if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)){
            try{
                desktop.browse(uri);
                return true;
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static void openmental(String urlString){
        try{
            Desktop.getDesktop().browse(new URL(urlString).toURI());
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public Label help1,help2;
    public void openHelp(ActionEvent actionEvent)
    {
        HelplineResponse obj=new HelplineResponse();
        String str[]=obj.returnHelplines();
        help1.setText(str[0]);
        help2.setText(str[1]);
    }



}
