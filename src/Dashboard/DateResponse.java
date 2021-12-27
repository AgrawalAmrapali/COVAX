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

public class DateResponse {
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

    public long[] returnDateStat(String date) {
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

        strjson = (content.toString());
        JSONObject job = new JSONObject(strjson);
        strjson2 = job.toString();
        Gson gson = new Gson();
        DateResponse.object obj = gson.fromJson(strjson2, DateResponse.object.class);
        long result[] = new long[5];

        for(int i=0;i<230;i++) {

            if(obj.data.history.get(i).day.equals(date)) {

                result[1]=obj.data.history.get(i).total.confirmed;//storing confirmed cases
                result[2]=obj.data.history.get(i).total.active;//storing active cases
                result[3]=obj.data.history.get(i).total.recovered;//storing recovered cases
                result[4]=obj.data.history.get(i).total.deaths;//storing deaths
            }
        }
        return result;


    }


}
