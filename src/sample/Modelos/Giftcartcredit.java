package sample.Modelos;

public class Giftcartcredit {
  int id_giftcartcredit;
  int credit;
  Company id_company;

    public Giftcartcredit(int id_giftcartcredit, int credit, Company id_company) {
        this.id_giftcartcredit = id_giftcartcredit;
        this.credit = credit;
        this.id_company = id_company;
    }

    public int getId_giftcartcredit() {
        return id_giftcartcredit;
    }

    public void setId_giftcartcredit(int id_giftcartcredit) {
        this.id_giftcartcredit = id_giftcartcredit;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public Company getId_company() {
        return id_company;
    }

    public void setId_company(Company id_company) {
        this.id_company = id_company;
    }
}
