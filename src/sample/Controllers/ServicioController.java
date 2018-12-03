package sample.Controllers;

import com.mysql.jdbc.authentication.MysqlClearPasswordPlugin;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import sample.Complements.MySQL;
import sample.DAO.*;
import sample.Modelos.*;

import java.awt.image.BufferStrategy;
import java.io.ByteArrayInputStream;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ServicioController implements Initializable {
    Controller controller=new Controller();
    User user=null;
    String direccion, company_name, referencia;
    int cantPagar=0, pago, id=0, id2=0,id3=0,idbus=0, idc=0;
    String telefono="",nombre;
    byte bi[] = null;

    @FXML ComboBox<String> combobox, combobox2,combobox3;
    @FXML Button btnRegresar, btnAceptar,acceptarbus;
    @FXML TextField TF4, TF1, TF2, TF3, TF5,origenbus,destinobus;
    @FXML Label lblPago;

    @FXML TableView tabla;
    @FXML TableColumn clmCantidad,clmOtros;
    @FXML ImageView lblImagen;
    @FXML Image img;
    @FXML StackPane stPane;

    CompanyDAO companyDAO=new CompanyDAO(MySQL.getConnection());
    CityDAO cityDAO = new CityDAO(MySQL.getConnection());
    PhoneplanDAO phoneplanDAO=new PhoneplanDAO(MySQL.getConnection());
    BusDAO busDAO = new BusDAO(MySQL.getConnection());
    TypeHomeServiceDAO typeHomeServiceDAO=new TypeHomeServiceDAO(MySQL.getConnection());
    HomeServiceDAO homeServiceDAO=new HomeServiceDAO(MySQL.getConnection());
    PlanHSDAO planHSDAO=new PlanHSDAO(MySQL.getConnection());
    PaymentHSDAO paymentHSDAO=new PaymentHSDAO(MySQL.getConnection());
    RechargeDAO rechargeDAO=new RechargeDAO(MySQL.getConnection());
    busticketDAO bustickDAO =new busticketDAO(MySQL.getConnection());
    GiftcartcreditDAO giftcartcreditDAO =new GiftcartcreditDAO(MySQL.getConnection());
    GiftcartDAO giftcartDAO = new GiftcartDAO(MySQL.getConnection());

    ObservableList<TablaHomeService> tablaHomeService=null;
    ObservableList<Phoneplan> p=null;
    ObservableList<Bus> b = null;
    ObservableList<Giftcartcredit> c = null;
    List<Company> company=new ArrayList<>();
    List<City> city =new ArrayList<>();
    List<TypeHomeService> typeHomeServices=new ArrayList<>();
    quantity_telephone e=null;
    Company company_select=null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        TF1.setVisible(false);
        TF2.setVisible(false);
        TF3.setVisible(false);
        TF4.setVisible(false);
        TF5.setVisible(false);
        combobox2.setVisible(false);
        combobox3.setVisible(false);
        acceptarbus.setVisible(false);
        clmOtros.setVisible(false);
        switch (direccion){
            case "Hogar":
                TF1.setText("Comision");
                TF2.setText("Pago");
                TF3.setText("Nuemero de referncia");
                TF4.setText("Telefono");
                TF5.setText("Confirmar telefono");
                clmCantidad.setText("Compañia");
                clmCantidad.setPrefWidth(595);
                company=companyDAO.findAll(2);
                typeHomeServices=typeHomeServiceDAO.findAll();
                combobox.setPromptText("Sevicios del Hogar");
                for (int i = 0; i < typeHomeServices.size(); i++) {
                    combobox.getItems().add(typeHomeServices.get(i).getType());
                }

                TF2.setEditable(false);
                TF1.setVisible(true);
                TF2.setVisible(true);
                TF3.setVisible(true);
                TF4.setVisible(true);
                TF5.setVisible(true);
                break;
            case "Recargas":
                TF1.setText("Comision");
                TF4.setText("Telefono");
                TF5.setText("Confirmar Telefono");
                clmCantidad.setText("Cantidad");
                company=companyDAO.findAll(1);
                combobox.setPromptText("Compañia");
                for (int i = 0; i < company.size(); i++) {
                    combobox.getItems().add(company.get(i).getName());
                }
                TF1.setVisible(true);
                TF4.setVisible(true);
                TF5.setVisible(true);
                break;

            case "Pagos":
                combobox.setPromptText("Pagos en linea");
                TF1.setVisible(true);
                company=companyDAO.fetchAll(4);
                for (int i = 0; i < company.size(); i++) {
                    combobox.getItems().add(company.get(i).getName());

                }
                btnAceptar.setText("PAGAR");




                break;
            case "Autobus":
                company=companyDAO.fetchAll(3);
                for (int i = 0; i < company.size(); i++) {
                combobox.getItems().add(company.get(i).getName());
            }
                city=cityDAO.fetchAll();
                for (int i = 0; i < city.size(); i++) {
                    combobox2.getItems().add(city.get(i).getCity());
                    combobox3.getItems().add(city.get(i).getCity());
                }
                combobox.setPromptText("Empresa");
                combobox2.setPromptText("origen");
                combobox3.setPromptText("Destino");
                clmCantidad.setText("SALIDA");
                clmOtros.setText("COSTO");
                clmOtros.setVisible(true);
                TF1.setVisible(true);
                TF5.setText("Nombre del Cliente");
                TF5.setVisible(true);
                combobox2.setVisible(true);
                combobox3.setVisible(true);
                acceptarbus.setVisible(true);
                btnAceptar.setText("PAGAR");
        }

        acceptarbus.setOnAction(event ->{
            for (int i = 0; i < company.size(); i++) {
                if (company.get(i).getName().equals(combobox.getSelectionModel().getSelectedItem())){
                    id=company.get(i).getId_company();
                    break;
                }
            }
            for (int i = 0; i < city.size(); i++) {
                if (city.get(i).getCity().equals(combobox2.getSelectionModel().getSelectedItem())){
                    id2=city.get(i).getId_city();
                    break;
                }
            }
            for (int i = 0; i < city.size(); i++) {
                if (city.get(i).getCity().equals(combobox3.getSelectionModel().getSelectedItem())){
                    id3=city.get(i).getId_city();
                    break;
                }
            }


            clmCantidad.setCellValueFactory(new PropertyValueFactory<Bus, String>("outdate"));
            clmOtros.setCellValueFactory(new PropertyValueFactory<Bus, String>("amount"));

            tabla.setItems(busDAO.fetch2(id,id2,id3));
            b=busDAO.fetch2(id,id2,id3);





        } );

        btnRegresar.setOnAction(event -> {
            controller.mostMenu(event, user);
        });

        TF1.setEditable(false);
        TF3.setOnMouseClicked(event);
        TF2.setOnMouseClicked(event);
        TF4.setOnMouseClicked(event);
        TF5.setOnMouseClicked(event);

        combobox.setOnAction(event1 -> {
            switch (direccion){
                case "Hogar":
                    int id_type=0;
                    for (int i = 0; i < typeHomeServices.size(); i++) {
                        if (typeHomeServices.get(i).getType().equals(combobox.getSelectionModel().getSelectedItem())){
                            id_type=typeHomeServices.get(i).getId_TypeHS();
                            break;
                        }
                    }
                    clmOtros.setVisible(false);
                    clmCantidad.setCellValueFactory(new PropertyValueFactory<TablaHomeService, String>("name"));
                    tabla.setItems(homeServiceDAO.fetch2(id_type));
                    tablaHomeService=homeServiceDAO.fetch2(id_type);
                    break;
                case "Recargas":
                    for (int i = 0; i < company.size(); i++) {
                        if (company.get(i).getName().equals(combobox.getSelectionModel().getSelectedItem())){
                            id=company.get(i).getId_company();
                            break;
                        }
                    }
                    clmCantidad.setCellValueFactory(new PropertyValueFactory<Phoneplan, String>("quantity"));
                    tabla.setItems(phoneplanDAO.fetchPlanCompany(id));
                    p=phoneplanDAO.fetchPlanCompany(id);
                    break;

                case "Pagos":
                    for (int i = 0; i < company.size(); i++) {
                        if (company.get(i).getName().equals(combobox.getSelectionModel().getSelectedItem())){
                            id=company.get(i).getId_company();
                            break;
                        }
                    }
                    clmCantidad.setCellValueFactory(new PropertyValueFactory<Phoneplan, String>("credit"));
                    tabla.setItems(giftcartcreditDAO.fetchGcredit(id));
                    c=giftcartcreditDAO.fetchGcredit(id);
            }
        });

        tabla.setOnMouseClicked(data);
        btnAceptar.setOnAction(Aceptar);
        TF3.setOnKeyPressed(info);
    }

    EventHandler<KeyEvent> info=new EventHandler<KeyEvent>() {
        @Override
        public void handle(KeyEvent event) {
            if (event.getCode().getName().equals("Enter")){
                if (direccion.equals("Hogar")) {
                    referencia = TF3.getText();
                    company_name = tablaHomeService.get(tabla.getSelectionModel().getSelectedIndex()).getName();
                    e = planHSDAO.getQuantityAndPhone(company_name, referencia);
                    Alert a = new Alert(Alert.AlertType.CONFIRMATION);
                    if (e != null) {
                        if (e.getPay_amount() != 0) {
                            lblPago.setText("Cuenta de usuario pagada");
                            lblPago.setVisible(true);
                        } else {
                            cantPagar = e.getQuantity();
                            telefono = e.getTelephone();
                            a.setTitle("Confirmar");
                            a.setContentText("Datos registrados");
                            a.show();
                            TF2.setEditable(true);
                        }
                    } else {
                        a.setTitle("Error");
                        a.setContentText("Numero de referencia o empresa invalido");
                        a.show();
                    }
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
                        if(TF4.getText().equals(TF5.getText())){
                            pago=Integer.parseInt(TF2.getText());
                            if (pago>=cantPagar && telefono.equals(TF4.getText())) {
                                if (TF4.getText().equals(e.getTelephone())){
                                    pago-=cantPagar;
                                    if (paymentHSDAO.update(cantPagar, pago, referencia))
                                        lblPago.setVisible(true);
                                }else{
                                    lblPago.setText("Numero de telefono invalido");
                                    lblPago.setVisible(true);
                                }
                            }
                            else{
                                lblPago.setText("Pago insuficiente");
                                lblPago.setVisible(true);
                            }
                        }else{
                            lblPago.setText("Los telefonos no coinciden");
                            lblPago.setVisible(true);
                        }
                        break;
                    case "Recargas":
                        if (TF4.getText().equals(TF5.getText())){
                            telefono=TF4.getText();
                            cantPagar=p.get(tabla.getSelectionModel().getSelectedIndex()).getQuantity();
                            company_name=combobox.getSelectionModel().getSelectedItem();
                            id=phoneplanDAO.getId_phoneplan(cantPagar,company_name);
                            if (rechargeDAO.insert(telefono, id)){
                                lblPago.setText("Recarga registrada");
                                lblPago.setVisible(true);
                            }else{
                                lblPago.setText("Recarga invalida");
                                lblPago.setVisible(true);
                            }
                        }else{
                            lblPago.setText("Los numero no coinciden");
                            lblPago.setVisible(true);
                        }
                        break;
                    case "Autobus":
                        idbus =b.get(tabla.getSelectionModel().getSelectedIndex()).getId_bus();
                        nombre = TF5.getText();
                        bustickDAO.insert(nombre,idbus);
                        lblPago.setText("Boleto registrado");
                        lblPago.setVisible(true);
                        break;
                    case "Pagos":
                       idc= c.get(tabla.getSelectionModel().getSelectedIndex()).getId_giftcartcredit();
                       giftcartDAO.insert(idc);
                        lblPago.setText("Pago registrado");
                        lblPago.setVisible(true);
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
            if (direccion.equals("Hogar")){
                try {
                    for (int i = 0; i < company.size(); i++) {
                        if (company.get(i).getName().equals(tablaHomeService.get(tabla.getSelectionModel().getSelectedIndex()).getName())){
                            id=company.get(i).getId_company();
                            break;
                        }
                    }
                    company_select=companyDAO.fetch(id);
                    bi= company_select.getIs().getBytes(1, (int) company_select.getIs().length());
                    Image image=new Image(new ByteArrayInputStream(bi));
                    lblImagen.setImage(image);
                    lblImagen.setFitWidth(460);
                    lblImagen.setFitHeight(320);
                    lblImagen.setPreserveRatio(true);

                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
        }
    };

    public void setdestino(String direccion){
        this.direccion=direccion;
    }
    public void setUser(User user){
        this.user=user;
    }
}
