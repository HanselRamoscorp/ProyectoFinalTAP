package sample.Modelos;

public class ClientHS {
    int id_clientHS;
    String firstname, lastname, numAddress, phonenumber;
    City id_city;
    States id_state;

    public ClientHS(int id_clientHS, String firstname, String lastname, String numAddress, String phonenumber, City id_city, States id_state) {
        this.id_clientHS = id_clientHS;
        this.firstname = firstname;
        this.lastname = lastname;
        this.numAddress = numAddress;
        this.phonenumber = phonenumber;
        this.id_city = id_city;
        this.id_state = id_state;
    }

    public int getId_clientHS() {
        return id_clientHS;
    }

    public void setId_clientHS(int id_clientHS) {
        this.id_clientHS = id_clientHS;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getNumAddress() {
        return numAddress;
    }

    public void setNumAddress(String numAddress) {
        this.numAddress = numAddress;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public City getId_city() {
        return id_city;
    }

    public void setId_city(City id_city) {
        this.id_city = id_city;
    }

    public States getId_state() {
        return id_state;
    }

    public void setId_state(States id_state) {
        this.id_state = id_state;
    }
}
