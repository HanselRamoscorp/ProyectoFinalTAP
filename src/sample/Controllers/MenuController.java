package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML Button btnCerrarSesion, btnServHogar, btnRecargas, btnPagos;
    String direccion;
    Controller controller=new Controller();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnCerrarSesion.setOnAction(event -> {
            Main.primaryStage.show();
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).hide();
        });

        btnServHogar.setOnAction(evt);
        btnRecargas.setOnAction(evt);
        btnPagos.setOnAction(evt);
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
            Stage servicioStage=new Stage();
            servicioStage.setTitle("Servicio");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/Servicio.fxml"));

            ServicioController servicioController = new ServicioController();
            servicioController.setdestino(direccion);
            controller.cargar(loader, servicioController, event);
        }
    };
}
