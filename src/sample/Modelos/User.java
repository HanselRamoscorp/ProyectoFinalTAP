package sample.Modelos;

public class User {
    int id_user;
    String firstname, lastname, password;
    Store id_store;
    TypeUser id_typeuser;

    public User(int id_user, String firstname, String lastname, String password, Store id_store, TypeUser id_typeuser) {
        this.id_user = id_user;
        this.firstname = firstname;
        this.lastname = lastname;
        this.password = password;
        this.id_store = id_store;
        this.id_typeuser = id_typeuser;
    }

    public int getId_user() {
        return id_user;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Store getId_store() {
        return id_store;
    }

    public void setId_store(Store id_store) {
        this.id_store = id_store;
    }

    public TypeUser getId_typeuser() {
        return id_typeuser;
    }

    public void setId_typeuser(TypeUser id_typeuser) {
        this.id_typeuser = id_typeuser;
    }
}
