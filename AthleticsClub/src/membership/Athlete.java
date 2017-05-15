package membership;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import training.Training;

/**
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */
public class Athlete extends Membership implements Serializable{
    //to avoid invalidClasException as deserialization
    //source: https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html
    private static final long serialVersionUID = 42L;
    
    public static List<Athlete> athletesList = new ArrayList<>();
    private List<Training> trainingsList;
    //private List<Event> eventsList; //dont needed, athletes are related with teams
    //and teams with events: maybe you will need List<Team>
    private List<Team> teamsList;

    public Athlete(String nameIn, String addressIn, String telIn, String sexIn, Date birthIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Athlete;
        this.trainingsList = new ArrayList<>();
        this.teamsList = new ArrayList<>();
        Membership.membersList.add(this);
        athletesList.add(this);
        
    }
    
    public Athlete(String nameIn, String addressIn, String telIn, String sexIn,
            Date birthIn, String ageGroupIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Athlete;
        this.trainingsList = new ArrayList<>();
        this.teamsList = new ArrayList<>();
        ageGroup = ageGroupEnum.valueOf(ageGroupIn);
        Membership.membersList.add(this);
        athletesList.add(this);
        
    }
    
    public Athlete(String nameIn, String addressIn, String telIn, String sexIn,
            Date birthIn, String ageGroupIn, List<Training> trainingsListIn,
            List<Team> teamsListIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Athlete;
        this.trainingsList = new ArrayList<>(trainingsListIn);
        this.teamsList = new ArrayList<>(teamsListIn);
        ageGroup = ageGroupEnum.valueOf(ageGroupIn);
        Membership.membersList.add(this);
        athletesList.add(this);
    }
    
    public static List<Athlete> getAthletesList(){
        return athletesList;
    }
    
    public List<Training> getTrainingsList(){
        return this.trainingsList;
    }
    
    public void addTraining(Training trainingIn){
        this.trainingsList.add(trainingIn);
    }
    
    public List<Team> getTeamsList(){
        return this.teamsList;
    }
    
    public void addTeam(Team teamIn){
        this.teamsList.add(teamIn);
    }
    
    @Override
    public String toString(){
        String res = super.toString();
        /* toString modified: too much information for interface
        for (Training t : this.trainingsList){
            res += "\nATHLETE TRAININGS\n: " + t;
        }
        
        for (Event e : this.eventsList){
            res += "\nATHLETE EVENTS\n: " + e;
        }
        */
        
        return res;
    }
    
    @Override
    public void addItself(){
        athletesList.add(this);
    }
    
    //to compare if two Athletes are the same
    //useful when you need to check if an athlete is already in a team
    //ref: http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java
    @Override
    public boolean equals(Object obj){
        boolean res = false;
        if(obj instanceof Athlete){
            Athlete ath = (Athlete) obj;
            if (this.getName().equals(ath.getName()) &&
                this.getSex().equals(ath.getSex())&&
                this.getAgeGroup().equals(ath.getAgeGroup())&&
                this.getAddress().equals(ath.getAddress())&&
                this.getQualification().equals(ath.getQualification())&&
                this.getType().equals(ath.getType()))
                {
                  res = true;
                }
        }
        
        return res;
    }
    
    //if two objects returns true at equals, they must have the same hashCode
    @Override
    public int hashCode(){
        String sexString = getSex();
        String ageGroupString = getAgeGroup();
        String qualificationString = getQualification();
        String typeString = getType();
        
        int hash = 7;
        hash = 31 * hash + (null == name ? 0 : name.hashCode());
        hash = 31 * hash + (null == sexString ? 0 : sexString.hashCode());
        hash = 31 * hash + (null == ageGroupString? 0 : ageGroupString.hashCode());
        hash = 31 * hash + (null == address? 0 : address.hashCode());
        hash = 31 * hash + (null == qualificationString? 0 : qualificationString.hashCode());
        hash = 31 * hash + (null == typeString? 0 : typeString.hashCode());
        
        return hash;
    }
    
}
