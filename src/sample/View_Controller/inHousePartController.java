package sample.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Model.InHouse;
import sample.Model.Inventory;
import sample.Model.Outsourced;

import java.io.IOException;

import static sample.Model.Part.*;

public class inHousePartController {

    @FXML
    private Label AddModifyInhouse;

    @FXML
    private RadioButton inHouseIn;

    @FXML
    private ToggleGroup sourceOfPart;

    @FXML
    private RadioButton outsourcedIn;

    @FXML
    private TextField IdInhousePartField;

    @FXML
    private TextField nameInHousePartField;

    @FXML
    private TextField inventoryInHousePartField;

    @FXML
    private TextField priceCostInHousePartField;

    @FXML
    private TextField maxInHousePartField;

    @FXML
    private TextField minInHousePartField;

    @FXML
    private TextField machineIdInHousePartField;

    @FXML
    private Button saveInHouseButton;

    @FXML
    private Button cancelInHouseButton;

    boolean isInHouse;

    // outsourcedPartSelectedHandler changes the scene to addModifyInHousePart.fxml after clicking the inHouseOut radio button
    @FXML
    public void outsourcedPartSelectedHandler(ActionEvent actionEvent) throws IOException {
        isInHouse = true;
        Stage stageOutsourced;
        Parent root;
        stageOutsourced = (Stage) outsourcedIn.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addModifyOutsourcedPart.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stageOutsourced.setScene(scene);
        stageOutsourced.show();
    }

    // Method below handles the save button when clicked
    @FXML
    public void saveButtonInHouseHandler(ActionEvent actionEvent) throws IOException {
        // need method to create ID #s and check against the observable list
        int idPart = 100;
        String name = nameInHousePartField.getText();
        double price = Double.parseDouble(priceCostInHousePartField.getText());
        int stock = Integer.parseInt(inventoryInHousePartField.getText());
        int min = Integer.parseInt(minInHousePartField.getText());
        int max = Integer.parseInt(maxInHousePartField.getText());
        int machineId = Integer.parseInt(machineIdInHousePartField.getText());
        String errorMsg = "";

        if (validInHousePart(name, price, stock, min, max, machineId, errorMsg)) {
            // Below save data from fields to respective database if the part data entered is valid
            InHouse newPart = new InHouse(idPart, name, price, stock, min, max, machineId);
            Inventory.addPart(newPart);

            // Exit to main screen below
            Stage stageMainScreen;
            Parent root;
            stageMainScreen = (Stage) saveInHouseButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainScreen.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stageMainScreen.setScene(scene);
            stageMainScreen.show();
        }
        Stage stageInHouse;
        Parent root;
        stageInHouse = (Stage) saveInHouseButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addModifyInHouse.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stageInHouse.setScene(scene);
        stageInHouse.show();
        System.out.println(partInputErrorMessageInHouse(name, price, stock, min, max, machineId, errorMsg));
    }

    // Method below handles the cancel button when clicked
    @FXML
    public void cancelButtonInHouseHandler(ActionEvent actionEvent) throws IOException {
        Stage stageMainScreen;
        Parent root;
        stageMainScreen = (Stage) cancelInHouseButton.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("mainScreen.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stageMainScreen.setScene(scene);
        stageMainScreen.show();
    }

    // Method below validates in-house parts and prints error message if not valid
    public boolean validInHousePart(String name, double price, int stock, int min, int max, int machineId, String errorMessage) {
        if (name.equals("") && price != 0 && stock >= 1 && min < max && stock <= max && stock >= min && machineId != 0) {
            return true;
        }
        else {
            errorMessage = partInputErrorMessageInHouse(name, price, stock, min, max, machineId, errorMessage);
            System.out.println(errorMessage);
            return false;
        }
    }
}
