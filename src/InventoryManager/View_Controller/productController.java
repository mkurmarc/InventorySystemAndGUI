package InventoryManager.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import InventoryManager.Model.Inventory;
import InventoryManager.Model.Part;
import InventoryManager.Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import static InventoryManager.Model.Inventory.getAllParts;
import static InventoryManager.View_Controller.MainScreenController.*;

/*
    @AUTHOR
    Marc Rios
    ID: 787989

 */

public class productController implements Initializable {

    @FXML
    private Label variableProductLabel;

    @FXML
    private TextField idProduct;

    @FXML
    private TextField nameProduct;

    @FXML
    private TextField invProduct;

    @FXML
    private TextField priceProduct;

    @FXML
    private TextField maxInvProduct;

    @FXML
    private TextField minInvProduct;

    @FXML
    private Button searchButtonProduct;

    @FXML
    private TextField searchProductField;

    @FXML
    private TableView<Part> tableViewAssocParts;

    @FXML
    private TableColumn<Part, Integer> idAssocPartColumn;

    @FXML
    private TableColumn<Part, String> nameAssocPartColumn;

    @FXML
    private TableColumn<Part, Integer> invAssocPartColumn;

    @FXML
    private TableColumn<Part, Double> priceAssocPartColumn;

    @FXML
    private Button addButtonProduct;

    @FXML
    private TableView<Part> allPartsTableView;

    @FXML
    private TableColumn<Part, Integer> idAllPartsColumn;

    @FXML
    private TableColumn<Part, String> nameAllPartsColumn;

    @FXML
    private TableColumn<Part, Integer> invAllPartsColumn;

    @FXML
    private TableColumn<Part, Double> priceAllPartsColumn;

    @FXML
    private Button deleteButtonProduct;

    @FXML
    private Button saveButtonProduct;

    @FXML
    private Button cancelButtonProduct;

    int indexModProduct = getIndexModifyProduct();
    int productId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
        This if statement checks if the modify button from main screen was pushed or not. If it was pushed, then the
        label changes appropriately. Vice versa if the button was not pushed.
        */
        if (isModifyProductScene) {
            variableProductLabel.setText("Modify Product");
        /*
        Block below gets users selection from main screen product table and transfers information of selected
        product to the text fields in add/modify product scene
        */
            Product modProduct = Inventory.getAllProducts().get(indexModProduct);
            productId = Inventory.getAllProducts().get(indexModProduct).getId();
            idProduct.setText(String.valueOf(productId));
            nameProduct.setText(modProduct.getNameProduct());
            invProduct.setText(String.valueOf(modProduct.getStock()));
            priceProduct.setText(String.valueOf(modProduct.getPrice()));
            minInvProduct.setText(String.valueOf(modProduct.getMin()));
            maxInvProduct.setText(String.valueOf(modProduct.getMax()));

        } else {
            variableProductLabel.setText("Add Product");
        }

        /*
         Sets top table view and columns with all the parts available in the observable array list for add and
         modify product scene
        */
        idAllPartsColumn.setCellValueFactory(new PropertyValueFactory<>("idPart"));
        nameAllPartsColumn.setCellValueFactory(new PropertyValueFactory<>("namePart"));
        priceAllPartsColumn.setCellValueFactory(new PropertyValueFactory<>("pricePart"));
        invAllPartsColumn.setCellValueFactory(new PropertyValueFactory<>("stockPart"));
        allPartsTableView.setItems(getAllParts());

