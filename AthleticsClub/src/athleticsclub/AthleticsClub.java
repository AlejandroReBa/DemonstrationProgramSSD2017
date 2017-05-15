package athleticsclub;

import event.Event;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import membership.Membership;
import membership.Team;
import training.Training;
import training.TrainingRecord;

/**
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */
public class AthleticsClub extends Application{

    //JAVA FX
     @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("AthleticsClub.fxml"));
        
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        
        loadFiles(); //deserialize objects
        
        stage.show();
    }
    

    public static void main(String[] args) {
        launch(args); //JAVAFX
    }
 
    
    private static void loadFiles(){
        deserializeMemberships();
        deserializeEvents();
        deserializeTeams();
        deserializeTrainings();
        deserializeTrainingRecords();
        
    }
    
    //reference: https://www.tutorialspoint.com/java/java_serialization.htm
    //class to serialize objects
    private static void serializeObject(Object ob){
        try {
         FileOutputStream fileOut =
         new FileOutputStream("object.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(ob);
         out.close();
         fileOut.close();
         System.out.println("Serialized data is saved in object.ser");
      }catch(IOException i) {
         i.printStackTrace();
      }
    }
    
    //class to deserialize Membership
    private static Membership deserializeMembership(){
        Membership member = null;
      try {
         FileInputStream fileIn = new FileInputStream("object.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         member = (Membership) in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i) {
         i.printStackTrace();
      }catch(ClassNotFoundException c) {
         System.out.println("Membership class not found");
         c.printStackTrace();
      }
      
      return member;
    }
    
    
    //serialize List<Membership> (including athletes, coachs...)
    public static void serializeMemberships(ArrayList<Membership> membershipsList){
        try {
         FileOutputStream fileOut =
         new FileOutputStream("memberships.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(membershipsList);
         out.close();
         fileOut.close();
         System.out.println("Serialized data is saved in memberships.ser");
      }catch(IOException i) {
          System.err.println("-----------------------------> FAIL ERROR NOT SERIALIZED");
         i.printStackTrace();
      }
    }
    
    //deserialize List<Membership> (including athletes, coachs...)
    public static ArrayList<Membership> deserializeMemberships(){
        ArrayList<Membership> membershipsList = null;
      try {
         FileInputStream fileIn = new FileInputStream("memberships.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         membershipsList = (ArrayList<Membership>)in.readObject();
         //added to insert deserialized memberships into static membershipsList
         Membership.membersList = new ArrayList<Membership>(membershipsList);
         for(Membership m : membershipsList){
             m.addItself();
         }
         //although is a little bit dirty though...
         in.close();
         fileIn.close();
      }catch(IOException i) {
         i.printStackTrace();
      }catch(ClassNotFoundException c) {
         System.out.println("Membership class not found");
         c.printStackTrace();
      }
      
      return membershipsList;
    }

    
    //serialize List<Event>
    public static void serializeEvents(ArrayList<Event> eventsList){
        try {
         FileOutputStream fileOut =
         new FileOutputStream("events.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(eventsList);
         out.close();
         fileOut.close();
         System.out.println("Serialized data is saved in events.ser");
      }catch(IOException i) {
          System.err.println("-----------------------------> FAIL ERROR NOT SERIALIZED");
         i.printStackTrace();
      }
    }
    
     //deserialize List<Event>
    public static ArrayList<Event> deserializeEvents(){
        ArrayList<Event> eventsList = null;
      try {
         FileInputStream fileIn = new FileInputStream("events.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         eventsList = (ArrayList<Event>)in.readObject();
         //added to insert deserialized events into static eventsList
         Event.eventsList = new ArrayList<>(eventsList);
         in.close();
         fileIn.close();
      }catch(IOException i) {
         i.printStackTrace();
      }catch(ClassNotFoundException c) {
         System.out.println("Event class not found");
         c.printStackTrace();
      }
      
      return eventsList;
    }
    
    
    //serialize List<Team>
    public static void serializeTeams(ArrayList<Team> teamsList){
        try {
         FileOutputStream fileOut =
         new FileOutputStream("teams.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(teamsList);
         out.close();
         fileOut.close();
         System.out.println("Serialized data is saved in teams.ser");
      }catch(IOException i) {
          System.err.println("-----------------------------> FAIL ERROR NOT SERIALIZED");
         i.printStackTrace();
      }
    }
    
     //deserialize List<Team>
    public static ArrayList<Team> deserializeTeams(){
        ArrayList<Team> teamsList = null;
      try {
         FileInputStream fileIn = new FileInputStream("teams.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         teamsList = (ArrayList<Team>)in.readObject();
         //added to insert deserialized events into static eventsList
        Team.teamsList = new ArrayList<>(teamsList);
        /* 
        for(Team t : teamsList){
             t.addItself();
         }
        */
         //although is a little bit dirty though...
         in.close();
         fileIn.close();
      }catch(IOException i) {
         i.printStackTrace();
      }catch(ClassNotFoundException c) {
         System.out.println("Team class not found");
         c.printStackTrace();
      }
      
      return teamsList;
    }
    
    
     //serialize List<Training>
    public static void serializeTrainings(ArrayList<Training> trainingsList){
        try {
         FileOutputStream fileOut =
         new FileOutputStream("trainings.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(trainingsList);
         out.close();
         fileOut.close();
         System.out.println("Serialized data is saved in trainings.ser");
      }catch(IOException i) {
          System.err.println("-----------------------------> FAIL ERROR NOT SERIALIZED");
         i.printStackTrace();
      }
    }
    
     //deserialize List<Training>
    public static ArrayList<Training> deserializeTrainings(){
        ArrayList<Training> trainingsList = null;
      try {
         FileInputStream fileIn = new FileInputStream("trainings.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         trainingsList = (ArrayList<Training>)in.readObject();
         //added to insert deserialized trainings into static eventsList
         Training.trainingsList = new ArrayList<>(trainingsList);

         in.close();
         fileIn.close();
      }catch(IOException i) {
         i.printStackTrace();
      }catch(ClassNotFoundException c) {
         System.out.println("Training class not found");
         c.printStackTrace();
      }
      
      return trainingsList;
    }
    
     //serialize List<TrainingRecord>
    public static void serializeTrainingRecords(ArrayList<TrainingRecord> trainingRecordsList){
        try {
         FileOutputStream fileOut =
         new FileOutputStream("trainingRecords.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(trainingRecordsList);
         out.close();
         fileOut.close();
         System.out.println("Serialized data is saved in trainingRecords.ser");
      }catch(IOException i) {
          System.err.println("-----------------------------> FAIL ERROR NOT SERIALIZED");
         i.printStackTrace();
      }
    }
    
     //deserialize List<TrainingRecord>
    public static ArrayList<TrainingRecord> deserializeTrainingRecords(){
        ArrayList<TrainingRecord> trainingRecordsList = null;
      try {
         FileInputStream fileIn = new FileInputStream("trainingRecords.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         trainingRecordsList = (ArrayList<TrainingRecord>)in.readObject();
         //added to insert deserialized trainings into static eventsList
         TrainingRecord.trainingRecordsList = new ArrayList<>(trainingRecordsList);

         in.close();
         fileIn.close();
      }catch(IOException i) {
         i.printStackTrace();
      }catch(ClassNotFoundException c) {
         System.out.println("TrainingRecord class not found");
         c.printStackTrace();
      }
      
      return trainingRecordsList;
    }
}
