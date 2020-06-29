package sample.View_Controller;

import sample.Model.Outsourced;
import sample.Model.Part;
import sample.Model.Inventory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Model.Part.partInputErrorMessage;

public class OutsourcedPartController implements Initializable {
    // All FXML elements listed below for addModifyOutsourcedPart.fxml
    @FXML
    public Label addModifyOutsourced;

    @FXML
    public RadioButton inHouseOut;

    @FXML
    public RadioButton outSourcedOut;

    @FXML
    public TextField idOutsourcedPartField;

    @FXML
    public TextField nameOutsourcedPartField;

    @FXML
    public TextField inventoryOutsourcedPartField;

    @FXML
    public TextField priceCostOutsourcedPartField;

    @FXML
    public TextField maxOutsourcedPartField;

    @FXML
    public TextField minOutsourcedPartField;

    @FXML
    public TextField compNameOutsourcedPartField;

    @FXML
    public Button saveOutsourcedButton;

    @FXML
    public Button cancelOutsourcedButton;

    @FXML
    private ToggleGroup sourceOfPart;

    // Local variables below
    boolean isInHouse;

    // inHousePartSelectedHandler changes the scene to addModifyInHousePart.fxml after clicking the inHouseOut radio button
    @FXML
    void inHousePartSelectedHandler(ActionEvent actionEvent) throws IOException {
        isInHouse = true;
        Stage stageInHouse;
        Parent root;
        stageInHouse = (Stage) inHouseOut.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addModifyInHousePart.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stageInHouse.setScene(scene);
        stageInHouse.show();
    }

    // cancelButtonActionHandler, this takes the user to the mainScreen.fxml after clicking cancel button
    @FXML
    void cancelButtonActionHandler(ActionEvent actionEvent) throws IOException {
        Stage stageMainScreen;
        Parent root;
        stageMainScreen = (Stage) cancelOutsourcedButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainScreen.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stageMainScreen.setScene(scene);
        stageMainScreen.show();
    }

    // Method below validates outsourced parts and prints error message if not valid
    public boolean validOutsourcedPart(String name, double price, int stock, int min, int max, String compName, String errorMessage) {
        if (name != null && price != 0 && stock >= 1 && min < max && stock <= max && stock >= min && compName != null) {
            return true;
        }
        else {
            errorMessage = partInputErrorMessage(name, price, stock, min, max, compName, errorMessage);
            System.out.println(errorMessage);
            return false;
        }
    }



    // Saves data when save button is clicked
    @FXML
    public void saveButtonActionHandler(ActionEvent actionEvent) throws IOException {
        // need method to create ID #s and check against the observable list
        int idPart = 1;
        String name = nameOutsourcedPartField.getText();
        double price = Double.parseDouble(priceCostOutsourcedPartField.getText());
        int stock = Integer.parseInt(inventoryOutsourcedPartField.getText());
        int min = Integer.parseInt(minOutsourcedPartField.getText());
        int max = Integer.parseInt(maxOutsourcedPartField.getText());
        String compName = compNameOutsourcedPartField.getText();
        String errorMsg = "";

        if (validOutsourcedPart(name, price, stock, min, max, compName, errorMsg)) {
            // Below save data from fields to respective database if the part data entered is valid
            Outsourced newPart = new Outsourced(idPart, name, price, stock, min, max, compName);
            Inventory.addPart(newPart);
            // Exit to main screen below
        }
        System.out.println(partInputErrorMessage(name, price, stock, min, max, compName, errorMsg));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
