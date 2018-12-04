package sample.Modelos;

public class TablaConsultar {
    int planes;
    int comision;

    public TablaConsultar(int planes, int comision) {
        this.planes = planes;
        this.comision = comision;
    }

    public int getPlanes() {
        return planes;
    }

    public void setPlanes(int planes) {
        this.planes = planes;
    }

    public int getComision() {
        return comision;
    }

    public void setComision(int comision) {
        this.comision = comision;
    }
}
