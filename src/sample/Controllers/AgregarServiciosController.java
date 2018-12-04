package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AgregarServiciosController implements Initializable {
    Controller controller=new Controller();
    User user=null;
    File file;
    FileChooser archivo=new FileChooser();
    boolean cambimagen=false, regiCompany=true;
    FileInputStream fis;
    String type;
    int id_company=0;
    int cantPlan=0;

    @FXML Button btnRegresar, btnCargar, btnGuardar;
    @FXML ComboBox cmbTypeCompany, cmbCommission, cmbQuantity, cmbPlanes, cmbTypeHS;
    @FXML ImageView lblImagen;
    @FXML TextField textNombreCompany, textCantPlan;

    TypeCompanyDAO typeCompanyDAO=new TypeCompanyDAO(MySQL.getConnection());
    CommissionDAO commissionDAO=new CommissionDAO(MySQL.getConnection());
    CompanyDAO companyDAO=new CompanyDAO(MySQL.getConnection());
    PhoneplanDAO phoneplanDAO=new PhoneplanDAO(MySQL.getConnection());
    PlanHSDAO planHSDAO=new PlanHSDAO(MySQL.getConnection());
    TypeHomeServiceDAO typeHomeServiceDAO=new TypeHomeServiceDAO(MySQL.getConnection());

    List<TypeCompany> typeCompanies=new ArrayList<>();
    List<Commission> commissions=new ArrayList<>();
    List<Phoneplan> phoneplans=new ArrayList<>();
    List<PlanHS> planHS=new ArrayList<>();
    List<TypeHomeService> typeHomeServices=new ArrayList<>();
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

        //Agrega los tipos de compañia (Recharge, Home Service, etc)
        typeCompanies=typeCompanyDAO.fetchAll();
        cmbTypeCompany.setPromptText("Tipo de servicio");
        for (int i = 0; i < typeCompanies.size(); i++)
            cmbTypeCompany.getItems().add(typeCompanies.get(i).getTypecompany());

        cmbTypeCompany.setOnAction(setNombreCompany);

        btnCargar.setOnAction(imagen);
        textNombreCompany.setOnMouseClicked(borrar);
        textCantPlan.setOnMouseClicked(borrar);
        btnGuardar.setOnAction(Guardar);
    }

    EventHandler<ActionEvent> setNombreCompany=new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            int a=0;
            btnGuardar.setVisible(true);
            type=cmbTypeCompany.getSelectionModel().getSelectedItem().toString();
            commissions=commissionDAO.fetchAll();
            cmbCommission.setPromptText("Comisiones");
            a=cmbCommission.getItems().size();
            if (a!=0){
                for (int i = a; i > 0; i--) {
                    cmbCommission.getItems().remove(i-1);
                }
            }
            for (int i = 0; i < commissions.size(); i++)
                cmbCommission.getItems().add(commissions.get(i).getPercentage());
            switch (type){
                case "Home Service":
                    textCantPlan.setVisible(false);
                    cmbTypeHS.setVisible(true);
                    cmbQuantity.setVisible(false);
                    cmbPlanes.setVisible(true);
                    planHS=planHSDAO.findAll();
                    cmbPlanes.setPromptText("Planes de servicio");
                    a=cmbPlanes.getItems().size();
                    if (a!=0){
                        for (int i = a; i > 0; i--) {
                            cmbPlanes.getItems().remove(i-1);
                        }
                    }
                    for (int i = 0; i < planHS.size(); i++) {
                        cmbPlanes.getItems().add(planHS.get(i).getQuantity());
                    }
                    a=cmbTypeHS.getItems().size();
                    if (a!=0){
                        for (int i = a; i > 0; i--) {
                            cmbTypeHS.getItems().remove(i-1);
                        }
                    }
                    typeHomeServices=typeHomeServiceDAO.findAll();
                    cmbTypeHS.setPromptText("Tipo de servicio");
                    for (int i = 0; i < typeHomeServices.size(); i++) {
                        cmbTypeHS.getItems().add(typeHomeServices.get(i).getType());
                    }
                    break;
                case "Recharge":
                    textCantPlan.setVisible(true);
                    cmbTypeHS.setVisible(false);
                    cmbQuantity.setVisible(true);
                    cmbPlanes.setVisible(false);
                    phoneplans=phoneplanDAO.findAllSinRepetidos();
                    cmbQuantity.setPromptText("Planes del telefono");
                    a=cmbQuantity.getItems().size();
                    if (a!=0){
                        for (int i = a; i > 0; i--) {
                            cmbQuantity.getItems().remove(i-1);
                        }
                    }
                    for (int i = 0; i < phoneplans.size(); i++) {
                        cmbQuantity.getItems().add(phoneplans.get(i).getQuantity());
                    }
                    break;
            }
        }
    };

    EventHandler<ActionEvent> imagen=new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            file=archivo.showOpenDialog(Main.primaryStage);
            if(file!=null){
                Image image=new Image(file.toURI().toString(),460, 320, true,true);
                lblImagen.setImage(image);
                lblImagen.setFitWidth(460);
                lblImagen.setFitHeight(320);
                lblImagen.setPreserveRatio(true);
                cambimagen=true;
            }
        }
    };

    public boolean agreImagen(InputStream image, File file, String name, int id_commission, int id_typecompany){
        if (companyDAO.insertWhitImagen(image, file, name, id_commission, id_typecompany)) {
            id_company=companyDAO.getId_Company(textNombreCompany.getText());
            if (companyDAO.insertHomeService(typeHomeServices.get(cmbTypeHS.getSelectionModel().getSelectedIndex()).getId_TypeHS(), id_company, planHS.get(cmbPlanes.getSelectionModel().getSelectedIndex()).getId_plan()))
                return true;
        }
        return false;
    }

    EventHandler<MouseEvent> borrar=new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            TextField text=(TextField) event.getSource();
            text.setText("");
        }
    };

    EventHandler<ActionEvent> Guardar=new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Alert a=new Alert(Alert.AlertType.INFORMATION);
            a.setTitle("Agregado");
            boolean bandera=false;
            switch (type){
                case "Home Service":
                    if (cambimagen) {
                        try {
                            fis=new FileInputStream(file);
                            if(agreImagen(fis, file, textNombreCompany.getText(),
                                    commissions.get(cmbCommission.getSelectionModel().getSelectedIndex()).getId_commission(),
                                    typeCompanies.get(cmbTypeCompany.getSelectionModel().getSelectedIndex()).getId_typecompany())){
                                bandera=true;
                            }
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }else{
                        if (companyDAO.insertSN(textNombreCompany.getText(), commissions.get(cmbCommission.getSelectionModel().getSelectedIndex()).getId_commission(), typeCompanies.get(cmbTypeCompany.getSelectionModel().getSelectedIndex()).getId_typecompany())){
                            id_company=companyDAO.getId_Company(textNombreCompany.getText());
                            if (companyDAO.insertHomeService(typeHomeServices.get(cmbTypeHS.getSelectionModel().getSelectedIndex()).getId_TypeHS(), id_company, planHS.get(cmbPlanes.getSelectionModel().getSelectedIndex()).getId_plan()))
                                bandera=true;
                        }
                    }
                    if (bandera){
                        a.setContentText("Servicio Agregado");
                        a.show();
                    }
                    btnGuardar.setVisible(false);
                    break;
                case "Recharge":
                    try {
                        cantPlan=Integer.parseInt(textCantPlan.getText());
                    }catch (Exception e){
                        a.setContentText("Debe proporcionar un numero de planes");
                        a.show();
                    }
                    if(cantPlan!=0){
                        if (regiCompany){
                            if (cambimagen) {
                                try {
                                    fis=new FileInputStream(file);
                                    if (companyDAO.insertWhitImagen(fis, file, textNombreCompany.getText(), commissions.get(cmbCommission.getSelectionModel().getSelectedIndex()).getId_commission(),
                                            typeCompanies.get(cmbTypeCompany.getSelectionModel().getSelectedIndex()).getId_typecompany())) {
                                        bandera=true;
                                    }
                                } catch (FileNotFoundException e) {
                                    e.printStackTrace();
                                }
                            }else {
                                if (companyDAO.insertSN(textNombreCompany.getText(), commissions.get(cmbCommission.getSelectionModel().getSelectedIndex()).getId_commission(), typeCompanies.get(cmbTypeCompany.getSelectionModel().getSelectedIndex()).getId_typecompany())){
                                    bandera=true;
                                }
                            }
                            if (bandera){
                                a.setContentText("Compañia registrada, falta añadir planes\nNumero de planes: "+cantPlan);
                                a.showAndWait();
                            }
                            id_company=companyDAO.getId_Company(textNombreCompany.getText());
                            regiCompany=false;
                        }
                        if (phoneplanDAO.insertPhonePlan(Integer.parseInt(String.valueOf(cmbQuantity.getSelectionModel().getSelectedItem())), id_company)){
                            a.setContentText("Plan registrado");
                            a.showAndWait();
                        }
                        cantPlan--;
                        textCantPlan.setText(""+cantPlan);
                        cmbQuantity.getItems().remove(cmbQuantity.getSelectionModel().getSelectedIndex());
                    }
                    break;
            }
        }
    };

    public void setUser(User user){
        this.user=user;
    }
}
