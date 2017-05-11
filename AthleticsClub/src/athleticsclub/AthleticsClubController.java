/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athleticsclub;

import event.Event;
import java.net.URL;
import java.util.ArrayList;
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
import membership.Team;

/**
 *
 * @author Alejandro Reyes
 */
public class AthleticsClubController implements Initializable {
    
    @FXML
    private Button bEvent, bTraining, bMembership;
    
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
        AnchorPane secondAnchorPane;
        StackPane secondStackPane;
        Scene secondScene;
        Stage secondStage;

        Button clickedButton = (Button) event.getSource();
        if (clickedButton.idProperty().equals(bMembership.idProperty())) {
            MembershipGUIController membershipGUIController;

            membershipGUIController = new MembershipGUIController();
            secondAnchorPane = membershipGUIController.getSecondAnchorPane();
            secondStackPane = new StackPane();
            secondStackPane.getChildren().add(secondAnchorPane);
            secondScene = new Scene(secondStackPane);
            secondStage = new Stage();
            secondStage.setTitle("Membership actions");
            secondStage.setScene(secondScene);
            this.bMembership.getScene().getWindow().hide();

            secondStage.showAndWait();
            ((Stage) this.bMembership.getScene().getWindow()).show();
        } else if (clickedButton.idProperty().equals(bEvent.idProperty())) {
            EventGUIController eventGUIController;
            eventGUIController = new EventGUIController();
            
            secondAnchorPane = eventGUIController.getSecondAnchorPane();
            secondStackPane = new StackPane();
            secondStackPane.getChildren().add(secondAnchorPane);
            secondScene = new Scene(secondStackPane);
            secondStage = new Stage();
            secondStage.setTitle("Event actions");
            secondStage.setScene(secondScene);
            this.bEvent.getScene().getWindow().hide();

            secondStage.showAndWait();
            ((Stage) this.bEvent.getScene().getWindow()).show();
        } else {
            System.out.println("NOOOOOOOOOOOOOOOOOOOOOOOO WORKS :(");
        }

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //everything was initialized in main activity

    }
    
    @FXML
    private void buttonExitClickedAction(ActionEvent event) {
        AthleticsClub.serializeMemberships((ArrayList<Membership>)Membership.getMembersList());
        AthleticsClub.serializeEvents((ArrayList<Event>)Event.getEvents());
        AthleticsClub.serializeTeams((ArrayList<Team>)Team.getTeams());
        bExit.getScene().getWindow().hide();
    }
    
    
}
