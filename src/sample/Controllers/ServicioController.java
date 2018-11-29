package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import sample.Complements.MySQL;
import sample.DAO.CompanyDAO;
import sample.DAO.HomeServiceDAO;
import sample.DAO.PhoneplanDAO;
import sample.DAO.TypeHomeServiceDAO;
import sample.Modelos.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.ResourceBundle;

public class ServicioController implements Initializable {
    Controller controller=new Controller();
    String direccion;
    @FXML ComboBox<String> cmbTipoServicios;
    @FXML Button btnRegresar;
    @FXML TextField textTelefono, textComision, textPago, textNumeReferencia, textConfTelefono;

    @FXML TableView tabla;
    @FXML TableColumn clmCantidad;

    CompanyDAO companyDAO=new CompanyDAO(MySQL.getConnection());
    List<Company> company=new ArrayList<>();
    PhoneplanDAO phoneplanDAO=new PhoneplanDAO(MySQL.getConnection());
    TypeHomeServiceDAO typeHomeServiceDAO=new TypeHomeServiceDAO(MySQL.getConnection());
    List<TypeHomeService> typeHomeServices=new ArrayList<>();
    HomeServiceDAO homeServiceDAO=new HomeServiceDAO(MySQL.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textTelefono.setVisible(false);
        textComision.setVisible(false);
        textPago.setVisible(false);
        textNumeReferencia.setVisible(false);
        textConfTelefono.setVisible(false);
        switch (direccion){
            case "Hogar":
                clmCantidad.setText("Compañia");
                typeHomeServices=typeHomeServiceDAO.findAll();
                cmbTipoServicios.setPromptText("Sevicios del Hogar");
                for (int i = 0; i < typeHomeServices.size(); i++) {
                    cmbTipoServicios.getItems().add(typeHomeServices.get(i).getType());
                }
                textNumeReferencia.setVisible(true);
                textPago.setVisible(true);
                textComision.setVisible(true);
                break;
            case "Recargas":
                clmCantidad.setText("Cantidad");
                company=companyDAO.findAll();
                cmbTipoServicios.setPromptText("Compañia");
                for (int i = 0; i < company.size(); i++) {
                    cmbTipoServicios.getItems().add(company.get(i).getName());
                }
                textComision.setVisible(true);
                textTelefono.setVisible(true);
                textConfTelefono.setVisible(true);
                break;
            case "Pagos":
                cmbTipoServicios.setPromptText("Pagos en linea");
                cmbTipoServicios.getItems().addAll(
                        "X-BOX", "STEAM", "AMAZON", "PLAY STATION", "SPOTIFY"
                );
                textComision.setVisible(true);
                textPago.setVisible(true);
                textNumeReferencia.setVisible(true);
        }
        btnRegresar.setOnAction(event -> {
            controller.mostMenu(event);
        });
        textComision.setEditable(false);
        textNumeReferencia.setOnMouseClicked(event);
        textPago.setOnMouseClicked(event);
        textTelefono.setOnMouseClicked(event);
        textConfTelefono.setOnMouseClicked(event);
        cmbTipoServicios.setOnAction(event1 -> {
            switch (direccion){
                case "Hogar":
                    int id_type=0;
                    for (int i = 0; i < typeHomeServices.size(); i++) {
                        if (typeHomeServices.get(i).getType().equals(cmbTipoServicios.getSelectionModel().getSelectedItem())){
                            id_type=typeHomeServices.get(i).getId_TypeHS();
                            break;
                        }
                    }
                    clmCantidad.setCellValueFactory(new PropertyValueFactory<TablaHomeService, String>("name"));
                    clmCantidad.setPrefWidth(237.5);
                    tabla.setItems(homeServiceDAO.fetch2(id_type));
                    break;
                case "Recargas":
                    int id=0;
                    for (int i = 0; i < company.size(); i++) {
                        if (company.get(i).getName().equals(cmbTipoServicios.getSelectionModel().getSelectedItem())){
                            id=company.get(i).getId_company();
                            break;
                        }
                    }
                    clmCantidad.setCellValueFactory(new PropertyValueFactory<Phoneplan, String>("quantity"));
                    tabla.setItems(phoneplanDAO.fetchPlanCompany(id));
                    break;
                case "Pagos":
            }
        });
    }

    EventHandler<MouseEvent> event=new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            TextField text=(TextField) event.getSource();
            text.setText("");
        }
    };

    public void setdestino(String direccion){
        this.direccion=direccion;
    }
}
