package sample.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Model.InHouse;
import sample.Model.Inventory;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static sample.Model.Part.*;

public class modifyPartController implements Initializable {

    @FXML
    private Label modifyPartLabel;

    @FXML
    private RadioButton inHouseModifyPart;

    @FXML
    private ToggleGroup sourceOfPart;

    @FXML
    private RadioButton outsourcedModifyPart;

    @FXML
    private TextField idModifyPartField;

    @FXML
    private TextField nameModifyPartField;

    @FXML
    private TextField inventoryModifyPartField;

    @FXML
    private TextField priceCostModifyField;

    @FXML
    private TextField maxModifyPartField;

    @FXML
    private TextField minModifyPartField;

    @FXML
    private Label variableModifyPartLabel;

    @FXML
    private TextField variableModifyPartField;

    @FXML
    private Button saveModifyPartButton;

    @FXML
    private Button cancelModifyPartButton;

    boolean isInHouse;

    @FXML
    public void inhouseModifyRadioButtonHandler(ActionEvent actionEvent) {
        isInHouse = true;
        variableModifyPartLabel.setText("Machine ID");
        variableModifyPartField.setText("Mach ID");
    }

    @FXML
    public void outsourcedModifyRadioHandler(ActionEvent actionEvent) {
        isInHouse = false;
        variableModifyPartLabel.setText("Company Name");
        variableModifyPartField.setText("Comp Name");
    }

    // outsourcedPartSelectedHandler changes the scene to modifyPart.fxml after clicking the inHouseOut radio button
    @FXML
    public void outsourcedPartSelectedHandler(ActionEvent actionEvent) throws IOException {
        isInHouse = true;
        Stage stageOutsourced;
        Parent root;
        stageOutsourced = (Stage) outsourcedModifyPart.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("addPart.fxml"));
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
        String name = nameModifyPartField.getText();
        double price = Double.parseDouble(priceCostModifyField.getText());
        int stock = Integer.parseInt(inventoryModifyPartField.getText());
        int min = Integer.parseInt(minModifyPartField.getText());
        int max = Integer.parseInt(maxModifyPartField.getText());
        int machineId = Integer.parseInt(variableModifyPartField.getText());
        String errorMsg = "";

        if (validInHousePart(name, price, stock, min, max, machineId, errorMsg)) {
            // Below save data from fields to respective database if the part data entered is valid
            InHouse newPart = new InHouse(idPart, name, price, stock, min, max, machineId);
            Inventory.addPart(newPart);

            // Exit to main screen below
            Stage stageMainScreen;
            Parent root;
            stageMainScreen = (Stage) saveModifyPartButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainScreen.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stageMainScreen.setScene(scene);
            stageMainScreen.show();
        }
        Stage stageInHouse;
        Parent root;
        stageInHouse = (Stage) saveModifyPartButton.getScene().getWindow();
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
        stageMainScreen = (Stage) cancelModifyPartButton.getScene().getWindow();
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
