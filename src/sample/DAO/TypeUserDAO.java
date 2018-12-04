package sample.DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class TypeUserDAO {

    Connection conn;

    private static ObservableList<sample.Modelos.TypeUser> data = FXCollections.observableArrayList();

    public TypeUserDAO(Connection conn) { this.conn = conn; }

    public static void addTransaction(sample.Modelos.TypeUser customer)
    {
        data.add(customer);
    }

    public List<sample.Modelos.TypeUser> findAll() {
        List<sample.Modelos.TypeUser> TypeUser = new ArrayList<sample.Modelos.TypeUser>();
        try {
            String query = "SELECT * FROM transaction";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.TypeUser p = null;
            while(rs.next()) {
                p = new sample.Modelos.TypeUser(
                        rs.getInt("id_typeuser"),
                        rs.getString("typeuser")
                );
                TypeUser.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return TypeUser;
    }


    public ObservableList<sample.Modelos.TypeUser> fetchAll() {
        ObservableList<sample.Modelos.TypeUser> TypeUser = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM typeuser";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.TypeUser p = null;
            while(rs.next()) {
                p = new sample.Modelos.TypeUser(
                        rs.getInt("id_typeuser"),
                        rs.getString("typeuser")
                );
                TypeUser.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return TypeUser;
    }

    public sample.Modelos.TypeUser fetch(int trans_id) {
        ResultSet rs = null;
        sample.Modelos.TypeUser e = null;
        try {
            String query = "SELECT * FROM typeuser where id_typeuser = " + trans_id;
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            if (rs.first()){
                e = new sample.Modelos.TypeUser(
                        rs.getInt("id_typeuser"),
                        rs.getString("typeuser")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
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

    public Boolean insert(sample.Modelos.TypeUser customer) {
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

    public Boolean update(sample.Modelos.TypeUser customer) {
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
