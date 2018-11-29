package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class RegiUsuarioController implements Initializable {
    @FXML Button btnRegresar;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnRegresar.setOnAction(event -> {
            Main.primaryStage.show();
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).hide();
        });
    }
}
