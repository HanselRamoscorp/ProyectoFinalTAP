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
    @FXML ComboBox cmbTipoServicios, cmbComission, cmbCantidadPlan, cmbTipeService, cmbTipe;
    @FXML ImageView lblImagen;
    @FXML TextField textName;

    HomeServiceDAO homeServiceDAO=new HomeServiceDAO(MySQL.getConnection());
    TypeHomeServiceDAO typeHomeServiceDAO=new TypeHomeServiceDAO(MySQL.getConnection());
    CompanyDAO companyDAO=new CompanyDAO(MySQL.getConnection());
    TypeCompanyDAO typeCompanyDAO=new TypeCompanyDAO(MySQL.getConnection());
    PlanHSDAO planHSDAO=new PlanHSDAO(MySQL.getConnection());
    CommissionDAO commissionDAO=new CommissionDAO(MySQL.getConnection());
    PhoneplanDAO phoneplanDAO=new PhoneplanDAO(MySQL.getConnection());

    List<TypeHomeService> typeHomeServices=new ArrayList<>();
    List<TypeCompany> typeCompanyes=new ArrayList<>();
    List<PlanHS> planHS=new ArrayList<>();
    List<Company> companies=new ArrayList<>();
    List<Commission> commissions=new ArrayList<>();
    List<Phoneplan> phoneplans=new ArrayList<>();
    List<Phoneplan> phoneplans2=new ArrayList<>();
    ObservableList<TablaHomeService> HomeService = FXCollections.observableArrayList();
    ObservableList<TablaModificar> e = FXCollections.observableArrayList();
    Company comp=null;

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

        //Agrega los tipos de compañia
        typeCompanyes=typeCompanyDAO.fetchAll();
        cmbTipe.setPromptText("Tipo de servicio");
        for (int i = 0; i < typeCompanyes.size(); i++) {
            cmbTipe.getItems().add(typeCompanyes.get(i).getTypecompany());
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
        cmbTipe.setOnAction(comboTipoServicio);

        btnGuardar.setOnAction(event1 -> {
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            switch (cmbTipe.getSelectionModel().getSelectedItem().toString()){
                case "Home Service":
                    if (!textName.getText().equals(e.get(tabla2.getSelectionModel().getSelectedIndex()).getName()))
                        cambNombre="Si";
                    if(cambNombre.equals("Si"))
                        actuNombre(a);
                    if(cambImagen.equals("Si"))
                        actuImagen(a);
                    if (cambComision.equals("Si"))
                        actuComision(a);
                    if (cambPlan.equals("Si"))
                        actuPlan(a);
                    if (cambTipoServicio.equals("Si"))
                        actuTipeService(a);
                    if(!cambTipoServicio.equals("")||!cambPlan.equals("")||!cambComision.equals("")||!cambImagen.equals(""))
                        cargarTabla2();
                    break;
                case "Recharge":

                    break;
            }
        });
    }

    public void actuNombre(Alert a){
        if (companyDAO.update(e.get(tabla2.getSelectionModel().getSelectedIndex()).getId_company().getId_company(), textName.getText())){
            a.setTitle("Registrado");
            a.setContentText("Datos actualizados");
            a.show();
            cargarTabla1("Home Service");
            tabla2.getItems().removeAll();
        }else {
            a.setTitle("Registro invalido");
            a.setContentText("Datos no actualizados");
            a.show();
        }
    }

    public void actuImagen(Alert a){
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
    }

    public void actuComision(Alert a){
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
    }

    public void actuPlan(Alert a){
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
    }

    public void actuTipeService(Alert a){
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

    EventHandler<ActionEvent> comboTipoServicio=new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (typeCompanyes.get(0).getTypecompany().equals(cmbTipe.getSelectionModel().getSelectedItem())){
                //Agrega las compañias de recargas
                int a=cmbTipoServicios.getItems().size();
                if (a!=0){
                    for (int i = a; i > 0; i--) {
                        cmbTipoServicios.getItems().remove(i-1);
                    }
                }
                for (int i = 0; i < typeHomeServices.size(); i++) {
                    cmbTipoServicios.getItems().add(typeHomeServices.get(i).getType());
                }
                cargarTabla1("Recharge");
                tabla.setVisible(false);
                clmCanttidad.setVisible(false);
                clmPorcentaje.setVisible(false);
                clmCompania.setPrefWidth(720);
                tabla2.setPrefWidth(720);
                textName.setVisible(false);
                cmbComission.setVisible(false);
                cmbTipeService.setVisible(false);
            }else{
                if (typeCompanyes.get(1).getTypecompany().equals(cmbTipe.getSelectionModel().getSelectedItem())){
                    //Agrega los tipo de servicio del HomeService
                    tabla.setVisible(true);
                    clmCanttidad.setVisible(true);
                    clmPorcentaje.setVisible(true);
                    clmCompania.setPrefWidth(158);
                    tabla2.setPrefWidth(500);
                    textName.setVisible(true);
                    cmbComission.setVisible(true);
                    cmbTipeService.setVisible(true);

                    typeHomeServices=typeHomeServiceDAO.findAll();
                    cmbTipoServicios.setPromptText("Sevicios del Hogar");
                    cmbTipeService.setPromptText("Tipo de sevicios");
                    int a=cmbTipoServicios.getItems().size();
                    if (a!=0){
                        for (int i = a; i > 0; i--) {
                            cmbTipoServicios.getItems().remove(i-1);
                        }
                    }
                    for (int i = 0; i < typeHomeServices.size(); i++) {
                        cmbTipoServicios.getItems().add(typeHomeServices.get(i).getType());
                    }
                }
            }
        }
    };

    EventHandler<ActionEvent> evt=new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            switch (cmbTipe.getSelectionModel().getSelectedItem().toString()){
                case "Home Service":
                    if (event.getSource().equals(cmbTipoServicios)) {
                        cargarTabla1("Home Service");
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
                    break;
                case "Recharge":
                    if(event.getSource().equals(cmbTipoServicios)){
                        for (int i = 0; i < companies.size(); i++) {
                            if (companies.get(i).getName().equals(cmbTipoServicios.getSelectionModel().getSelectedItem())){
                                id=companies.get(i).getId_company();
                                break;
                            }
                        }
                        clmCompania.setCellValueFactory(new PropertyValueFactory<Phoneplan, String>("quantity"));
                        tabla2.setItems(phoneplanDAO.fetchPlanCompany(id));
                        phoneplans2=phoneplanDAO.fetchPlanCompany(id);
                        phoneplans=phoneplanDAO.findAllSinRepetidos();
                        int a=cmbCantidadPlan.getItems().size();
                        if (a!=0){
                            for (int i = a; i > 0; i--) {
                                cmbCantidadPlan.getItems().remove(i-1);
                            }
                        }
                        for (int i = 0; i < phoneplans.size(); i++) {
                            cmbCantidadPlan.getItems().add(phoneplans.get(i).getQuantity());
                        }
                        //Imagen
                        try {
                            comp=companyDAO.fetch(id);
                            if(comp.getIs()==null){
                                lblImagen.setVisible(false);
                            }else {
                                bi = comp.getIs().getBytes(1, (int) comp.getIs().length());
                                Image image = new Image(new ByteArrayInputStream(bi));
                                lblImagen.setImage(image);
                                lblImagen.setFitWidth(460);
                                lblImagen.setFitHeight(320);
                                lblImagen.setPreserveRatio(true);
                            }
                        }catch (SQLException e1) {
                            e1.printStackTrace();
                        }
                    }
                    break;
            }
        }
    };

    EventHandler<MouseEvent> data=new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            cargarTabla2();
        }
    };

    public void cargarTabla1(String type){
        if(type.equals("Recharge")){
            //Agrega el nombre de las compañias a un combobox ¡NO A UNA TABLA!
            companies=companyDAO.findAll(1);
            cmbTipoServicios.setPromptText("Compañia");
            for (int i = 0; i < companies.size(); i++) {
                cmbTipoServicios.getItems().add(companies.get(i).getName());
            }
        }else{
            if (type.equals("Home Service")){
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
        }
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
            switch(cmbTipe.getSelectionModel().getSelectedItem().toString()){
                case "Home Service":
                    datos = e.get(tabla2.getSelectionModel().getSelectedIndex());
                    try {
                        if(datos.getId_company().getIs()==null){
                            lblImagen.setVisible(false);
                        }else {
                            bi=datos.getId_company().getIs().getBytes(1, (int) datos.getId_company().getIs().length());
                            Image image = new Image(new ByteArrayInputStream(bi));
                            lblImagen.setImage(image);
                            lblImagen.setFitWidth(460);
                            lblImagen.setFitHeight(320);
                            lblImagen.setPreserveRatio(true);
                        }
                        textName.setText(e.get(tabla2.getSelectionModel().getSelectedIndex()).getName());
                        cmbCantidadPlan.getSelectionModel().select(e.get(tabla2.getSelectionModel().getSelectedIndex()).getCantidad());
                        cmbComission.getSelectionModel().select(e.get(tabla2.getSelectionModel().getSelectedIndex()).getId_commission().getPercentage());
                        cmbTipeService.getSelectionModel().select(e.get(tabla2.getSelectionModel().getSelectedIndex()).getId_homeservice().getId_TypeHS().getType());
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    }
                    break;
                case "Recharge":
                    cmbCantidadPlan.getSelectionModel().select(String.valueOf(phoneplans2.get(tabla2.getSelectionModel().getSelectedIndex()).getQuantity()));
                    break;
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
