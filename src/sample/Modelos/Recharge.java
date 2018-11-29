package sample.Modelos;

public class Recharge {
    int id_recharge;
    String phonenumber;
    Phoneplan id_phoneplan;

    public Recharge(int id_recharge, String phonenumber, Phoneplan id_phoneplan) {
        this.id_recharge = id_recharge;
        this.phonenumber = phonenumber;
        this.id_phoneplan = id_phoneplan;
    }

    public int getId_recharge() {
        return id_recharge;
    }

    public void setId_recharge(int id_recharge) {
        this.id_recharge = id_recharge;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public Phoneplan getId_phoneplan() {
        return id_phoneplan;
    }

    public void setId_phoneplan(Phoneplan id_phoneplan) {
        this.id_phoneplan = id_phoneplan;
    }
}
