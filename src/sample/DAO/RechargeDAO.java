package sample.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Complements.MySQL;

public class RechargeDAO {

    Connection conn;
    PhoneplanDAO phoneplanDAO=new PhoneplanDAO(MySQL.getConnection());

    private static ObservableList<sample.Modelos.Recharge> data = FXCollections.observableArrayList();

    public RechargeDAO(Connection conn) { this.conn = conn; }

    public static void addTransaction(sample.Modelos.Recharge customer)
    {
        data.add(customer);
    }

    public List<sample.Modelos.Recharge> findAll() {
        List<sample.Modelos.Recharge> Recharge = new ArrayList<sample.Modelos.Recharge>();
        try {
            String query = "SELECT * FROM transaction";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.Recharge p = null;
            while(rs.next()) {
                p = new sample.Modelos.Recharge(
                        rs.getInt("id_recharge"),
                        rs.getString("phonenumber"),
                        phoneplanDAO.fetch(rs.getInt("id_phoneplan"))
                );
                Recharge.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Recharge;
    }


    public ObservableList<sample.Modelos.Recharge> fetchAll() {
        ObservableList<sample.Modelos.Recharge> Recharge = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM transaction";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.Recharge p = null;
            while(rs.next()) {
                p = new sample.Modelos.Recharge(
                        rs.getInt("id_recharge"),
                        rs.getString("phonenumber"),
                        phoneplanDAO.fetch(rs.getInt("id_phoneplan"))
                );
                Recharge.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Recharge;
    }

    public sample.Modelos.Recharge fetch(int id) {
        ResultSet rs = null;
        sample.Modelos.Recharge e = null;
        try {
            String query = "SELECT * FROM recharge where id_recharge = " + id;
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            if (rs.next()){
                e = new sample.Modelos.Recharge(
                        rs.getInt("id_recharge"),
                        rs.getString("phonenumber"),
                        phoneplanDAO.fetch(rs.getInt("id_phoneplane"))
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return e;
    }

    public int count() {
        ResultSet rs = null;
        int e = 0;
        try {
            String query = "SELECT count(*) valor FROM recharge";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            if (rs.next()){
                e = rs.getInt("id_recharge");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return e;
    }

    public Boolean insert(int id, String telefono, int id_plan) {
        try {
            String query = "insert into recharge (id_recharge, phonenumber,id_phoneplane,date_amount)"
                    + " values (?, ?, ?, now())";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(1, id);
            st.setString(2, telefono);
            st.setInt(3, id_plan);
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
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

    public Boolean update(sample.Modelos.Recharge customer) {
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
