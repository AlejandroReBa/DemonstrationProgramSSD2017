/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Alejandro Reyes
 */
public class Training {
    private int id;
    private String type;
    private int coach;
    private String discipline;
    private String ageGroup;
    private Date date;
    public static List<Training> trainingList = new ArrayList<>();
    
    public Training (String typeIn, int coachIn, String disciplineIn,
            String ageGroupIn, Date dateIn){
        this.type = typeIn;
        this.coach = coachIn;
        this.discipline = disciplineIn;
        this.ageGroup = ageGroupIn;
        this.date = dateIn;
        
        trainingList.add(this);
    }
    
    public String getType(){
        return this.type;
    }
    
    public int getCoach(){
        return this.coach;
    }
    
    public String getDiscipline(){
        return this.discipline;
    }
    
    public String getAgeGroup(){
        return this.ageGroup;
    }
    
    //TO DO
    public Training viewTrainingById(int id){
        return null;
    }
    
    //TO DO
    public Training viewTrainingByType(String type){
        return null;
    }
    
    //TO DO
    public Training viewTrainingByDiscipline(String discipline){
        return null;
    }
    
    //TO DO
    public Training viewTrainingByAgeGroup(String ageGroup){
        return null;
    }
    
    //TO DO
    public Training viewTrainingByDate(Date date){
        return null;
    }
    
}
