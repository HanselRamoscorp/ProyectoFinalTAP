package sample.Controllers;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import sample.Modelos.User;

import java.io.File;
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
        btnSeleccionar.setOnAction(event);
        btnAgregar.setOnAction(event);
    }

    EventHandler<ActionEvent> event=new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            if (event.getSource().equals(btnSeleccionar)){
                Stage servicioStage=new Stage();
                servicioStage.setTitle("Ver Servicios");
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/ConsultarServicios.fxml"));

                ConsultarServiciosController consultarServiciosController=new ConsultarServiciosController();
                consultarServiciosController.setUser(user);
                controller.cargar(loader, consultarServiciosController, event);
            }else {
                if (event.getSource().equals(btnActualizar)){
                    Stage servicioStage=new Stage();
                    servicioStage.setTitle("Modificar Servicio");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/ModificarServicio.fxml"));

                    ModificarServicioController modificarServicioController=new ModificarServicioController();
                    modificarServicioController.setUser(user);
                    controller.cargar(loader, modificarServicioController, event);
                }else{
                    if (event.getSource().equals(btnEliminar)){

                    }else{
                        if (event.getSource().equals(btnAgregar)){
                            Stage servicioStage=new Stage();
                            servicioStage.setTitle("Agregar Servicio");
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/AgregarServicios.fxml"));

                            AgregarServiciosController agregarServiciosController=new AgregarServiciosController();
                            agregarServiciosController.setUser(user);
                            controller.cargar(loader, agregarServiciosController, event);
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
