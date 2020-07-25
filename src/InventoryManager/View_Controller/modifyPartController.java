package InventoryManager.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import InventoryManager.Model.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import static InventoryManager.View_Controller.MainScreenController.getIndexModifyPart;

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
    int partID;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        /*
        Block below gets users selection from main screen part table and transfers information of selected
        part to the text fields in modify part scene
        */
        Part modPart = Inventory.getAllParts().get(indexModPart);
        partID = Inventory.getAllParts().get(indexModPart).getIdPart();
        idModifyPartField.setText(String.valueOf(partID));
        nameModifyPartField.setText(modPart.getNamePart());
        inventoryModifyPartField.setText(String.valueOf(modPart.getStockPart())); // Must be string argument
        priceCostModifyField.setText(String.valueOf(modPart.getPricePart()));
        minModifyPartField.setText(String.valueOf(modPart.getMinPart()));
        maxModifyPartField.setText(String.valueOf(modPart.getMaxPart()));

        // checks is part selected is outsourced
        if (modPart instanceof Outsourced) {
            // below sets radio button
            outsourcedModifyPart.setSelected(true);
            // below sets variable part field
            variableModifyPartField.setText(((Outsourced) modPart).getCompanyName());
            // below sets variable part label
            variableModifyPartLabel.setText("Company Name");
        }

        else {
            // below sets radio button
            inHouseModifyPart.setSelected(true);
            // below sets variable part field
            variableModifyPartField.setText(String.valueOf(((InHouse) modPart).getMachineId()));
            // below sets variable part label
            variableModifyPartLabel.setText("Machine ID");
        }
    }

    /*
    When outsource radio button is pushed, changes variable field and label. The previously saved field data
    is preserved even when radio button is switched.
    */
    @FXML
    public void inHouseModifyRadioButtonHandler(ActionEvent actionEvent) {
        isInHouse = true;
        variableModifyPartLabel.setText("Machine ID");
        variableModifyPartField.getText();
    }

    /*
    When outsource radio button is pushed, changes variable field and label. The previously saved field data
    is preserved even when radio button is switched.
    */
    @FXML
    public void outsourcedModifyRadioHandler(ActionEvent actionEvent) {
        isInHouse = false;
        variableModifyPartLabel.setText("Company Name");
        variableModifyPartField.getText();
    }

    // method below handles the save button when clicked
    @FXML
    public void saveButtonModifyHandler(ActionEvent actionEvent) throws IOException, NumberFormatException {
        String name = nameModifyPartField.getText();
        double price = Double.parseDouble(priceCostModifyField.getText());
        int stock = Integer.parseInt(inventoryModifyPartField.getText());
        int min = Integer.parseInt(minModifyPartField.getText());
        int max = Integer.parseInt(maxModifyPartField.getText());
        int indexPart = getIndexModifyPart();
        boolean partModifiedSuccessfully = false;
        String errorMessage = "";

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

        /*
         This if statement checks if the part is in house, and if it is, it will create an outsourced part and update
         the inventory
        */
        if (!isInHouse) {
            String companyName = variableModifyPartField.getText();
            if (companyName.equals("")) {
                errorMessage += "Company name field is empty. ";
            }
            if (!errorMessage.equals("")) {
                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                alert1.setTitle("Error Dialogue Box");
                alert1.setContentText(errorMessage); // this errorMessage is the cumulative string response to errors
                alert1.showAndWait();
            }
            if (errorMessage.equals("")) {
                Outsourced outsourcedPart = new Outsourced(partID, name, price, stock, min, max, companyName);
                Inventory.updatePart(indexPart, outsourcedPart);
                partModifiedSuccessfully = true;
            }
        }
        /*
         This if statement checks if the part is in house, and if it is, it will create an in house part and update
         the inventory
        */
        try {
            if (isInHouse) {
                int machineId = Integer.parseInt(variableModifyPartField.getText());
                if (machineId == 0) {
                    errorMessage += "Machine ID field is empty. ";
                }
                if (!errorMessage.equals("")) {
                    Alert alert1 = new Alert(Alert.AlertType.ERROR);
                    alert1.setTitle("Error Dialogue Box");
                    alert1.setContentText(errorMessage); // this errorMessage is the cumulative string response to errors
                    alert1.showAndWait();
                }
                if (errorMessage.equals("")) {
                    InHouse inHousePart = new InHouse(partID, name, price, stock, min, max, machineId);
                    Inventory.updatePart(indexPart, inHousePart);
                    partModifiedSuccessfully = true;
                }
            }
        } catch (NumberFormatException e) {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setTitle("Error Dialogue Box");
            alert1.setContentText("Machine ID must be a whole number. ");
            alert1.showAndWait();
        }


        // Exit to main screen below if a part was added successfully
        if (partModifiedSuccessfully) {
            Stage stageMainScreen;
            Parent root;
            stageMainScreen = (Stage) saveModifyPartButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainScreen.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stageMainScreen.setScene(scene);
            stageMainScreen.show();
        }
    }

    // method below handles the cancel button when clicked
    @FXML
    public void cancelButtonHandler(ActionEvent actionEvent) throws IOException {
        // Created an alert when the cancel button is clicked to prevent accidental information loss
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION, "Changes will not be saved. " +
                "Do you want to continue?");
        // Use result variable to get information on the buttons, like if one was pushed
        Optional<ButtonType> result = alert.showAndWait();

        // This IF statement checks if button was clicked and if it was OK
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Takes user to main screen - code block below
            Stage stageMainScreen;
            Parent root;
            stageMainScreen = (Stage) cancelModifyPartButton.getScene().getWindow();
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