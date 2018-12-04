package sample.Controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Complements.MySQL;
import sample.DAO.CompanyDAO;
import sample.Modelos.TablaReportes;
import sample.Modelos.User;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportesController implements Initializable {
    Controller controller=new Controller();
    User user=new User();

    @FXML Button btnRegresar;
    @FXML TableView tablaRecharge;
    @FXML TableView tablaHomeService;
    @FXML TableColumn clmNombreRecharge, clmRecharge, clmNombreHomeService, clmHomeService;
    @FXML TextField textTotalRecharge, textTotalHomeService;

    CompanyDAO companyDAO=new CompanyDAO(MySQL.getConnection());

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnRegresar.setOnAction(event -> {
            Stage servicioStage=new Stage();
            servicioStage.setTitle("Menu Modificar");
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/sample/FXML/MenuModificar.fxml"));

            MenuModificarController menuModificarController=new MenuModificarController();
            menuModificarController.setUser(user);
            controller.cargar(loader, menuModificarController, event);
        });

        clmNombreHomeService.setCellValueFactory(new PropertyValueFactory<TablaReportes, String>("name"));
        clmHomeService.setCellValueFactory(new PropertyValueFactory<TablaReportes, String>("valor"));
        tablaHomeService.setItems(companyDAO.countHomeService());
        textTotalHomeService.setText(""+companyDAO.countTotalHomeService());

        clmNombreRecharge.setCellValueFactory(new PropertyValueFactory<TablaReportes, String>("name"));
        clmRecharge.setCellValueFactory(new PropertyValueFactory<TablaReportes, String>("valor"));
        tablaRecharge.setItems(companyDAO.countRecharge());
        textTotalRecharge.setText(""+companyDAO.countTotalRecharge());
    }

    public void setUser(User user){
        this.user=user;
    }
}
