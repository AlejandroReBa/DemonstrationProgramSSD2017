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
public class MembershipGUIController implements Initializable {

    private ArrayList<String> searchTypeList;
    private ArrayList<String> searchFilterList; //this changes depends on searchTypeList
    /**
     * Initializes the controller class.
     */
    public MembershipGUIController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MembershipGUI.fxml"));

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

    @FXML
    Button buttonReturn, showMembershipsButton, searchMembershipsButton;

    @FXML
    ListView<Membership> membershipsListView;

    @FXML
    Label resultLabel;

    @FXML
    TextArea resultTextArea;

    @FXML
    TextField searchNameTextField, nameTextField, telNumberTextField,
            addressTextField;

    @FXML
    DatePicker birthdayDatePicker;

    @FXML
    ComboBox<String> membershipComboBox, membershipFilterComboBox;
    
    @FXML
    ComboBox<String> sexAddComboBox, typeAddComboBox, ageGroupAddComboBox,
            qualificationAddComboBox;
    

    @FXML
    private void showMembershipsButtonAction(ActionEvent event) {      
        //to make it beautiful: custom List view with HBox
        //Not implemented, source: http://stackoverflow.com/questions/36121707/javafx-custom-listview-appearance
        membershipsListView.getItems().clear();
        membershipsListView.getItems().addAll(Membership.getMembersList());
        resultTextArea.setText("All memberships have been loaded"
                + "\nDisplaying " + Membership.getMembersList().size() + " found results.");
        if (membershipsListView.getItems().size() > 0){
            membershipsListView.getSelectionModel().select(0);
        }
    }

