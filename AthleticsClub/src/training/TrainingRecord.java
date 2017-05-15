/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import membership.Athlete;
import membership.Coach;
import membership.Membership;

/**
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */
public class TrainingRecord implements Serializable{ 
//another class because "Coaches hold separate records relating to their athletes, training routine..."
    private static final long serialVersionUID = 42L;
    public static List<TrainingRecord> trainingRecordsList = new ArrayList<>();
    private static int incrementalId = 0;
    
    private int id;
    private int athleteId;
    private int coachId;
    private int trainingId;
    private String record;
    //add date? date is already in training
    
    public TrainingRecord (int athleteIdIn, int coachIdIn, int trainingIdIn, String recordIn){
        this.athleteId = athleteIdIn;
        this.coachId = coachIdIn;
        this.trainingId = trainingIdIn;
        this.record = recordIn;
        
        this.id = incrementalId;
        incrementalId++;
        trainingRecordsList.add(this);
        
    }
    
    public static List<TrainingRecord> getTrainingRecords(){
        return trainingRecordsList;
    }
    
    ///Should I to add getTrainingRecordbyId, or viewTrainingRecord by coach, athlete...?
    public int getAthleteId(){
        return this.athleteId;
    }
    
    public Athlete getAthlete(){
        return (Athlete)Membership.getMembersList().get(this.athleteId);
    }
    
    public int getCoachId(){
        return this.coachId;
    }
    
    public Coach getCoach(){
        return (Coach)Membership.getMembersList().get(this.coachId);
    }
    
    public int getTrainingId(){
        return this.trainingId;
    }
    
    public Training getTraining(){
        return (Training)Training.getTrainingsList().get(this.trainingId);
    }
    
    public String getRecord(){
        return this.record;
    }
    
    public String toString(){ //do not forget about serialize/deserialize trainingRecord...
        String res = "";
        ArrayList<Membership> members  = (ArrayList<Membership>)Membership.getMembersList();
        Training training = Training.getTrainingsList().get(this.trainingId);
        res += "Athlete: " + members.get(this.athleteId).getName();
        res += " - Coach: " + members.get(this.coachId).getName();
        res += " - Training: \"" + training.getType() + " - " +
                training.getDiscipline() + "\"";
        res += "\nDate: " + training.getDate();
        //res += "\nRecord: \n" + this.record;
        
        return res;
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        incrementalId++;
    }
    
    public static List<TrainingRecord> viewTrainingRecordsByType(String type){
        List<TrainingRecord> resTrainingRecords = new ArrayList<>();
        for (TrainingRecord currentTrainingRecord : trainingRecordsList){
            if (currentTrainingRecord.getTraining().getType().equals(type)){
                resTrainingRecords.add(currentTrainingRecord);
            }
        }
        return resTrainingRecords;
    }
    
    public static List<TrainingRecord> viewTrainingRecordsByDiscipline(String discipline){
        List<TrainingRecord> resTrainingRecords = new ArrayList<>();
        for (TrainingRecord currentTrainingRecord : trainingRecordsList){
            if (currentTrainingRecord.getTraining().getDiscipline().equals(discipline)){
                resTrainingRecords.add(currentTrainingRecord);
            }
        }
        return resTrainingRecords;
    }
    
    public static List<TrainingRecord> viewTrainingRecordsByAgeGroup(String ageGroup){
        List<TrainingRecord> resTrainingRecords = new ArrayList<>();
        for (TrainingRecord currentTrainingRecord : trainingRecordsList){
            if (currentTrainingRecord.getTraining().getAgeGroup().equals(ageGroup)){
                resTrainingRecords.add(currentTrainingRecord);
            }
        }
        return resTrainingRecords;
    }
    
    public static List<TrainingRecord> viewTrainingsByCoachName(String coachName){
        List<TrainingRecord> resTrainingRecords = new ArrayList<>();
        int lastCharIndex = coachName.length();
        for (TrainingRecord currentTrainingRecord : trainingRecordsList){
            String currentCoachName = Membership.getMembersList().get(currentTrainingRecord.coachId).getName();
            if (currentCoachName.length() >= coachName.length() &&
                    currentCoachName.toLowerCase().substring(0, lastCharIndex)
                    .equals(coachName.toLowerCase())){
                resTrainingRecords.add(currentTrainingRecord);
            }  
        }   
        return resTrainingRecords;
    }
}
