package sample.DAO;

import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CityDAO {

    Connection conn;

    private static ObservableList<sample.Modelos.City> data = FXCollections.observableArrayList();

    public CityDAO(Connection conn) { this.conn = conn; }

    public static void addTransaction(sample.Modelos.City customer)
    {
        data.add(customer);
    }

    public List<sample.Modelos.City> findAll() {
        List<sample.Modelos.City> City = new ArrayList<sample.Modelos.City>();
        try {
            String query = "SELECT * FROM transaction";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.City p = null;
            while(rs.next()) {
                p = new sample.Modelos.City(
                    rs.getInt("id_city"),
                    rs.getString("city")
                );
                City.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return City;
    }


    public ObservableList<sample.Modelos.City> fetchAll() {
        ObservableList<sample.Modelos.City> City = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM transaction";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.City p = null;
            while(rs.next()) {
                p = new sample.Modelos.City(
                        rs.getInt("id_city"),
                        rs.getString("city")
                );
                City.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return City;
    }

    public sample.Modelos.City fetch(int id_city) {
        ResultSet rs = null;
        sample.Modelos.City e = null;
        try {
            String query = "SELECT * FROM transaction where id = " + id_city;
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            e = new sample.Modelos.City(
                    rs.getInt("id_city"),
                    rs.getString("city")
            );
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

    public Boolean insert(sample.Modelos.City customer) {
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

    public Boolean update(sample.Modelos.City customer) {
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
