package sample.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Complements.MySQL;

public class PaymentHSDAO {

    Connection conn;
    HomeServiceDAO homeServiceDAO=new HomeServiceDAO(MySQL.getConnection());
    UserDAO userDAO=new UserDAO(MySQL.getConnection());

    private static ObservableList<sample.Modelos.PaymentHS> data = FXCollections.observableArrayList();

    public PaymentHSDAO(Connection conn) { this.conn = conn; }

    public static void addTransaction(sample.Modelos.PaymentHS customer)
    {
        data.add(customer);
    }

    public List<sample.Modelos.PaymentHS> findAll() {
        List<sample.Modelos.PaymentHS> PaymentHS = new ArrayList<sample.Modelos.PaymentHS>();
        try {
            String query = "SELECT * FROM transaction";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.PaymentHS p = null;
            while(rs.next()) {
                p = new sample.Modelos.PaymentHS(
                        rs.getInt("id_paymentHS"),
                        rs.getString("pay_date"),
                        rs.getInt("pay_amount"),
                        homeServiceDAO.fetch(rs.getInt("id_HomeService")),
                        userDAO.fetch(rs.getInt("id_user"))
                );
                PaymentHS.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return PaymentHS;
    }


    public ObservableList<sample.Modelos.PaymentHS> fetchAll() {
        ObservableList<sample.Modelos.PaymentHS> PaymentHS = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM transaction";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.PaymentHS p = null;
            while(rs.next()) {
                p = new sample.Modelos.PaymentHS(
                        rs.getInt("id_paymentHS"),
                        rs.getString("pay_date"),
                        rs.getInt("pay_amount"),
                        homeServiceDAO.fetch(rs.getInt("id_HomeService")),
                        userDAO.fetch(rs.getInt("id_user"))
                );
                PaymentHS.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return PaymentHS;
    }

    public sample.Modelos.PaymentHS fetch(String referenceshs) {
        ResultSet rs = null;
        sample.Modelos.PaymentHS e = null;
        try {
            String query = "SELECT * FROM paymenths where referenceshs = " + referenceshs;
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            e = new sample.Modelos.PaymentHS(
                    rs.getInt("id_paymentHS"),
                    rs.getString("pay_date"),
                    rs.getInt("pay_amount"),
                    homeServiceDAO.fetch(rs.getInt("id_HomeService")),
                    userDAO.fetch(rs.getInt("id_user"))
            );
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return e;
    }

    public Boolean update(int pago, int cuenta, String referencia) {
        try {
            String query = "update paymentHS "
                    + " set pay_amount=?, cuenta=?"
                    + " where referencesHS=?";
            System.out.println(query + "updating....");
            PreparedStatement st =  conn.prepareStatement(query);

            st.setInt(1, pago);
            st.setInt(2, cuenta);
            st.setString(3, referencia);
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

    public Boolean insert(sample.Modelos.PaymentHS customer) {
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
*/
}
