package ShowStatistics;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class DistrictResponse {

    private static HttpURLConnection connection;
    public String  getDistrictRes(String name1, String name2){
        BufferedReader reader;
        String line,strjson="";
        StringBuffer content = new StringBuffer();
        String strjson2 = "";
        try {
            URL url = new URL("https://api.covid19india.org/state_district_wise.json");
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
        JSONObject job= new JSONObject(strjson);
        System.out.println("hey"+job.toString());
        strjson2 = job.toString();
        String[] names = job.getNames(job);
        String nstr="";

        for (int i = 0; i < names.length; i++) {

            if(name1.equals(names[i]))
            System.out.println(names[i]);
            JSONObject jsonObject1 = job.getJSONObject(names[i]);
            JSONObject job2=jsonObject1.getJSONObject("districtData");
            String districts[]=job2.getNames(job2);
            for(int j=0;j<districts.length;j++)
            {
                if (name2.equals(districts[j])){
                System.out.println(districts[j]);
                JSONObject job3=job2.getJSONObject(districts[j]);
                    System.out.println(job3.getLong("active"));
                    System.out.println(job3.getLong("confirmed"));
                    System.out.println(job3.getLong("recovered"));
                    System.out.println(job3.getLong("deceased"));
                    nstr="Last Refreshed: "+date+"\nDistrict: "+districts[j]+"\nActive: "+job3.getLong("active")+"\nConfirmed: "+job3.getLong("confirmed")+"\nRecovered: "+job3.getLong("confirmed")+"\nDeaths: "+job3.getLong("deceased");

            }}

        }
        return nstr;

    }


}
