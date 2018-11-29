package sample.Modelos;

public class User {
    int id_user;
    String user_name, password;
    Store id_store;
    TypeUser id_typeuser;

    public User() {
    }

    public User(int id_user, String user_name, String password, Store id_store, TypeUser id_typeuser) {
        this.id_user = id_user;
        this.user_name = user_name;
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

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
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
