/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athleticsclub;

import java.io.IOException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import membership.Membership;
import event.Event;
import event.Event.ageGroupRelatedEnum;
import event.Event.genderEnum;
import java.util.ArrayList;
import membership.Athlete;
import membership.Coach;
import membership.Membership.ageGroupEnum;
import membership.Membership.qualificationsEnum;
import membership.Membership.sexEnum;
import membership.Membership.typeEnum;
import membership.Official;
import membership.StaffAdmin;
import membership.Team;
import training.Training;
import training.TrainingRecord;

/**
 * FXML Controller class
 *
 * @author Alejandro Reyes
 */
public class TeamGUIController implements Initializable {

    private ArrayList<String> searchTypeList;
    private ArrayList<String> searchFilterList; //this changes depends on searchTypeList
    /**
     * Initializes the controller class.
     */
    public TeamGUIController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TeamGUI.fxml"));

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
    Button buttonNext;

    /*
    @FXML
    public void disableButtonNext() {
        this.buttonNext.disableProperty().set(true);
    }
    */

    @FXML
    Button buttonReturn, showTeamsButton, searchTeamsButton;

    @FXML
    ListView<Team> teamsListView;
    
    @FXML
    ListView<Athlete> athletesMembersListView;

    @FXML
    Label resultLabel;

    @FXML
    TextArea resultTextArea;

    @FXML
    TextField searchNameTextField, nameTextField;
    
    @FXML
    DatePicker searchDateDatePicker, addDateDatePicker;

    @FXML
    ComboBox<String> teamComboBox, teamFilterComboBox;
    
    @FXML
    ComboBox<String> sexAddComboBox, ageGroupAddComboBox,
            transportAddComboBox;
    
    @FXML
    ComboBox<Athlete> pickAthletesComboBox;
    
    @FXML
    ComboBox<Official> captainAddComboBox;
    
    //@FXML
    //ComboBox<Organisation> organisationComboBox;

    //FXML
    //ComboBox<Event> eventComboBox;

    @FXML
    private void showTeamsButtonAction(ActionEvent event) {      
        //to make it beautiful: custom List view with HBox
        //Not implemented, source: http://stackoverflow.com/questions/36121707/javafx-custom-listview-appearance
        teamsListView.getItems().clear();
        teamsListView.getItems().addAll(Team.getTeams());
        resultTextArea.setText("All teams have been loaded"
                + "\nDisplaying " + Team.getTeams().size() + " found results.");
        
        if (teamsListView.getItems().size() > 0){
            teamsListView.getSelectionModel().select(0);
        }
    }

    @FXML
    private void addTeamButtonAction(ActionEvent event) {
            String newName = nameTextField.getText();
            
            String newAgeGroup = ageGroupAddComboBox.getSelectionModel().getSelectedItem();
            String newGender = sexAddComboBox.getSelectionModel().getSelectedItem();
            Official captain = captainAddComboBox.getSelectionModel().getSelectedItem();

            Team team = new Team(newName, newGender, newAgeGroup, captain.getId());
           
            this.showTeamsButtonAction(new ActionEvent());
            resultTextArea.setText("The team has been created successfully"); 
    }
    
    
    @FXML
    private void modifyTeamButtonAction(ActionEvent event) {
        if (!teamsListView.getItems().isEmpty()) {
            int selectedIndex = teamsListView.getSelectionModel().getSelectedIndex();
            Team selectedTeam = teamsListView.getItems().get(selectedIndex);
            
            selectedTeam.setName(nameTextField.getText());
                
            //at the moment all details can be modified except Sex
            selectedTeam.setAgeGroup(ageGroupAddComboBox.getSelectionModel().getSelectedItem());
            selectedTeam.setGender(sexAddComboBox.getSelectionModel().getSelectedItem());
            selectedTeam.changeCaptainId(captainAddComboBox.getSelectionModel().getSelectedItem().getId());
            
                
            this.showTeamsButtonAction(new ActionEvent());

            resultTextArea.setText("The team has been modified successfully");
        }
    }

