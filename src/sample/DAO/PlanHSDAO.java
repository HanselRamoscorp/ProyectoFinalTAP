package sample.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Modelos.quantity_telephone;

public class PlanHSDAO {

    Connection conn;

    private static ObservableList<sample.Modelos.PlanHS> data = FXCollections.observableArrayList();

    public PlanHSDAO(Connection conn) { this.conn = conn; }

    public static void addTransaction(sample.Modelos.PlanHS customer)
    {
        data.add(customer);
    }

    public List<sample.Modelos.PlanHS> findAll() {
        List<sample.Modelos.PlanHS> PlanHS = new ArrayList<sample.Modelos.PlanHS>();
        try {
            String query = "SELECT * FROM planhs";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.PlanHS p = null;
            while(rs.next()) {
                p = new sample.Modelos.PlanHS(
                        rs.getInt("id_planHS"),
                        rs.getInt("quantity")
                );
                PlanHS.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return PlanHS;
    }


    public ObservableList<sample.Modelos.PlanHS> fetchAll() {
        ObservableList<sample.Modelos.PlanHS> PlanHS = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM planhs";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.PlanHS p = null;
            while(rs.next()) {
                p = new sample.Modelos.PlanHS(
                        rs.getInt("id_planHS"),
                        rs.getInt("quantity")
                );
                PlanHS.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return PlanHS;
    }

    public sample.Modelos.PlanHS fetch(int trans_id) {
        ResultSet rs = null;
        sample.Modelos.PlanHS e = null;
        try {
            String query = "SELECT * FROM planhs where id_planHS = " + trans_id;
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            if (rs.first()){
                e = new sample.Modelos.PlanHS(
                        rs.getInt("id_planHS"),
                        rs.getInt("quantity")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return e;
    }

    public quantity_telephone getQuantityAndPhone(String nameCompany, String references) {
        ResultSet rs = null;
        quantity_telephone e=null;
        try {
            String query = "select p.quantity, c.phonenumber, p2.pay_amount "+
            "from planhs p inner join homeservice h on p.id_planHS = h.id_planHS "+
            "inner join paymenths p2 on h.id_HomeService = p2.id_HomeService "+
            "inner join clienths c on p2.id_clienths = c.id_clientHS "+
            "inner join company c2 on h.id_company = c2.id_company "+
            "where c2.name='"+nameCompany+"' "+
            "and p2.referencesHS='"+references+"'";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            if (rs.first()){
                e = new quantity_telephone(
                        rs.getInt("quantity"),
                        rs.getString("phonenumber"),
                        rs.getInt("pay_amount")
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

    public Boolean insert(sample.Modelos.PlanHS customer) {
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

    public Boolean update(sample.Modelos.PlanHS customer) {
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
