package sample.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import sample.Model.Inventory;
import sample.Model.Part;
import sample.Model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Model.Inventory.getAllParts;

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
    private TableView<Product> tableViewProduct;

    @FXML
    private TableColumn<Product, Integer> idProductColumn;

    @FXML
    private TableColumn<Product, String> nameProductColumn;

    @FXML
    private TableColumn<Product, Integer> invProductColumn;

    @FXML
    private TableColumn<Product, Double> priceProductColumn;

    @FXML
    private Button addButtonProduct;

    @FXML
    private TableView<Part> tableViewProduct2;

    @FXML
    private TableColumn<Part, Integer> idProductColumn2;

    @FXML
    private TableColumn<Part, String> nameProductColumn2;

    @FXML
    private TableColumn<Part, Integer> invProductColumn2;

    @FXML
    private TableColumn<Part, Double> priceProductColumn2;

    @FXML
    private Button deleteButtonProduct;

    @FXML
    private Button saveButtonProduct;

    @FXML
    private Button cancelButtonProduct;

    // Variables for switching screens later
    Stage stage;
    Parent scene;

    public void searchButtonProductHandler(ActionEvent actionEvent) {

    }

    public void addButtonProductHandler(ActionEvent actionEvent) {

    }

    public void deleteButtonProductHandler(ActionEvent actionEvent) {
        Product deleteProduct = tableViewProduct.getSelectionModel().getSelectedItem();
        Inventory.deleteProduct(deleteProduct);
        System.out.println("Product selected has been deleted from existence.");

    }

    public void saveButtonProductHandler(ActionEvent actionEvent) {

    }
    /*
        @AUTHOR
        Marc Rios
        ID: 787989

     */
    public void cancelButtonProductHandler(ActionEvent actionEvent) throws IOException {
        // Uses button to find source and casts it into a Stage. Also, next window is loaded onto scene.
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));

        // Now that the scene is loaded, set the scene to the stage
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Parts table and columns or tableViewProducts2
        idProductColumn2.setCellValueFactory(new PropertyValueFactory<>("idPart"));
        nameProductColumn2.setCellValueFactory(new PropertyValueFactory<>("namePart"));
        priceProductColumn2.setCellValueFactory(new PropertyValueFactory<>("pricePart"));
        invProductColumn2.setCellValueFactory(new PropertyValueFactory<>("stockPart"));
        tableViewProduct2.setItems(getAllParts());

        // Products table and columns or tableViewProducts1
        idProductColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameProductColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        priceProductColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        invProductColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        tableViewProduct.setItems(Inventory.getAllProducts());
    }
}

/*
    @AUTHOR
    Marc Rios
    ID: 787989

 */