    @FXML
    private void addMembershipButtonAction(ActionEvent event) {
        
        try {
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat format = new SimpleDateFormat(pattern);

            LocalDate birthdayLocalDate = birthdayDatePicker.getValue();
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
        
    }
    @FXML
    private void modifyMembershipButtonAction(ActionEvent event) {
        if (!membershipsListView.getItems().isEmpty()) {
            int selectedIndex = membershipsListView.getSelectionModel().getSelectedIndex();
            Membership selectedMembership = membershipsListView.getItems().get(selectedIndex);
            try {
                String pattern = "dd/MM/yyyy";
                SimpleDateFormat format = new SimpleDateFormat(pattern);

                LocalDate birthdayLocalDate = birthdayDatePicker.getValue();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String selectedBirthday = birthdayLocalDate.format(formatter);

                Date newBirthday = format.parse(selectedBirthday);
                selectedMembership.setBirthday(newBirthday);

                selectedMembership.setName(nameTextField.getText());
                selectedMembership.setTelephone(telNumberTextField.getText());
                selectedMembership.setAddress(addressTextField.getText());
                
                //at the moment all details can be modified except Sex
                //and type. If you want to change the type you have to create
                //a new one. Because change type implies delete the membership
                //from one list and added it to another...future feature.
    
                //selectedMembership.setType(typeAddComboBox.getSelectionModel().getSelectedItem());
                selectedMembership.setAgeGroup(ageGroupAddComboBox.getSelectionModel().getSelectedItem());
                selectedMembership.setQualification(qualificationAddComboBox.getSelectionModel().getSelectedItem());
                
                this.showMembershipsButtonAction(new ActionEvent());

                resultTextArea.setText("The membership has been modified successfully");

            } catch (ParseException ex) {
                //exception never reached due to the use of a DatePicker
            }

        }
        

    }

    @FXML
    
    private void searchMembershipsButtonAction(ActionEvent event) {
        String viewBy = this.membershipComboBox.getSelectionModel().getSelectedItem();
        //another combo box with the age, type, etc required by the user.
        String filter = "";
        if (viewBy.equals("Name")){
            filter = this.searchNameTextField.getText();
        }else{
            filter = this.membershipFilterComboBox.getSelectionModel().getSelectedItem();
        }
        

        this.membershipsListView.getItems().clear();
        ArrayList<Membership> retrievedMemberships = 
                retrieveMembershipsBySearchType(this.membershipComboBox.getSelectionModel().getSelectedIndex(),
                        filter);
        
        if (retrievedMemberships.isEmpty()){
            resultTextArea.setText("No matches found for " + viewBy + " = " + filter);
        }else{
            String text = "View of memberships by " + viewBy + " = " + filter +
                    "\nDisplaying " + retrievedMemberships.size() + " found results.";
            resultTextArea.setText(text);
            this.membershipsListView.getItems().addAll(retrievedMemberships);
        }
        
        
        if (membershipsListView.getItems().size() > 0){
            membershipsListView.getSelectionModel().select(0);
        }
    }

    @FXML
    private void listMembershipViewClickedHandle(MouseEvent event) {
        if (!this.membershipsListView.getItems().isEmpty()) {
            int selectedIndex = this.membershipsListView.getSelectionModel().getSelectedIndex();
            Membership selectedMembership = membershipsListView.getItems().get(selectedIndex);

            nameTextField.setText(selectedMembership.getName());

            Date selectedBirthday = selectedMembership.getBirthday();
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            String birthdayString = format.format(selectedBirthday);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(birthdayString, formatter);

            birthdayDatePicker.setValue(localDate);

            telNumberTextField.setText(selectedMembership.getTelephone());
            addressTextField.setText(selectedMembership.getAddress());
            
            sexAddComboBox.getSelectionModel().select(selectedMembership.getSex());
            typeAddComboBox.getSelectionModel().select(selectedMembership.getType());
            ageGroupAddComboBox.getSelectionModel().select(selectedMembership.getAgeGroup());
            qualificationAddComboBox.getSelectionModel().select(selectedMembership.getQualification());
            resultTextArea.setText("The membership details have been loaded into"
                    + " left fields");
        }
    }
    
     @FXML
    private void membershipComboBoxChanges() {
        if (!membershipComboBox.getItems().isEmpty()) {
            int selectedIndex = membershipComboBox.getSelectionModel().getSelectedIndex();
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
        this.birthdayDatePicker.setValue(LocalDate.now());
        searchTypeList = new ArrayList<String>();
        searchTypeList.add("Age Group"); //index 0
        searchTypeList.add("Type");
        searchTypeList.add("Qualification");
        searchTypeList.add("Name");
        searchTypeList.add("Sex"); //index 4
        membershipComboBox.getItems().addAll(searchTypeList);
        membershipComboBox.getSelectionModel().select(0);
    
        setNewFilterList(0);
        
        //initialize comboBox for modifying and adding events
        ageGroupEnum[] ageGroupArray = Membership.ageGroupEnum.values();
        for (int i=0; i < ageGroupArray.length; i++){
            this.ageGroupAddComboBox.getItems().add(ageGroupArray[i].toString());
        }
        this.ageGroupAddComboBox.getSelectionModel().select(0);
        
        typeEnum[] typeArray = Membership.typeEnum.values();
        for (int i=0; i < typeArray.length; i++){
            this.typeAddComboBox.getItems().add(typeArray[i].toString());
        }
        this.typeAddComboBox.getSelectionModel().select(0);
                
        qualificationsEnum[] qualificationsArray = Membership.qualificationsEnum.values();
        for (int i=0; i < qualificationsArray.length; i++){
            this.qualificationAddComboBox.getItems().add(qualificationsArray[i].toString());
        }
        this.qualificationAddComboBox.getSelectionModel().select(0);
        
        sexEnum[] sexArray = Membership.sexEnum.values();
        for (int i=0; i < sexArray.length; i++){
            this.sexAddComboBox.getItems().add(sexArray[i].toString());
        }
        this.sexAddComboBox.getSelectionModel().select(0);
                    
    }
    
    //support method to set new filter List matching the type of search
    //the user has chosen
    private void setNewFilterList(int index){
        if (index > -1 && index < 5){
            searchFilterList = new ArrayList<String>();
            switch (index){
                case 0:
                    ageGroupEnum[] ageGroupArray = Membership.ageGroupEnum.values();
                    for (int i=0; i < ageGroupArray.length; i++){
                        searchFilterList.add(ageGroupArray[i].toString());
                    }
                     break;
                case 1:
                    typeEnum[] typeArray = Membership.typeEnum.values();
                    for (int i=0; i < typeArray.length; i++){
                        searchFilterList.add(typeArray[i].toString());
                    }
                     break;
                case 2:
                    qualificationsEnum[] qualificationsArray = Membership.qualificationsEnum.values();
                    for (int i=0; i < qualificationsArray.length; i++){
                        searchFilterList.add(qualificationsArray[i].toString());
                    }
                     break;
                case 3:
                    //break, name doesn't have anything at second comboBox. 
                    searchFilterList.add("Use the text field below to write in the name");
                     break;
                case 4:
                    sexEnum[] sexArray = Membership.sexEnum.values();
                    for (int i=0; i < sexArray.length; i++){
                        searchFilterList.add(sexArray[i].toString());
                    }
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
            membershipFilterComboBox.getItems().clear();
            membershipFilterComboBox.getItems().addAll(searchFilterList);
            membershipFilterComboBox.getSelectionModel().select(0);
                    
        }
    }
    
    private ArrayList<Membership> retrieveMembershipsBySearchType(int searchTypeIndex, String filter){
        ArrayList<Membership> retrievedMemberships = new ArrayList<>();
        if (searchTypeIndex > -1 && searchTypeIndex < 5){
            switch (searchTypeIndex){
                case 0:
                     retrievedMemberships = (ArrayList<Membership>)Membership.viewMembershipsByAgeGroup(filter);
                     break;
                case 1:
                    retrievedMemberships = (ArrayList<Membership>)Membership.viewMembershipsByType(filter);
                     break;
                case 2:
                    retrievedMemberships = (ArrayList<Membership>)Membership.viewMembershipsByQualification(filter);
                     break;
                case 3:
                    retrievedMemberships = (ArrayList<Membership>)Membership.viewMembershipByName(filter);
                     break;
                case 4:
                    retrievedMemberships = (ArrayList<Membership>)Membership.viewMembershipsBySex(filter);
                     break;
            }
        }
        return retrievedMemberships;
       }
}

