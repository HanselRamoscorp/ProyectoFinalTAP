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

public class PhoneplanDAO {

    Connection conn;
    CompanyDAO companyDAO=new CompanyDAO(MySQL.getConnection());

    private static ObservableList<sample.Modelos.Phoneplan> data = FXCollections.observableArrayList();

    public PhoneplanDAO(Connection conn) { this.conn = conn; }

    public static void addTransaction(sample.Modelos.Phoneplan customer)
    {
        data.add(customer);
    }

    public List<sample.Modelos.Phoneplan> findAll() {
        List<sample.Modelos.Phoneplan> Phoneplan = new ArrayList<sample.Modelos.Phoneplan>();
        try {
            String query = "SELECT * FROM transaction";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.Phoneplan p = null;
            while(rs.next()) {
                p = new sample.Modelos.Phoneplan(
                        rs.getInt("id_phoneplane"),
                        rs.getInt("quantity"),
                        companyDAO.fetch(rs.getInt("id_company"))
                );
                Phoneplan.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Phoneplan;
    }


    public ObservableList<sample.Modelos.Phoneplan> fetchPlanCompany(int id_company) {
        ObservableList<sample.Modelos.Phoneplan> Phoneplan = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM phoneplan where id_company="+id_company;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.Phoneplan p = null;
            while(rs.next()) {
                p = new sample.Modelos.Phoneplan(
                        rs.getInt("id_phoneplane"),
                        rs.getInt("quantity"),
                        companyDAO.fetch(rs.getInt("id_company"))
                );
                Phoneplan.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Phoneplan;
    }

    public sample.Modelos.Phoneplan fetch(int id_phoneplan) {
        ResultSet rs = null;
        sample.Modelos.Phoneplan e = null;
        try {
            String query = "SELECT * FROM phoneplan where id_phoneplan = " + id_phoneplan;
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            if (rs.first()){
                e = new sample.Modelos.Phoneplan(
                        rs.getInt("id_phoneplane"),
                        rs.getInt("quantity"),
                        companyDAO.fetch(rs.getInt("id_company"))
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

    public Boolean insert(sample.Modelos.Phoneplan customer) {
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

    public Boolean update(sample.Modelos.Phoneplan customer) {
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
