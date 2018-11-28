package sample.Modelos;

public class Commission {
    int id_commission;
    String percentage ;

    public Commission(int id_commission, String percentage) {
        this.id_commission = id_commission;
        this.percentage = percentage;
    }

    public int getId_commission() {
        return id_commission;
    }

    public void setId_commission(int id_commission) {
        this.id_commission = id_commission;
    }

    public String getPercentage() {
        return percentage;
    }

    public void setPercentage(String percentage) {
        this.percentage = percentage;
    }
}
