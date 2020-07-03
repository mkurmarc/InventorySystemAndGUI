package sample.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Model.*;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import static sample.Model.Part.*;
import static sample.View_Controller.MainScreenController.getIndexModifyPart;

/*
    @AUTHOR
    Marc Rios
    ID: 787989

 */

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
    int indexModPart = getIndexModifyPart();
    int indexModProduct = getIndexModifyPart();
    int partID;

//    public static void changeLabelField(String labelText, String fieldText) {
//        variableModifyPartLabel.setText(labelText);
//        variableModifyPartField.setText(fieldText);
//    }

    // When in-house radio button is pushed, changes variable field and label
    @FXML
    public void inhouseModifyRadioButtonHandler(ActionEvent actionEvent) {
        isInHouse = true;
        variableModifyPartLabel.setText("Machine ID");
        variableModifyPartField.setText("Mach ID");
//        changeLabelField("Machine ID", "Mach ID");
    }

    // When outsource radio button is pushed, changes variable field and label
    @FXML
    public void outsourcedModifyRadioHandler(ActionEvent actionEvent) {
        isInHouse = false;
        variableModifyPartLabel.setText("Company Name");
        variableModifyPartField.setText("Comp Name");
//        changeLabelField("Company Name", "comp Nm");
    }

/*
    public void sendPart(Outsourced partModifyOutsourced) {
        idModifyPartField.setText(String.valueOf(partModifyOutsourced.getIdPart()));  // Must be string argument
        nameModifyPartField.setText(partModifyOutsourced.getNamePart());
        inventoryModifyPartField.setText(String.valueOf(partModifyOutsourced.getStockPart()));
        priceCostModifyField.setText(String.valueOf(partModifyOutsourced.getPricePart()));
        minModifyPartField.setText(String.valueOf(partModifyOutsourced.getMinPart()));
        maxModifyPartField.setText(String.valueOf(partModifyOutsourced.getMaxPart()));
        variableModifyPartField.setText(partModifyOutsourced.getCompanyName());

    }

    public void sendInHousePart(InHouse partModifyInHouse) {
        idModifyPartField.setText(String.valueOf(partModifyInHouse.getIdPart()));  // Must be string argument
        nameModifyPartField.setText(partModifyInHouse.getNamePart());
        inventoryModifyPartField.setText(String.valueOf(partModifyInHouse.getStockPart()));
        priceCostModifyField.setText(String.valueOf(partModifyInHouse.getPricePart()));
        minModifyPartField.setText(String.valueOf(partModifyInHouse.getMinPart()));
        maxModifyPartField.setText(String.valueOf(partModifyInHouse.getMaxPart()));
        variableModifyPartField.setText(String.valueOf(partModifyInHouse.getMachineId()));
    }
 */

    // Method below handles the save button when clicked
    @FXML
    public void saveButtonModifyHandler(ActionEvent actionEvent) throws IOException {

        int idPart = 100;
        String name = nameModifyPartField.getText();
        double price = Double.parseDouble(priceCostModifyField.getText());
        int stock = Integer.parseInt(inventoryModifyPartField.getText());
        int min = Integer.parseInt(minModifyPartField.getText());
        int max = Integer.parseInt(maxModifyPartField.getText());
        int machineId = Integer.parseInt(variableModifyPartField.getText());
        String errorMsg = "";

        if (isInHouse) {
            // need method to create ID #s and check against the observable list
            if (validInHousePart(name, price, stock, min, max, machineId, errorMsg)) {
                // Below save data from fields to respective database if the part data entered is valid
//            InHouse newPart = new InHouse(idPart, name, price, stock, min, max, machineId);
//            Inventory.addPart(newPart);
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
            else {
                Stage stageInHouse;
                Parent root;
                stageInHouse = (Stage) saveModifyPartButton.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("modifyPart.fxml"));
                root = loader.load();
                Scene scene = new Scene(root);
                stageInHouse.setScene(scene);
                stageInHouse.show();
                System.out.println(partInputErrorMessageInHouse(name, price, stock, min, max, machineId, errorMsg));
            }
        }

        if (!isInHouse) {
            String companyName = variableModifyPartField.getText();
            int partID = 100; // Need method to generate ID
            int indexPart = getIndexModifyPart();
            if (addPartController.validOutsourcedPart(name, price, stock, min, max, companyName, errorMsg)) {
                Outsourced outsourcedPart = new Outsourced(partID, name, price, stock, min, max, companyName);
                Inventory.updatePart(indexPart, outsourcedPart);
            }
            else {
                Stage stageInHouse;
                Parent root;
                stageInHouse = (Stage) saveModifyPartButton.getScene().getWindow();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("modifyPart.fxml"));
                root = loader.load();
                Scene scene = new Scene(root);
                stageInHouse.setScene(scene);
                stageInHouse.show();
                System.out.println(partInputErrorMessageOutsourced(name, price, stock, min, max, companyName, errorMsg));
            }
        }
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
    public boolean validInHousePart(String name, double price, int stock, int min, int max,
                                    int machineId, String errorMessage) {
        if (name.equals("") && price != 0 && stock >= 1 && min < max && stock <= max &&
                stock >= min && machineId != 0) {
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
        Part modPart = Inventory.getAllParts().get(indexModPart);
        partID = Inventory.getAllParts().get(indexModPart).getIdPart();
        idModifyPartField.setText(String.valueOf(partID));
        nameModifyPartField.setText(modPart.getNamePart());
        inventoryModifyPartField.setText(String.valueOf(modPart.getStockPart())); // Must be string argument
        priceCostModifyField.setText(String.valueOf(modPart.getPricePart()));
        minModifyPartField.setText(String.valueOf(modPart.getMinPart()));
        maxModifyPartField.setText(String.valueOf(modPart.getMaxPart()));

        if (modPart instanceof Outsourced) {
            // Below sets radio button
            outsourcedModifyPart.setSelected(true);
            // Below sets variable part field
            variableModifyPartField.setText(((Outsourced) modPart).getCompanyName());
            // Below sets variable part label
            variableModifyPartLabel.setText("Company Name");
        }

        else {
            // Below sets radio button
            inHouseModifyPart.setSelected(true);
            // Below sets variable part field
            variableModifyPartField.setText(String.valueOf(((InHouse) modPart).getMachineId()));
            // Below sets variable part label
            variableModifyPartLabel.setText("Machine ID");
        }
    }
}
