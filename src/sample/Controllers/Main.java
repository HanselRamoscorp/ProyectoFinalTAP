package sample.Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
    public static Stage primaryStage;

    @Override
    public void start(Stage _primaryStage) throws Exception{
        primaryStage=_primaryStage;
        Parent root = FXMLLoader.load(getClass().getResource("/sample/FXML/sample.fxml"));
        Scene scene = new Scene(root, 1270, 690);
        scene.getStylesheets().add("/sample/Complements/DarkTheme2.css");
        primaryStage.setScene(scene);
        primaryStage.setMaximized(true);
        primaryStage.setTitle("Iniciar Sesion");
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