    @FXML 
    private void searchTeamsButtonAction(ActionEvent event) {
        
        String viewBy = this.teamComboBox.getSelectionModel().getSelectedItem();
        //another combo box with the age, type, etc required by the user.
        String filter = "";
        if (viewBy.equals("Name")){
            filter = this.searchNameTextField.getText();
        }else{
            filter = this.teamFilterComboBox.getSelectionModel().getSelectedItem();
        }
        

        this.teamsListView.getItems().clear();
        ArrayList<Team> retrievedTeams = 
                retrieveTeamsBySearchType(this.teamComboBox.getSelectionModel().getSelectedIndex(),
                        filter);
        
        if (retrievedTeams.isEmpty()){
            resultTextArea.setText("No matches found for " + viewBy + " = " + filter);
        }else{
            String text = "View of teams by " + viewBy + " = " + filter +
                    "\nDisplaying " + retrievedTeams.size() + " found results.";
            resultTextArea.setText(text);
            this.teamsListView.getItems().addAll(retrievedTeams);
        }
    }
    
    
    @FXML
    private void listTeamViewClickedHandle(MouseEvent event) {
        
        if (!this.teamsListView.getItems().isEmpty()) {
            int selectedIndex = this.teamsListView.getSelectionModel().getSelectedIndex();
            Team selectedTeam = teamsListView.getItems().get(selectedIndex);

            nameTextField.setText(selectedTeam.getName());
            
            /*
            Date selectedDate = selectedEvent.getDate();
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            String selectedDateString = format.format(selectedDate);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(selectedDateString, formatter);

            addDateDatePicker.setValue(localDate);
            */
            
            sexAddComboBox.getSelectionModel().select(selectedTeam.getGender());
            ageGroupAddComboBox.getSelectionModel().select(selectedTeam.getAgeGroup());
            
            
            athletesMembersListView.getItems().clear();
            for (Athlete t : selectedTeam.getAthletesList()){
                athletesMembersListView.getItems().add(t);
            }
            
            //to display at ComboBox athletes are not already in only
            ArrayList<Athlete> athletesAlreadyIn = (ArrayList<Athlete>)selectedTeam.getAthletesList();
            ArrayList<Athlete> allAthletes = new ArrayList<>();
            for (Athlete t : Athlete.getAthletesList()){
                if (!athletesAlreadyIn.contains(t)){ //need to override equals/hashCode at Athlete
                    allAthletes.add(t);
                }
            }
            
            pickAthletesComboBox.getItems().clear(); //clear the previous list of athletes
            pickAthletesComboBox.getItems().addAll(allAthletes);
            if (!allAthletes.isEmpty()){
                pickAthletesComboBox.getSelectionModel().select(0);
            }
            
            
            resultTextArea.setText("The team details have been loaded into left fields");
        }
        
    }
    
     @FXML
    private void teamComboBoxChanges() {
        if (!teamComboBox.getItems().isEmpty()) {
            int selectedIndex = teamComboBox.getSelectionModel().getSelectedIndex();
            setNewFilterList(selectedIndex);
            
            /*
            String searchTypeList = membershipComboBox.getItems().get(selectedIndex);
            Organisation selectedOrganisation = membershipComboBox.getItems().get(selectedIndex);
            eventComboBox.getItems().clear();
            for (Event ev : selectedOrganisation.findAllEvent()) {
                eventComboBox.getItems().add(ev);
            }
            if (!eventComboBox.getItems().isEmpty()) {
                eventComboBox.getSelectionModel().select(0);
            }
            */
        }
    }
    
    @FXML
    private void organisationComboBoxHide() {
        /*
        if (!organisationComboBox.getItems().isEmpty()) {
            int selectedIndex = organisationComboBox.getSelectionModel().getSelectedIndex();
            Organisation selectedOrganisation = organisationComboBox.getItems().get(selectedIndex);
            eventComboBox.getItems().clear();
            for (Event ev : selectedOrganisation.findAllEvent()) {
                eventComboBox.getItems().add(ev);
            }
            if (!eventComboBox.getItems().isEmpty()) {
                eventComboBox.getSelectionModel().select(0);
            }
        }
        */
    }
    
    //add picked athlete to the list of teams for the selected event
     @FXML
    private void addAthleteActionEvent(ActionEvent ev) {
        int teamsSelectedIndex = this.teamsListView.getSelectionModel().getSelectedIndex();
        if (teamsSelectedIndex > -1){
            if (pickAthletesComboBox.getSelectionModel().getSelectedIndex() < 0){
                resultTextArea.setText("No athlete selected");
            }else{
                int athleteIndex = pickAthletesComboBox.getSelectionModel().getSelectedIndex();
                Athlete pickedAthlete = pickAthletesComboBox.getItems().remove(athleteIndex); //get Athlete and remove it from combobox
                if (pickAthletesComboBox.getItems().size() > 0){
                    pickAthletesComboBox.getSelectionModel().select(0); //select first athlete of the comboBox
                }
                athletesMembersListView.getItems().add(pickedAthlete); //add Athlete to listView
                resultTextArea.setText("The athlete has been picked");
            }
        }else{
            resultTextArea.setText("No athlete selected");
        }
    }
    
