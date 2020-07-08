package sample.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Model.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import static sample.Model.Inventory.getAllParts;
import static sample.Model.Inventory.getAllProducts;

/*
    @AUTHOR
    Marc Rios
    ID: 787989

 */

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

    private static int indexModifyPart;
    private static int indexModifyProduct;
    public static boolean isModifyProductScene;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
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

    public static int getIndexModifyPart() {
        return indexModifyPart;
    }

    public static int getIndexModifyProduct() {
        return indexModifyProduct;
    }

    //  This handles the search button on the "part" side of the main screen.
    @FXML
    public void partsSearchButtonHandler(ActionEvent actionEvent) throws IOException {
        String searchText = partsSearchField.getText();
        partsTableView.setItems(Inventory.lookupPart(searchText));
    }

    @FXML
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

    @FXML
    public void partsDeleteButtonHandler(ActionEvent actionEvent) {
        Part deletePart = partsTableView.getSelectionModel().getSelectedItem();
        if(deletePart != null) {
            // Created an alert when no part is selected for deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete part from database." +
                    "Do you want to continue?");
            // Use result variable to get information on the buttons, like if one was pushed
            Optional<ButtonType> result = alert.showAndWait();
            // This IF statement checks if button was clicked and if it was OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deletePart(deletePart);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select one part from the table above.");
            alert.setTitle("Error Dialogue Box");
            alert.showAndWait();
        }
    }

    @FXML
    public void exitButtonHandler(ActionEvent actionEvent) throws IOException {
        // Created an alert when the cancel button is clicked confirm exit of application
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Are you sure you want to close the application?");
        // Use result variable to get information on the buttons, like if one was pushed
        Optional<ButtonType> result = alert.showAndWait();

        // This IF statement checks if button was clicked and if it was OK
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Exits application - code block below
            System.exit(0);
        }
    }

    @FXML
    public void productsSearchButtonHandler(ActionEvent actionEvent) {
        String searchText = productsSearchField.getText();
        productsTableView.setItems(Inventory.lookupProduct(searchText));
    }


    // When modify button in part area on main screen is clicked, the method below handles it
    @FXML
    public void partsModifyButtonHandler(ActionEvent actionEvent) throws IOException {
        Part modPart = partsTableView.getSelectionModel().getSelectedItem();
        if (modPart != null) {
            indexModifyPart = getAllParts().indexOf(modPart);
            Parent modParts = FXMLLoader.load(getClass().getResource("modifyPart.fxml"));
            Scene scene = new Scene(modParts);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select one part from the table above.");
            alert.setTitle("Error Dialogue Box");
            alert.showAndWait();
        }
    }

    /*

    @AUTHOR
    Marc Rios
    ID: 787989

    */

    @FXML
    public void productsAddButtonHandler(ActionEvent actionEvent) throws IOException {
        isModifyProductScene = false;
        // Uses button to find source and casts it into a Stage. Also, next window is loaded onto scene.
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("addModifyProduct.fxml"));
        // Now that the scene is loaded, set the scene to the stage
        stage.setScene(new Scene(scene));
        stage.show();

    }

    // When modify button in product area on main screen is clicked, the method below handles it
    @FXML
    public void productsModifyButtonHandler(ActionEvent actionEvent) throws IOException {
        isModifyProductScene = true;
        Product productToModify = productsTableView.getSelectionModel().getSelectedItem();
        if (productToModify != null) {
            indexModifyProduct = getAllProducts().indexOf(productToModify);
            Parent modProducts = FXMLLoader.load(getClass().getResource("addModifyProduct.fxml"));
            Scene scene = new Scene(modProducts);
            Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            window.setScene(scene);
            window.show();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select one product from the table above.");
            alert.setTitle("Error Dialogue Box");
            alert.showAndWait();
        }
    }

    @FXML
    public void productsDeleteButtonHandler(ActionEvent actionEvent) {
        Product deleteProduct = productsTableView.getSelectionModel().getSelectedItem();
        if(deleteProduct != null) {
            // Created an alert when no product is selected for deletion
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will delete product from database." +
                    "Do you want to continue?");
            // Use result variable to get information on the buttons, like if one was pushed
            Optional<ButtonType> result = alert.showAndWait();
            // This IF statement checks if button was clicked and if it was OK
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Inventory.deleteProduct(deleteProduct);
            }
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR, "Please select one part from the product table.");
            alert.setTitle("Error Dialogue Box");
            alert.showAndWait();
        }
    }
}

/*
    @AUTHOR
    Marc Rios
    ID: 787989

 */