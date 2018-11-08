package ProyectoReciboTelmex;

import ProyectoReciboTelmex.models.dao.Invoice;
import ProyectoReciboTelmex.models.dao.InvoiceDAO;
import ProyectoReciboTelmex.models.dao.MySQL;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ProyectoReciboTelmex.Invoice.InvoiceController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    @FXML
    Button Button1, Button2, Button3, Button4, Button5, Button6, Button7, Button8, Button9, Button10, BtnBorrar, BtnConsultar, BtnCancelar, BtnAyuda;
    @FXML
    TextField textField1;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Button1.setOnAction(eventBtn);
        Button2.setOnAction(eventBtn);
        Button3.setOnAction(eventBtn);
        Button4.setOnAction(eventBtn);
        Button5.setOnAction(eventBtn);
        Button6.setOnAction(eventBtn);
        Button7.setOnAction(eventBtn);
        Button8.setOnAction(eventBtn);
        Button9.setOnAction(eventBtn);
        Button10.setOnAction(eventBtn);
        BtnBorrar.setOnAction(eventBtn);
        BtnConsultar.setOnAction(eventConsultar);
    }

    Invoice invoice = new Invoice();

    InvoiceDAO invoiceDAO = new InvoiceDAO(MySQL.getConnection());

    private EventHandler<ActionEvent> eventBtn = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            String texto;
            if(event.getSource().equals(BtnBorrar))
                if(!textField1.getText().equals("")){
                texto = textField1.getText();
                texto = texto.substring(0, texto.length()-1);
                textField1.setText(texto);}
                else textField1.setText("");
            else {
                Button btn = (Button) event.getSource();
                texto = textField1.getText();
                textField1.setText(texto + btn.getText());
            }
        }
    };

    EventHandler<ActionEvent> eventConsultar = new EventHandler<ActionEvent>()
    {
        @Override
        public void handle(ActionEvent event)
        {
            if(validatePhoneNumber())
            {
                showInvoice(event);
            }
        }
    };

    private boolean validatePhoneNumber()
    {
        String phoneNumber = textField1.getText();
        invoice = invoiceDAO.fetchByPhoneNumber(phoneNumber);

        boolean result = true;

        if(invoice == null)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Message");
            String message = "Incorrect phone Number";
            result = false;
            alert.setContentText(""+message);
            alert.show();
        }


        return result;
    }

    private void showInvoice(ActionEvent event){
        try {
            Stage invoiceStage=new Stage();
            invoiceStage.setTitle("InvoiceController");
            Parent root= null;
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/ProyectoReciboTelmex/Invoice/invoiceFormat.fxml"));
            //
            InvoiceController invCont = new InvoiceController();
            invCont.setInvoice(invoice);
            loader.setController(invCont);

            root=loader.load();
            Scene scene=new Scene(root);
            scene.getStylesheets().add("bootstrapfx.css");
            invoiceStage.setScene(scene);
            invoiceStage.setMaximized(true);

            //invoice.setResizable(false);
            invoiceStage.show();
            ((Stage)(((Button) event.getSource()).getScene().getWindow())).close();
        }catch (IOException e ){
            e.printStackTrace();
        }
    }


    void sendMessege(String alerta){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Detalles");
        alert.setHeaderText("");
        alert.setContentText(alerta);
        alert.show();
    }
}
