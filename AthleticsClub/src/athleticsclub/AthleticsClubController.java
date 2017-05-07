/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athleticsclub;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import membership.Membership;

/**
 *
 * @author Alejandro Reyes
 */
public class AthleticsClubController implements Initializable {
    
    @FXML
    private Button bEvent;
    
    @FXML
    private Button bTraining;
    
    @FXML
    private Button bMembership;
    
    @FXML
    private Button bExit;
    
    @FXML
    private Label label;
    
    @FXML
    private void handleButtonAction(ActionEvent event) {
        /*
        System.out.println("You clicked me!");
        for (Membership m : Membership.getMembersList()){
            System.out.println(m);
        }
        label.setText("Hello World!");
        */
        SecondGUIController secondController;
        //ThirdGUIController thirdController;
        AnchorPane secondAnchorPane; //thirdAnchorPane;
        StackPane secondStackPane;//, thirdStackPane;
        Scene secondScene; //, thirdScene;
        Stage secondStage; //, thirdStage;
        
        secondController = new SecondGUIController();
        secondAnchorPane = secondController.getSecondAnchorPane();
        secondStackPane = new StackPane();
        secondStackPane.getChildren().add(secondAnchorPane);
        secondScene = new Scene(secondStackPane);
        secondStage = new Stage();
        secondStage.setTitle("Membership actions");
        secondStage.setScene(secondScene);
        this.bEvent.getScene().getWindow().hide();
                
        secondStage.showAndWait();
        ((Stage)this.bEvent.getScene().getWindow()).show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //everything was initialized in main activity

    }
    
    @FXML
    private void buttonExitClickedAction(ActionEvent event) {
        bExit.getScene().getWindow().hide();
    }
    
    
}
