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
import static membership.Membership.typeEnum.Athlete;
import membership.Official;
import membership.StaffAdmin;
import membership.Team;
import training.Training;

/**
 * FXML Controller class
 *
 * @author Alejandro Reyes
 */
public class EventGUIController implements Initializable {

    private ArrayList<String> searchTypeList;
    private ArrayList<String> searchFilterList; //this changes depends on searchTypeList
    /**
     * Initializes the controller class.
     */
    public EventGUIController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("EventGUI.fxml"));

        loader.setController(this);
        
        try {
            loader.load();
            
            //here or at initialize?
            
            
            /*
            ageGroupEnum[] ageGroupArray = Membership.ageGroupEnum.values();
            for (int i=0; i<ageGroupArray.length; i++){
                     searchFilterList.add(ageGroupArray[i].toString());
                }
            membershipFilterComboBox.getItems().addAll(searchFilterList);
            membershipFilterComboBox.getSelectionModel().select(0);
            /*
            for (Organisation org : Organisation.findAllOrganisation()) {
            organisationComboBox.getItems().add(org);
            }
            if (!organisationComboBox.getItems().isEmpty()) {
            organisationComboBox.getSelectionModel().select(0);
            Organisation selectedOrganisation = organisationComboBox.getItems().get(0);
            for (Event ev : selectedOrganisation.findAllEvent()) {
                eventComboBox.getItems().add(ev);
            }
            if (!eventComboBox.getItems().isEmpty()) {
                eventComboBox.getSelectionModel().select(0);
            }
                
        }*/
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
    Button buttonReturn, showEventsButton, searchEventsButton;

    @FXML
    ListView<Event> eventsListView;
    
    @FXML
    ListView<Team> teamsParticipatingListView;

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
    ComboBox<String> eventComboBox, eventFilterComboBox;
    
    @FXML
    ComboBox<String> sexAddComboBox, typeAddComboBox, ageGroupAddComboBox,
            transportAddComboBox;
    
    @FXML
    ComboBox<Team> pickTeamsComboBox;
    
    //@FXML
    //ComboBox<Organisation> organisationComboBox;

    //FXML
    //ComboBox<Event> eventComboBox;

    @FXML
    private void showEventsButtonAction(ActionEvent event) {      
        //to make it beautiful: custom List view with HBox
        //Not implemented, source: http://stackoverflow.com/questions/36121707/javafx-custom-listview-appearance
        eventsListView.getItems().clear();
        eventsListView.getItems().addAll(Event.getEvents());
        resultTextArea.setText("All events have been loaded"
                + "\nDisplaying " + Event.getEvents().size() + " found results.");
        
        if (eventsListView.getItems().size() > 0){
            eventsListView.getSelectionModel().select(0);
        }
        /*
        organisationComboBox.getItems().clear();
        eventComboBox.getItems().clear();
        */

        /*
        
        for (Organisation org : Organisation.findAllOrganisation()) {
            organisationComboBox.getItems().add(org);
            for (Agent agent : org.findAllAgent()) {
                agentsListView.getItems().add(agent);
            }
        }

        if (!organisationComboBox.getItems().isEmpty()) {
            organisationComboBox.getSelectionModel().select(0);
            Organisation selectedOrganisation = organisationComboBox.getItems().get(0);
            for (Event ev : selectedOrganisation.findAllEvent()) {
                eventComboBox.getItems().add(ev);
            }
            if (!eventComboBox.getItems().isEmpty()) {
                eventComboBox.getSelectionModel().select(0);
            }

        }

        if (!agentsListView.getItems().isEmpty()) {
            agentsListView.getSelectionModel().select(0);
            Agent selectedAgent = agentsListView.getItems().get(0);
            nameTextField.setText(selectedAgent.getName());

            Date selectedBirthday = selectedAgent.getBirthday();

            String pattern = "dd/MM/yyyy";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            String birthdayString = format.format(selectedBirthday);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(birthdayString, formatter);

            addDateDatePicker.setValue(localDate);

            telNumberTextField.setText(selectedAgent.getTelNumber());

            //buttonReturn.getScene().getWindow().hide();
        }
        */
    }

    @FXML
    private void addEventButtonAction(ActionEvent event) {
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
    private void modifyEventButtonAction(ActionEvent event) {
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
    
    private void searchEventsButtonAction(ActionEvent event) {
        
        String viewBy = this.eventComboBox.getSelectionModel().getSelectedItem();
        //another combo box with the age, type, etc required by the user.
        String filter = "";
        Date newDate = null;
        String selectedDate = "";
        if (viewBy.equals("Name")){
            filter = this.searchNameTextField.getText();
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
        }else{
            filter = this.eventFilterComboBox.getSelectionModel().getSelectedItem();
        }
        

        this.eventsListView.getItems().clear();
        ArrayList<Event> retrievedEvents = 
                retrieveEventsBySearchType(this.eventComboBox.getSelectionModel().getSelectedIndex(),
                        filter, newDate); //filter=="" || newDate==null
        
        if (retrievedEvents.isEmpty()){
            resultTextArea.setText("No matches found for " + viewBy + " = " + filter + selectedDate); //filter="" || selectedDate=""
        }else{
            String text = "View of events by " + viewBy + " = " + filter + selectedDate +
                    "\nDisplaying " + retrievedEvents.size() + " found results.";
            resultTextArea.setText(text);
            this.eventsListView.getItems().addAll(retrievedEvents);
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
    private void listEventViewClickedHandle(MouseEvent event) {
        
        if (!this.eventsListView.getItems().isEmpty()) {
            int selectedIndex = this.eventsListView.getSelectionModel().getSelectedIndex();
            Event selectedEvent = eventsListView.getItems().get(selectedIndex);

            nameTextField.setText(selectedEvent.getName());

            Date selectedDate = selectedEvent.getDate();
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            String selectedDateString = format.format(selectedDate);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(selectedDateString, formatter);

            addDateDatePicker.setValue(localDate);

            
            sexAddComboBox.getSelectionModel().select(selectedEvent.getGender());
            typeAddComboBox.getSelectionModel().select(selectedEvent.getType());
            ageGroupAddComboBox.getSelectionModel().select(selectedEvent.getAgeGroup());
            transportAddComboBox.getSelectionModel().select(selectedEvent.getTransport());
            
            
            teamsParticipatingListView.getItems().clear();
            for (Team t: selectedEvent.getParticipants()){
                teamsParticipatingListView.getItems().add(t);
            }
            
            ArrayList<Team> allTeams = (ArrayList<Team>)Team.getTeams();
            
            pickTeamsComboBox.getItems().addAll(allTeams);
            if (!allTeams.isEmpty()){
                pickTeamsComboBox.getSelectionModel().select(0);
            }
            
            
            resultTextArea.setText("The event details have been loaded into left fields");
        }
        
    }
    
     @FXML
    private void eventComboBoxChanges() {
        if (!eventComboBox.getItems().isEmpty()) {
            int selectedIndex = eventComboBox.getSelectionModel().getSelectedIndex();
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
    
    //add picked team to the list of teams for the selected event
     @FXML
    private void addTeamActionEvent(ActionEvent ev) {
        int eventsSelectedIndex = this.eventsListView.getSelectionModel().getSelectedIndex();
        if (eventsSelectedIndex > 0){
            if (pickTeamsComboBox.getSelectionModel().getSelectedIndex() < 1){
                resultTextArea.setText("No team selected");
            }else{
                 Team pickedTeam = pickTeamsComboBox.getSelectionModel().getSelectedItem();
                teamsParticipatingListView.getItems().add(pickedTeam);
                resultTextArea.setText("The team has been picked");
            }
        }else{
            resultTextArea.setText("No event selected");
        }
    }
    
    //delete the selected team (selected at tamsParticipatingListView, not at comboBox)
    //from the list of participatings in the event
    @FXML
    private void deleteTeamActionEvent(ActionEvent ev) {
       int currentIndex = teamsParticipatingListView.getSelectionModel().getSelectedIndex();
       if (currentIndex < 0){
           resultTextArea.setText("No team selected");
       }else{
           teamsParticipatingListView.getItems().remove(currentIndex);
           resultTextArea.setText("The team has been deleted from the event");
       }
       
    }
    
    @FXML
    private void acceptChangesActionEvent(ActionEvent ev) {      
        int eventsSelectedIndex = this.eventsListView.getSelectionModel().getSelectedIndex();
        if (eventsSelectedIndex > 0){
            Event selectedEvent = this.eventsListView.getSelectionModel().getSelectedItem();
            ArrayList<Team> allParticipants = new ArrayList<Team>();
            resultTextArea.setText("The participants for the event has been updated");
            for (Team t : teamsParticipatingListView.getItems()){
                allParticipants.add(t);
            }
            selectedEvent.setParticipants(allParticipants);
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
        this.searchDateDatePicker.setValue(LocalDate.now());//DatePicker to search
        this.addDateDatePicker.setValue(LocalDate.now());//DatePicker to add/modify
        searchTypeList = new ArrayList<String>();
        searchTypeList.add("Age Group"); //index 0
        searchTypeList.add("Type");
        searchTypeList.add("Name");
        searchTypeList.add("Sex");
        searchTypeList.add("Date"); //index 4
        eventComboBox.getItems().addAll(searchTypeList);
        eventComboBox.getSelectionModel().select(0);
    
        setNewFilterList(0);
        
        //initialize comboBox for modifying and adding events
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
        
        Event.transportEnum[] transportArray = Event.transportEnum.values();
        for (int i=0; i < transportArray.length; i++){
            this.transportAddComboBox.getItems().add(transportArray[i].toString());
        }
        this.transportAddComboBox.getSelectionModel().select(0);
                    
    }
    
    //support method to set new filter List matching the type of search
    //the user has chosen
    private void setNewFilterList(int index){
        if (index > -1 && index < 5){
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
                case 4:
                    //break, date doesn't have anything at second comboBox. 
                    searchFilterList.add("Use the date picker below to pick the date");
                     break;
            }
            
            //searchNameTextField is only editable when we are searching by name
            if (index == 2){
                searchNameTextField.setDisable(false);
                this.searchDateDatePicker.setDisable(true);
            }else if (index == 4){
                //-------------------------------------------------> TODOOOOOOOOOOOOOOOOOOOOOOO to disable data picker!!!!!!!!!!!!!
                this.searchDateDatePicker.setDisable(false);
                searchNameTextField.setDisable(true);
                searchNameTextField.setText("Name");
            }else{
                this.searchDateDatePicker.setDisable(true);
                searchNameTextField.setDisable(true);
                searchNameTextField.setText("Name");
            }
            searchNameTextField.setEditable(true);
            eventFilterComboBox.getItems().clear();
            eventFilterComboBox.getItems().addAll(searchFilterList);
            eventFilterComboBox.getSelectionModel().select(0);
                    
        }
    }
    
    private ArrayList<Event> retrieveEventsBySearchType(int searchTypeIndex, String filter, Date dateFilter){
        ArrayList<Event> retrievedEvents = new ArrayList<>();
        if (searchTypeIndex > -1 && searchTypeIndex < 5){
            switch (searchTypeIndex){
                case 0:
                     retrievedEvents = (ArrayList<Event>)Event.viewEventsByAgeGroup(filter);
                     break;
                case 1:
                    retrievedEvents = (ArrayList<Event>)Event.viewEventsByType(filter);
                     break;
                case 2:
                    retrievedEvents = (ArrayList<Event>)Event.viewEventByName(filter);
                     break;
                case 3:
                    retrievedEvents = (ArrayList<Event>)Event.viewEventsByGender(filter);
                     break;
                case 4:
                    retrievedEvents = (ArrayList<Event>)Event.viewEventsByDate(dateFilter);
                     break;
            }
        }
        return retrievedEvents;
       }
}

