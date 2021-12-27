package News;

import com.google.gson.Gson;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class HelplineResponse {
    public class object{
        Data data;

    }
    public class Data{
        Contact contacts;
    }
public class Contact{
        Primary primary;
        ArrayList <Regional> regional;
}
public class Primary{
        String number;
        String email;
}
public class Regional{
        String loc;
        String number;
}
    private static HttpURLConnection connection;

    public  String[] returnHelplines() {
        BufferedReader reader;
        String line, strjson = "";
        StringBuffer content = new StringBuffer();
        String strjson2 = "";
        try {
            URL url = new URL("https://api.rootnet.in/covid19-in/contacts");
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
        System.out.println(date);
        strjson = (content.toString());
        JSONObject job = new JSONObject(strjson);
        System.out.println("hey" + job.toString());
        strjson2 = job.toString();
        Gson gson = new Gson();
        HelplineResponse.object obj = gson.fromJson(strjson2, HelplineResponse.object.class);
        String str[]=new String[2];
        str[0]="";
        str[1]="";
        for(int i=0;i<=18;i++)
        {
            str[0]=str[0]+obj.data.contacts.regional.get(i).loc+"\t"+obj.data.contacts.regional.get(i).number+"\n";

        }
        for(int i=19;i<37;i++)
        {
            str[1]=str[1]+obj.data.contacts.regional.get(i).loc+"\t"+obj.data.contacts.regional.get(i).number+"\n";
        }
 return str;

    }}
