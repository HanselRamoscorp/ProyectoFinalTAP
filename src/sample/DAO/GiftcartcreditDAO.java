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

public class GiftcartcreditDAO {

    Connection conn;
    CompanyDAO companyDAO=new CompanyDAO(MySQL.getConnection());

    private static ObservableList<sample.Modelos.Giftcartcredit> data = FXCollections.observableArrayList();

    public GiftcartcreditDAO(Connection conn) { this.conn = conn; }

    public static void addTransaction(sample.Modelos.Giftcartcredit customer)
    {
        data.add(customer);
    }

    public List<sample.Modelos.Giftcartcredit> findAll() {
        List<sample.Modelos.Giftcartcredit> Giftcartcredit = new ArrayList<sample.Modelos.Giftcartcredit>();
        try {
            String query = "SELECT * FROM transaction";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.Giftcartcredit p = null;
            while(rs.next()) {
                p = new sample.Modelos.Giftcartcredit(
                        rs.getInt("id_Giftcartcredite"),
                        rs.getInt("quantity"),
                        companyDAO.fetch(rs.getInt("id_company"))
                );
                Giftcartcredit.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Giftcartcredit;
    }


    public ObservableList<sample.Modelos.Giftcartcredit> fetchGcredit(int id_company) {
        ObservableList<sample.Modelos.Giftcartcredit> Giftcartcredit = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM giftcartcredit where id_company="+id_company;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.Giftcartcredit p = null;
            while(rs.next()) {
                p = new sample.Modelos.Giftcartcredit(
                        rs.getInt("id_giftcartcredite"),
                        rs.getInt("credit"),
                        companyDAO.fetch(rs.getInt("id_company"))
                );
                Giftcartcredit.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Giftcartcredit;
    }

    public sample.Modelos.Giftcartcredit fetch(int id_Giftcartcredit) {
        ResultSet rs = null;
        sample.Modelos.Giftcartcredit e = null;
        try {
            String query = "SELECT * FROM Giftcartcredit where id_Giftcartcredit = " + id_Giftcartcredit;
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            if (rs.first()){
                e = new sample.Modelos.Giftcartcredit(
                        rs.getInt("id_Giftcartcredite"),
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

    public int getId_Giftcartcredit(int quantity, String compañia) {
        ResultSet rs = null;
        int e = 0;
        try {
            String query = "select p.id_Giftcartcredite"+
                    " from Giftcartcredit p inner join company c on p.id_company = c.id_company"+
                    " where p.quantity="+quantity+
                    " and c.name='"+compañia+"'";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            if (rs.first()){
                e = rs.getInt("id_Giftcartcredite");
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

    public Boolean insert(sample.Modelos.Giftcartcredit customer) {
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

    public Boolean update(sample.Modelos.Giftcartcredit customer) {
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
