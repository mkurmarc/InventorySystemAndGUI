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

import static sample.Model.Outsourced.validOutsourcedPart;
import static sample.Model.Part.partInputErrorMessageOutsourced;



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

    // Local variables below
    boolean isInHouse;

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
        try
        {
            // need method to create ID #s and check against the observable list
            int idPart = 1;
            String name = namePartField.getText();
            double price = Double.parseDouble(priceCostPartField.getText());
            int stock = Integer.parseInt(inventoryPartField.getText());
            int min = Integer.parseInt(minPartField.getText());
            int max = Integer.parseInt(maxPartField.getText());
            String variableValue = variableField.getText();
            String errorMsg = "";

            // need to add another condition to check if part is valid
            if (!isInHouse) { //validOutsourcedPart(name, price, stock, min, max, variableValue, errorMsg) &&
                // Below save data from fields to respective database if the part data entered is valid
                Outsourced newPart = new Outsourced(idPart, name, price, stock, min, max, variableValue);
                Inventory.addPart(newPart);
            }
            // need to add another condition to check if part is valid
            if (isInHouse) { // InHouse.validInHousePart(name, price, stock, min, max, Integer.parseInt(variableValue), errorMsg) &&
                // Below save data from fields to respective database if the part data entered is valid
                InHouse newPart = new InHouse(idPart, name, price, stock, min, max, Integer.parseInt(variableValue));
                Inventory.addPart(newPart);
            }
            // Exit to main screen below
            Stage stageMainScreen;
            Parent root;
            stageMainScreen = (Stage) savePartButton.getScene().getWindow();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("mainScreen.fxml"));
            root = loader.load();
            Scene scene = new Scene(root);
            stageMainScreen.setScene(scene);
            stageMainScreen.show();
        }
        catch (NumberFormatException e)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialogue Box");
            alert.setContentText("Please enter the correct formats for each text field.");
            alert.showAndWait();
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
