package sample.Modelos;

public class Company {
    int id_company;
    String name;
    Commission id_commission;
    TypeCompany id_typecompany;

    public Company(int id_company, String name, Commission id_commission, TypeCompany id_typecompany) {
        this.id_company = id_company;
        this.name = name;
        this.id_commission = id_commission;
        this.id_typecompany = id_typecompany;
    }

    public int getId_company() {
        return id_company;
    }

    public void setId_company(int id_company) {
        this.id_company = id_company;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Commission getId_commission() {
        return id_commission;
    }

    public void setId_commission(Commission id_commission) {
        this.id_commission = id_commission;
    }

    public TypeCompany getId_typecompany() {
        return id_typecompany;
    }

    public void setId_typecompany(TypeCompany id_typecompany) {
        this.id_typecompany = id_typecompany;
    }
}
