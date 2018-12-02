package sample.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Complements.MySQL;
import sample.DAO.*;
import sample.Modelos.*;

import java.io.*;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ModificarServicioController implements Initializable {
    Controller controller=new Controller();
    User user=null;
    TablaModificar datos=null;
    int id=0;
    String company_name="", cambTipoServicio="",cambComision="", cambPlan="", cambNombre="", cambImagen="";
    FileChooser archivo=new FileChooser();
    File file1;
    FileInputStream fis;
    byte bi[] = null;

    @FXML Button btnRegresar, btnCargar, btnGuardar;
    @FXML TableView tabla;
    @FXML TableColumn clmCompañia;
    @FXML TableView tabla2;
    @FXML TableColumn clmCompania, clmPorcentaje, clmCanttidad;
    @FXML ComboBox cmbTipoServicios, cmbComission, cmbCantidadPlan, cmbTipeService;
    @FXML ImageView lblImagen;
    @FXML TextField textName;

    HomeServiceDAO homeServiceDAO=new HomeServiceDAO(MySQL.getConnection());
    TypeHomeServiceDAO typeHomeServiceDAO=new TypeHomeServiceDAO(MySQL.getConnection());
    CompanyDAO companyDAO=new CompanyDAO(MySQL.getConnection());
    TypeCompanyDAO typeCompanyDAO=new TypeCompanyDAO(MySQL.getConnection());
    PlanHSDAO planHSDAO=new PlanHSDAO(MySQL.getConnection());
    CommissionDAO commissionDAO=new CommissionDAO(MySQL.getConnection());

    List<TypeHomeService> typeHomeServices=new ArrayList<>();
    List<TypeCompany> typeCompanies=new ArrayList<>();
    List<PlanHS> planHS=new ArrayList<>();
    List<Commission> commissions=new ArrayList<>();
    ObservableList<TablaHomeService> HomeService = FXCollections.observableArrayList();
    ObservableList<TablaModificar> e = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        archivo.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Todas las imagenes","*.png","*.jpg","*.jpeg")
        );
        archivo.setTitle("Seleccionar imagen");
        btnRegresar.setOnAction(event -> {
            Stage servicioStage=new Stage();
            servicioStage.setTitle("Menu Modificar");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/MenuModificar.fxml"));

            MenuModificarController menuModificarController=new MenuModificarController();
            menuModificarController.setUser(user);
            controller.cargar(loader, menuModificarController, event);
        });

        //Agrega el tipo de servicio
        typeHomeServices=typeHomeServiceDAO.findAll();
        cmbTipoServicios.setPromptText("Sevicios del Hogar");
        cmbTipeService.setPromptText("Tipo de sevicios");
        for (int i = 0; i < typeHomeServices.size(); i++) {
            cmbTipoServicios.getItems().add(typeHomeServices.get(i).getType());
            cmbTipeService.getItems().add(typeHomeServices.get(i).getType());
        }

        //Agrega el costo del plan
        planHS=planHSDAO.fetchAll();
        cmbCantidadPlan.setPromptText("Planes");
        for (int i = 0; i < planHS.size(); i++) {
            cmbCantidadPlan.getItems().add(planHS.get(i).getQuantity());
        }

        //Agrega el porcentaje de comision
        commissions=commissionDAO.fetchAll();
        cmbComission.setPromptText("Porcentaje de comision");
        for (int i = 0; i < commissions.size(); i++) {
            cmbComission.getItems().add(commissions.get(i).getPercentage());
        }

        cmbTipoServicios.setOnAction(evt);
        cmbTipeService.setOnAction(evt);
        cmbComission.setOnAction(evt);
        cmbCantidadPlan.setOnAction(evt);

        tabla.setOnMouseClicked(data);
        tabla2.setOnMouseClicked(info);
        btnCargar.setOnAction(event);
        btnGuardar.setOnAction(event1 -> {
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            if (!textName.getText().equals(e.get(tabla2.getSelectionModel().getSelectedIndex()).getName()))
                cambNombre="Si";
            if(cambNombre.equals("Si")){
                if (companyDAO.update(e.get(tabla2.getSelectionModel().getSelectedIndex()).getId_company().getId_company(), textName.getText())){
                    a.setTitle("Registrado");
                    a.setContentText("Datos actualizados");
                    a.show();
                    cargarTabla1();
                }else {
                    a.setTitle("Registro invalido");
                    a.setContentText("Datos no actualizados");
                    a.show();
                }
            }else{
                if(cambImagen.equals("Si")){
                    try {
                        fis=new FileInputStream(file1);
                        if (companyDAO.update(fis, e.get(tabla2.getSelectionModel().getSelectedIndex()).getId_company().getId_company(), file1)){
                            a.setTitle("Registrado");
                            a.setContentText("Datos actualizados");
                            a.show();
                        }else {
                            a.setTitle("Registro invalido");
                            a.setContentText("Datos no actualizados");
                            a.show();
                        }
                    } catch (FileNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }else{
                    if (cambComision.equals("Si")){
                        int id_commission=commissions.get(cmbComission.getSelectionModel().getSelectedIndex()).getId_commission();
                        int id_company=e.get(tabla2.getSelectionModel().getSelectedIndex()).getId_company().getId_company();
                        if (commissionDAO.update(id_commission, id_company)){
                            a.setTitle("Registrado");
                            a.setContentText("Datos actualizados");
                            a.show();
                        }else {
                            a.setTitle("Registro invalido");
                            a.setContentText("Datos no actualizados");
                            a.show();
                        }
                    }else{
                        if (cambPlan.equals("Si")){
                            int id_plan=planHS.get(cmbCantidadPlan.getSelectionModel().getSelectedIndex()).getId_plan();
                            int id_homeservice=e.get(tabla2.getSelectionModel().getSelectedIndex()).getId_homeservice().getId_HomeService();
                            int id_company=e.get(tabla2.getSelectionModel().getSelectedIndex()).getId_company().getId_company();
                            if(companyDAO.update(id_company, id_plan, id_homeservice)){
                                a.setTitle("Registrado");
                                a.setContentText("Datos actualizados");
                                a.show();
                            }else {
                                a.setTitle("Registro invalido");
                                a.setContentText("Datos no actualizados");
                                a.show();
                            }
                        }else{
                            if (cambTipoServicio.equals("Si")){
                                int id_typeHS=typeHomeServices.get(cmbTipeService.getSelectionModel().getSelectedIndex()).getId_TypeHS();
                                int id_company=e.get(tabla2.getSelectionModel().getSelectedIndex()).getId_company().getId_company();
                                if (companyDAO.update(id_typeHS, id_company)){
                                    a.setTitle("Registrado");
                                    a.setContentText("Datos actualizados");
                                    a.show();
                                }else {
                                    a.setTitle("Registro invalido");
                                    a.setContentText("Datos no actualizados");
                                    a.show();
                                }
                            }
                        }
                    }
                }
            }
            if(!cambTipoServicio.equals("")||!cambPlan.equals("")||!cambComision.equals("")||!cambImagen.equals(""))
                cargarTabla2();
        });
    }

    EventHandler<ActionEvent> evt=new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource().equals(cmbTipoServicios)) {
                cargarTabla1();
            }else{
                if (event.getSource().equals(cmbTipeService)){
                    if (!cmbTipoServicios.getSelectionModel().getSelectedItem().equals(e.get(tabla2.getSelectionModel().getSelectedIndex()).getId_homeservice().getId_TypeHS().getType()))
                            cambTipoServicio="Si";
                }else{
                    if (event.getSource().equals(cmbComission)){
                        if (!cmbComission.getSelectionModel().getSelectedItem().equals(e.get(tabla2.getSelectionModel().getSelectedIndex()).getId_commission().getPercentage()))
                            cambComision="Si";
                    }else{
                        if (event.getSource().equals(cmbCantidadPlan)){
                            if (!cmbCantidadPlan.getSelectionModel().getSelectedItem().equals(e.get(tabla2.getSelectionModel().getSelectedIndex()).getCantidad()))
                                cambPlan="Si";
                        }
                    }
                }
            }
        }
    };

    EventHandler<MouseEvent> data=new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            cargarTabla2();
        }
    };

    public void cargarTabla1(){
        for (int i = 0; i < typeHomeServices.size(); i++) {
            if (typeHomeServices.get(i).getType().equals(cmbTipoServicios.getSelectionModel().getSelectedItem())) {
                id = typeHomeServices.get(i).getId_TypeHS();
                break;
            }
        }
        clmCompañia.setCellValueFactory(new PropertyValueFactory<TablaHomeService, String>("name"));
        tabla.setItems(homeServiceDAO.fetch2(id));
        HomeService = homeServiceDAO.fetch2(id);
    }

    public void cargarTabla2(){
        company_name=HomeService.get(tabla.getSelectionModel().getSelectedIndex()).getName();
        e=companyDAO.fetchEdit(company_name);
        clmCompania.setCellValueFactory(new PropertyValueFactory<TablaModificar, String>("name"));
        clmCanttidad.setCellValueFactory(new PropertyValueFactory<TablaModificar, String>("cantidad"));
        clmPorcentaje.setCellValueFactory(new PropertyValueFactory<TablaModificar, String>("porcentaje"));
        tabla2.setItems(companyDAO.fetchEdit(company_name));
    }

    final EventHandler<MouseEvent> info = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            datos = e.get(tabla2.getSelectionModel().getSelectedIndex());
            try {
                bi=datos.getId_company().getIs().getBytes(1, (int) datos.getId_company().getIs().length());
                Image image=new Image(new ByteArrayInputStream(bi));
                lblImagen.setImage(image);
                lblImagen.setFitWidth(460);
                lblImagen.setFitHeight(320);
                lblImagen.setPreserveRatio(true);
                textName.setText(e.get(tabla2.getSelectionModel().getSelectedIndex()).getName());
                cmbCantidadPlan.getSelectionModel().select(e.get(tabla2.getSelectionModel().getSelectedIndex()).getCantidad());
                cmbComission.getSelectionModel().select(e.get(tabla2.getSelectionModel().getSelectedIndex()).getId_commission().getPercentage());
                cmbTipeService.getSelectionModel().select(e.get(tabla2.getSelectionModel().getSelectedIndex()).getId_homeservice().getId_TypeHS().getType());
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    };

    EventHandler<ActionEvent> event=new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            file1=archivo.showOpenDialog(Main.primaryStage);
            if(file1!=null){
                Image image=new Image(file1.toURI().toString(),460, 320, true,true);
                lblImagen.setImage(image);
                lblImagen.setFitWidth(460);
                lblImagen.setFitHeight(320);
                lblImagen.setPreserveRatio(true);
                cambImagen="Si";
            }else cambImagen="";
        }
    };

    public void setUser(User user){
        this.user=user;
    }
}
