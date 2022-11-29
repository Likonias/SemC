/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package gui;

import druhPamatek.Gps;
import druhPamatek.Zamek;
import generator.Generator;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import pamatky.Pamatky;
import perzistence.TextovySoubor;


/**
 * FXML Controller class
 *
 * @author ludek
 */
public class FXML_GuiController implements Initializable {

    @FXML
    private Button btnVloz;
    @FXML
    private Button btnOdeber;
    @FXML
    private Button btnPrebuduj;
    @FXML
    private Button btnZrus;
    @FXML
    private Button btnImport;
    @FXML
    private Button btnNacti;
    @FXML
    private Button btnUloz;
    @FXML
    private ListView<Zamek> listView;

    private Pamatky pamatkyTree = new Pamatky();
    
    private ObservableList<Zamek> items = FXCollections.observableArrayList();
    
    private ObservableList<String> itemsVzdalenost = FXCollections.observableArrayList();
    @FXML
    private Button btnGeneruj;
    @FXML
    private Button btnNajdiProces;
    @FXML
    private Button btnSerad;
    @FXML
    private Button btnNajdiNejbliz;
    
    private enums.Enum.eTypProhl druhProhlizeni = enums.Enum.eTypProhl.HLOUBKY;
    @FXML
    private Button btnVytvorHeap;
    @FXML
    private Button btnZrusHeap;
    @FXML
    private Button btnOdeberMax;
    
