package sample.View_Controller;

import javafx.beans.binding.When;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Model.*;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Model.Inventory.getAllParts;

public class MainScreenController implements Initializable {

    // Variables for switching screens later
    Stage stage;
    Parent scene;

    // All FXML elements listed below for mainScreen.fxml
    @FXML
    private Button partsSearchButton;

    @FXML
    private TextField partsSearchField;

    @FXML
    private Button partsAddButton;

    @FXML
    private Button partsModifyButton;

    @FXML
    private Button partsDeleteButton;

    @FXML
    private Button exitButton;

    @FXML
    private Button productsSearchButton;

    @FXML
    private TextField productsSearchField;

    @FXML
    private Button productsAddButton;

    @FXML
    private Button productsModifyButton;

    @FXML
    private Button productsDeleteButton;

    @FXML
    private Label applicationNameLabel;

    @FXML
    private Label partsSearchLabel;

    @FXML
    private Label productsSearchLabel;

    @FXML
    private TableView<Product> productsTableView;

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Part, Integer> partIDColumn;

    @FXML
    private TableColumn<Part, String> partNameColumn;

    @FXML
    private TableColumn<Part, Integer> partInventoryColumn;

    @FXML
    private TableColumn<Part, Double> partPriceColumn;

    @FXML
    private TableColumn<Product, Integer> productIDColumn;

    @FXML
    private TableColumn<Product, String> productNameColumn;

    @FXML
    private TableColumn<Product, Integer> productInventoryColumn;

    @FXML
    private TableColumn<Product, Double> productPriceColumn;

    private static boolean started;



    private static int indexModifyPart;
    private static int indexModifyProduct;
    private Product productToModify;

    public static int getIndexModifyPart() {
        return indexModifyPart;
    }

    public void partsSearchButtonHandler(ActionEvent actionEvent) throws IOException {
        String searchText = partsSearchField.getText();
        partsTableView.setItems(Inventory.lookupPart(searchText));
    }

    public void partsAddButtonHandler(ActionEvent actionEvent) throws IOException {
        Stage stageAddPartOutsourcedScreen;
        Parent root;
        stageAddPartOutsourcedScreen = (Stage) partsAddButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addPart.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stageAddPartOutsourcedScreen.setScene(scene);
        stageAddPartOutsourcedScreen.show();
    }

    public void partsDeleteButtonHandler(ActionEvent actionEvent) {
        Part deletePart = partsTableView.getSelectionModel().getSelectedItem();
        Inventory.deletePart(deletePart);
        System.out.println("Part selected has been deleted from existence.");
    }


    public void exitButtonHandler(ActionEvent actionEvent) {
        System.exit(0);
    }


    public void productsSearchButtonHandler(ActionEvent actionEvent) {
        String searchText = productsSearchField.getText();
        productsTableView.setItems(Inventory.lookupProduct(searchText));
    }


    public void productsAddButtonHandler(ActionEvent actionEvent) throws IOException {
        // Uses button to find source and casts it into a Stage. Also, next window is loaded onto scene.
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("addModifyProduct.fxml"));
        // Now that the scene is loaded, set the scene to the stage
        stage.setScene(new Scene(scene));
        stage.show();

    }

    // When modify button in part area on main screen is clicked, the method below handles it
    public void partsModifyButtonHandler(ActionEvent actionEvent) throws IOException {
//        Part partToModify = partsTableView.getSelectionModel().getSelectedItem();
//        indexModifyPart = getAllParts().indexOf(partToModify);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("modifyPart.fxml"));
        loader.load();

        modifyPartController MPController = loader.getController();
        MPController.sendPart(partsTableView.getSelectionModel().getSelectedItem());

        // Uses button to find source and casts it into a Stage. Also, next window is loaded onto scene.
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();
        // Now that the scene is loaded, set the scene to the stage
        stage.setScene(new Scene(scene));
        stage.showAndWait();

        /*
        Stage stageModifyPartScreen;
        Parent root;
        stageModifyPartScreen = (Stage) partsModifyButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifyPart.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stageModifyPartScreen.setScene(scene);
        stageModifyPartScreen.showAndWait();
         */



    }

    // When modify button in product area on main screen is clicked, the method below handles it
    public void productsModifyButtonHandler(ActionEvent actionEvent) throws IOException {
        // Uses button to find source and casts it into a Stage. Also, next window is loaded onto scene.
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("addModifyProduct.fxml"));

        // Now that the scene is loaded, set the scene to the stage
        stage.setScene(new Scene(scene));
        stage.show();
        /*
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("addPart.fxml"));
        loader.load();

        OutsourcedPartController MSController = loader.getController();
        MSController.sendPart(productsTableView.getSelectionModel().getSelectedItem());


        // Uses button to find source and casts it into a Stage. Also, next window is loaded onto scene.
        stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
        Parent scene = loader.getRoot();

        // Now that the scene is loaded, set the scene to the stage
        stage.setScene(new Scene(scene));
        stage.show();
         */
    }



    public void productsDeleteButtonHandler(ActionEvent actionEvent) {
        Product deleteProduct = productsTableView.getSelectionModel().getSelectedItem();
        Inventory.deleteProduct(deleteProduct);
        System.out.println("Product selected has been deleted from existence.");
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        if(!started) {
            // Creates default parts below
            Inventory.addPart(new Outsourced(1, "Motherboard X-123", 125.25, 8,
                    1, 500, "Grandpa Apple"));
            Inventory.addPart(new Outsourced(2, "Central Processing Unit 1500", 223.55, 19,
                    1, 500, "Samyoung"));
            Inventory.addPart(new InHouse(3, "Solid State Drive SSD-120", 198.56, 30,
                    1, 500, 12556));
            Inventory.addPart(new InHouse(4, "Hard Disk Drive HDD-1TB", 86.33, 55,
                    1, 500, 15697));

            // Creates default products below
            Inventory.addProduct(new Product(1000, "Grandpa CamBook Apple 15 inch", 1556.99, 6, 1, 500));
            Inventory.addProduct(new Product(1015, "Samyoung Black Hole Smart Phone", 805.69, 12, 1, 500));
            Inventory.addProduct(new Product(1015, "Bell Enspiron Notebook 17 inch", 1159.44, 15, 1, 500));

            started = true;
        }
        //Parts table and columns
        partIDColumn.setCellValueFactory(new PropertyValueFactory<>("idPart"));
        partNameColumn.setCellValueFactory(new PropertyValueFactory<>("namePart"));
        partPriceColumn.setCellValueFactory(new PropertyValueFactory<>("pricePart"));
        partInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stockPart"));
        partsTableView.setItems(getAllParts());

        // Products table and columns
        productIDColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productPriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        productInventoryColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsTableView.setItems(Inventory.getAllProducts());

    }
}
