/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athleticsclub;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import membership.Membership;
import event.Event;
import event.Event.ageGroupRelatedEnum;
import java.util.ArrayList;
import membership.Team;
import training.Training;
import training.TrainingRecord;

/**
 * FXML Controller class
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */
public class TrainingRecordGUIController implements Initializable {

    private ArrayList<String> searchTypeList;
    private ArrayList<String> searchFilterList; //this changes depends on searchTypeList
    /**
     * Initializes the controller class.
     */
    public TrainingRecordGUIController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TrainingRecordGUI.fxml"));

        loader.setController(this);
        
        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(AthleticsClubController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    @FXML
    AnchorPane SecondAnchorPane;

    @FXML
    public void setSecondAnchorPane(AnchorPane anchorPane) {
        this.SecondAnchorPane = anchorPane;
    }

    @FXML
    public AnchorPane getSecondAnchorPane() {
        return this.SecondAnchorPane;
    }

    @FXML
    Button buttonReturn, showTrainingRecordsButton, searchTrainingRecordsButton;

    @FXML
    ListView<TrainingRecord> trainingRecordsListView;

    @FXML
    ComboBox<String> trainingRecordComboBox, trainingRecordFilterComboBox;

    @FXML
    TextArea resultTextArea, trainingRecordTextArea;

    @FXML
    TextField searchNameTextField;


    @FXML
    private void showTrainingRecordsButtonAction(ActionEvent event) {      
        //to make it beautiful: custom List view with HBox
        //Not implemented, source: http://stackoverflow.com/questions/36121707/javafx-custom-listview-appearance
        trainingRecordsListView.getItems().clear();
        trainingRecordsListView.getItems().addAll(TrainingRecord.getTrainingRecords());
        resultTextArea.setText("All teams have been loaded"
                + "\nDisplaying " + Team.getTeams().size() + " found results.");
        
        if (trainingRecordsListView.getItems().size() > 0){
            trainingRecordsListView.getSelectionModel().select(0);
        }
    }
    

    @FXML 
    private void searchTrainingRecordsButtonAction(ActionEvent event) {
        
        String viewBy = this.trainingRecordComboBox.getSelectionModel().getSelectedItem();
        String filter = "";
        if (viewBy.equals("Coach Name")){
            filter = this.searchNameTextField.getText();
        }else{
            filter = this.trainingRecordFilterComboBox.getSelectionModel().getSelectedItem();
        }
        
        this.trainingRecordsListView.getItems().clear();
        ArrayList<TrainingRecord> retrievedTrainingRecords = 
                retrieveTrainingRecordsBySearchType(this.trainingRecordComboBox.getSelectionModel().getSelectedIndex(),
                        filter);
        
        if (retrievedTrainingRecords.isEmpty()){
            resultTextArea.setText("No matches found for " + viewBy + " = " + filter);
        }else{
            String text = "View of training records by " + viewBy + " = " + filter +
                    "\nDisplaying " + retrievedTrainingRecords.size() + " found results.";
            resultTextArea.setText(text);
            this.trainingRecordsListView.getItems().addAll(retrievedTrainingRecords);
        }
    }
    
    
    @FXML
    private void listTrainingRecordViewClickedHandle(MouseEvent event) {
        
        if (!this.trainingRecordsListView.getItems().isEmpty()) {
            int selectedIndex = this.trainingRecordsListView.getSelectionModel().getSelectedIndex();
            TrainingRecord selectedTrainingRecord = trainingRecordsListView.getItems().get(selectedIndex);

            trainingRecordTextArea.setText(selectedTrainingRecord.getRecord());
                
            resultTextArea.setText("The selected record description has been loaded into left field");
        }
        
    }
    
     @FXML
    private void trainingRecordComboBoxChanges() {
        if (!trainingRecordComboBox.getItems().isEmpty()) {
            int selectedIndex = trainingRecordComboBox.getSelectionModel().getSelectedIndex();
            setNewFilterList(selectedIndex);
        }
    }
    
    @FXML
    private void buttonReturnClickedAction(ActionEvent event) {
        buttonReturn.getScene().getWindow().hide();
    }

    @FXML
    private void buttonExitClickedAction(ActionEvent event) {
        //buttonReturn.getScene().getWindow().hide();
        Platform.exit();
        AthleticsClub.serializeMemberships((ArrayList<Membership>)Membership.getMembersList());
        AthleticsClub.serializeEvents((ArrayList<Event>)Event.getEvents());
        AthleticsClub.serializeTeams((ArrayList<Team>)Team.getTeams());
        AthleticsClub.serializeTrainings((ArrayList<Training>)Training.getTrainingsList());
        AthleticsClub.serializeTrainingRecords((ArrayList<TrainingRecord>) TrainingRecord.getTrainingRecords());
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchTypeList = new ArrayList<String>();
        searchTypeList.add("Type"); //index 0
        searchTypeList.add("Discipline");
        searchTypeList.add("Age Group");
        searchTypeList.add("Coach Name"); //index 3
        trainingRecordComboBox.getItems().addAll(searchTypeList);
        trainingRecordComboBox.getSelectionModel().select(0);
    
        setNewFilterList(0);

        
    }
    
    //support method to set new filter List, matching the type of search
    //(first comboBox) the user has chosen
    private void setNewFilterList(int index){
        if (index > -1 && index < 4){
            searchFilterList = new ArrayList<String>();
        switch (index){
                case 0:
                    Training.typeEnum[] typeArray = Training.typeEnum.values();
                    for (int i=0; i < typeArray.length; i++){
                        searchFilterList.add(typeArray[i].toString());
                     }
                    break;
                case 1:
                    Training.disciplineTrainingEnum[] disciplineArray = Training.disciplineTrainingEnum.values();
                    for (int i=0; i < disciplineArray.length; i++){
                        searchFilterList.add(disciplineArray[i].toString());
                    }
                case 2:
                    ageGroupRelatedEnum[] ageGroupArray = Event.ageGroupRelatedEnum.values();
                    for (int i=0; i < ageGroupArray.length; i++){
                        searchFilterList.add(ageGroupArray[i].toString());
                    }
                     break;
                case 3:
                   //break, name doesn't have anything at second comboBox. 
                    searchFilterList.add("Use the text field below to write in the coach's name");
                     break;
            }
            
            //searchNameTextField is only editable when we are searching by name
            if (index == 3){
                searchNameTextField.setDisable(false);
            }else{
                searchNameTextField.setDisable(true);
                searchNameTextField.setText("Name");
            }
            searchNameTextField.setEditable(true);
            trainingRecordFilterComboBox.getItems().clear();
            trainingRecordFilterComboBox.getItems().addAll(searchFilterList);
            trainingRecordFilterComboBox.getSelectionModel().select(0);
                    
        }
    }
    
    private ArrayList<TrainingRecord> retrieveTrainingRecordsBySearchType(int searchTypeIndex, String filter){
        ArrayList<TrainingRecord> retrievedTrainingRecords = new ArrayList<>();
        if (searchTypeIndex > -1 && searchTypeIndex < 4){
            switch (searchTypeIndex){
                case 0: //type
                     retrievedTrainingRecords = (ArrayList<TrainingRecord>) TrainingRecord.viewTrainingRecordsByType(filter);
                     break;
                case 1://discipline
                    retrievedTrainingRecords = (ArrayList<TrainingRecord>) TrainingRecord.viewTrainingRecordsByDiscipline(filter);
                     break;
                case 2://age group
                    retrievedTrainingRecords = (ArrayList<TrainingRecord>) TrainingRecord.viewTrainingRecordsByAgeGroup(filter);
                     break;
                case 3: //coach name
                    retrievedTrainingRecords = (ArrayList<TrainingRecord>) TrainingRecord.viewTrainingsByCoachName(filter);
            }
        }
        return retrievedTrainingRecords;
       }
}

