package sample.Modelos;

public class PlanHS {
    int id_plan, quantity;

    public PlanHS(int id_plan, int quantity) {
        this.id_plan = id_plan;
        this.quantity = quantity;
    }

    public int getId_plan() {
        return id_plan;
    }

    public void setId_plan(int id_plan) {
        this.id_plan = id_plan;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
