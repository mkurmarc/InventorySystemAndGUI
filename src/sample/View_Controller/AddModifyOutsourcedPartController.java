package sample.View_Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.RadioButton;
import sample.Model.InHouse;
import sample.Model.Outsourced;
import sample.Model.Inventory;
import sample.Model.Part;
import sample.Model.Product;


import java.awt.*;
import java.net.URL;
import java.util.ResourceBundle;

public class AddModifyOutsourcedPartController implements Initializable {

    @FXML
    private Label addPart;

    @FXML
    private RadioButton inHouse;

    @FXML
    private RadioButton outSourced;

    @FXML
    private TextField idPartField;

    @FXML
    private TextField namePartField;

    @FXML
    private TextField inventoryPartField;

    @FXML
    private TextField costPricePartField;

    @FXML
    private TextField maxPartField;

    @FXML
    private TextField minPartField;

    @FXML
    private Label variableLabel;

    @FXML
    private TextField variableField;

    @FXML
    private Button saveButton;

    @FXML
    private Button cancelButton;

    private boolean isInHouse;

    private Part newPart;

    @FXML
    public void InHousePartSelectedHandler(ActionEvent actionEvent) {
        isInHouse = true;
        variableLabel.setText("Machine ID");
        variableField.setText("Mach ID");
    }

    @FXML
    public void OutsourcedPartSelectedHandler(ActionEvent actionEvent) {
        isInHouse = false;
        variableLabel.setText("Company Name");
        variableField.setText("Comp Name");
    }


    
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


}
