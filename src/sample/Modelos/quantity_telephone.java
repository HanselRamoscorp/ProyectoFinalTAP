package sample.Modelos;

public class quantity_telephone {
    int quantity;
    String telephone;
    int pay_amount;

    public quantity_telephone(int quantity, String telephone, int pay_amount) {
        this.quantity = quantity;
        this.telephone = telephone;
        this.pay_amount = pay_amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getTelephone() {
        return telephone;
    }

    public int getPay_amount() {
        return pay_amount;
    }
}
