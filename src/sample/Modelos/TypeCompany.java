package sample.Modelos;

public class TypeCompany {
    int id_typecompany;
    String typecompany;

    public TypeCompany(int id_typecompany, String typecompany) {
        this.id_typecompany = id_typecompany;
        this.typecompany = typecompany;
    }

    public int getId_typecompany() {
        return id_typecompany;
    }

    public void setId_typecompany(int id_typecompany) {
        this.id_typecompany = id_typecompany;
    }

    public String getTypecompany() {
        return typecompany;
    }

    public void setTypecompany(String typecompany) {
        this.typecompany = typecompany;
    }
}
