package sample.Clases;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML TextField textNombre;
    @FXML PasswordField textContrasena;
    @FXML Button btnInicSesion, btnRegistrarse;
    @FXML Label lblContIncorrecta;
    Stage menuStage=new Stage();
    Stage userStage=new Stage();
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnInicSesion.setOnAction(event -> {
            lblContIncorrecta.setVisible(false);
            valiUsuario(event);
        });
        lblContIncorrecta.setVisible(false);
        btnRegistrarse.setOnAction(event1 -> {
            try {
                userStage.setTitle("Registrarse");
                Parent root= null;
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/RegiUsuario.fxml"));

                RegiUsuarioController regiUsuarioController = new RegiUsuarioController();
                loader.setController(regiUsuarioController);
                root=loader.load();
                Scene scene=new Scene(root);
                scene.getStylesheets().add("/sample/Complements/DarkTheme2.css");
                userStage.setScene(scene);
                userStage.setMaximized(true);
                userStage.show();
                ((Stage)(((Button) event1.getSource()).getScene().getWindow())).hide();
            }catch (IOException e ){
                e.printStackTrace();
            }
        });
    }

    EventHandler<ActionEvent> event=new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            valiUsuario(event);
        }
    };

    public void valiUsuario(ActionEvent event){
        if("admin".equals(textNombre.getText()) && "1234".equals(textContrasena.getText()))
            mostMenu(event);
        else
            lblContIncorrecta.setVisible(true);

    }

    public void mostMenu(ActionEvent event){
        try {
            menuStage.setTitle("Menu");
            Parent root= null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/Menu.fxml"));

            MenuController menuController = new MenuController();
            loader.setController(menuController);
            root=loader.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("/sample/Complements/DarkTheme2.css");
            menuStage.setScene(scene);
            menuStage.setMaximized(true);
            menuStage.show();
            ((Stage)(((Button) event.getSource()).getScene().getWindow())).hide();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }
}
