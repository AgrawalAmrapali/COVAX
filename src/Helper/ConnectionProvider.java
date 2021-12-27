package Helper;


import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionProvider {
        private static Connection conn;
        public static Connection getConnection()
        {
            try
            {
                if(conn==null)
                {

                    Class.forName("com.mysql.jdbc.Driver");
                    conn= DriverManager.getConnection("jdbc:mysql://localhost:3306/Covax?useSSL=false","root","9649726949");
                }
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            return conn;
        }
    }

