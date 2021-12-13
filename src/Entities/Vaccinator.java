package Entities;

public class Vaccinator {
    private String phone_id;
    private String password;
    private int vaccinator_id;
    public Vaccinator()
    {

    }
    Vaccinator(String phone_id,String password,int vaccinator_id)
    {
        this.password=password;
        this.phone_id=phone_id;
        this.vaccinator_id=vaccinator_id;

    }
    Vaccinator(String phone_id,String password)
    {
        this.password=password;
        this.phone_id=phone_id;


    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone_id() {
        return phone_id;
    }

    public void setPhone_id(String phone_id) {
        this.phone_id = phone_id;
    }

    public int getVaccinator_id() {
        return vaccinator_id;
    }

    public void setVaccinator_id(int vaccinator_id) {
        this.vaccinator_id = vaccinator_id;
    }
}
