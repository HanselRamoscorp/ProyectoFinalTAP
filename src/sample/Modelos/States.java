package sample.Modelos;

public class States {
    int id_state;
    String state;

    public States(int id_state, String state) {
        this.id_state = id_state;
        this.state = state;
    }

    public int getId_state() {
        return id_state;
    }

    public void setId_state(int id_state) {
        this.id_state = id_state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
