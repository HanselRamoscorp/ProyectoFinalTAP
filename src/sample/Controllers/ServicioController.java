package sample.Controllers;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class ServicioController implements Initializable {
    Controller controller=new Controller();
    String direccion;
    @FXML ComboBox<String> cmbTipoServicios;
    @FXML Button btnRegresar;
    @FXML TextField textTelefono, textComision, textPago, textNumeReferencia, textConfTelefono;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        textTelefono.setVisible(false);
        textComision.setVisible(false);
        textPago.setVisible(false);
        textNumeReferencia.setVisible(false);
        textConfTelefono.setVisible(false);
        switch (direccion){
            case "Hogar":
                cmbTipoServicios.setPromptText("Sevicios del Hogar");
                cmbTipoServicios.getItems().addAll(
                        "LUZ", "AGUA", "TELEFONO", "GAS", "TELEVISION"
                );
                textNumeReferencia.setVisible(true);
                textPago.setVisible(true);
                textComision.setVisible(true);
                break;
            case "Recargas":
                cmbTipoServicios.setPromptText("Compañia");
                cmbTipoServicios.getItems().addAll(
                        "TELCEL", "MOVISTAR", "UNEFON", "AT&T", "USSACEL"
                );
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
