package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

// @author marcrios

public class marcriosc482 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View_Controller/mainScreen.fxml")); // View_Controller/addPart.fxml
        primaryStage.setTitle("Inventory Manager");
        primaryStage.setScene(new Scene(root, 1050, 400));
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
