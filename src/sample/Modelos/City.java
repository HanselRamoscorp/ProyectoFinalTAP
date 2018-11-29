package sample.Modelos;

public class City {
    int id_city;
    String city;

    public City(int id_city, String city) {
        this.id_city = id_city;
        this.city = city;
    }

    public int getId_city() {
        return id_city;
    }

    public void setId_city(int id_city) {
        this.id_city = id_city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
