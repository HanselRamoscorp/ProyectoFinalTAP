package sample.Modelos;

public class Linebus {
    int id_linebus;
    String trip;
    Company id_company;

    public Linebus(int id_linebus, String trip, Company id_company) {
        this.id_linebus = id_linebus;
        this.trip = trip;
        this.id_company = id_company;
    }

    public int getId_linebus() {
        return id_linebus;
    }

    public void setId_linebus(int id_linebus) {
        this.id_linebus = id_linebus;
    }

    public String getTrip() {
        return trip;
    }

    public void setTrip(String trip) {
        this.trip = trip;
    }

    public Company getId_company() {
        return id_company;
    }

    public void setId_company(Company id_company) {
        this.id_company = id_company;
    }
}
