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
import java.util.ArrayList;
import membership.Membership.ageGroupEnum;
import membership.Membership.qualificationsEnum;
import membership.Membership.sexEnum;
import membership.Membership.typeEnum;
import training.Training;

/**
 * FXML Controller class
 *
 * @author Alejandro Reyes
 */
public class SecondGUIController implements Initializable {

    private ArrayList<String> searchTypeList;
    private ArrayList<String> searchFilterList; //this changes depends on searchTypeList
    /**
     * Initializes the controller class.
     */
    public SecondGUIController() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("SecondGUI.fxml"));

        loader.setController(this);
        
        try {
            loader.load();
            
            //here or at initialize?
            searchTypeList = new ArrayList<String>();
            searchTypeList.add("Age Group"); //index 0
            searchTypeList.add("Type");
            searchTypeList.add("Qualification");
            searchTypeList.add("Name");
            searchTypeList.add("Sex"); //index 4
            membershipComboBox.getItems().addAll(searchTypeList);
            membershipComboBox.getSelectionModel().select(0);
                        
            setNewFilterList(0);
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
    Button buttonReturn, showMembershipsButton, searchMembershipsButton;

    @FXML
    ListView<Membership> membershipsListView;

    @FXML
    Label resultLabel;

    @FXML
    TextArea resultTextArea;

    @FXML
    TextField searchNameTextField, nameTextField, telNumberTextField;

    @FXML
    DatePicker birthdayDatePicker;

    @FXML
    ComboBox<String> membershipComboBox, membershipFilterComboBox;
    
    //@FXML
    //ComboBox<Organisation> organisationComboBox;

    //FXML
    //ComboBox<Event> eventComboBox;

    @FXML
    private void showMembershipsButtonAction(ActionEvent event) {

        resultTextArea.setText("ALL MEMBERSHIPS LOADED");
        
        //to make it beautiful: custom List view with HBox
        //Not implemented, source: http://stackoverflow.com/questions/36121707/javafx-custom-listview-appearance
        membershipsListView.getItems().clear();
        membershipsListView.getItems().addAll(Membership.getMembersList());
        
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

            birthdayDatePicker.setValue(localDate);

            telNumberTextField.setText(selectedAgent.getTelNumber());

            //buttonReturn.getScene().getWindow().hide();
        }
        */
    }

    @FXML
    private void addAgentButtonAction(ActionEvent event) {
        /*
        try {
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat format = new SimpleDateFormat(pattern);

            LocalDate birthdayLocalDate = birthdayDatePicker.getValue();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String selectedBirthday = birthdayLocalDate.format(formatter);

            Date newBirthday = format.parse(selectedBirthday);
            String newName = nameTextField.getText();
            String newTelNumber = telNumberTextField.getText();

            Agent newAgent = new Agent(newName, newBirthday, newTelNumber);
            Organisation org = organisationComboBox.getSelectionModel().getSelectedItem();
            org.addAgent(newAgent);
            newAgent.addEvent(eventComboBox.getSelectionModel().getSelectedItem());
            eventComboBox.getSelectionModel().getSelectedItem().addAgent(newAgent);

            this.showAgentsButtonAction(new ActionEvent());

            resultTextArea.setText("The agent has been added successfully");
        } catch (ParseException ex) {
            //exception never reached due to the use of a DatePicker
        }
        */
    }
    @FXML
    private void modifyAgentButtonAction(ActionEvent event) {
        /*
        if (!agentsListView.getItems().isEmpty()) {
            int selectedIndex = agentsListView.getSelectionModel().getSelectedIndex();
            Agent selectedAgent = agentsListView.getItems().get(selectedIndex);
            try {
                String pattern = "dd/MM/yyyy";
                SimpleDateFormat format = new SimpleDateFormat(pattern);

                LocalDate birthdayLocalDate = birthdayDatePicker.getValue();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String selectedBirthday = birthdayLocalDate.format(formatter);

                Date newBirthday = format.parse(selectedBirthday);
                selectedAgent.setBirthday(newBirthday);

                selectedAgent.setName(nameTextField.getText());
                selectedAgent.setTelNumber(telNumberTextField.getText());
                this.showAgentsButtonAction(new ActionEvent());

                resultTextArea.setText("The agent has been modified successfully");

            } catch (ParseException ex) {
                //exception never reached due to the use of a DatePicker
            }

            //buttonReturn.getScene().getWindow().hide();
        }
        */

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
        */
    }

    @FXML
    private void listMembershipViewClickedHandle(MouseEvent event) {
        /*
        if (!agentsListView.getItems().isEmpty()) {
            int selectedIndex = agentsListView.getSelectionModel().getSelectedIndex();
            Agent selectedAgent = agentsListView.getItems().get(selectedIndex);

            nameTextField.setText(selectedAgent.getName());

            Date selectedBirthday = selectedAgent.getBirthday();
            String pattern = "dd/MM/yyyy";
            SimpleDateFormat format = new SimpleDateFormat(pattern);
            String birthdayString = format.format(selectedBirthday);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            LocalDate localDate = LocalDate.parse(birthdayString, formatter);

            birthdayDatePicker.setValue(localDate);

            telNumberTextField.setText(selectedAgent.getTelNumber());

            //buttonReturn.getScene().getWindow().hide();
        }
        */
    }
    
     @FXML
    private void membershipComboBoxChanges() {
        if (!membershipComboBox.getItems().isEmpty()) {
            int selectedIndex = membershipComboBox.getSelectionModel().getSelectedIndex();
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
    


    @FXML
    private void buttonReturnClickedAction(ActionEvent event) {
        buttonReturn.getScene().getWindow().hide();
    }

    @FXML
    private void buttonExitClickedAction(ActionEvent event) {
        //buttonReturn.getScene().getWindow().hide();
        Platform.exit();
        System.exit(0);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {        
        this.birthdayDatePicker.setValue(LocalDate.now());
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

