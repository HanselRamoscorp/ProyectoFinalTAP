package sample.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Complements.MySQL;

public class GiftcartDAO {

    Connection conn;
    CompanyDAO companyDAO=new CompanyDAO(MySQL.getConnection());

    private static ObservableList<sample.Modelos.Giftcart> data = FXCollections.observableArrayList();

    public GiftcartDAO(Connection conn) { this.conn = conn; }

    public static void addTransaction(sample.Modelos.Giftcart customer)
    {
        data.add(customer);
    }
 /*
    public List<sample.Modelos.Giftcart> findAll() {
        List<sample.Modelos.Giftcart> Giftcart = new ArrayList<sample.Modelos.Giftcart>();
        try {
            String query = "SELECT * FROM transaction";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.Giftcart p = null;
            while(rs.next()) {
                p = new sample.Modelos.Giftcart(
                        rs.getInt("id_Giftcarte"),
                        rs.getInt("quantity"),
                        companyDAO.fetch(rs.getInt("id_company"))
                );
                Giftcart.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Giftcart;
    }


    public ObservableList<sample.Modelos.Giftcart> fetchGcredit(int id_company) {
        ObservableList<sample.Modelos.Giftcart> Giftcart = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM Giftcart where id_company="+id_company;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.Giftcart p = null;
            while(rs.next()) {
                p = new sample.Modelos.Giftcart(
                        rs.getInt("id_Giftcarte"),
                        rs.getInt("credit"),
                        companyDAO.fetch(rs.getInt("id_company"))
                );
                Giftcart.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Giftcart;
    }

    public sample.Modelos.Giftcart fetch(int id_Giftcart) {
        ResultSet rs = null;
        sample.Modelos.Giftcart e = null;
        try {
            String query = "SELECT * FROM Giftcart where id_Giftcart = " + id_Giftcart;
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            if (rs.first()){
                e = new sample.Modelos.Giftcart(
                        rs.getInt("id_Giftcarte"),
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

    public int getId_Giftcart(int quantity, String compañia) {
        ResultSet rs = null;
        int e = 0;
        try {
            String query = "select p.id_Giftcarte"+
                    " from Giftcart p inner join company c on p.id_company = c.id_company"+
                    " where p.quantity="+quantity+
                    " and c.name='"+compañia+"'";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            if (rs.first()){
                e = rs.getInt("id_Giftcarte");
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
*/
 public Boolean insert(int id_giftcartcredit) {
     try {
         String query = "insert into giftcard (id_giftcartcredit, pay_date)"
                 + " values (?, now())";
         PreparedStatement st =  conn.prepareStatement(query);
         st.setInt(1, id_giftcartcredit);
         st.execute();
         return true;
     } catch (Exception e) {
         e.printStackTrace();
         System.out.println(e.getMessage());
     }

     return false;
 }

/*
    public Boolean update(sample.Modelos.Giftcart customer) {
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
