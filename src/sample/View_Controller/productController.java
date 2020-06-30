package sample.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class productController {

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
    private TableView tableViewProduct;

    @FXML
    private TableColumn idProductColumn;

    @FXML
    private TableColumn nameProductColumn;

    @FXML
    private TableColumn invProductColumn;

    @FXML
    private TableColumn priceProductColumn;

    @FXML
    private Button addButtonProduct;

    @FXML
    private TableView tableViewProduct2;

    @FXML
    private TableColumn idProductColumn2;

    @FXML
    private TableColumn nameProductColumn2;

    @FXML
    private TableColumn invProductColumn2;

    @FXML
    private TableColumn priceViewProduct2;

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

    }

    public void saveButtonProductHandler(ActionEvent actionEvent) {

    }

    public void cancelButtonProductHandler(ActionEvent actionEvent) throws IOException {
        // Uses button to find source and casts it into a Stage. Also, next window is loaded onto scene.
        stage = (Stage) ((Button)actionEvent.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));

        // Now that the scene is loaded, set the scene to the stage
        stage.setScene(new Scene(scene));
        stage.show();
    }
}
