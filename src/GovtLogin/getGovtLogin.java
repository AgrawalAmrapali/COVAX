package GovtLogin;

import Entities.Vaccinator;
import Helper.ConnectionProvider;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class getGovtLogin {
   public  Vaccinator getGovt(String phone_no, String password)
   {
       Vaccinator vacc=null;
       try{
           String query="select * from vaccinator where phone_no=? and password=?";
           Connection con= ConnectionProvider.getConnection();
           PreparedStatement pstmt=con.prepareStatement(query);
           pstmt.setString(1, phone_no);
           pstmt.setString(2, password);
           ResultSet set=pstmt.executeQuery();
           if(set.next())
           {
               vacc=new Vaccinator();
               vacc.setPhone_id(set.getString("phone_no"));
               vacc.setPassword(set.getString("password"));
               vacc.setVaccinator_id(set.getInt("vaccinator_id"));
                System.out.println("hello");



           }



       }catch(Exception e)
       {
           e.printStackTrace();
       }
       return vacc;


   }

}
