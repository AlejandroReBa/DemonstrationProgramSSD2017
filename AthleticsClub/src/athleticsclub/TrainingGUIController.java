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
import java.util.Date;
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
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import membership.Membership;
import event.Event;
import event.Event.ageGroupRelatedEnum;
import java.util.ArrayList;
import membership.Athlete;
import membership.Coach;
import membership.Team;
import training.Training;
import training.Training.disciplineTrainingEnum;
import training.TrainingRecord;

/**
 * FXML Controller class
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */
public class TrainingGUIController implements Initializable {

    private ArrayList<String> searchTypeList;
    private ArrayList<String> searchFilterList; //this changes depends on searchTypeList
    /**
     * Initializes the controller class.
     */
    public TrainingGUIController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("TrainingGUI.fxml"));

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
    Button buttonReturn, showTrainingsButton, searchTrainingsButton;

    @FXML
    ListView<Training> trainingsListView;
    
    @FXML
    ListView<Athlete> athletesTrainingListView;

    @FXML
    Label resultLabel;

    @FXML
    TextArea resultTextArea, recordTextArea;

    @FXML
    TextField searchNameTextField;

    @FXML
    DatePicker searchDateDatePicker, addDateDatePicker;

    @FXML
    ComboBox<String> trainingComboBox, trainingFilterComboBox;
    
    @FXML
    ComboBox<String> disciplineAddComboBox, typeAddComboBox, ageGroupAddComboBox;
    
    @FXML
    ComboBox<Coach> coachAddComboBox;
    
    @FXML
    ComboBox<Athlete> selectAthleteComboBox,pickAthletesComboBox;

    @FXML
    private void showTrainingsButtonAction(ActionEvent event) {
        trainingsListView.getItems().clear();
        trainingsListView.getItems().addAll(Training.getTrainingsList());
        resultTextArea.setText("All trainings have been loaded"
                + "\nDisplaying " + Training.getTrainingsList().size() + " found results.");
        
        if (trainingsListView.getItems().size() > 0){
            trainingsListView.getSelectionModel().select(0);
        }
    }
    

    @FXML
    private void addTrainingButtonAction(ActionEvent event) {
        try {
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat format = new SimpleDateFormat(pattern);

            LocalDate dateLocalDate = searchDateDatePicker.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String selectedDate = dateLocalDate.format(formatter);

            Date newDate = format.parse(selectedDate);
            
            String type = typeAddComboBox.getSelectionModel().getSelectedItem();
            String newDiscipline = disciplineAddComboBox.getSelectionModel().getSelectedItem();
            String newAgeGroup = ageGroupAddComboBox.getSelectionModel().getSelectedItem();
            int newCoach = coachAddComboBox.getSelectionModel().getSelectedItem().getId();
            

            Training newTraining = new Training(type, newDiscipline, newAgeGroup, newDate, newCoach);
            
            this.showTrainingsButtonAction(new ActionEvent());

            resultTextArea.setText("The training has been added successfully");
        } catch (ParseException ex) {
            //exception never reached due to the use of a DatePicker
        }   
    }
    
    
    @FXML
    private void modifyTrainingButtonAction(ActionEvent event) {
        if (!trainingsListView.getItems().isEmpty()) {
            int selectedIndex = trainingsListView.getSelectionModel().getSelectedIndex();
            Training selectedTraining = trainingsListView.getItems().get(selectedIndex);
            try {
                String pattern = "dd/MM/yyyy";
                SimpleDateFormat format = new SimpleDateFormat(pattern);

                LocalDate dateLocalDate = searchDateDatePicker.getValue();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String selectedDate = dateLocalDate.format(formatter);

                Date newDate = format.parse(selectedDate);
                

                String type = typeAddComboBox.getSelectionModel().getSelectedItem();
                String newDiscipline = disciplineAddComboBox.getSelectionModel().getSelectedItem();
                String newAgeGroup = ageGroupAddComboBox.getSelectionModel().getSelectedItem();
                Coach newCoach = coachAddComboBox.getSelectionModel().getSelectedItem();
                

                selectedTraining.setDate(newDate);

                selectedTraining.setType(type);
                selectedTraining.setDiscipline(newDiscipline);
                selectedTraining.setAgeGroup(newAgeGroup);
                selectedTraining.setCoach(newCoach);

                this.showTrainingsButtonAction(new ActionEvent());

                resultTextArea.setText("The training has been modified successfully");

            } catch (ParseException ex) {
                //exception never reached due to the use of a DatePicker
            }
        }
    }

    
    @FXML
    private void searchTrainingsButtonAction(ActionEvent event) {
        
        String viewBy = this.trainingComboBox.getSelectionModel().getSelectedItem();
        //another combo box with the discipline, type, etc required by the user.
        String filter = "";
        Date newDate = null;
        String selectedDate = "";
        if (viewBy.equals("Coach Name")){
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
                //never throwed due to we are using a DatePicker
            }
        }else{
            filter = this.trainingFilterComboBox.getSelectionModel().getSelectedItem();
        }
        

        this.trainingsListView.getItems().clear();
        ArrayList<Training> retrievedTrainings = 
                retrieveTrainingsBySearchType(this.trainingComboBox.getSelectionModel().getSelectedIndex(),
                        filter, newDate); //filter=="" || newDate==null
        
        if (retrievedTrainings.isEmpty()){
            resultTextArea.setText("No matches found for " + viewBy + " = " + filter + selectedDate); //filter="" || selectedDate=""
        }else{
            String text = "View of trainings by " + viewBy + " = " + filter + selectedDate +
                    "\nDisplaying " + retrievedTrainings.size() + " found results.";
            resultTextArea.setText(text);
            this.trainingsListView.getItems().addAll(retrievedTrainings);
        }
    }
        
    
    @FXML
    private void listTrainingViewClickedHandle(MouseEvent event) {
        
        if (!this.trainingsListView.getItems().isEmpty()) {
            int selectedIndex = this.trainingsListView.getSelectionModel().getSelectedIndex();
            Training selectedTraining = trainingsListView.getItems().get(selectedIndex);

            Date selectedDate = selectedTraining.getDate();
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            String selectedDateString = format.format(selectedDate);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(selectedDateString, formatter);

            addDateDatePicker.setValue(localDate);

            
            disciplineAddComboBox.getSelectionModel().select(selectedTraining.getDiscipline());
            typeAddComboBox.getSelectionModel().select(selectedTraining.getType());
            ageGroupAddComboBox.getSelectionModel().select(selectedTraining.getAgeGroup());
            coachAddComboBox.getSelectionModel().select(selectedTraining.getCoach());

            
            athletesTrainingListView.getItems().clear();
            for (Athlete t : selectedTraining.getAthletesList()){
                athletesTrainingListView.getItems().add(t);
            }
            
            //to display at ComboBox athletes are not already in only
            ArrayList<Athlete> athletesAlreadyIn = (ArrayList<Athlete>)selectedTraining.getAthletesList();
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
            
            
            /*
            //select only athletes in the training that are currently being
            //trained by the coach conducting the training.
            //This is due to the existance of free athletes withouth coach
            //who are not available to track records.
            Coach selectedCoach = selectedTraining.getCoach();
            ArrayList<Athlete> athletesTrainedByCoach = (ArrayList<Athlete>)selectedCoach.getAthletesList();
            ArrayList<Athlete> athletesAvailableForRecord = new ArrayList<>();
            for (Athlete ath : selectedTraining.getAthletesList()){
                if (athletesTrainedByCoach.contains(ath)){
                    athletesAvailableForRecord.add(ath);
                }
            }
            */
            
            //just for now, because we can't add athletes to a coach at the moment...
            ArrayList<Athlete> athletesAvailableForRecord = (ArrayList<Athlete>)selectedTraining.getAthletesList(); 
            selectAthleteComboBox.getItems().clear(); //clear the previous list of teams
            selectAthleteComboBox.getItems().addAll(athletesAvailableForRecord);
            if (!athletesAvailableForRecord.isEmpty()){
                selectAthleteComboBox.getSelectionModel().select(0);
            }
            
            //you need to select and athlete and click accept before be able of writting the record
            recordTextArea.setDisable(true);
            recordTextArea.setText("New Record: ");
            
            resultTextArea.setText("The training details have been loaded into left fields");
        }
        
    }
    
     @FXML
    private void trainingComboBoxChanges() {
        if (!trainingComboBox.getItems().isEmpty()) {
            int selectedIndex = trainingComboBox.getSelectionModel().getSelectedIndex();
            setNewFilterList(selectedIndex);
        }
    }
    
   
    //method to manage "Add new record" GUI feature
     @FXML
     private void selectAthleteComboBoxChanges (){
         if (!trainingsListView.getItems().isEmpty() &&
                 selectAthleteComboBox.getSelectionModel().getSelectedIndex() > -1) {
            recordTextArea.setDisable(true);
            recordTextArea.setText("New Record: ");
        }
     }
     
     @FXML
     private void selectAthleteActionEvent (ActionEvent ev){
         if (!trainingsListView.getItems().isEmpty() && 
                 selectAthleteComboBox.getSelectionModel().getSelectedIndex() > -1) {
            recordTextArea.setDisable(false);
            recordTextArea.setText("New Record: ");
            recordTextArea.setEditable(true);
            Athlete currentAthlete = selectAthleteComboBox.getSelectionModel().getSelectedItem();
            Coach currentCoach = trainingsListView.getSelectionModel().getSelectedItem().getCoach();
            resultTextArea.setText("Writting a new record of Athlete \"" + 
                    currentAthlete.getName() + "\" - by Coach \"" + currentCoach.getName() + "\"");
         }else{
             resultTextArea.setText("Sorry, you haven't selected an athlete from"
                     + " this training to write a record.\nSelect first and try again");
         }
         
     }
     
     @FXML
     private void saveRecordActionEvent (ActionEvent ev){
         if (trainingsListView.getSelectionModel().getSelectedIndex() > -1 && 
                 selectAthleteComboBox.getSelectionModel().getSelectedIndex() > -1) {
            Athlete currentAthlete = selectAthleteComboBox.getSelectionModel().getSelectedItem();
            Training currentTraining = trainingsListView.getSelectionModel().getSelectedItem();
            Coach currentCoach = trainingsListView.getSelectionModel().getSelectedItem().getCoach();
            String record = recordTextArea.getText();
            new TrainingRecord (currentAthlete.getId(), currentCoach.getId(),
                    currentTraining.getId(), record);
            resultTextArea.setText("You have added a record of Athlete \"" + 
                    currentAthlete.getName() + "\" - by Coach \"\n" +
                    currentCoach.getName() + "\" at current training of \"" +
                    currentTraining.getType() + "\" for \"" +
                    currentTraining.getDiscipline() + "\"");
         }else{
             resultTextArea.setText("Sorry, you haven't selected an athlete from"
                     + " this training to write a record.\nSelect first and try again");
         }
     }
     
     
    
     //add picked athlete to the list of athletes for the selected training
     @FXML
    private void addAthleteActionEvent(ActionEvent ev) {
        int trainingSelectedIndex = this.trainingsListView.getSelectionModel().getSelectedIndex();
        if (trainingSelectedIndex > -1){
            if (pickAthletesComboBox.getSelectionModel().getSelectedIndex() < 0){
                resultTextArea.setText("No athlete selected");
            }else{
                int athleteIndex = pickAthletesComboBox.getSelectionModel().getSelectedIndex();
                Athlete pickedAthlete = pickAthletesComboBox.getItems().remove(athleteIndex); //get Athlete and remove it from combobox
                if (pickAthletesComboBox.getItems().size() > 0){
                    pickAthletesComboBox.getSelectionModel().select(0); //select first athlete of the comboBox
                }
                athletesTrainingListView.getItems().add(pickedAthlete); //add Athlete to listView
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
       int currentIndex = athletesTrainingListView.getSelectionModel().getSelectedIndex();
       if (currentIndex < 0){
           resultTextArea.setText("No athlete selected");
       }else{
           Athlete deletedAthlete = athletesTrainingListView.getItems().remove(currentIndex); //get Athlete and remove it from listView
           pickAthletesComboBox.getItems().add(deletedAthlete); //add Athlete to ComboBox again
           pickAthletesComboBox.getSelectionModel().select(0); //select first athlete of the comboBox
           resultTextArea.setText("The athlete has been deleted from the training");
       }
       
    }
    
    @FXML
    private void acceptChangesActionEvent(ActionEvent ev) {      
        int athleteSelectedIndex = this.trainingsListView.getSelectionModel().getSelectedIndex();
        if (athleteSelectedIndex > -1){
            Training selectedTraining = this.trainingsListView.getSelectionModel().getSelectedItem();
            ArrayList<Athlete> allAthletesTraining = new ArrayList<Athlete>();
            for (Athlete ath: athletesTrainingListView.getItems()){
                allAthletesTraining.add(ath);
            }
            selectedTraining.setAthletesList(allAthletesTraining);
            this.showTrainingsButtonAction(new ActionEvent()); //refresh list of trainings. More/less artist for write records..
            resultTextArea.setText("The athletes of the training have been updated");
        }else{
            resultTextArea.setText("No athlete selected");
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
        this.searchDateDatePicker.setValue(LocalDate.now());//DatePicker to search
        this.addDateDatePicker.setValue(LocalDate.now());//DatePicker to add/modify
        searchTypeList = new ArrayList<String>();
        searchTypeList.add("Type"); //index 0
        searchTypeList.add("Discipline");
        searchTypeList.add("Age Group");
        searchTypeList.add("Coach Name");
        searchTypeList.add("Date"); //index 4
        trainingComboBox.getItems().addAll(searchTypeList);
        trainingComboBox.getSelectionModel().select(0);
    
        setNewFilterList(0);
        
        //initialize comboBox for modifying and adding events
        
        Training.typeEnum[] typeArray = Training.typeEnum.values();
        for (int i=0; i < typeArray.length; i++){
            this.typeAddComboBox.getItems().add(typeArray[i].toString());
        }
        this.typeAddComboBox.getSelectionModel().select(0);
        
        disciplineTrainingEnum[] sexArray = Training.disciplineTrainingEnum.values();
        for (int i=0; i < sexArray.length; i++){
            this.disciplineAddComboBox.getItems().add(sexArray[i].toString());
        }
        this.disciplineAddComboBox.getSelectionModel().select(0);
        
        ageGroupRelatedEnum[] ageGroupArray = Event.ageGroupRelatedEnum.values();
        for (int i=0; i < ageGroupArray.length; i++){
            this.ageGroupAddComboBox.getItems().add(ageGroupArray[i].toString());
        }
        this.ageGroupAddComboBox.getSelectionModel().select(0);

        //add Coach comboBox
        this.coachAddComboBox.getItems().addAll(Coach.getCoachsList());
        if (coachAddComboBox.getItems().size() > 0 ){
            this.coachAddComboBox.getSelectionModel().select(0);
        }   
                    
    }
    
    //support method to set new filter List matching the type of search
    //the user has chosen
    private void setNewFilterList(int index){
        if (index > -1 && index < 5){
            searchFilterList = new ArrayList<String>();
            switch (index){
                case 0:
                    Training.typeEnum[] typeArray = Training.typeEnum.values();
                    for (int i=0; i < typeArray.length; i++){
                        searchFilterList.add(typeArray[i].toString());
                     }
                    break;
                case 1:
                    disciplineTrainingEnum[] disciplineArray = Training.disciplineTrainingEnum.values();
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
                case 4:
                    //break, date doesn't have anything at second comboBox. 
                    searchFilterList.add("Use the date picker below to pick the date");
                     break;
            }
            
            //searchNameTextField is only editable when we are searching by name
            if (index == 3){
                searchNameTextField.setDisable(false);
                this.searchDateDatePicker.setDisable(true);
            }else if (index == 4){
                this.searchDateDatePicker.setDisable(false);
                searchNameTextField.setDisable(true);
                searchNameTextField.setText("Name");
            }else{
                this.searchDateDatePicker.setDisable(true);
                searchNameTextField.setDisable(true);
                searchNameTextField.setText("Name");
            }
            searchNameTextField.setEditable(true);
            trainingFilterComboBox.getItems().clear();
            trainingFilterComboBox.getItems().addAll(searchFilterList);
            trainingFilterComboBox.getSelectionModel().select(0);
                    
        }
    }
    
    private ArrayList<Training> retrieveTrainingsBySearchType(int searchTypeIndex, String filter, Date dateFilter){
        ArrayList<Training> retrievedTrainings = new ArrayList<>();
        if (searchTypeIndex > -1 && searchTypeIndex < 5){
            switch (searchTypeIndex){
                case 0:
                     retrievedTrainings = (ArrayList<Training>)Training.viewTrainingsByType(filter);
                     break;
                case 1:
                    retrievedTrainings = (ArrayList<Training>)Training.viewTrainingsByDiscipline(filter);
                     break;
                case 2:
                    retrievedTrainings = (ArrayList<Training>)Training.viewTrainingByAgeGroup(filter);
                     break;
                case 3:
                    retrievedTrainings = (ArrayList<Training>)Training.viewTrainingsByCoachName(filter);
                     break;
                case 4:
                    retrievedTrainings = (ArrayList<Training>)Training.viewTrainingsByDate(dateFilter);
                     break;
            }
        }
        return retrievedTrainings;
       }
}

