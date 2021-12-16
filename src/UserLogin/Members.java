package UserLogin;

import java.sql.Blob;
import java.sql.Date;

public class Members {
    private int Member_ID;
    private String Member_Name;
    private String YOB;
    private int Dose1;
    private int Dose2;
    private String Vaccine;

    public Members(int Member_ID,String Member_Name,String YOB,int Dose1,int Dose2,String Vaccine) {
        this.Member_ID=Member_ID;
        this.Member_Name=Member_Name;
        this.YOB=YOB;
        this.Dose1=Dose1;
        this.Dose2=Dose2;
        this.Vaccine=Vaccine;
    }

    public String getVaccine() {
        return Vaccine;
    }

    public void setVaccine(String vaccine) {
        Vaccine = vaccine;
    }

    public String getYOB() {
        return YOB;
    }

    public void setYOB(String YOB) {
        this.YOB = YOB;
    }

    public int getDose2() {
        return Dose2;
    }

    public void setDose2(int dose2) {
        Dose2 = dose2;
    }

    public int getDose1() {
        return Dose1;
    }

    public void setDose1(int dose1) {
        Dose1 = dose1;
    }

    public String getMember_Name() {
        return Member_Name;
    }

    public void setMember_Name(String member_Name) {
        Member_Name = member_Name;
    }

    public String getyOB() {
        return YOB;
    }

    public void setyOB(String yOB) {
        this.YOB = yOB;
    }

    public int getMember_ID() {
        return Member_ID;
    }

    public void setMember_ID(int member_ID) {
        Member_ID = member_ID;
    }
}
