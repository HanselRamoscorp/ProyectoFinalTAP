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
import sample.Modelos.Linebus;

public class BusDAO {

    Connection conn;
    CompanyDAO companyDAO=new CompanyDAO(MySQL.getConnection());
    CityDAO  cityDAO = new CityDAO(MySQL.getConnection());
    LinebusDAO linebusDAO = new LinebusDAO(MySQL.getConnection());
    private static ObservableList<sample.Modelos.Bus> data = FXCollections.observableArrayList();

    public BusDAO(Connection conn) { this.conn = conn; }

    public static void addTransaction(sample.Modelos.Bus customer)
    {
        data.add(customer);
    }


    public ObservableList<sample.Modelos.Bus> fetchAll(int id_bus) {
        ObservableList<sample.Modelos.Bus> Bus = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM bus where id_bus = " + id_bus;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.Bus p = null;
            while(rs.next()) {
                p = new sample.Modelos.Bus(
                        rs.getInt("id_bus"),
                        rs.getString("outdate"),
                        cityDAO.fetch(rs.getInt("id_origin")),
                                cityDAO.fetch(rs.getInt("id_destiny")),
                      linebusDAO.fetch2(rs.getInt("id_linebus")),
                                rs.getInt("amount")
                        );
                Bus.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Bus;
    }

    public ObservableList<sample.Modelos.Bus> fetch2(int id_company,int id_origin,int id_destiny) {     // fetch con id_company
        ObservableList<sample.Modelos.Bus> Bus = FXCollections.observableArrayList();
        try {
            String query = "select b.* "+
                    " from bus b inner join linebus l on b.id_linebus = l.id_linebus" +
                    " inner join company c on l.id_company = c.id_company " +
                    " where l.id_company = "+ id_company +" and b.id_origin = "+ id_origin + " and b.id_destiny = "+ id_destiny;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.Bus p = null;
            while(rs.next()) {
                p = new sample.Modelos.Bus(
                        rs.getInt("id_bus"),
                        rs.getString("outdate"),
                        cityDAO.fetch(rs.getInt("id_origin")),
                        cityDAO.fetch(rs.getInt("id_destiny")),
                        linebusDAO.fetch2(rs.getInt("id_linebus")),
                        rs.getInt("amount")
                );
                Bus.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Bus;
    }

    public List<sample.Modelos.TypeCompany> findAll() {
        List<sample.Modelos.TypeCompany> TypeCompany = new ArrayList<sample.Modelos.TypeCompany>();
        try {
            String query = "SELECT * FROM transaction";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.TypeCompany p = null;
            while(rs.next()) {
                p = new sample.Modelos.TypeCompany(
                        rs.getInt("id_typecompany"),
                        rs.getString("typecompany")
                );
                TypeCompany.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return TypeCompany;
    }

}