/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ludek
 */
public class FXML_SeradController implements Initializable {

    @FXML
    private Button btnOk;
    @FXML
    private Button btnCancel;
    @FXML
    private ChoiceBox<String> choiceBox;

    private Stage stage;
    private ObservableList<String> items = FXCollections.observableArrayList();
    public boolean isOkClicked = false;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        items.add("dle Hloubky");
        items.add("dle Šířky");
        choiceBox.setItems(items);
        isOkClicked = false;
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
    
    public enums.Enum.eTypProhl getEnumProhlizeni(){
         enums.Enum.eTypProhl vybranyEnum = null;
        switch(choiceBox.getSelectionModel().getSelectedIndex()){
            case 0:
                vybranyEnum = enums.Enum.eTypProhl.HLOUBKY;
                break;
            case 1:
                vybranyEnum = enums.Enum.eTypProhl.SIRKY;
                break;
        }
        return vybranyEnum;
    }
    
}
