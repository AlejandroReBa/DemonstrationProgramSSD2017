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
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import membership.Membership;
import membership.Team;
import training.Training;
import training.TrainingRecord;

/**
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */
public class AthleticsClubController implements Initializable {

    @FXML
    private Button bEvent, bTraining, bMembership, bTeams, bTrainingRecord;

    @FXML
    private Button bExit;

    @FXML
    private void handleButtonAction(ActionEvent event) {
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
        } else if (clickedButton.idProperty().equals(bTeams.idProperty())) {
            TeamGUIController teamGUIController;
            teamGUIController = new TeamGUIController();

            secondAnchorPane = teamGUIController.getSecondAnchorPane();
            secondStackPane = new StackPane();
            secondStackPane.getChildren().add(secondAnchorPane);
            secondScene = new Scene(secondStackPane);
            secondStage = new Stage();
            secondStage.setTitle("Management of Teams");
            secondStage.setScene(secondScene);
            this.bTeams.getScene().getWindow().hide();

            secondStage.showAndWait();
            ((Stage) this.bTeams.getScene().getWindow()).show();
        } else  if (clickedButton.idProperty().equals(bTraining.idProperty())) {
            TrainingGUIController trainingGUIController;
            trainingGUIController = new TrainingGUIController();
            
            secondAnchorPane = trainingGUIController.getSecondAnchorPane();
            secondStackPane = new StackPane();
            secondStackPane.getChildren().add(secondAnchorPane);
            secondScene = new Scene(secondStackPane);
            secondStage = new Stage();
            secondStage.setTitle("Training Actions");
            secondStage.setScene(secondScene);
            this.bTraining.getScene().getWindow().hide();
            
            secondStage.showAndWait();
            ((Stage) this.bTraining.getScene().getWindow()).show();
        } else if (clickedButton.idProperty().equals(bTrainingRecord.idProperty())){
            TrainingRecordGUIController trainingRecordGUIController;
            trainingRecordGUIController = new TrainingRecordGUIController();
            
            secondAnchorPane = trainingRecordGUIController.getSecondAnchorPane();
            secondStackPane = new StackPane();
            secondStackPane.getChildren().add(secondAnchorPane);
            secondScene = new Scene(secondStackPane);
            secondStage = new Stage();
            secondStage.setTitle("View of Training Records");
            secondStage.setScene(secondScene);
            this.bTrainingRecord.getScene().getWindow().hide();
            
            secondStage.showAndWait();
            ((Stage) this.bTrainingRecord.getScene().getWindow()).show();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //everything was initialized in main activity

    }

    @FXML
    private void buttonExitClickedAction(ActionEvent event) {
        AthleticsClub.serializeMemberships((ArrayList<Membership>) Membership.getMembersList());
        AthleticsClub.serializeEvents((ArrayList<Event>) Event.getEvents());
        AthleticsClub.serializeTeams((ArrayList<Team>) Team.getTeams());
        AthleticsClub.serializeTrainings((ArrayList<Training>)Training.getTrainingsList());
        AthleticsClub.serializeTrainingRecords((ArrayList<TrainingRecord>) TrainingRecord.getTrainingRecords());
        bExit.getScene().getWindow().hide();
    }

}