    private Gps zvolenaGps;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        listView.setItems(items);
    }    

    @FXML
    private void onActBtnVloz(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GuiMain.class.getResource("FXML_VlozProces.fxml"));
            
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.setTitle("Vložení Procesu");
            
            FXML_VlozProcesController controller = loader.getController();
            controller.setStage(stage);
            stage.showAndWait();
            
            if(controller.isOkClicked){
                pamatkyTree.vlozZamek(controller.napsanyZamek());
                obnovListView();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FXML_GuiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onActBtnOdeber(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GuiMain.class.getResource("FXML_OdeberProces.fxml"));
            
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.setTitle("Odebrání Procesu");
            
            FXML_OdeberProcesController controller = loader.getController();
            controller.setStage(stage);
            stage.showAndWait();
            
            if(controller.isOkClicked){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Odebrany Prvek");
                alert.setHeaderText("Tento zámek byl odebrán:");
                alert.setContentText(pamatkyTree.odeberZamek(controller.zadanyKlic()).toString());
                alert.showAndWait();
                obnovListView();
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FXML_GuiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onActBtnPrebuduj(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GuiMain.class.getResource("FXML_Reorg.fxml"));
            
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.setTitle("Reorganizace");
            
            FXML_ReorgController controller = loader.getController();
            controller.setStage(stage);
            stage.showAndWait();
            
            pamatkyTree.nastavKlic(controller.getEnumReorg());
            obnovListView();
        } catch (IOException ex) {
            Logger.getLogger(FXML_GuiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    @FXML
    private void onActBtnZrus(ActionEvent event) {
        pamatkyTree.zrus();
        items.clear();
        obnovListView();
    }
    
    private void obnovListView(){
        itemsVzdalenost.clear();
        listView.setItems(items);
        items.clear();
        
        if(!pamatkyTree.getTree().jePrazdny()){
            
            Iterator iterator = pamatkyTree.iterator(druhProhlizeni);
            
            while(iterator.hasNext()){
                items.add((Zamek)iterator.next());
            }
            
        }
    }
    
    @FXML
    private void onActBtnImport(ActionEvent event) {
        pamatkyTree.importDatZTXT();
        obnovListView();
    }

    @FXML
    private void onActBtnNacti(ActionEvent event) {
        try {
            items.clear();
            TextovySoubor textovySoubor = new TextovySoubor();
            textovySoubor.nactiSoubor("mojeData.txt", pamatkyTree.getTree(), pamatkyTree.getDruhKlice());
            obnovListView();
        } catch (IOException ex) {
            Logger.getLogger(FXML_GuiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onActBtnUloz(ActionEvent event) {
        try {
            TextovySoubor textovySoubor = new TextovySoubor();
            textovySoubor.ulozSoubor("mojeDataUlozena.txt", pamatkyTree.getTree());
        } catch (IOException ex) {
            Logger.getLogger(FXML_GuiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onActBtnGeneruj(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GuiMain.class.getResource("FXML_Generuj.fxml"));
            
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.setTitle("Generování Procesu");
            
            FXML_GenerujController controller = loader.getController();
            controller.setStage(stage);
            stage.showAndWait();
            
            for (int i = 0; i < controller.pozadovaneCislo(); i++) {
                Generator generator = new Generator();
                pamatkyTree.vlozZamek(generator.generuj());
            }
            obnovListView();
            
        } catch (IOException ex) {
            Logger.getLogger(FXML_GuiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void onActBtnNajdiProces(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GuiMain.class.getResource("FXML_OdeberProces.fxml"));
            
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.setTitle("Hledání Procesu");
            
            FXML_OdeberProcesController controller = loader.getController();
            controller.setStage(stage);
            stage.showAndWait();
            
            if(controller.isOkClicked){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Nalezený Prvek");
                alert.setHeaderText("Tento zámek byl nalezen:");
                alert.setContentText(pamatkyTree.najdiZamek(controller.zadanyKlic()).toString());
                alert.showAndWait();
                listView.getSelectionModel().select(pamatkyTree.najdiZamek(controller.zadanyKlic()));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FXML_GuiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onActBtnSerad(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GuiMain.class.getResource("FXML_Serad.fxml"));
            
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.setTitle("Nastavení druhu prohlížení");
            
            FXML_SeradController controller = loader.getController();
            controller.setStage(stage);
            stage.showAndWait();
            
            druhProhlizeni = controller.getEnumProhlizeni();
            obnovListView();
        } catch (IOException ex) {
            Logger.getLogger(FXML_GuiController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void onActBtnNajdiNejbliz(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GuiMain.class.getResource("FXML_OdeberProces.fxml"));
            
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.setTitle("Hledání nejbližšího Procesu");
            
            FXML_OdeberProcesController controller = loader.getController();
            controller.setStage(stage);
            stage.showAndWait();
            
            if(controller.isOkClicked){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Nalezený Prvek");
                alert.setHeaderText("Tento zámek byl nejblíže:");
                alert.setContentText(pamatkyTree.najdiNejbliz(controller.zadanyKlic()).toString());
                alert.showAndWait();
                listView.getSelectionModel().select(pamatkyTree.najdiNejbliz(controller.zadanyKlic()));
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FXML_GuiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void onActbtnVytvorHeap(ActionEvent event) {
        
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(GuiMain.class.getResource("FXML_OdeberProces.fxml"));
            
            Parent parent = loader.load();
            Scene scene = new Scene(parent);
            
            Stage stage = new Stage();
            
            stage.setScene(scene);
            stage.setTitle("Tvorba Heap");
            
            FXML_OdeberProcesController controller = loader.getController();
            controller.setStage(stage);
            stage.showAndWait();
            
            if(controller.isOkClicked){
                
                String dataZCont = controller.zadanyKlic();
                
                Zamek zamek = new Zamek("zamek", new Gps(dataZCont.substring(0, 11), dataZCont.substring(12, 24)));
                zvolenaGps = zamek.getGpsGps();
                pamatkyTree.nastaveniGps(zvolenaGps);
                pamatkyTree.vybudujHeap();
                obnovListView();
                
            }
            
        } catch (IOException ex) {
            Logger.getLogger(FXML_GuiController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    private void onActBtnZrusHeap(ActionEvent event) {
        
        obnovListView();
    }

    @FXML
    private void onActBtnOdeberMax(ActionEvent event) {
        
        obnovListView();
    }

    
}
