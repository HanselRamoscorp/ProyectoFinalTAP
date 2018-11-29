package sample.DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Complements.MySQL;

public class UserDAO {

    Connection conn;
    StoreDAO storeDAO=new StoreDAO(MySQL.getConnection());
    TypeUserDAO typeUserDAO=new TypeUserDAO(MySQL.getConnection());

    private static ObservableList<sample.Modelos.User> data = FXCollections.observableArrayList();

    public UserDAO(Connection conn) { this.conn = conn; }

    public static void addTransaction(sample.Modelos.User customer)
    {
        data.add(customer);
    }

    public List<sample.Modelos.User> findAll() {
        List<sample.Modelos.User> User = new ArrayList<sample.Modelos.User>();
        try {
            String query = "SELECT * FROM transaction";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.User p = null;
            while(rs.next()) {
                p = new sample.Modelos.User(
                        rs.getInt("id_user"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        storeDAO.fetch(rs.getInt("id_store")),
                        typeUserDAO.fetch(rs.getInt("id_typeuser"))
                );
                User.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return User;
    }


    public ObservableList<sample.Modelos.User> fetchAll() {
        ObservableList<sample.Modelos.User> User = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM transaction";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.User p = null;
            while(rs.next()) {
                p = new sample.Modelos.User(
                        rs.getInt("id_user"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        storeDAO.fetch(rs.getInt("id_store")),
                        typeUserDAO.fetch(rs.getInt("id_typeuser"))
                );
                User.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return User;
    }

    public sample.Modelos.User fetch(int trans_id) {
        ResultSet rs = null;
        sample.Modelos.User e = null;
        try {
            String query = "SELECT * FROM user where id = " + trans_id;
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            if(rs.first()){
                e = new sample.Modelos.User(
                        rs.getInt("id_user"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        storeDAO.fetch(rs.getInt("id_store")),
                        typeUserDAO.fetch(rs.getInt("id_typeuser"))
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return e;
    }

    public sample.Modelos.User valide(String firstname, String password) {
        ResultSet rs = null;
        sample.Modelos.User e = null;
        try {
            String query = "SELECT * FROM user " +
                    "where user_name = '" + firstname + "'" +
                    "and password = '"+password+"'";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            if(rs.first()){
                e = new sample.Modelos.User(
                        rs.getInt("id_user"),
                        rs.getString("user_name"),
                        rs.getString("password"),
                        storeDAO.fetch(rs.getInt("id_store")),
                        typeUserDAO.fetch(rs.getInt("id_typeuser"))
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información..."+ex.getMessage());
        }
        return e;
    }
/*
    public Boolean delete(int trans_id) {
        try {
            String query = "delete from transaction where id = ?";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, trans_id);
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public Boolean insert(sample.Modelos.User customer) {
        try {
            String query = "insert into customer "
                    + " (category, description, date_created, amount, type)"
                    + " values (?, ?, ?, ?, ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, customer.getCategory());
            st.setString(2, customer.getDescription());
            st.setDate(  3, customer.getDate_created());
            st.setDouble(4, customer.getAmount());
            st.setString(5, String.valueOf(customer.getType()));
            st.execute();
            //data.add(customer);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(sample.Modelos.User customer) {
        try {
            String query = "update customer "
                    + " set category = ?, description = ?, date_created = ?, amount = ?, type = ?"
                    + " where id=?";
            System.out.println(query + "updating....");
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(1, customer.getCategory());
            st.setString(2, customer.getDescription());
            st.setDate(  3, customer.getDate_created());
            st.setDouble(4, customer.getAmount());
            st.setString(5, String.valueOf(customer.getType()));
            st.setInt(6, customer.getId());
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }
*/
}
