package InventoryManager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import InventoryManager.Model.*;

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
        root.requestFocus();

        // Creates default product
        Product defaultProduct1 = new Product(100,"Grandpa CamBook Apple 15 inch", 2556.99, 6, 1, 500);
        Inventory.addProduct(defaultProduct1);
        // Creates default product
        Product defaultProduct2 = new Product(101,"Samyoung Black Hole Smart Phone", 2805.69, 12, 1, 500);
        Inventory.addProduct(defaultProduct2);
        // Creates default product
        Product defaultProduct3 = new Product(102,"Bell Enspiron Notebook 17 inch", 1159.44, 15, 1, 500);
        Inventory.addProduct(defaultProduct3);

        // Creates an outsourced part
        Outsourced outsourcedPart1 = new Outsourced( 1,"Motherboard X-123", 125.25, 8,
                1, 500, "Grandpa Apple");
        Inventory.addPart(outsourcedPart1);
        // Creates an outsourced part
        Outsourced outsourcedPart2 = new Outsourced( 2, "Central Processing Unit 1500", 223.55, 19,
                1, 500, "Samyoung");
        Inventory.addPart(outsourcedPart2);
        // Creates an in house part
        InHouse inHousePart1 = new InHouse(3,"Solid State Drive SSD-120", 198.56, 30,
                1, 500, 12556);
        Inventory.addPart(inHousePart1);
        // Creates an in house part
        InHouse inHousePart2 = new InHouse(4,"Hard Disk Drive HDD-1TB", 86.33, 55,
                1, 500, 15697);
        Inventory.addPart(inHousePart2);
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