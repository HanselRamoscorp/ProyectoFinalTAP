package sample.Modelos;

public class Phoneplan {
    int id_phoneplan;
    int quantity;
    Company id_company;

    public Phoneplan(int id_phoneplan, int quantity, Company id_company) {
        this.id_phoneplan = id_phoneplan;
        this.quantity = quantity;
        this.id_company = id_company;
    }

    public int getId_phoneplan() {
        return id_phoneplan;
    }

    public void setId_phoneplan(int id_phoneplan) {
        this.id_phoneplan = id_phoneplan;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Company getId_company() {
        return id_company;
    }

    public void setId_company(Company id_company) {
        this.id_company = id_company;
    }
}
