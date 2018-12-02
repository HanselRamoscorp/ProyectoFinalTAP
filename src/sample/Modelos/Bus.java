package sample.Modelos;

import javafx.collections.ObservableList;

public class Bus {
    int id_bus;
    String outdate;
    City id_origin;
    City id_destiny;
    Linebus id_linebus;
    int amount;

    public Bus(int id_bus, String outdate, City id_origin, City id_destiny, Linebus id_linebus, int amount) {
        this.id_bus = id_bus;
        this.outdate = outdate;
        this.id_origin = id_origin;
        this.id_destiny = id_destiny;
        this.id_linebus = id_linebus;
        this.amount = amount;
    }

    public int getId_bus() {
        return id_bus;
    }

    public void setId_bus(int id_bus) {
        this.id_bus = id_bus;
    }

    public String getOutdate() {
        return outdate;
    }

    public void setOutdate(String outdate) {
        this.outdate = outdate;
    }

    public City getId_origin() {
        return id_origin;
    }

    public void setId_origin(City id_origin) {
        this.id_origin = id_origin;
    }

    public City getId_destiny() {
        return id_destiny;
    }

    public void setId_destiny(City id_destiny) {
        this.id_destiny = id_destiny;
    }

    public Linebus getId_linebus() {
        return id_linebus;
    }

    public void setId_linebus(Linebus id_linebus) {
        this.id_linebus = id_linebus;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}