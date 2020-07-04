package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sample.Model.*;

/*
    @AUTHOR
    Marc Rios
    ID: 787989

 */

public class marcriosc482 extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("View_Controller/mainScreen.fxml")); // View_Controller/addPart.fxml
        primaryStage.setTitle("Inventory Manager");
        primaryStage.setScene(new Scene(root, 1050, 400));
        primaryStage.show();

        // Creates a few default parts below
        Inventory.addPart(new Outsourced( 1,"Motherboard X-123", 125.25, 8,
                1, 500, "Grandpa Apple"));
        Inventory.addPart(new Outsourced( 2, "Central Processing Unit 1500", 223.55, 19,
                1, 500, "Samyoung"));
        Inventory.addPart(new InHouse(3,"Solid State Drive SSD-120", 198.56, 30,
                1, 500, 12556));
        Inventory.addPart(new InHouse(4,"Hard Disk Drive HDD-1TB", 86.33, 55,
                1, 500, 15697));

        // Creates a few default products below
        Inventory.addProduct(new Product(100,"Grandpa CamBook Apple 15 inch", 1556.99, 6, 1, 500));
        Inventory.addProduct(new Product(101,"Samyoung Black Hole Smart Phone", 805.69, 12, 1, 500));
        Inventory.addProduct(new Product(102,"Bell Enspiron Notebook 17 inch", 1159.44, 15, 1, 500));
    }


    public static void main(String[] args) {

        launch(args);
    }
}


/*
    @AUTHOR
    Marc Rios
    ID: 787989

 */