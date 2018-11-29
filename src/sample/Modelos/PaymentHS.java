package sample.Modelos;

public class PaymentHS {
    int id_paymentHS;
    String pay_date;
    int pay_amount;
    HomeService id_HomeService;
    User id_user;

    public PaymentHS(int id_paymentHS, String pay_date, int pay_amount, HomeService id_HomeService, User id_user) {
        this.id_paymentHS = id_paymentHS;
        this.pay_date = pay_date;
        this.pay_amount = pay_amount;
        this.id_HomeService = id_HomeService;
        this.id_user = id_user;
    }

    public int getId_paymentHS() {
        return id_paymentHS;
    }

    public void setId_paymentHS(int id_paymentHS) {
        this.id_paymentHS = id_paymentHS;
    }

    public String getPay_date() {
        return pay_date;
    }

    public void setPay_date(String pay_date) {
        this.pay_date = pay_date;
    }

    public int getPay_amount() {
        return pay_amount;
    }

    public void setPay_amount(int pay_amount) {
        this.pay_amount = pay_amount;
    }

    public HomeService getId_HomeService() {
        return id_HomeService;
    }

    public void setId_HomeService(HomeService id_HomeService) {
        this.id_HomeService = id_HomeService;
    }

    public User getId_user() {
        return id_user;
    }

    public void setId_user(User id_user) {
        this.id_user = id_user;
    }
}
