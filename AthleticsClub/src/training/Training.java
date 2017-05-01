package training;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */
public class Training {
    enum typeEnum {RoadDistance, XCountryDistance, Track, Field, Gym};
    enum disciplineTrainingEnum {Hurdling, Sprinting, Jumping, Throwing};
    enum ageGroupRelatedEnum {U13, U15, U17, U20, Senior, Masters};
    
    private static int incrementalId = 1;
    public static List<Training> trainingsList = new ArrayList<>();
    
    private int id;
    private typeEnum type;
    //not initialized yet, does not exist negative ID
    private int coachId = -1;
    private disciplineTrainingEnum discipline;
    private ageGroupRelatedEnum ageGroup;
    private Date date;
    
   
    public Training (String typeIn, String disciplineIn,
            String ageGroupIn, Date dateIn){
        this.type = typeEnum.valueOf(typeIn);
        this.discipline = disciplineTrainingEnum.valueOf(disciplineIn);
        this.ageGroup = ageGroupRelatedEnum.valueOf(ageGroupIn);
        this.date = dateIn;
        
        this.id = incrementalId;
        incrementalId++;
        
        trainingsList.add(this);
    }
    
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
    
    public static List<Training> viewTrainingsByType(String type){
        List<Training> resTrainings = new ArrayList<>();
        for (Training currentTraining : trainingsList){
            if (currentTraining.type.toString().equals(type)){
                resTrainings.add(currentTraining);
            }
        }
        return resTrainings;
    }
    
    public static List<Training> viewTrainingsByDiscipline(String discipline){
        List<Training> resTrainings = new ArrayList<>();
        for (Training currentTraining : trainingsList){
            if (currentTraining.discipline.toString().equals(discipline)){
                resTrainings.add(currentTraining);
            }
        }
        return resTrainings;
    }
    
    public static List<Training> viewTrainingByAgeGroup(String ageGroup){
        List<Training> resTrainings = new ArrayList<>();
        for (Training currentTraining : trainingsList){
            if (currentTraining.ageGroup.toString().equals(ageGroup)){
                resTrainings.add(currentTraining);
            }
        }
        return resTrainings;
    }
    
    public static List<Training> viewTrainingsByDate(Date date){
        List<Training> resTrainings = new ArrayList<>();
        for (Training currentTraining : trainingsList){
            if (currentTraining.date.equals(date)){ //perhaps is more complex..
                resTrainings.add(currentTraining);
            }
        }
        return resTrainings;
    }
    
}
