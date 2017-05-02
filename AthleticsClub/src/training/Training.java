package training;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import membership.Coach;
import membership.Membership;

/**
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */
public class Training {
    enum typeEnum {RoadDistance, XCountryDistance, Track, Field, Gym};
    enum disciplineTrainingEnum {Hurdling, Sprinting, Jumping, Throwing,
    Weighting, Repetitions, Roading};
    enum ageGroupRelatedEnum {U13, U15, U17, U20, Senior, Masters};
    
    private static int incrementalId = 0;
    protected static List<Training> trainingsList = new ArrayList<>();
    
    private int id;
    private typeEnum type;
    //not initialized yet, does not exist negative ID
    private int coachId = -1;
    private disciplineTrainingEnum discipline;
    private ageGroupRelatedEnum ageGroup;
    private Date date;
    
   
    public Training (String typeIn, String disciplineIn,
            String ageGroupIn){
        this.type = typeEnum.valueOf(typeIn);
        this.discipline = disciplineTrainingEnum.valueOf(disciplineIn);
        this.ageGroup = ageGroupRelatedEnum.valueOf(ageGroupIn);
        this.date = Date.from(Instant.now());
        this.coachId = -1;
        
        this.id = incrementalId;
        incrementalId++;
        
        trainingsList.add(this);
    }
    
    public Training (String typeIn, String disciplineIn,
            String ageGroupIn, Date dateIn, int coachIdIn){
        this.type = typeEnum.valueOf(typeIn);
        this.discipline = disciplineTrainingEnum.valueOf(disciplineIn);
        this.ageGroup = ageGroupRelatedEnum.valueOf(ageGroupIn);
        this.date = dateIn;
        this.coachId = coachIdIn;
        
        this.id = incrementalId;
        incrementalId++;
        
        trainingsList.add(this);
    }
    
    //singleton pattern
    public static List<Training> getTrainingsList(){
        return trainingsList;
    }
    
    //attribute that can't be changed
    public int getId(){
        return this.id;
    }
    
    public String getType(){
        return this.type.toString();
    }
    
    public void setType(String typeIn){
        this.type = typeEnum.valueOf(typeIn);
    }
    
    public int getCoach(){
        return this.coachId;
    }
    
    public void setCoach(int coachIdIn){
        this.coachId = coachIdIn;
    }
    
    public String getDiscipline(){
        return this.discipline.toString();
    }
    
    public void setDiscipline(String disciplineIn){
        this.discipline = disciplineTrainingEnum.valueOf(disciplineIn);
    }
    
    public String getAgeGroup(){
        return this.ageGroup.toString();
    }
    
    public void setAgeGroup(String ageGroupIn){
        this.ageGroup = ageGroupRelatedEnum.valueOf(ageGroupIn);
    }
    
    public Date getDate(){
        return this.date;
    }
    
    public void setDate (Date dateIn){
        this.date = dateIn;
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
    
    public String toString(){
        String coach;
        if (this.coachId > -1){//if id == -1 display error
           coach = Membership.getMembersList().get(this.coachId).getName();
        }else{
            coach = "NO COACH ASSIGNED";
        }
        String res = "TRAINING. Type: " + this.getType()
                + ", Discipline: " + this.getDiscipline()
                + ", Age Group: " + this.getAgeGroup()
                + ", Date: " + this.getDate()
                + ", Coach: " + coach;
        
        return res;
    }
    
}
