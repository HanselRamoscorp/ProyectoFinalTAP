package sample.Modelos;

public class TablaModificar {
    String name;
    String porcentaje;
    String cantidad;
    Company id_company;
    Commission id_commission;
    HomeService id_homeservice;
    PlanHS id_planhs;

    public TablaModificar(String name, String porcentaje, String cantidad, Company id_company, Commission id_commission, HomeService id_homeservice, PlanHS id_planhs) {
        this.name = name;
        this.porcentaje = porcentaje;
        this.cantidad = cantidad;
        this.id_company = id_company;
        this.id_commission = id_commission;
        this.id_homeservice = id_homeservice;
        this.id_planhs = id_planhs;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(String porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public Company getId_company() {
        return id_company;
    }

    public void setId_company(Company id_company) {
        this.id_company = id_company;
    }

    public Commission getId_commission() {
        return id_commission;
    }

    public void setId_commission(Commission id_commission) {
        this.id_commission = id_commission;
    }

    public HomeService getId_homeservice() {
        return id_homeservice;
    }

    public void setId_homeservice(HomeService id_homeservice) {
        this.id_homeservice = id_homeservice;
    }

    public PlanHS getId_planhs() {
        return id_planhs;
    }

    public void setId_planhs(PlanHS id_planhs) {
        this.id_planhs = id_planhs;
    }
}
