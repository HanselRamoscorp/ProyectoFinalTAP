package sample;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML Button btnExample1, btnExample2, btnExample3, btnExample4, btnTicket;
    public static final String DEST1 = "results/chapter01/hello_world.pdf";
    public static final String DEST2 = "results/chapter01/rick_astley.pdf";
    public static final String DEST3 = "results/chapter01/quick_brown_fox.pdf";
    public static final String DEST4 = "results/chapter01/united_states.pdf";
    public static final String ticket = "results/chapter01/ticket.pdf";

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnExample1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    File file = new File(DEST1);
                    file.getParentFile().mkdirs();
                    new C01E01_HelloWorld().createPdf(DEST1);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnExample2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    File file = new File(DEST2);
                    file.getParentFile().mkdirs();
                    new C01E02_RickAstley().createPdf(DEST2);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnExample3.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    File file = new File(DEST3);
                    file.getParentFile().mkdirs();
                    new C01E03_QuickBrownFox().createPdf(DEST3);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnExample4.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    File file = new File(DEST4);
                    file.getParentFile().mkdirs();
                    new C01E04_UnitedStates().createPdf(DEST4);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnTicket.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    File file = new File(ticket);
                    file.getParentFile().mkdirs();
                    new Ticket().createPdf(ticket);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
