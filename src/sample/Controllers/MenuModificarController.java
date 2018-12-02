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

public class MenuModificarController implements Initializable {
    Controller controller=new Controller();
    User user=new User();
    @FXML Button btnRegresar, btnSeleccionar, btnActualizar, btnEliminar, btnAgregar;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnRegresar.setOnAction(event -> {
            controller.mostMenu(event, user);
        });
        btnActualizar.setOnAction(event);
    }

    EventHandler<ActionEvent> event=new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource().equals(btnSeleccionar)){

            }else {
                if (event.getSource().equals(btnActualizar)){
                    Stage servicioStage=new Stage();
                    servicioStage.setTitle("Modificar");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/ModificarServicio.fxml"));

                    ModificarServicioController modificarServicioController=new ModificarServicioController();
                    modificarServicioController.setUser(user);
                    controller.cargar(loader, modificarServicioController, event);
                }else{
                    if (event.getSource().equals(btnEliminar)){

                    }else{
                        if (event.getSource().equals(btnAgregar)){

                        }else{

                        }
                    }
                }
            }
        }
    };

    public void setUser(User user){
        this.user=user;
    }
}
