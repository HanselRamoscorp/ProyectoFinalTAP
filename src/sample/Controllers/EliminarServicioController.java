package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import sample.Complements.MySQL;
import sample.DAO.CompanyDAO;
import sample.DAO.TypeCompanyDAO;
import sample.Modelos.*;

import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class EliminarServicioController implements Initializable {
    Controller controller=new Controller();
    User user;
    String type="";
    int id_typecompany;
    byte bi[] = null;

    @FXML Button btnRegresar, btnEliminar;
    @FXML ComboBox cmbTypeCompany;
    @FXML ImageView lblImagen;
    @FXML TableView tablaCompany;
    @FXML TableColumn clmNombreCompany;

    TypeCompanyDAO typeCompanyDAO=new TypeCompanyDAO(MySQL.getConnection());
    CompanyDAO companyDAO=new CompanyDAO(MySQL.getConnection());

    ObservableList<Company> companies= FXCollections.observableArrayList();
    Company company_select=null;
    List<TypeCompany> typeCompanies=new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnRegresar.setOnAction(event -> {
            Stage servicioStage=new Stage();
            servicioStage.setTitle("Menu Modificar");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/MenuModificar.fxml"));

            MenuModificarController menuModificarController=new MenuModificarController();
            menuModificarController.setUser(user);
            controller.cargar(loader, menuModificarController, event);
        });

        //Agrega los tipos de compañia
        typeCompanies=typeCompanyDAO.fetchAll();
        cmbTypeCompany.setPromptText("Tipo de servicio");
        for (int i = 0; i < typeCompanies.size(); i++) {
            cmbTypeCompany.getItems().add(typeCompanies.get(i).getTypecompany());
        }

        cmbTypeCompany.setOnAction(setNombreCompany);
        tablaCompany.setOnMouseClicked(info);
        btnEliminar.setOnAction(eliminarCompany);
    }

    EventHandler<ActionEvent> setNombreCompany=new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            cargarTabla();
        }
    };

    EventHandler<MouseEvent> info=new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            int id=companies.get(tablaCompany.getSelectionModel().getSelectedIndex()).getId_company();
            company_select=companyDAO.fetch(id);
            try {
                if(company_select.getIs()==null){
                    lblImagen.setVisible(false);
                }else {
                    lblImagen.setVisible(true);
                    bi= company_select.getIs().getBytes(1, (int) company_select.getIs().length());
                    Image image=new Image(new ByteArrayInputStream(bi));
                    lblImagen.setImage(image);
                    lblImagen.setFitWidth(460);
                    lblImagen.setFitHeight(320);
                    lblImagen.setPreserveRatio(true);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    };

    EventHandler<ActionEvent> eliminarCompany=new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            if (companyDAO.delete(company_select.getId_company())){
                a.setContentText("Compañia eliminada");
                a.showAndWait();
            }
            cargarTabla();
        }
    };

    public void cargarTabla(){
        for (int i = 0; i < typeCompanies.size(); i++) {
            if (typeCompanies.get(i).getTypecompany().equals(cmbTypeCompany.getSelectionModel().getSelectedItem())) {
                id_typecompany = typeCompanies.get(i).getId_typecompany();
                break;
            }
        }
        clmNombreCompany.setCellValueFactory(new PropertyValueFactory<Company, String>("name"));
        tablaCompany.setItems(companyDAO.fetchAll(id_typecompany));
        companies=companyDAO.fetchAll(id_typecompany);
    }

    public void setUser(User user){
        this.user=user;
    }
}
