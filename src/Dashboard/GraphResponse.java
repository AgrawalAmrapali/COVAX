package Dashboard;

import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class GraphResponse {
    public class object{
        boolean success;
        Data data;

    }
    public class Data{
        String source;
        String lastRefreshed;
        ArrayList<History> history;
    }
    public class History {

        String day;
        Total total;
    }
    public class Total {
        long confirmed, recovered, deaths, active;
    }

    private static HttpURLConnection connection;

    public  String[] XaxisRes() {
        BufferedReader reader;
        String line, strjson = "";
        StringBuffer content = new StringBuffer();
        String strjson2 = "";
        try {
            URL url = new URL("https://api.rootnet.in/covid19-in/unofficial/covid19india.org/statewise/history");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status = connection.getResponseCode();
            //System.out.println(status);
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));

            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (
                MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        //System.out.println(date);
        strjson = (content.toString());
        JSONObject job = new JSONObject(strjson);
        //System.out.println("hey" + job.toString());
        strjson2 = job.toString();
        Gson gson = new Gson();
        GraphResponse.object obj = gson.fromJson(strjson2, GraphResponse.object.class);
        String xaxis[]=new String[230] ;
        for(int i=0;i<230;i++) {

            xaxis[i]= obj.data.history.get(i).day;
            //System.out.println(obj.data.history.get(i).day);

        }
        return xaxis;


    }

    public  Long[] YaxisRes() {
        BufferedReader reader;
        String line, strjson = "";
        StringBuffer content = new StringBuffer();
        String strjson2 = "";
        try {
            URL url = new URL("https://api.rootnet.in/covid19-in/unofficial/covid19india.org/statewise/history");
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            int status = connection.getResponseCode();
            //System.out.println(status);
            if (status > 299) {
                reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));

            } else {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            }
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        } catch (
                MalformedURLException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
        long millis = System.currentTimeMillis();
        java.util.Date date = new java.util.Date(millis);
        //System.out.println(date);
        strjson = (content.toString());
        JSONObject job = new JSONObject(strjson);
        //System.out.println("hey" + job.toString());
        strjson2 = job.toString();
        Gson gson = new Gson();
        GraphResponse.object obj = gson.fromJson(strjson2, GraphResponse.object.class);
        Long yaxis[]=new Long[230] ;
        for(int i=0;i<230;i++) {

            yaxis[i]= obj.data.history.get(i).total.active;
            //System.out.println(obj.data.history.get(i).total.active);
        }
        return yaxis;


    }

}
