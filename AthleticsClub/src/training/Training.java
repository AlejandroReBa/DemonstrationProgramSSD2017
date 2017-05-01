/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import membership.Coach;

/**
 *
 * @author Alejandro Reyes
 */
public class Training {
    enum typeEnum {RoadDistance, XCountryDistance, Track, Field, Gym};
    enum disciplineTrainingEnum {Hurdling, Sprinting, Jumping, Throwing};
    enum ageGroupRelatedEnum {U13, U15, U17, U20, Senior, Masters};
    
    private static int incrementalId = 1;
    public static List<Training> trainingsList = new ArrayList<>();
    
    private int id;
    private typeEnum type;
    private int coachId;
    private disciplineTrainingEnum discipline;
    private ageGroupRelatedEnum ageGroup;
    private Date date;
    
    
    public Training (String typeIn, int coachIdIn, String disciplineIn,
            String ageGroupIn, Date dateIn){
        this.type = typeEnum.valueOf(typeIn);
        this.coachId = coachIdIn;
        this.discipline = disciplineTrainingEnum.valueOf(disciplineIn);
        this.ageGroup = ageGroupRelatedEnum.valueOf(ageGroupIn);
        this.date = dateIn;
        
        this.id = incrementalId;
        incrementalId++;
        
        trainingsList.add(this);
    }
    
    public int getId(){
        return this.id;
    }
    
    public String getType(){
        return this.type.toString();
    }
    
    public int getCoach(){
        return this.coachId;
    }
    
    public String getDiscipline(){
        return this.discipline.toString();
    }
    
    public String getAgeGroup(){
        return this.ageGroup.toString();
    }
    
    //TO DO
    public static Training viewTrainingById(int id){
        Training resTraining = null;
        for (Training currentTraining : trainingsList){
            if (currentTraining.id == id){
                resTraining = currentTraining;
                break;
            }
        }
        return resTraining;
    }
    
    //TO DO
    public static Training viewTrainingByType(String type){
        Training resTraining = null;
        for (Training currentTraining : trainingsList){
            if (currentTraining.type.toString().equals(type)){
                resTraining = currentTraining;
                break;
            }
        }
        return resTraining;
    }
    
    //TO DO
    public static Training viewTrainingByDiscipline(String discipline){
        Training resTraining = null;
        for (Training currentTraining : trainingsList){
            if (currentTraining.discipline.toString().equals(discipline)){
                resTraining = currentTraining;
                break;
            }
        }
        return resTraining;
    }
    
    //TO DO
    public static Training viewTrainingByAgeGroup(String ageGroup){
        Training resTraining = null;
        for (Training currentTraining : trainingsList){
            if (currentTraining.ageGroup.toString().equals(ageGroup)){
                resTraining = currentTraining;
                break;
            }
        }
        return resTraining;
    }
    
    //TO DO
    public static Training viewTrainingByDate(Date date){
        Training resTraining = null;
        for (Training currentTraining : trainingsList){
            if (currentTraining.date.equals(date)){ //perhaps is more complex..
                resTraining = currentTraining;
                break;
            }
        }
        return resTraining;
    }
    
}
