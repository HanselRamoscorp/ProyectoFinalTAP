package sample.Modelos;

public class Busticket {
    int id_busticket;
    String clientname;
    String pay_date;
    Bus id_bus;

    public Busticket(int id_busticket, String clientname, String pay_date, Bus id_bus) {
        this.id_busticket = id_busticket;
        this.clientname = clientname;
        this.pay_date = pay_date;
        this.id_bus = id_bus;
    }

    public int getId_busticket() {
        return id_busticket;
    }

    public void setId_busticket(int id_busticket) {
        this.id_busticket = id_busticket;
    }

    public String getClientname() {
        return clientname;
    }

    public void setClientname(String clientname) {
        this.clientname = clientname;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    public Bus getId_bus() {
        return id_bus;
    }

    public void setId_bus(Bus id_bus) {
        this.id_bus = id_bus;
    }
}
