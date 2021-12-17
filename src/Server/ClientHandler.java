package Server;

import Helper.ConnectionProvider;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.sql.*;

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
            connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/Covax", "root", "9649726949");
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
            do{
                message = in.readLine();
                System.out.println(message);
                if(message.equals("REGISTER"))
                    register();
                if(message.equals("UserDashBoard")) {

                   UserDashBoard();
                }

                if(message.equals("DOWNLOAD"))
                    downloadCertificate();
                if(message.equals("UPLOAD"))
                    uploadMember();
                if(message.equals("govtOfficial"))
                    govtOfficial();
                if(message.equals("DELETE_MEMBER"))
                    memberDelete();
                if(message.equals("USER"))
                    user();
                if(message.equals("DELETE_USER"))
                    userDelete();
                if(message.equals("AddSlot"))
                    AddSlot();
                if(message.equals("LOGOUT")) {
                    System.out.println("Client disconnected");
                    break;
                }
            }while(!message.isEmpty());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void AddSlot() {
        try {
            String phoneno=in.readLine();
            int center= Integer.parseInt(in.readLine());
            int vaccine= Integer.parseInt(in.readLine());
            String dose=in.readLine();
            int age= Integer.parseInt(in.readLine());
            int slot= Integer.parseInt(in.readLine());
            int value= Integer.parseInt(in.readLine());
            Date dt= Date.valueOf(in.readLine());
            if(dose.equals("dose1")) {
                PreparedStatement stat = connect.prepareStatement("INSERT into Covax.slot(vaccinator_id,vaccin_id,dose1,age,datefor,timefor,center) VALUES(?,?,?, ?, ?, ?, ?)");

                stat.setString(1, phoneno);
                stat.setInt(2, vaccine);
                stat.setInt(3, value);
                stat.setInt(4, age);
                stat.setDate(5, dt);
                stat.setInt(6, slot);
                stat.setInt(7, center);
                stat.execute();
            }
            else
            {
                PreparedStatement stat = connect.prepareStatement("INSERT into Covax.slot(vaccinator_id,vaccin_id,dose2,age,datefor,timefor,center) VALUES(?,?,?, ?, ?, ?, ?)");

                stat.setString(1, phoneno);
                stat.setInt(2, vaccine);
                stat.setInt(3, value);
                stat.setInt(4, age);
                stat.setDate(5, dt);
                stat.setInt(6, slot);
                stat.setInt(7, center);
                stat.execute();
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    private void govtOfficial() {
        String phone= null;
        try {
            phone = in.readLine();
            String pass=in.readLine();

            String query="select * from vaccinator where phone_no=? and password=?";
            Connection con= ConnectionProvider.getConnection();
            PreparedStatement pstmt=con.prepareStatement(query);
            pstmt.setString(1, phone);
            pstmt.setString(2, pass);
            ResultSet set=pstmt.executeQuery();
            if(set.next())
            {
                out.println("find");
            }
            else
            {
                out.println("not find");
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }


    }

    private void downloadCertificate() {
        try {
            //receiving the filecode
            String id = in.readLine();

            //getting the file from the server.
            String query = "SELECT * FROM Covax.members WHERE member_id ="+id+";";
            ResultSet rs = connect.createStatement().executeQuery(query);
            rs.next();
            System.out.println(rs.getString("member_name"));

            //relevant response for incorrect code


                        //downloading the file into the downloads section
                        try {
                            Blob blob = (Blob) rs.getBlob("id_proof");

                            InputStream inputStream = blob.getBinaryStream();

                            String home = System.getProperty("user.home");
                            Files.copy(inputStream, Paths.get(home + "\\Downloads\\" + "Certificate"));
                            System.out.println("[SERVER]: File Downloaded successfully");
                            out.println(true);
                        } catch (Exception e) {
                            out.println(false);
                        }




        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void uploadMember() {
        try {
            //Getting the relevant details for uploading files
            String id=in.readLine();
            String fileName = in.readLine();
            String filePath = in.readLine();
            //getting the file
            File file = new File(filePath);
            InputStream inFile = new FileInputStream(file);

            //size in kB
            String name=in.readLine();
            String Idproof=in.readLine();
            String gen=in.readLine();
            int age= Integer.parseInt(in.readLine());


            //Inserting file into DB
            PreparedStatement stat = connect.prepareStatement("INSERT into Covax.members(member_name,idProof_no,gender,age,id_proof) VALUES(?, ?, ?, ?, ?)");
            stat.setString(1, name);
            stat.setString(2, Idproof);
            stat.setString(3, gen);
            stat.setInt(4, age);
            stat.setBlob(5, inFile);
            stat.execute();
            String q="SELECT * FROM Covax.members WHERE idProof_no='"+Idproof+"';";
            Statement st = connect.createStatement();
            ResultSet rs = st.executeQuery(q);
            rs.next();
            int value=rs.getInt("member_id");
            System.out.println(value);
            String query="SELECT * FROM Covax.user WHERE email = '"+id+"' OR phone_number='" + id+"';";
            Statement sta = connect.createStatement();
            ResultSet r = sta.executeQuery(query);
            r.next();
            int mem1=r.getInt("member_id1");
            int mem2=r.getInt("member_id2");
            int mem3=r.getInt("member_id3");
            int mem4=r.getInt("member_id4");
            if(mem1==0)
            {
                query="UPDATE Covax.user SET member_id1= ? where email=? OR phone_number=?";
                stat= connect.prepareStatement(query);
                stat.setInt(1,value);
                stat.setString(2,id);
                stat.setString(3,id);
                stat.execute();
            }
            else if(mem2==0)
            {
                query="UPDATE Covax.user SET member_id2= ? where email=? OR phone_number=?";
                stat= connect.prepareStatement(query);
                stat.setInt(1,value);
                stat.setString(2,id);
                stat.setString(3,id);
                stat.execute();
            }
            else if(mem3==0)
            {
                query="UPDATE Covax.user SET member_id3= ? where email=? OR phone_number=?";
                stat= connect.prepareStatement(query);
                stat.setInt(1,value);
                stat.setString(2,id);
                stat.setString(3,id);
                stat.execute();
            }
            else if(mem4==0)
            {
                query="UPDATE Covax.user SET member_id4= ? where email=? OR phone_number=?";
                stat= connect.prepareStatement(query);
                stat.setInt(1,value);
                stat.setString(2,id);
                stat.setString(3,id);
                stat.execute();
            }


            System.out.println("[SERVER]: File has been uploaded");
            stat.close();




            out.println("FileCode.fxml");
            return;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    private void memberDelete() {

        try {
            String query = "DELETE FROM Covax.members where member_id = ? ";
            PreparedStatement pst = connect.prepareStatement(query);
            //filename sent from client
            int s = Integer.parseInt(in.readLine());
            pst.setInt(1, s);
           pst.execute();
            String id= in.readLine();
            query="UPDATE Covax.user SET member_id1=NULL where member_id1= ? AND (email= ? OR phone_number= ? )";
            PreparedStatement psr = connect.prepareStatement(query);
            psr.setInt(1,s);
            psr.setString(2,id);
            psr.setString(3,id);

            psr.execute();
            query="UPDATE Covax.user SET member_id2=NULL where member_id2= ? AND (email= ? OR phone_number= ? )";
            psr = connect.prepareStatement(query);
            psr.setInt(1,s);
            psr.setString(2,id);
            psr.setString(3,id);

            psr.execute();
            query="UPDATE Covax.user SET member_id3=NULL where member_id3= ? AND (email= ? OR phone_number= ? )";
            psr = connect.prepareStatement(query);
            psr.setInt(1,s);
            psr.setString(2,id);
            psr.setString(3,id);

            psr.execute();
            query="UPDATE Covax.user SET member_id4=NULL where member_id4= ? AND (email= ? OR phone_number= ? )";
            psr = connect.prepareStatement(query);
            psr.setInt(1,s);
            psr.setString(2,id);
            psr.setString(3,id);

            psr.execute();

            System.out.println("done");

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("not done");

        }

    }

    private void UserDashBoard() {
        String choice = null;
        try {
            choice=in.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(choice=="phoneno") {
            try {
                String id = (in.readLine());
                Statement stat = connect.createStatement();
                //executing the query
                ResultSet checkDuplicate = stat.executeQuery("SELECT phone_number FROM Covax.user WHERE phone_number = " + id + ";");
                int m1 = checkDuplicate.getInt("member_id1");
                int m2 = checkDuplicate.getInt("member_id2");
                int m3 = checkDuplicate.getInt("member_id3");
                int m4 = checkDuplicate.getInt("member_id4");
                String query = "SELECT * from Covax.members WHERE member_id=" + m1 + " OR member_id=" + m2 + " OR member_id=" + m3 + " OR member_id=" + m4 + ";";
                ResultSet rs = connect.createStatement().executeQuery(query);
                while ((rs.next())) {

                    out.println("MORE");
                    out.println(rs.getInt("member_id"));
                    out.println(rs.getString("member_name"));
                    out.println(rs.getString("idProof_no"));
                    out.println(rs.getInt("dose1_id"));
                    out.println(rs.getInt("dose2_id"));
                    out.println(rs.getString("vaccine"));


                }
                //when all the details have been sent
                out.println("OVER");


            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }
        }else
        {
            try {
                String id = (in.readLine());
                //System.out.println(id);
                Statement stat = connect.createStatement();
                //executing the query
                ResultSet checkDuplicate = stat.executeQuery("SELECT * FROM Covax.user WHERE email='"+id+"';");
                if(checkDuplicate.next()) {
                    int m1 = checkDuplicate.getInt("member_id1");
                    int m2 = checkDuplicate.getInt("member_id2");
                    int m3 = checkDuplicate.getInt("member_id3");
                    int m4 = checkDuplicate.getInt("member_id4");
                    //System.out.println(m1 );
                    ResultSet rs = connect.createStatement().executeQuery("SELECT * from Covax.members WHERE member_id=" + m1 + " OR member_id=" + m2 + " OR member_id=" + m3 + " OR member_id=" + m4 + ";");
                    while ((rs.next())) {

                        out.println("MORE");
                        out.println(rs.getInt("member_id"));
                        out.println(rs.getString("member_name"));
                        //System.out.println(rs.getString("member_name"));
                        out.println(rs.getString("idProof_no"));
                        out.println(rs.getInt("dose1_id"));
                        out.println(rs.getInt("dose2_id"));
                        out.println(rs.getString("vaccine"));


                    }
                }
                //when all the details have been sent
                out.println("OVER");


            } catch (IOException | SQLException e) {
                e.printStackTrace();
            }

        }

    }



    //Register function for Admin and User
    private void register() {
        try {
            //Receiving all the required fields from client
            int id = Integer.parseInt(in.readLine());
            String name = in.readLine();
            String password = in.readLine();
            String code = in.readLine();

            String resource;

            Statement stat = connect.createStatement();

            //executing the query
            ResultSet checkDuplicate = stat.executeQuery("SELECT id FROM mydb.users_amigos WHERE id = " + id + ";");

            //check for duplicate ID
            if (checkDuplicate.next())
                out.println(false);
            else {
                //executing the query to register
                out.println(true);
                String query1 = "INSERT INTO users_amigos VALUES(?,?,?, ?)";
                PreparedStatement preStat = connect.prepareStatement(query1);
                preStat.setInt(1, id);
                preStat.setString(2, name);
                preStat.setString(3, password);

                //if the registering person is admin, secret value should be correct
                if (code.equals("123")) {
                    preStat.setString(4, "ADMIN");
                    resource = "../Admin/adminlogin.fxml";
                }
                //else will be registered as user.
                else {
                    preStat.setString(4, "USER");
                    resource = "../Login/UserHome.fxml";
                }
                preStat.executeUpdate();
                preStat.close();

                System.out.println("[SERVER]: Successfully registered.");
                out.println(resource);
            }
        } catch(SQLException throwables){
            throwables.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    //login for admin and user.
    private void login(){
        try {
            //receiving the credentials for login
            int id = Integer.parseInt(in.readLine());
            String pass = in.readLine();
            String query="SELECT password, category FROM mydb.users_amigos WHERE id = "+id+";";


            Statement stat = connect.createStatement();
            //executing the query
            ResultSet r = stat.executeQuery(query);
            String dbPass = null, category = null;

            //if some r has been generated, then credentials are correct.
            if(r.next()) {
                out.println(true);
                //check for admin or user
                do {
                    dbPass = r.getString("password");
                    category = r.getString("category");
                } while (r.next());

                if (dbPass.equals(pass)) {

                    if (category.equals("USER"))
                        out.println("UserHome.fxml");
                    else
                        out.println("adminlogin.fxml");

                    System.out.println("[SERVER]: Logged in successfully");
                } else
                    out.println("");
            }
            //if credentials are wrong, relevant response is being sent.
            else
                out.println(false);
        } catch (SQLException | IOException throwables) {
            throwables.printStackTrace();
        }
    }

    //function to generate hash function for file code
    private String getHash(int id, String filename) {
        try {
            MessageDigest md = MessageDigest.getInstance(("SHA"));
            String in = id + filename;
            md.update(in.getBytes());
            byte[] bytes = md.digest();
            StringBuffer sb = new StringBuffer();
            for (byte b : bytes) {
                sb.append(Integer.toHexString(b & 0xff));
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    //upload function
    private void upload(){

        try {
            //Getting the relevant details for uploading files
            String fileName = in.readLine();
            int id = Integer.parseInt(in.readLine());
            String hash = getHash(id, fileName);
            String filePath = in.readLine();
            int downloads = Integer.parseInt(in.readLine());
            String interval = in.readLine();

            //getting the file
            File file = new File(filePath);
            InputStream inFile = new FileInputStream(file);

            //size in kB
            double size = file.length()%1024.0;
            System.out.println(size+" \t "+filePath+"\t"+fileName);
            String type = fileName.substring(fileName.lastIndexOf(".")+1);

            //Inserting file into DB
            PreparedStatement stat = connect.prepareStatement("INSERT into file_server(filename, file, id, hash_code, size_kB, type, download_left, allowed_interval) VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
            stat.setString(1, fileName);
            stat.setBlob(2, inFile);
            stat.setInt(3, id);
            stat.setString(4, hash);
            stat.setDouble(5, size);
            stat.setString(6, type);
            stat.setInt(7, downloads);
            stat.setTime(8, Time.valueOf(interval));
            stat.execute();
            System.out.println("[SERVER]: File has been uploaded");
            stat.close();

            //sending the FileCode back to user.
            out.println(hash);

            out.println("FileCode.fxml");
            return;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    //function for downloading the file based on code generated.
    private void download(){

        try {
            //receiving the filecode
            String fileCode = in.readLine();

            //getting the file from the server.
            String query = "SELECT * FROM mydb.file_server WHERE hash_code ='" + fileCode + "';";
            ResultSet rs = connect.createStatement().executeQuery(query);

            //relevant response for incorrect code
            if (rs.next() == false) {
                out.println("INCORRECT");
            } else {
                do {
                    int left = rs.getInt("download_left");
                    //check if no. of downloads for that file is not over
                    if(left == 0){
                        out.println("Downloads Over");
                        break;
                    }
                    else {
                        //downloading the file into the downloads section
                        try {
                            Blob blob = (Blob) rs.getBlob("file");
                            String filename = rs.getString("filename");
                            String ext = filename.substring(filename.lastIndexOf("."));
                            InputStream inputStream = blob.getBinaryStream();

                            String home = System.getProperty("user.home");
                            Files.copy(inputStream, Paths.get(home + "\\Downloads\\" + rs.getString("filename") + ext));


                            //decrementing the no. of downloads on each successive downloads.
                            String decrement = "UPDATE mydb.file_server SET download_left= download_left - 1 where hash_code = '" + rs.getString("hash_code") + "';";
                            Statement query2 = connect.createStatement();
                            query2.execute(decrement);
                            System.out.println("[SERVER]: File Downloaded successfully");
                            out.println(true);
                        } catch (Exception e) {
                            out.println(false);
                        }
                    }
                }while (rs.next());
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //function to set admin status window
    private void admin() {

        try (ResultSet rs = connect.createStatement().executeQuery("select * from users_amigos,file_server where users_amigos.id=file_server.id")) {
            while ((rs.next())) {

                out.println("MORE");
                out.println(rs.getInt("id"));
                out.println(rs.getString("username"));
                out.println(rs.getString("filename"));
                out.println(rs.getString("type"));
                out.println(rs.getDouble("size_kB"));
                out.println(rs.getInt("download_left"));
                out.println(rs.getTimestamp("created_at"));

            }
            //when all the details have been sent
            out.println("OVER");

        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //function to delete a file
    private void adminDelete() {
        //deleted selected file
        String query = "DELETE FROM file_server where filename = ? ";
        try {
            PreparedStatement pst = connect.prepareStatement(query);
            //filename sent from client
            String s = in.readLine();
            pst.setString(1, s);
            pst.execute();

        } catch (Exception e) {
            System.out.println("not done");

        }
    }

    //function to see files uploaded by that particular user having a unique ID.
    private void user() {
        int id = 0;
        try {
            id = Integer.parseInt(in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ResultSet rs = connect.createStatement().executeQuery("SELECT * FROM file_server where id = " + id + ";")) {
            while ((rs.next())) {

                out.println("MORE");
                out.println(rs.getString("filename"));
                out.println(rs.getString("hash_code"));
                out.println(rs.getString("type"));
                out.println(rs.getDouble("size_kB"));
                out.println(rs.getInt("download_left"));
                out.println(rs.getTimestamp("created_at"));

            }
            //when all the files uploaded by that server have been sent
            out.println("OVER");

        }
        catch (SQLException e) {
            e.printStackTrace();
        }

    }


    //function to delete selected file by User
    private void userDelete() {

        String query = "DELETE FROM file_server where filename = ? ";
        try {
            PreparedStatement pst = connect.prepareStatement(query);
            //file name received  to be deleted from server.
            String s = in.readLine();
            pst.setString(1, s);
            pst.execute();

        } catch (Exception e) {
            System.out.println("not done");

        }
    }


}


