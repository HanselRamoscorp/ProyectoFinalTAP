package sample.Modelos;

public class Giftcart {
    int id_giftcart;
    Company id_company;
    Giftcartcredit id_giftcartcredit;

    public Giftcart(int id_giftcart, Company id_company, Giftcartcredit id_giftcartcredit) {
        this.id_giftcart = id_giftcart;
        this.id_company = id_company;
        this.id_giftcartcredit = id_giftcartcredit;
    }

    public int getId_giftcart() {
        return id_giftcart;
    }

    public void setId_giftcart(int id_giftcart) {
        this.id_giftcart = id_giftcart;
    }

    public Company getId_company() {
        return id_company;
    }

    public void setId_company(Company id_company) {
        this.id_company = id_company;
    }

    public Giftcartcredit getId_giftcartcredit() {
        return id_giftcartcredit;
    }

    public void setId_giftcartcredit(Giftcartcredit id_giftcartcredit) {
        this.id_giftcartcredit = id_giftcartcredit;
    }
}
