package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import sample.Modelos.User;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML Button btnCerrarSesion, btnServHogar, btnRecargas, btnPagos, btnbus, btnModificar;
    String direccion;
    Controller controller=new Controller();
    User user=new User();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(user.getId_typeuser().getTypeuser().equals("Administrador"))
            btnModificar.setVisible(true);
        else
            btnModificar.setVisible(false);
        btnCerrarSesion.setOnAction(event -> {
            Main.primaryStage.show();
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).hide();
        });

        btnServHogar.setOnAction(evt);
        btnRecargas.setOnAction(evt);
        btnPagos.setOnAction(evt);
        btnbus.setOnAction(evt);
        btnModificar.setOnAction(menuModicar);
    }

    EventHandler<ActionEvent> evt=new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if(event.getSource().equals(btnServHogar))
                direccion="Hogar";
            else
                if(event.getSource().equals(btnRecargas))
                    direccion="Recargas";
                else
                    if(event.getSource().equals(btnPagos))
                        direccion="Pagos";
                    else
                        if(event.getSource().equals(btnbus))
                            direccion="Autobus";
            Stage servicioStage=new Stage();
            servicioStage.setTitle("Servicio");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/Servicio.fxml"));

            ServicioController servicioController = new ServicioController();
            servicioController.setdestino(direccion);
            servicioController.setUser(user);
            controller.cargar(loader, servicioController, event);
        }
    };

    EventHandler<ActionEvent> menuModicar=new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Stage servicioStage=new Stage();
            servicioStage.setTitle("Menu Modificar Servicios");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/MenuModificar.fxml"));

            MenuModificarController menuModificarController=new MenuModificarController();
            menuModificarController.setUser(user);
            controller.cargar(loader, menuModificarController, event);
        }
    };

    public void setUser(User user){
        this.user=user;
    }
}
