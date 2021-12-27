package Server;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainServer {
    public static final int PORT = 9806;

    //Thread pool to run multiple clients.
    private static final ExecutorService pool = Executors.newFixedThreadPool(10);

    public static void main(String[] args){
        try{
            //Server socket ready...
            ServerSocket server = new ServerSocket(PORT);
            System.out.println("Server Ready");
            while(true) {
                //waiting for client to connect.
                Socket client = server.accept();
                System.out.println("[SERVER]: Connection established");

                //Initialising an object of client class.
                Runnable newClient = new ClientHandler(client);

                //executing the thread.
                pool.execute(newClient);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }


}
