package sample.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Complements.MySQL;

public class GiftcartDAO {

    Connection conn;

    private static ObservableList<sample.Modelos.Giftcart> data = FXCollections.observableArrayList();

    public GiftcartDAO(Connection conn) { this.conn = conn; }



    /*  public List<sample.Modelos.Giftcart> findAll() {
          List<sample.Modelos.Giftcart> Giftcart = new ArrayList<sample.Modelos.Giftcart>();
          try {
              String query = "SELECT * FROM transaction";
              Statement st = conn.createStatement();
              ResultSet rs = st.executeQuery(query);
              sample.Modelos.Giftcart p = null;
              while(rs.next()) {
                  p = new sample.Modelos.Giftcart(
                          rs.getInt("id_Giftcart"),
                          rs.getString("phonenumber"),
                          phoneplanDAO.fetch(rs.getInt("id_phoneplan"))
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
  
  
      public ObservableList<sample.Modelos.Giftcart> fetchAll() {
          ObservableList<sample.Modelos.Giftcart> Giftcart = FXCollections.observableArrayList();
          try {
              String query = "SELECT * FROM transaction";
              Statement st = conn.createStatement();
              ResultSet rs = st.executeQuery(query);
              sample.Modelos.Giftcart p = null;
              while(rs.next()) {
                  p = new sample.Modelos.Giftcart(
                          rs.getInt("id_Giftcart"),
                          rs.getString("phonenumber"),
                          phoneplanDAO.fetch(rs.getInt("id_phoneplan"))
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
  
      public sample.Modelos.Giftcart fetch(String trans_id) {
          ResultSet rs = null;
          sample.Modelos.Giftcart e = null;
          try {
              String query = "SELECT * FROM transaction where id = " + trans_id;
              Statement st = conn.createStatement();
              rs = st.executeQuery(query);
              e = new sample.Modelos.Giftcart(
                      rs.getInt("id_Giftcart"),
                      rs.getString("phonenumber"),
                      phoneplanDAO.fetch(rs.getInt("id_phoneplan"))
              );
          } catch (SQLException ex) {
              ex.printStackTrace();
              System.out.println("Error al recuperar información...");
          }
          return e;
      }
  */
    public Boolean insert(int id) {
        try {
            String query = "insert into giftcart (id_giftcartcredit, pay_date)"
                    + " values (?, now())";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setInt(1, id);

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
