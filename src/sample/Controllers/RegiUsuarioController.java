package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Modelos.*;
import sample.DAO.*;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import sample.Complements.MySQL;
import sun.security.util.Password;

public class RegiUsuarioController implements Initializable {
    Controller controller=new Controller();
    @FXML
    Button btnRegresar, btnRegistrar;
    @FXML
    TextField NombreUTF;
    @FXML
    PasswordField PS1TF, PSTF2;
    @FXML
    ComboBox cmb;
    @FXML
    Label msj;
    List<TypeUser> typeUsers =new ArrayList<>();
    TypeUserDAO typeUserDAO = new TypeUserDAO(MySQL.getConnection());
    UserDAO userDAO = new UserDAO(MySQL.getConnection());
    int id;
    String Nombre;
    String password1 , password2;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        msj.setVisible(false);
        typeUsers=typeUserDAO.fetchAll();
        for (int i = 0; i < typeUsers.size(); i++) {
            cmb.getItems().add(typeUsers.get(i).getTypeuser());
        }



        btnRegresar.setOnAction(event -> {
            Main.primaryStage.show();
            ((Stage) (((Button) event.getSource()).getScene().getWindow())).hide();


        });


        btnRegistrar.setOnAction(event -> {
            for (int i = 0; i < typeUsers.size(); i++) {
                if (typeUsers.get(i).getTypeuser().equals(cmb.getSelectionModel().getSelectedItem())){
                    id=typeUsers.get(i).getId_typeuser();
                    break;
                }
            }
            Nombre = NombreUTF.getText();
            password1 = PS1TF.getText();
            password2 = PSTF2.getText();

            if (password1.equals(password2)){
                userDAO.insert(Nombre,password1,id,1);
                msj.setVisible(true);
                msj.setText("USUARIO REGISTRADO");
            } else {
                msj.setVisible(true);
                msj.setText("INGRESA BIEN TUS DATOS");
            }


        });

    }
}