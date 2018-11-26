package sample.Clases;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ServicioController implements Initializable {
    Controller controller=new Controller();
    String direccion;
    @FXML ComboBox<String> cmbTipoServicios;
    @FXML Button btnRegresar;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        switch (direccion){
            case "Hogar":
                cmbTipoServicios.setPromptText("Sevicios del Hogar");
                cmbTipoServicios.getItems().addAll(
                        "LUZ", "AGUA", "TELEFONO", "GAS", "TELEVISION"
                );
                break;
            case "Recargas":
                cmbTipoServicios.setPromptText("Compañia");
                cmbTipoServicios.getItems().addAll(
                        "TELCEL", "MOVISTAR", "UNEFON", "AT&T", "USSACEL"
                );
                break;
            case "Pagos":
                cmbTipoServicios.setPromptText("Pagos en linea");
                cmbTipoServicios.getItems().addAll(
                        "X-BOX", "STEAM", "AMAZON", "PLAY STATION", "SPOTIFY"
                );
        }
        btnRegresar.setOnAction(event -> {
            controller.mostMenu(event);
        });
    }


    public void setdestino(String direccion){
        this.direccion=direccion;
    }
}
