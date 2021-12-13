package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ClientHandler implements Runnable {
    //Field for setting the socket connection
    private Socket client;
    private BufferedReader in;
    private PrintWriter out;

    //field for socket connection
    private Connection connect;

    //field to receive requests and send responses.
    private String message;

    //Constructor that sends the client from Mainserver sets the MySQL DB connection
    public ClientHandler(Socket client) {
        this.client = client;
        try {
            in = new BufferedReader(new InputStreamReader(client.getInputStream()));
            out = new PrintWriter(client.getOutputStream(), true);
            Class.forName("com.mysql.cj.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "9649726949");
            System.out.println("Connected to MYSQL Database");
            client.setTcpNoDelay(true);
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void run() {
        execute();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            System.out.println("[Server]: Client has disconnected");
        }
    }

    //function that executes various functions according to the messages received.
    private void execute() {
        try {

                message = in.readLine();
                System.out.println(message);


        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
