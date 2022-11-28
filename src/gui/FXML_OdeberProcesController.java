/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import enums.Enum.Pozice;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ludek
 */
public class FXML_OdeberProcesController implements Initializable {

    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;

    private Stage stage;
    public boolean isOkClicked = false;
    @FXML
    private TextField textFieldOdeber;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        isOkClicked = false;
    }    

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @FXML
    private void onActBtnOk(ActionEvent event) {
        isOkClicked = true;
        stage.close();
    }

    @FXML
    private void onActBtnCancel(ActionEvent event) {
        stage.close();
    }
    
    public String zadanyKlic(){
        
        String klic = textFieldOdeber.getText();
        
        return klic;
    }
    
}
