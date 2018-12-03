package sample.DAO;

import java.io.File;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.Complements.MySQL;
import sample.Modelos.*;

public class CompanyDAO {

    Connection conn;
    CommissionDAO commissionDAO=new CommissionDAO(MySQL.getConnection());
    TypeCompanyDAO typeCompanyDAO=new TypeCompanyDAO(MySQL.getConnection());

    TypeHomeServiceDAO typeHomeServiceDAO=new TypeHomeServiceDAO(MySQL.getConnection());
    PlanHSDAO planHSDAO=new PlanHSDAO(MySQL.getConnection());

    private static ObservableList<sample.Modelos.Company> data = FXCollections.observableArrayList();

    public CompanyDAO(Connection conn) { this.conn = conn; }

    public static void addTransaction(sample.Modelos.Company customer)
    {
        data.add(customer);
    }

    public List<sample.Modelos.Company> findAll(int id_typecompany) {
        List<sample.Modelos.Company> Company = new ArrayList<sample.Modelos.Company>();
        try {
            String query = "SELECT * FROM company where id_typecompany="+id_typecompany;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.Company p = null;
            while(rs.next()) {
                p = new sample.Modelos.Company(
                        rs.getInt("id_company"),
                        rs.getString("name"),
                        commissionDAO.fetch(rs.getInt("id_commission")),
                        typeCompanyDAO.fetch(rs.getInt("id_typecompany")),
                        rs.getBlob("image")
                );
                Company.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Company;
    }

    public ObservableList<sample.Modelos.Company> fetchAll(int id_typecompany) {
        ObservableList<sample.Modelos.Company> Company = FXCollections.observableArrayList();
        try {
            String query = "SELECT * FROM company where id_typecompany = " + id_typecompany;
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            sample.Modelos.Company p = null;
            while(rs.next()) {
                p = new sample.Modelos.Company(
                        rs.getInt("id_company"),
                        rs.getString("name"),
                        commissionDAO.fetch(rs.getInt("id_commission")),
                        typeCompanyDAO.fetch(rs.getInt("id_typecompany")),
                        rs.getBlob("image")
                );
                Company.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return Company;
    }

    public sample.Modelos.Company fetch(int id_company) {
        ResultSet rs = null;
        sample.Modelos.Company e = null;
        try {
            String query = "SELECT * FROM company where id_company = " + id_company;
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            if (rs.next()){
                e = new sample.Modelos.Company(
                        rs.getInt("id_company"),
                        rs.getString("name"),
                        commissionDAO.fetch(rs.getInt("id_commission")),
                        typeCompanyDAO.fetch(rs.getInt("id_typecompany")),
                        rs.getBlob("image")
                );
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return e;
    }

    public ObservableList<TablaModificar> fetchEdit(String name) {
        ObservableList<TablaModificar> e = FXCollections.observableArrayList();
        try {
            String query = "select *"+
            " from company c inner join commission c2 on c.id_commission = c2.id_commission"+
            " inner join homeservice h on c.id_company = h.id_company"+
            " inner join planhs p on h.id_planHS = p.id_planHS"+
            " where c.name='"+name+"'";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            TablaModificar p = null;
            Company c=null;
            Commission cms=null;
            HomeService hs=null;
            PlanHS phs=null;
            TypeCompany typeCompany=null;
            while(rs.next()) {
                p = new TablaModificar(
                        rs.getString("name"),
                        rs.getString("percentage"),
                        rs.getString("quantity"),
                        c=new Company(rs.getInt("id_company"),
                                rs.getString("name"),
                                commissionDAO.fetch(rs.getInt("id_commission")),
                                typeCompany,
                                rs.getBlob("image")),
                        cms=new Commission(rs.getInt("id_commission"),
                                rs.getString("percentage")),
                        hs=new HomeService(rs.getInt("id_HomeService"),
                                typeHomeServiceDAO.fetch(rs.getInt("id_TypeHS")),
                                this.fetch(rs.getInt("id_company")),
                                planHSDAO.fetch(rs.getInt("id_planHS"))),
                        phs=new PlanHS(rs.getInt("id_planHS"),
                                rs.getInt("quantity"))
                );
                e.add(p);
            }
            rs.close();
            st.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return e;
    }

    public Boolean update(int id_company, String nameNew) {
        try {
            String query = "update company "
                    + " set name=?"
                    + " where id_company=?";
            System.out.println(query + "updating....");
            PreparedStatement st =  conn.prepareStatement(query);

            st.setString(1, nameNew);
            st.setInt(2, id_company);
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(int id_company, int id_plan, int id_homeservice) {
        try {
            String query = "update company c inner join homeservice h on c.id_company = h.id_company " +
                    "inner join planhs p on h.id_planHS = p.id_planHS " +
                    "set h.id_planHS=? " +
                    "where h.id_HomeService=? " +
                    "and c.id_company=?";
            System.out.println(query + "updating....");
            PreparedStatement st =  conn.prepareStatement(query);

            st.setInt(1, id_plan);
            st.setInt(2, id_homeservice);
            st.setInt(3, id_company);
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(int id_typeHS, int id_company) {
        try {
            String query = "update company c inner join homeservice h on c.id_company = h.id_company " +
                    "inner join typehomeservice t on h.id_TypeHS = t.id_TypeHS " +
                    "set h.id_TypeHS=? " +
                    "where c.id_company=?";
            System.out.println(query + "updating....");
            PreparedStatement st =  conn.prepareStatement(query);

            st.setInt(1, id_typeHS);
            st.setInt(2, id_company);
            st.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        return false;
    }

    public Boolean update(InputStream img, int id_company, File file) {
        try {
            String query = "update company "
                    + " set image=?"
                    + " where id_company=?";
            System.out.println(query + "updating....");
            PreparedStatement st =  conn.prepareStatement(query);

            st.setBinaryStream(1, (InputStream) img, (int) file.length());
            st.setInt(2, id_company);
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

    public Boolean insert(sample.Modelos.Company customer) {
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
