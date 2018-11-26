package sample.Clases;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MenuController implements Initializable {
    @FXML Button btnCerrarSesion, btnServHogar, btnRecargas, btnPagos;
    String direccion;
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
            try {
                Stage invoiceStage=new Stage();
                invoiceStage.setTitle("Servicio");
                Parent root= null;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/Servicio.fxml"));

                ServicioController servicioController = new ServicioController();
                servicioController.setdestino(direccion);
                loader.setController(servicioController);
                root=loader.load();
                Scene scene=new Scene(root);
                scene.getStylesheets().add("/sample/Complements/DarkTheme2.css");
                invoiceStage.setScene(scene);
                invoiceStage.setMaximized(true);
                invoiceStage.show();
                ((Stage)(((Button) event.getSource()).getScene().getWindow())).hide();
            }catch (IOException e ){
                e.printStackTrace();
            }
        }
    };
}
