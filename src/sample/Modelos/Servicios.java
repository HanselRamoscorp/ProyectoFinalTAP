package sample.Modelos;

public class Servicios {
    int id_servicios, id_compañia;
    String nombre;

    public Servicios(int id_servicios, int id_compañia, String nombre) {
        this.id_servicios = id_servicios;
        this.id_compañia = id_compañia;
        this.nombre = nombre;
    }

    public int getId_servicios() {
        return id_servicios;
    }

    public void setId_servicios(int id_servicios) {
        this.id_servicios = id_servicios;
    }

    public int getId_compañia() {
        return id_compañia;
    }

    public void setId_compañia(int id_compañia) {
        this.id_compañia = id_compañia;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
