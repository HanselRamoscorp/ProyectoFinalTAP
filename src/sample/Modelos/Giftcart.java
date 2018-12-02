package sample.Modelos;

public class Giftcart {
    int id_giftcart;
    Giftcartcredit id_giftcartcredit;
    String pay_date;

    public Giftcart(int id_giftcart, Giftcartcredit id_giftcartcredit, String pay_date) {
        this.id_giftcart = id_giftcart;
        this.id_giftcartcredit = id_giftcartcredit;
        this.pay_date = pay_date;
    }

    public int getId_giftcart() {
        return id_giftcart;
    }

    public void setId_giftcart(int id_giftcart) {
        this.id_giftcart = id_giftcart;
    }

    public Giftcartcredit getId_giftcartcredit() {
        return id_giftcartcredit;
    }

    public void setId_giftcartcredit(Giftcartcredit id_giftcartcredit) {
        this.id_giftcartcredit = id_giftcartcredit;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }
}

