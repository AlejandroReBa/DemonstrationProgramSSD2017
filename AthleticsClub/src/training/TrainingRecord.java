/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */
public class TrainingRecord implements Serializable{ //another class because "Coaches hold separate records relating to their athletes, training routine..."
    private static List<TrainingRecord> trainingRecordsList;
    private static int incrementalId = 1;
    
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
    
    ///Should I to add getTrainingRecordbyId, or viewTrainingRecord by coach, athlete...?
    public int getAthleteId(){
        return this.athleteId;
    }
    
    public int getCoachId(){
        return this.coachId;
    }
    
    public int getTrainingId(){
        return this.trainingId;
    }
    
    public String getRecord(){
        return this.record;
    }
}
