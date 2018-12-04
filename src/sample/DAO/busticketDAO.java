package sample.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Complements.MySQL;

public class busticketDAO {

    Connection conn;

    private static ObservableList<sample.Modelos.Busticket> data = FXCollections.observableArrayList();

    public busticketDAO(Connection conn) { this.conn = conn; }
   BusDAO busDAO = new BusDAO(MySQL.getConnection());


  /*  public List<sample.Modelos.Busticket> findAll() {
        List<sample.Modelos.Busticket> Busticket = new ArrayList<sample.Modelos.Busticket>();
        try {
            String query = "SELECT * FROM transaction";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.Busticket p = null;
            while(rs.next()) {
                p = new sample.Modelos.Busticket(
                        rs.getInt("id_Busticket"),
                        rs.getString("phonenumber"),
                        phoneplanDAO.fetch(rs.getInt("id_phoneplan"))
                );
                Busticket.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Busticket;
    }


    public ObservableList<sample.Modelos.Busticket> fetchAll() {
        ObservableList<sample.Modelos.Busticket> Busticket = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM transaction";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.Busticket p = null;
            while(rs.next()) {
                p = new sample.Modelos.Busticket(
                        rs.getInt("id_Busticket"),
                        rs.getString("phonenumber"),
                        phoneplanDAO.fetch(rs.getInt("id_phoneplan"))
                );
                Busticket.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Busticket;
    }
    */
  public sample.Modelos.Busticket fetch(int id){
      ResultSet rs = null;
      sample.Modelos.Busticket e = null;
      try {
          String query = "select * "+
                  " from busticket "+
                  " busticket where id_busticket = " + id;
          Statement st = conn.createStatement();
          rs = st.executeQuery(query);
          e = new sample.Modelos.Busticket(
                  rs.getInt("id_busticket"),
                  rs.getString("clientname"),
                  rs.getString("pay_date"),
                  busDAO.fetch(rs.getInt("id_bus"))
          );
      } catch (SQLException ex) {
          ex.printStackTrace();
          System.out.println("Error al recuperar información...");
      }
      return e;
  }

    public int Count() {
        ResultSet rs = null;
        int e = 0;
        try {
            String query = "SELECT count(*) id FROM busticket";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                e  = rs.getInt("id");
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return e;
    }

    public Boolean insert(String name, int id_bus) {
        try {
            String query = "insert into busticket (clientname, pay_date, id_bus)"
                    + " values (?, now(), ?)";
            PreparedStatement st =  conn.prepareStatement(query);
            st.setString(1, name);
            st.setInt(2, id_bus);
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

    public Boolean update(sample.Modelos.Busticket customer) {
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
