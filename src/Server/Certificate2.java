package Server;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.*;
import java.util.Properties;

public class Certificate2 {
    private Connection connect;
    private int id;

    public Certificate2(Connection connect ,int id) {
        this.connect = connect;
        this.id=id;
    }
    public void generate()
    {

        try {
            String file_name="C:\\Users\\User\\Desktop\\COVAX\\COVAX\\src\\Server\\Certificate.pdf";
            Document document=new Document();
            File file=new File(file_name);
            FileOutputStream fo=new FileOutputStream(file);
            PdfWriter.getInstance(document,fo);
            document.open();
            /*Paragraph para=new Paragraph("Covid Vaccination Certificate");
            document.add(para);*/
            Paragraph par=new Paragraph("");
            document.add(par);
            PdfPTable table=new PdfPTable(2);
            PdfPCell c1=new PdfPCell(new Phrase("Heading"));
            table.addCell(c1);
            PdfPCell c2=new PdfPCell(new Phrase("Details"));
            table.addCell(c2);
            table.setHeaderRows(1);
            String query="SELECT * FROM Covax.members WHERE member_id="+id+";";
            Statement sta = connect.createStatement();
            ResultSet r = sta.executeQuery(query);
            r.next();
            String name=r.getString("member_name");
            String id_proof=r.getString("idProof_no");
            int age=r.getInt("age");
            table.addCell("Member_id");
            table.addCell(r.getString("member_id"));
            int did= Integer.parseInt(r.getString("dose2_id"));
            query="SELECT * FROM Covax.dose2 WHERE dose_id="+did+";";
            sta = connect.createStatement();
            r = sta.executeQuery(query);
            r.next();
            table.addCell("Name");
            table.addCell(name);
            table.addCell("Age");
            table.addCell(String.valueOf(age));
            table.addCell("ID Proof Number");
            table.addCell(id_proof);
            table.addCell("vaccinator_id");
            table.addCell(r.getString("vaccinator_id"));
           /* table.addCell("vaccine_id");
            int i= Integer.parseInt(r.getString("vaccine_id"));
            if(i==1)
                table.addCell("Covishield");
            else
                table.addCell("Covaxin");*/
            table.addCell("center_id");
            table.addCell(r.getString("center_id"));
            table.addCell("Dose");
            table.addCell("Dose 2");
            document.add(table);

            //document.add(Image.getInstance("C:\\Users\\acer\\Desktop\\COVAX\\src\\Resources\\mo.jpg"));
            document.close();
            InputStream io=new FileInputStream(file);
            PreparedStatement stat = connect.prepareStatement("UPDATE Covax.members SET certificate=? where member_id=?");

            stat.setBlob(1,io);
            stat.setInt(2, id);
            stat.execute();
            file.delete();
            query="SELECT * FROM Covax.user WHERE member_id1="+id+" OR member_id2="+id+" OR member_id3="+id+" OR member_id4="+id+";";
            sta = connect.createStatement();
            r = sta.executeQuery(query);
            r.next();
            String em=r.getString("email");
            String nu=r.getString("phone_number");
            if(em==null)
            {
                String message = "Dear "+name+" Congratulations You have successfully been vaccinated with your second dose of vaccine .You may dowload ypur certificate.";
                try {
                    message = URLEncoder.encode(message, "UTF-8");
                    String APIKey = "Xqdyw5bB7svNuHDUj6mnAI1Vz4ZxWCERMcSkp39fQtKY2gOGTLhzcvxqm9bjVMkHJCdKIFAYf3aE1iGQ";
                    String myUrl = "https://www.fast2sms.com/dev/bulkV2?authorization="+APIKey+"&variables_values="+name+"&route=otp&numbers="+nu;
                    URL url = new URL(myUrl);
                    HttpsURLConnection con = (HttpsURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("User-Agent", "Mozzilla/5.0");
                    con.setRequestProperty("cache-control", "no-cache");
                    System.out.println("Wait.....");
                    System.out.print(con.getResponseCode());
                    StringBuffer respose = new StringBuffer();
                    BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

                    while (true) {
                        String line = br.readLine();
                        if (line == null)
                            break;
                        respose.append(line);
                    }
                    System.out.println(respose);


                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else
            {
                String host="smtp.gmail.com";

                //get the system properties
                Properties properties = System.getProperties();
                System.out.println("PROPERTIES "+properties);

                //setting important information to properties object

                //host set
                properties.put("mail.smtp.host", host);
                properties.put("mail.smtp.port","465");
                properties.put("mail.smtp.ssl.enable","true");
                properties.put("mail.smtp.auth","true");

                //Step 1: to get the session object..

                Session session=Session.getInstance(properties, new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {

                        return new PasswordAuthentication("covax2k23@gmail.com","aditiritucovax");
                    }



                });

                session.setDebug(true);

                //Step 2 : compose the message [text,multi media]
                MimeMessage m = new MimeMessage(session);

                try {

                    //from email
                    var ema = "agrawalamrapali@gmail.com" ;
                    m.setFrom();

                    //adding recipient to message
                    m.addRecipient(Message.RecipientType.TO, new InternetAddress(em));

                    //adding subject to message
                    String subject = "This is your OTP" ;
                    m.setSubject(subject);


                    //adding text to message
                    String message = "Dear "+name+" Congratulations You have successfully been vaccinated with your second dose of vaccine .You may dowload ypur certificate.";
                    m.setText(message);

                    //send

                    //Step 3 : send the message using Transport class
                    Transport.send(m);

                    System.out.println("Sent success...................");


                }catch (Exception e) {
                    e.printStackTrace();
                }

            }

        } catch (SQLException | FileNotFoundException | DocumentException throwables) {
            throwables.printStackTrace();
        }

    }
}
