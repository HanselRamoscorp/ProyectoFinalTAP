package sample.Modelos;

public class TypeUser {
    int id_typeuser;
    String typeuser;

    public TypeUser(int id_typeuser, String typeuser) {
        this.id_typeuser = id_typeuser;
        this.typeuser = typeuser;
    }

    public int getId_typeuser() {
        return id_typeuser;
    }

    public void setId_typeuser(int id_typeuser) {
        this.id_typeuser = id_typeuser;
    }

    public String getTypeuser() {
        return typeuser;
    }

    public void setTypeuser(String typeuser) {
        this.typeuser = typeuser;
    }
}
