package sample.Modelos;

public class Store {
    int id_store;
    String name, street, colony, numAddress;
    City id_city;
    States id_state;

    public Store(int id_store, String name, String street, String colony, String numAddress, City id_city, States id_state) {
        this.id_store = id_store;
        this.name = name;
        this.street = street;
        this.colony = colony;
        this.numAddress = numAddress;
        this.id_city = id_city;
        this.id_state = id_state;
    }

    public int getId_store() {
        return id_store;
    }

    public void setId_store(int id_store) {
        this.id_store = id_store;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getColony() {
        return colony;
    }

    public void setColony(String colony) {
        this.colony = colony;
    }

    public String getNumAddress() {
        return numAddress;
    }

    public void setNumAddress(String numAddress) {
        this.numAddress = numAddress;
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
