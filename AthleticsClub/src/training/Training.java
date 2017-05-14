package training;

import event.Event;
import static event.Event.eventsList;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import membership.Athlete;
import membership.Coach;
import membership.Membership;

/**
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */
public class Training implements Serializable{
    public enum typeEnum {RoadDistance, XCountryDistance, Track, Sprinting, Field, Gym};
    public enum disciplineTrainingEnum {Hurdling, Sprinting, Jumping, Throwing,
    Weighting, Repetitions, Roading, Hamming};
    public enum ageGroupRelatedEnum {U13, U15, U17, U20, Senior, Masters};
    //no List<Athlete>...?
    private static int incrementalId = 0;
    public static List<Training> trainingsList = new ArrayList<>();
    
    private int id;
    private List<Integer> athletesList; //refers athletes identifiers
    private typeEnum type;
    //not initialized yet, does not exist negative ID
    private int coachId = -1; //only one coach conduct the training
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
        this.athletesList = new ArrayList<>();
        
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
        this.athletesList = new ArrayList<>();
        
        this.id = incrementalId;
        incrementalId++;
        
        trainingsList.add(this);
    }
    
    //singleton pattern
    public static List<Training> getTrainingsList(){
        return trainingsList;
    }
    
    public void setAthletesList(List<Athlete> members){
        this.athletesList = new ArrayList<>();
        for (Athlete ath : members){
            this.athletesList.add(ath.getId());
        }
    }
        
    public List<Athlete> getAthletesList(){
        ArrayList<Athlete> list = new ArrayList<>();
        ArrayList<Membership> listMembers = (ArrayList<Membership>)Membership.getMembersList();
        for (Integer currentID : this.athletesList){
            list.add((Athlete)listMembers.get(currentID));
        }
        return list;
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
    
    /*
    public int getCoach(){
        return this.coachId;
    }
    */
    
    public Coach getCoach(){
        return (Coach)Membership.getMembersList().get(this.coachId);
    }
    
    /*
    public void setCoach(int coachIdIn){
        this.coachId = coachIdIn;
    }
    */
    
    public void setCoach (Coach coachIn){
        this.coachId = coachIn.getId();
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
    
    public static List<Training> viewTrainingsByCoachName(String coachName){
        List<Training> resTrainings = new ArrayList<>();
        int lastCharIndex = coachName.length();
        for (Training currentTraining : trainingsList){
            String currentCoachName = Membership.getMembersList().get(currentTraining.coachId).getName();
            if (currentCoachName.length() >= coachName.length() &&
                    currentCoachName.toLowerCase().substring(0, lastCharIndex)
                    .equals(coachName.toLowerCase())){
                resTrainings.add(currentTraining);
            }  
        }   
        return resTrainings;
    }
    
    
    //TO DO
    public static List<Training> viewTrainingsByDate(Date date){
        List<Training> resTrainings = new ArrayList<>();
        for (Training currentTraining : trainingsList){ //fix to compare dates.......
            //java.time.LocalDateTime first = new java.time.LocalDateTime(currentEvent.getDate());
            //java.time.LocalDate = 
            //java.time.DateTimeComparator.getDateOnlyInstance();
            if (currentTraining.getDate().equals(date)){
                resTrainings.add(currentTraining);
            }
        }
        return resTrainings;
    }
    
    @Override
    public String toString(){
        String coach;
        if (this.coachId > -1){//if id == -1 display error
           coach = Membership.getMembersList().get(this.coachId).getName();
        }else{
            coach = "NO COACH ASSIGNED";
        }
        String res = "Type: " + this.getType()
                + ", Discipline: " + this.getDiscipline()
                + ", Age Group: " + this.getAgeGroup()
                + ", Date: " + this.getDate()
                + ", Coach: " + coach;
        
        return res;
    }
    
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
            in.defaultReadObject();
            incrementalId++;
    }
}
