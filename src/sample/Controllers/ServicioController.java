package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import sample.Complements.MySQL;
import sample.DAO.*;
import sample.Modelos.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ServicioController implements Initializable {
    Controller controller=new Controller();
    String direccion, company_name;
    int cantPagar=0;

    @FXML ComboBox<String> cmbTipoServicios;
    @FXML Button btnRegresar, btnAceptar;
    @FXML TextField textTelefono, textComision, textPago, textNumeReferencia, textConfTelefono;

    @FXML TableView tabla;
    @FXML TableColumn clmCantidad;

    CompanyDAO companyDAO=new CompanyDAO(MySQL.getConnection());
    PhoneplanDAO phoneplanDAO=new PhoneplanDAO(MySQL.getConnection());
    TypeHomeServiceDAO typeHomeServiceDAO=new TypeHomeServiceDAO(MySQL.getConnection());
    HomeServiceDAO homeServiceDAO=new HomeServiceDAO(MySQL.getConnection());
    PlanHSDAO planHSDAO=new PlanHSDAO(MySQL.getConnection());

    ObservableList<TablaHomeService> tablaHomeService=null;
    List<Company> company=new ArrayList<>();
    List<TypeHomeService> typeHomeServices=new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textComision.setVisible(false);
        textPago.setVisible(false);
        textNumeReferencia.setVisible(false);
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
                textPago.setEditable(false);
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
                    tabla.setItems(homeServiceDAO.fetch2(id_type));
                    tablaHomeService=homeServiceDAO.fetch2(id_type);
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

        //tabla.setOnMouseClicked(data);
        btnAceptar.setOnAction(Aceptar);
        textNumeReferencia.setOnKeyPressed(info);
    }

    EventHandler<KeyEvent> info=new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode().getName().equals("Enter")){
                switch (direccion){
                    case "Hogar":
                        String referencia=textNumeReferencia.getText();
                        company_name=tablaHomeService.get(tabla.getSelectionModel().getSelectedIndex()).getName();
                        cantPagar=planHSDAO.getQuantity(company_name, referencia);
                        Alert a=new Alert(Alert.AlertType.CONFIRMATION);
                        if (cantPagar!=0){
                            a.setTitle("Confirmar");
                            a.setContentText("Datos registrados");
                            a.show();
                            textPago.setEditable(true);
                        }else{
                            a.setTitle("Error");
                            a.setContentText("Error al registrar");
                            a.show();
                        }
                        break;
                    case "Recargas":
                        break;
                    case "Pagos":
                }
            }
        }
    };

    EventHandler<ActionEvent> Aceptar=new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            try {
                switch (direccion) {
                    case "Hogar":
                        textNumeReferencia.getText();
                        textPago.getText();
                        break;
                    case "Recargas":
                        break;
                    case "Pagos":
                }
            }catch (Exception e){
                System.out.println(e);
            }
        }
    };

    EventHandler<MouseEvent> event=new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            TextField text=(TextField) event.getSource();
            text.setText("");
        }
    };

    EventHandler<MouseEvent> data=new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            company_name=tablaHomeService.get(tabla.getSelectionModel().getSelectedIndex()).getName();
        }
    };

    public void setdestino(String direccion){
        this.direccion=direccion;
    }
}