        /*
         Sets bottom table view and columns with associated parts linked to the product available in the
         observable array list for add and modify product scene
        */
        idAssocPartColumn.setCellValueFactory(new PropertyValueFactory<>("idPart"));
        nameAssocPartColumn.setCellValueFactory(new PropertyValueFactory<>("namePart"));
        priceAssocPartColumn.setCellValueFactory(new PropertyValueFactory<>("pricePart"));
        invAssocPartColumn.setCellValueFactory(new PropertyValueFactory<>("stockPart"));
        tableViewAssocParts.setItems(Product.getAllAssociatedParts());

    }

    @FXML
    public void searchButtonProductHandler(ActionEvent actionEvent) {
        String searchText = searchProductField.getText();
        allPartsTableView.setItems(Inventory.lookupPart(searchText));
    }

    @FXML
    public void addButtonProductHandler(ActionEvent actionEvent) {
        // line below turns user selection into a part object
        Part partAddToAssociated = allPartsTableView.getSelectionModel().getSelectedItem();
        boolean duplicateItem = false;

        // condition checks if user has not selected anything
        if (partAddToAssociated == null) {
            // alerts user that nothing ahs been selected
            Alert alert = new Alert(Alert.AlertType.ERROR, "\nPlease select one part from the table above" +
                    " to add to the associated part list.");
            alert.setTitle("Error Dialogue Box");
            alert.showAndWait();
        } else {
            int id = partAddToAssociated.getIdPart();
            // for loop checks associatedParts list against user selected part for any duplicates
            for (int i = 0; i < Product.getAllAssociatedParts().size(); i++) {
                if (Product.getAllAssociatedParts().get(i).getIdPart() == id) {
                    duplicateItem = true;
                    break;
                }
            }
            // the if statement adds user selected part to associatedPart list if it is not a duplicate part
            if (!duplicateItem) {
                Product.addAssociatedPart(partAddToAssociated);
            } else {
                // alerts user if selected part is a duplicate
                Alert alert = new Alert(Alert.AlertType.ERROR, "\nPart selected is already associated with" +
                        "that product.");
                alert.setTitle("Error Dialogue Box");
                alert.showAndWait();
            }
            tableViewAssocParts.setItems(Product.getAllAssociatedParts());
        }
    }

    /*
    @AUTHOR
    Marc Rios
    ID: 787989

    */

    // method deletes the user's selection from the associated part list
    @FXML
    public void deleteAssocPartButtonHandler(ActionEvent actionEvent) {
        Part deletePart = tableViewAssocParts.getSelectionModel().getSelectedItem();
        Product.deleteAssociatedPart(deletePart);
        System.out.println("Product selected has been deleted from existence.");

    }

    @FXML
    public void saveButtonProductHandler(ActionEvent actionEvent) throws IOException {
        String name = nameProduct.getText();
        double price = Double.parseDouble(priceProduct.getText());
        int stock = Integer.parseInt(invProduct.getText());
        int min = Integer.parseInt(minInvProduct.getText());
        int max = Integer.parseInt(maxInvProduct.getText());
        int indexPart;
        boolean productAddedSuccessfully = false;
        String errorMessage = "";

        // series of if statements that creates an error message if there are any errors with the user's input
        if (name.equals("")) {
            errorMessage = "Name field is empty. ";
        }
        if (price == 0) {
            errorMessage += "Price field must be grater than 0. ";
        }
        if (!(stock >= 1)) {
            errorMessage += "Inventory must be more than 0. ";
        }
        if (!(min < max)) {
            errorMessage += "Minimum must be less than the maximum. ";
        }
        if (!(stock <= max)) {
            errorMessage += "Inventory must be lower than maximum. ";
        }
        if (!(stock >= min)) {
            errorMessage += "Inventory must be greater than minimum. ";
        }
        if(Product.getAllAssociatedParts().size() == 0) {
            errorMessage += "Product must have at least one associate part. ";
        }

        for (int i = 0; i < Product.getAllAssociatedParts().size(); i++) {
            double total = Product.getAllAssociatedParts().get(i).getPricePart();
            total += total;
            // checks if associate parts price total is more than product price
            if(total > price) {
                errorMessage += "The associate parts total price is more than product price.";
                break;
            }
        }

        if (!errorMessage.equals("")) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialogue Box");
            alert1.setContentText(errorMessage); // this errorMessage is the cumulative string response to errors
            alert1.showAndWait();
        }
        /*
         if block below checks if errorMessage is empty because if it is empty, that means no errors were found in
         the user's data entry
        */
        if(errorMessage.equals("")) {
            if (isModifyProductScene) { // block below is modifying product
                indexPart = getIndexModifyProduct();
                Product newModProduct = new Product(productId, name, price, stock, min, max);
                Inventory.updateProduct(indexPart, newModProduct);
                productAddedSuccessfully = true;
            }
            if(!isModifyProductScene) { // this is adding product to list
                Product newModProduct = new Product(Product.generateIdProduct(), name, price, stock, min, max);
                Inventory.addProduct(newModProduct);
                productAddedSuccessfully = true;
            }
        }
        // Exit to main screen below if a part was added successfully
        if (productAddedSuccessfully) {
            Stage stageMainScreen;
            Parent root;
            stageMainScreen = (Stage) saveButtonProduct.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainScreen.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stageMainScreen.setScene(scene);
            stageMainScreen.show();
            // resets the boolean to false to ensure it works next time user wants to add or modify a product
            isModifyProductScene = false;
        }
    }

    @FXML
    public void cancelButtonProductHandler(ActionEvent actionEvent) throws IOException {
        // Created a confirmation alert when the cancel button is clicked to prevent accidental information loss.
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "This will clear all text field values. " +
                "Do you want to continue?");
        // Use result variable to get information on the buttons, like if one was pushed
        Optional<ButtonType> result = alert.showAndWait();

        // This IF statement checks if button was clicked and if it was OK
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Takes user to main screen - code block below
            Stage stageMainScreen;
            Parent root;
            stageMainScreen = (Stage) cancelButtonProduct.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainScreen.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stageMainScreen.setScene(scene);
            stageMainScreen.show();
        }
    }
}

/*
    @AUTHOR
    Marc Rios
    ID: 787989

 */