package sample.View_Controller;

import sample.Model.InHouse;
import sample.Model.Outsourced;
import sample.Model.Inventory;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import sample.Model.Product;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

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


    /*
    // inHousePartSelectedHandler changes the scene to modifyPart.fxml after clicking the inHouseOut radio button
    @FXML
    void inHousePartSelectedHandler(ActionEvent actionEvent) throws IOException {
        isInHouse = true;
        Stage stageInHouse;
        Parent root;
        stageInHouse = (Stage) inHouse.getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("modifyPart.fxml"));
        root = loader.load();
        Scene scene = new Scene(root);
        stageInHouse.setScene(scene);
        stageInHouse.show();
    }
     */

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

    // Method below validates outsourced parts and prints error message if not valid
    public static boolean validOutsourcedPart(String name, double price, int stock, int min, int max, String compName,
                                              String errorMessage) {
        if (!name.equals("") && price != 0 && stock >= 1 && min < max && stock <= max && stock >= min
                && !compName.equals("")) {
            return true;
        }
        else {
            errorMessage = partInputErrorMessageOutsourced(name, price, stock, min, max, compName, errorMessage);
            System.out.println(errorMessage);
            return false;
        }
    }

    // Saves data when save button is clicked
    @FXML
    public void saveButtonActionHandler(ActionEvent actionEvent) throws IOException {
        // need method to create ID #s and check against the observable list
        int idPart = 1;
        String name = namePartField.getText();
        double price = Double.parseDouble(priceCostPartField.getText());
        int stock = Integer.parseInt(inventoryPartField.getText());
        int min = Integer.parseInt(minPartField.getText());
        int max = Integer.parseInt(maxPartField.getText());
        String variableValue = variableField.getText();
        String errorMsg = "";
        boolean isOutsourced = false;

        if (validOutsourcedPart(name, price, stock, min, max, variableValue, errorMsg)) {
            // Below save data from fields to respective database if the part data entered is valid
            Outsourced newPart = new Outsourced(idPart, name, price, stock, min, max, variableValue);
            Inventory.addPart(newPart);
            // if this code is run, boolean ensures in house will not run and build a duplicate part
            isOutsourced = true;
            // Exit to main screen below
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));

            // Now that the scene is loaded, set the scene to the stage
            stage.setScene(new Scene(scene));
            stage.show();
            }
        if (InHouse.validInHousePart(name, price, stock, min, max, Integer.parseInt(variableValue), errorMsg)
                && !isOutsourced) {
            // Below save data from fields to respective database if the part data entered is valid
            InHouse newPart = new InHouse(idPart, name, price, stock, min, max, Integer.parseInt(variableValue));
            Inventory.addPart(newPart);
            // Exit to main screen below
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("mainScreen.fxml"));
        }
        else {
            // Uses button to find source and casts it into a Stage. Also, next window is loaded onto scene.
            stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("addPart.fxml"));

            // Now that the scene is loaded, set the scene to the stage
            stage.setScene(new Scene(scene));
            stage.show();
            System.out.println(partInputErrorMessageOutsourced(name, price, stock, min, max, variableValue, errorMsg));
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
