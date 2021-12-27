package ShowStatistics;

import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ResponseHandler {
    String str,strjson,strjson2;

    public class covid {
        String lastRefreshed;
        Data data;

        String lastup;
        boolean success;

    }
    public class Data{
        Summary summary;
        ArrayList <reg> regional;

        public String toString()
        {  String nstr=summary.toString();
            return nstr;
        }
    }
    public class Summary{
        long total;
        long confirmedCasesIndian;
        long confirmedCasesForeign;
        long discharged;
        long deaths;
        long confirmedButLocationUnidentified;

        public String toString()
        {
            String nstr= "Total: "+total+"\nConfirmedIndian: "+confirmedCasesIndian+"\nConfirmedForeign: "+confirmedCasesForeign+"\nDischarged: "+discharged+"\nDeaths: "+deaths;
            return nstr;
        }
    }
    public class unoffSumm{

        String source;
        long total;
        long recovered;
        long deaths;
        long active;
    }
    public class reg{
        String loc;
        long confirmedCasesForeign;
        long confirmedCasesIndian;
        long discharged;
        long deaths;
        long totalConfirmed;
        public String toString(){
            String nstr="\nLocation: "+loc+"\ndeaths"+deaths;
            return nstr;
        }
    }

    private static HttpURLConnection connection;
    public String getCountryData() {
        BufferedReader reader;
        String line;
        StringBuffer content = new StringBuffer();
        String strjson2 = "";
        try {
            URL url = new URL("https://api.rootnet.in/covid19-in/stats/latest");
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
        long millis=System. currentTimeMillis();
        java. util. Date date=new java. util. Date(millis);
        System. out. println(date);
        strjson = (content.toString());
        JSONObject job = new JSONObject(strjson);
        System.out.println("hey"+job.toString());
        strjson2 = job.toString();
        Gson gson = new Gson();
        covid obj = gson.fromJson(strjson2, covid.class);

        String countryData="Last Refreshed: "+date+"\nTotal: "+obj.data.summary.total+"\nDischarged: "+obj.data.summary.discharged+"\nDeaths: "+obj.data.summary.deaths;


        return countryData;
    }

    //State Data
public String getStatedata(String stateN) {
    BufferedReader reader;
    String line;
    StringBuffer content = new StringBuffer();
    String strjson2 = "";
    try {
        URL url = new URL("https://api.rootnet.in/covid19-in/stats/latest");
        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        int status = connection.getResponseCode();

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

    } catch (
            IOException e) {
        e.printStackTrace();
    }
    strjson = (content.toString());

    JSONObject job = new JSONObject(strjson);
    System.out.println(job.toString());
    strjson2 = job.toString();


    Gson gson = new Gson();
   String nstr = null;
    covid obj = gson.fromJson(strjson2, covid.class);
    Long[] arr = new Long[3];
    long millis=System. currentTimeMillis();
    java. util. Date date=new java. util. Date(millis);
    for (int i = 0; i < 35; i++) {
        String Loc = obj.data.regional.get(i).loc;

        if (Loc.equals(stateN)) {
            System.out.println(Loc);


            arr[0] = obj.data.regional.get(i).totalConfirmed;
            arr[1] = obj.data.regional.get(i).discharged;
            arr[2] = obj.data.regional.get(i).deaths;
            nstr="Last Refreshed:"+date+"\nState:"+Loc+"\nTotal confirmed cases: "+arr[0]+"\nRecovered: "+ arr[1]+"Deaths: "+arr[2];

        }
    }
    System.out.println(arr[0]+" "+arr[1]+" "+arr[2]);


    return nstr;

}}
