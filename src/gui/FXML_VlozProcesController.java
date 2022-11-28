/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import druhPamatek.Gps;
import druhPamatek.Zamek;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author ludek
 */
public class FXML_VlozProcesController implements Initializable {

    private Stage stage;
    public boolean isOkClicked = false;
    @FXML
    private TextField textFieldX;
    @FXML
    private TextField textFieldY;
    @FXML
    private TextField textFieldNazevZamku;

    public void setStage(Stage stage) {
        this.stage = stage;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    
    public Zamek napsanyZamek(){
        
        Zamek zamek = null;
        
        if(textFieldNazevZamku != null || textFieldX != null || textFieldY != null){
            zamek = new Zamek(textFieldNazevZamku.getText(), new Gps(textFieldX.getText(), textFieldY.getText()));
        }
        return zamek;
    }
}