    //delete the selected athlete from the team (selected at athletesParticipatingListView
    //not at comboBox)from the list of participatings in the team
    @FXML
    private void deleteAthleteActionEvent(ActionEvent ev) {
       int currentIndex = athletesMembersListView.getSelectionModel().getSelectedIndex();
       if (currentIndex < 0){
           resultTextArea.setText("No athlete selected");
       }else{
           Athlete deletedAthlete = athletesMembersListView.getItems().remove(currentIndex); //get Athlete and remove it from listView
           pickAthletesComboBox.getItems().add(deletedAthlete); //add Athlete to ComboBox again
            pickAthletesComboBox.getSelectionModel().select(0); //select first athlete of the comboBox
           resultTextArea.setText("The athlete has been deleted from the team");
       }
       
    }
    
    @FXML
    private void acceptChangesActionEvent(ActionEvent ev) {      
        int teamsSelectedIndex = this.teamsListView.getSelectionModel().getSelectedIndex();
        if (teamsSelectedIndex > -1){
            Team selectedTeam = this.teamsListView.getSelectionModel().getSelectedItem();
            ArrayList<Athlete> allTeamMembers = new ArrayList<Athlete>();
            for (Athlete ath: athletesMembersListView.getItems()){
                allTeamMembers.add(ath);
            }
            selectedTeam.setMembersOfTeam(allTeamMembers);
            this.showTeamsButtonAction(new ActionEvent()); //refresh list of teams
            resultTextArea.setText("The members of the team have been updated");
        }else{
            resultTextArea.setText("No event selected");
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
        searchTypeList.add("Age Group"); //index 0
        searchTypeList.add("Name");
        searchTypeList.add("Sex"); //index 2
        teamComboBox.getItems().addAll(searchTypeList);
        teamComboBox.getSelectionModel().select(0);
    
        setNewFilterList(0);
        
        //initialize comboBox for modifying and adding Teams:
        //ageGroup, type and gender
        ageGroupRelatedEnum[] ageGroupArray = Event.ageGroupRelatedEnum.values();
        for (int i=0; i < ageGroupArray.length; i++){
            this.ageGroupAddComboBox.getItems().add(ageGroupArray[i].toString());
        }
        this.ageGroupAddComboBox.getSelectionModel().select(0);    
        
        genderEnum[] sexArray = Event.genderEnum.values();
        for (int i=0; i < sexArray.length; i++){
            this.sexAddComboBox.getItems().add(sexArray[i].toString());
        }
        this.sexAddComboBox.getSelectionModel().select(0);
        
        //addCaptain comboBox
        this.captainAddComboBox.getItems().addAll(Official.
                viewOfficialsByQualification(Membership.qualificationsEnum.None.toString()));
        if (captainAddComboBox.getItems().size() > 0 ){
            this.captainAddComboBox.getSelectionModel().select(0);
        }
        
    }
    
    //support method to set new filter List, matching the type of search
    //(first comboBox) the user has chosen
    private void setNewFilterList(int index){
        if (index > -1 && index < 3){
            searchFilterList = new ArrayList<String>();
            switch (index){
                case 0:
                    ageGroupRelatedEnum[] ageGroupArray = Event.ageGroupRelatedEnum.values();
                    for (int i=0; i < ageGroupArray.length; i++){
                        searchFilterList.add(ageGroupArray[i].toString());
                    }
                     break;
                case 1:
                    //break, name doesn't have anything at second comboBox. 
                    searchFilterList.add("Use the text field below to write in the name");
                     break;
                case 2:
                    genderEnum[] sexArray = Event.genderEnum.values();
                    for (int i=0; i < sexArray.length; i++){
                        searchFilterList.add(sexArray[i].toString());
                    }
                     break;
            }
            
            //searchNameTextField is only editable when we are searching by name
            if (index == 1){
                searchNameTextField.setDisable(false);
            }else{
                searchNameTextField.setDisable(true);
                searchNameTextField.setText("Name");
            }
            searchNameTextField.setEditable(true);
            teamFilterComboBox.getItems().clear();
            teamFilterComboBox.getItems().addAll(searchFilterList);
            teamFilterComboBox.getSelectionModel().select(0);
                    
        }
    }
    
    private ArrayList<Team> retrieveTeamsBySearchType(int searchTypeIndex, String filter){
        ArrayList<Team> retrievedTeams = new ArrayList<>();
        if (searchTypeIndex > -1 && searchTypeIndex < 3){
            switch (searchTypeIndex){
                case 0:
                     retrievedTeams = (ArrayList<Team>)Team.viewTeamsByAgeGroup(filter);
                     break;
                case 1:
                    retrievedTeams = (ArrayList<Team>)Team.viewTeamsByName(filter);
                     break;
                case 2:
                    retrievedTeams = (ArrayList<Team>)Team.viewTeamsByGender(filter);
                     break;
            }
        }
        return retrievedTeams;
       }
}

