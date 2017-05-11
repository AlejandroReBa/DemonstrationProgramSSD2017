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
    TextField searchNameTextField, nameTextField, telNumberTextField,
            addressTextField;

    @FXML
    DatePicker searchDateDatePicker, addDateDatePicker;

    @FXML
    ComboBox<String> teamComboBox, teamFilterComboBox;
    
    @FXML
    ComboBox<String> sexAddComboBox, typeAddComboBox, ageGroupAddComboBox,
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
        /*
        try {
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat format = new SimpleDateFormat(pattern);

            LocalDate birthdayLocalDate = addDateDatePicker.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String selectedBirthday = birthdayLocalDate.format(formatter);

            Date newBirthday = format.parse(selectedBirthday);
            String newName = nameTextField.getText();
            String newAddress = addressTextField.getText();
            String newTelNumber = telNumberTextField.getText();
            
            String type = typeAddComboBox.getSelectionModel().getSelectedItem();
            String newAgeGroup = ageGroupAddComboBox.getSelectionModel().getSelectedItem();
            String newQualification = qualificationAddComboBox.getSelectionModel().getSelectedItem();
            String newSex = sexAddComboBox.getSelectionModel().getSelectedItem();

            Membership newMembership;
            if (type.equals(typeEnum.Athlete.name())){
                newMembership = new Athlete(newName, newAddress, newTelNumber,
                newSex, newBirthday, newAgeGroup);
            }else if (type.equals(typeEnum.Coach.name())){
                newMembership = new Coach(newName, newAddress, newTelNumber,
                newSex, newBirthday, newQualification);
            }else if (type.equals(typeEnum.Official.name())){
                newMembership = new Official(newName, newAddress, newTelNumber,
                newSex, newBirthday, newQualification);
            }else if (type.equals(typeEnum.Administration.name())){
                newMembership = new StaffAdmin(newName, newAddress, newTelNumber,
                newSex, newBirthday);
            }else{
                newMembership = new Membership(newName, newAddress, newTelNumber,
                newSex, newBirthday, true);
            }
            
            this.showMembershipsButtonAction(new ActionEvent());

            resultTextArea.setText("The membership has been added successfully");
        } catch (ParseException ex) {
            //exception never reached due to the use of a DatePicker
        }
        */
        
    }
    @FXML
    private void modifyTeamButtonAction(ActionEvent event) {
        /*
        if (!membershipsListView.getItems().isEmpty()) {
            int selectedIndex = membershipsListView.getSelectionModel().getSelectedIndex();
            Membership selectedMembership = membershipsListView.getItems().get(selectedIndex);
            try {
                String pattern = "dd/MM/yyyy";
                SimpleDateFormat format = new SimpleDateFormat(pattern);

                LocalDate birthdayLocalDate = addDateDatePicker.getValue();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String selectedBirthday = birthdayLocalDate.format(formatter);

                Date newBirthday = format.parse(selectedBirthday);
                selectedMembership.setBirthday(newBirthday);

                selectedMembership.setName(nameTextField.getText());
                selectedMembership.setTelephone(telNumberTextField.getText());
                selectedMembership.setAddress(addressTextField.getText());
                
                //at the moment all details can be modified except Sex
                selectedMembership.setType(typeAddComboBox.getSelectionModel().getSelectedItem());
                selectedMembership.setAgeGroup(ageGroupAddComboBox.getSelectionModel().getSelectedItem());
                selectedMembership.setQualification(qualificationAddComboBox.getSelectionModel().getSelectedItem());
                
                this.showMembershipsButtonAction(new ActionEvent());

                resultTextArea.setText("The membership has been modified successfully");

            } catch (ParseException ex) {
                //exception never reached due to the use of a DatePicker
            }

            //buttonReturn.getScene().getWindow().hide();
        }
        */

    }

    @FXML 
    private void searchTeamsButtonAction(ActionEvent event) {
        
        String viewBy = this.teamComboBox.getSelectionModel().getSelectedItem();
        //another combo box with the age, type, etc required by the user.
        String filter = "";
        Date newDate = null;
        String selectedDate = "";
        if (viewBy.equals("Name")){
            filter = this.searchNameTextField.getText();
            /*
        }else if (viewBy.equals("Date")){
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat format = new SimpleDateFormat(pattern);

            LocalDate dateLocalDate = searchDateDatePicker.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            selectedDate = dateLocalDate.format(formatter);

            try {
                newDate = format.parse(selectedDate);
            } catch (ParseException ex) {
                //never throwed because we are using a DatePicker
            }
            */
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
            String text = "View of teams by " + viewBy + " = " + filter + selectedDate +
                    "\nDisplaying " + retrievedTeams.size() + " found results.";
            resultTextArea.setText(text);
            this.teamsListView.getItems().addAll(retrievedTeams);
        }
    }
        
        /*
        if (!agentsListView.getItems().isEmpty()) {
            int selectedIndex = agentsListView.getSelectionModel().getSelectedIndex();
            Agent selectedAgent = agentsListView.getItems().get(selectedIndex);
            for (Organisation org : Organisation.findAllOrganisation()) {
                org.deleteAgent(selectedAgent);
            }
            this.showAgentsButtonAction(new ActionEvent());

            resultTextArea.setText("The agent has been deleted successfully");

            //buttonReturn.getScene().getWindow().hide();
        }
        
    }
*/
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
            typeAddComboBox.getSelectionModel().select(selectedTeam.getType());
            ageGroupAddComboBox.getSelectionModel().select(selectedTeam.getAgeGroup());
            
            
            athletesMembersListView.getItems().clear();
            for (Athlete t : selectedTeam.getAthletesList()){
                athletesMembersListView.getItems().add(t);
            }
            
            
            ArrayList<Athlete> allAthletes = (ArrayList<Athlete>)Athlete.getAthletesList();
            
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
        int eventsSelectedIndex = this.teamsListView.getSelectionModel().getSelectedIndex();
        if (eventsSelectedIndex > 0){
            if (pickAthletesComboBox.getSelectionModel().getSelectedIndex() < 1){
                resultTextArea.setText("No athlete selected");
            }else{
                 Athlete pickedAthlete = pickAthletesComboBox.getSelectionModel().getSelectedItem();
                athletesMembersListView.getItems().add(pickedAthlete);
                resultTextArea.setText("The athlete has been picked");
            }
        }else{
            resultTextArea.setText("No athlete selected");
        }
    }
    
    //delete the selected team (selected at tamsParticipatingListView, not at comboBox)
    //from the list of participatings in the event
    @FXML
    private void deleteAthleteActionEvent(ActionEvent ev) {
       int currentIndex = athletesMembersListView.getSelectionModel().getSelectedIndex();
       if (currentIndex < 0){
           resultTextArea.setText("No athlete selected");
       }else{
           athletesMembersListView.getItems().remove(currentIndex);
           resultTextArea.setText("The athlete has been deleted from the team");
       }
       
    }
    
    @FXML
    private void acceptChangesActionEvent(ActionEvent ev) {      
        int teamsSelectedIndex = this.teamsListView.getSelectionModel().getSelectedIndex();
        if (teamsSelectedIndex > 0){
            Team selectedTeam = this.teamsListView.getSelectionModel().getSelectedItem();
            ArrayList<Athlete> allTeamMembers = new ArrayList<Athlete>();
            resultTextArea.setText("The members of the team have been updated");
            for (Athlete ath: athletesMembersListView.getItems()){
                allTeamMembers.add(ath);
            }
            selectedTeam.setMembersOfTeam(allTeamMembers);
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
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        searchTypeList = new ArrayList<String>();
        searchTypeList.add("Age Group"); //index 0
        searchTypeList.add("Type");
        searchTypeList.add("Name");
        searchTypeList.add("Sex"); //index 3
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
        
        Event.typeEnum[] typeArray = Event.typeEnum.values();
        for (int i=0; i < typeArray.length; i++){
            this.typeAddComboBox.getItems().add(typeArray[i].toString());
        }
        this.typeAddComboBox.getSelectionModel().select(0);
        
        
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
        if (index > -1 && index < 4){
            searchFilterList = new ArrayList<String>();
            switch (index){
                case 0:
                    ageGroupRelatedEnum[] ageGroupArray = Event.ageGroupRelatedEnum.values();
                    for (int i=0; i < ageGroupArray.length; i++){
                        searchFilterList.add(ageGroupArray[i].toString());
                    }
                     break;
                case 1:
                    Event.typeEnum[] typeArray = Event.typeEnum.values();
                    for (int i=0; i < typeArray.length; i++){
                        searchFilterList.add(typeArray[i].toString());
                    }
                     break;
                case 2:
                    //break, name doesn't have anything at second comboBox. 
                    searchFilterList.add("Use the text field below to write in the name");
                     break;
                case 3:
                    genderEnum[] sexArray = Event.genderEnum.values();
                    for (int i=0; i < sexArray.length; i++){
                        searchFilterList.add(sexArray[i].toString());
                    }
                     break;
            }
            
            //searchNameTextField is only editable when we are searching by name
            if (index == 2){
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
        if (searchTypeIndex > -1 && searchTypeIndex < 5){
            switch (searchTypeIndex){
                case 0:
                     retrievedTeams = (ArrayList<Team>)Team.viewTeamsByAgeGroup(filter);
                     break;
                case 1:
                    retrievedTeams = (ArrayList<Team>)Team.viewTeamsByType(filter);
                     break;
                case 2:
                    retrievedTeams = (ArrayList<Team>)Team.viewTeamsByName(filter);
                     break;
                case 3:
                    retrievedTeams = (ArrayList<Team>)Team.viewTeamsByGender(filter);
                     break;
            }
        }
        return retrievedTeams;
       }
}

