package sample.Modelos;

public class Giftcartcredit {
    int id_giftcartcredit, credit;

    public Giftcartcredit(int id_giftcartcredit, int credit) {
        this.id_giftcartcredit = id_giftcartcredit;
        this.credit = credit;
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
}
