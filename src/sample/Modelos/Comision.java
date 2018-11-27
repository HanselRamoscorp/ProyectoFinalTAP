package sample.Modelos;

public class Comision {
    int id_commission;
    String typecompany;

    public Comision(int id_commission, String typecompany) {
        this.id_commission = id_commission;
        this.typecompany = typecompany;
    }

    public int getId_commission() {
        return id_commission;
    }

    public void setId_commission(int id_commission) {
        this.id_commission = id_commission;
    }

    public String getTypecompany() {
        return typecompany;
    }

    public void setTypecompany(String typecompany) {
        this.typecompany = typecompany;
    }
}
