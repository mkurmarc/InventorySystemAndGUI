package sample.View_Controller;

import sample.Model.*;
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
import java.util.Optional;
import java.util.ResourceBundle;




public class addPartController implements Initializable {

    // Variables for switching screens later
    Stage stage;
    Parent scene;

    // All FXML elements listed below for addPart.fxml
    @FXML
    private Label addPartLabel;

    @FXML
    private RadioButton inHouse;

    @FXML
    private RadioButton outsourced;

    @FXML
    private TextField idPartField;

    @FXML
    private TextField namePartField;

    @FXML
    private TextField inventoryPartField;

    @FXML
    private TextField priceCostPartField;

    @FXML
    private TextField maxPartField;

    @FXML
    private TextField minPartField;

    @FXML
    private Label companyNameLabel;

    @FXML
    private TextField variableField;

    @FXML
    private Button savePartButton;

    @FXML
    private Button cancelPartButton;

    @FXML
    private ToggleGroup sourceOfPart;

    // not FXML variables below
    boolean isInHouse = false;
    String errorMsg = "";

    public static boolean isInteger(String checkStr) {
        if (checkStr == null) {
            return false;
        }
        int strLength = checkStr.length();
        if ( strLength == 0) {
            return false;
        }
        int i = 0;
        if (checkStr.charAt(0) == '-') {
            if (strLength == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < strLength; i++) {
            char x = checkStr.charAt(i);
            if (x < '0' || x > '9') {
                return false;
            }
        }
        return true;
    }

    // When in-house radio button is pushed, changes variable field and label
    @FXML
    public void inHouseRadioButtonHandler(ActionEvent actionEvent) {
        isInHouse = true;
        companyNameLabel.setText("Machine ID");
        variableField.setText("Mach ID");
    }

    // When outsource radio button is pushed, changes variable field and label
    @FXML
    public void outsourceRadioButtonHandler(ActionEvent actionEvent) {
        isInHouse = false;
        companyNameLabel.setText("Company Name");
        variableField.setText("Comp Name");
    }

    // cancelButtonActionHandler, this takes the user to the mainScreen.fxml after clicking cancel button
    @FXML
    void cancelButtonActionHandler(ActionEvent actionEvent) throws IOException {
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
            stageMainScreen = (Stage) cancelPartButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainScreen.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stageMainScreen.setScene(scene);
            stageMainScreen.show();
        }
    }

    // Saves data when save button is clicked
    @FXML
    public void saveButtonActionHandler(ActionEvent actionEvent) throws IOException {

        // need method to create ID #s and check against the observable list
        int idPart = Part.generatePartId();
        String name = namePartField.getText();
        double price = Double.parseDouble(priceCostPartField.getText());
        int stock = Integer.parseInt(inventoryPartField.getText());
        int min = Integer.parseInt(minPartField.getText());
        int max = Integer.parseInt(maxPartField.getText());
        String variableValue = variableField.getText();
        boolean partAddedSuccessfully = false;
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

        if (!isInHouse) {
            if (variableValue.equals("")) {
                errorMessage += "Company name field is empty. ";
            }
        } else {
            if (variableValue.equals("")) {
                errorMessage += "Machine ID field is empty. ";
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
        if (errorMessage.equals("")) {
            if (isInHouse) {
                InHouse newPartInHouse = new InHouse(idPart, name, price, stock, min, max, Integer.parseInt(variableValue));
                Inventory.addPart(newPartInHouse);
                partAddedSuccessfully = true;
            }
            if (!isInHouse) {
                // Below save data from fields to respective database if the part data entered is valid
                Outsourced newPart = new Outsourced(idPart, name, price, stock, min, max, variableValue);
                Inventory.addPart(newPart);
                partAddedSuccessfully = true;
            }
        }
        // Exit to main screen below if a part was added successfully
        if (partAddedSuccessfully) {
            Stage stageMainScreen;
            Parent root;
            stageMainScreen = (Stage) savePartButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainScreen.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stageMainScreen.setScene(scene);
            stageMainScreen.show();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
