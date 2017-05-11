/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package membership;

import event.Event;
import static event.Event.eventsList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import static membership.Athlete.athletesList;

/**
 *
 * @author Alejandro Reyes
 */
public class Team implements Serializable{
    private static final long serialVersionUID = 7133659194365448172L;
    private static int incrementalId = 1;
    public static List<Team> teamsList = new ArrayList<>();
    
    private int id;
    private String name;
    private List<Athlete> athletesList;
    private List<Event> eventsList;
    private int captainId; //has to be an official with no qualification
    private int coachId; //dont need it
    
    private Event.typeEnum type;
    private Event.genderEnum gender;
    private Event.ageGroupRelatedEnum ageGroup;
    
    public Team (String nameIn, int captainIdIn, int coachIdIn, String typeIn, String genderIn, String ageGroupIn, List<Athlete> athletesListIn){
        this.name = nameIn;
        this.captainId = captainIdIn;
        this.coachId = coachIdIn;
        this.type = Event.typeEnum.valueOf(typeIn);
        this.gender = Event.genderEnum.valueOf(genderIn);
        this.ageGroup = Event.ageGroupRelatedEnum.valueOf(ageGroupIn);
        
        this.athletesList = new ArrayList<Athlete>(athletesListIn);
        this.eventsList = new ArrayList<>();
        
        this.id = incrementalId;
        incrementalId++; 
        teamsList.add(this);
    }
    
        public Team (String nameIn, int captainIdIn, int coachIdIn, String typeIn, String genderIn, String ageGroupIn,
                List<Athlete> athletesListIn, List<Event> eventsListIn){
        this.name = nameIn;
        this.captainId = captainIdIn;
        this.coachId = coachIdIn;
        this.type = Event.typeEnum.valueOf(typeIn);
        this.gender = Event.genderEnum.valueOf(genderIn);
        this.ageGroup = Event.ageGroupRelatedEnum.valueOf(ageGroupIn);
        
        this.athletesList = new ArrayList<Athlete>(athletesListIn);
        this.eventsList = eventsListIn;
        
        this.id = incrementalId;
        incrementalId++; 
        teamsList.add(this);
    }
    
    public static List<Team> getTeams(){
        return teamsList;
    }    
    
    public String getName(){
        return this.name;
    }
    
    public void setName (String nameIn){
        this.name = nameIn;
    }
    
    public void addAthlete(Athlete atheleteIn){
        this.athletesList.add(atheleteIn);
    }
    
    public void deleteAthlete(int athleteId){
        int athleteIndex = -1;
        int index = 0;
        
        for (Athlete currentAthlete : this.athletesList){
            if (currentAthlete.getId() == athleteId){
                athleteIndex = index;
                break;
            }else{
                index++;
            }
        }
        if (athleteIndex != -1){
            this.athletesList.remove(athleteIndex);
        }
    }
    
    public void setMembersOfTeam(List<Athlete> members){
        this.athletesList = new ArrayList<Athlete>(members);
    }
        
    public List<Athlete> getAthletesList(){
        return this.athletesList;
    }
        
    public int getCaptainId(){
        return this.captainId;
    }
    
    public void changeCaptainId(int captainIdIn){
        this.captainId = captainIdIn;
    }
    
    public int getCoachId(){
        return this.coachId;
    }
    
    public void changeCoachId(int coachIdIn){
        this.coachId = coachIdIn;
    }
    
     public String getType (){
        return this.type.toString();
    }
    
     //////////////im here modify typeEnum by type from this class....dontknow refers other perhapss
    public void setType (String typeIn){
        this.type = Event.typeEnum.valueOf(typeIn);
    }
    
    public String getGender (){
        return this.gender.toString();
    }
    
    public void setGender (String genderIn){
        this.gender = Event.genderEnum.valueOf(genderIn);
    }
    
    public String getAgeGroup (){
        return this.ageGroup.toString();
    }
    
    public void setAgeGroup (String ageGroupIn){
        this.ageGroup = Event.ageGroupRelatedEnum.valueOf(ageGroupIn);
    }
    
    public String toString(){
        //captain should be an official!!!
        Athlete cap = (Athlete)Membership.getMembersList().get(this.captainId); //crash if id doesn't belong to an athlete
        Coach coach = (Coach)Membership.getMembersList().get(this.coachId);
        String res = "Team Name: " + this.name + ", Captain: " + cap.getName() +
                ", Coach: " + coach.getName() + ", Type:" + this.type.toString() +
                ", Gender: " + this.gender.toString() + ", Age Group: " +
                this.ageGroup.toString() + ".\nAthletes List: \n";
        for (Athlete ath : this.athletesList){
            res += ath.getName() + ",";
        }
        
        return res;
    }
    

    //to add the object to teamsList. When deserializes constructor is not called
    //therefore this is the only manner of doing it. Dont needed
    //I change the value of static List<Team> when deserialize these objects
    public void addItself(){
        teamsList.add(this);
    }
    
     public static List<Team> viewTeamsByName(String name){
        List<Team> resTeams = new ArrayList<>();
        int lastCharIndex = name.length();
        for (Team currentTeam : teamsList){
            String currentName = currentTeam.getName();
            if (currentName.length() >= name.length() &&
                    currentName.toLowerCase().substring(0, lastCharIndex)
                    .equals(name.toLowerCase())){
                resTeams.add(currentTeam);
            }
            
        }
        
        return resTeams;
    }
    
    public static List<Team> viewTeamsByType(String type){
        List<Team> resTeams = new ArrayList<>();
        for (Team currentTeam : teamsList){
            if (currentTeam.getType().equals(type)){
                resTeams.add(currentTeam);
            }
        }
        return resTeams;
    }
    
    public static List<Team> viewTeamsByAgeGroup(String ageGroup){
        List<Team> resTeams = new ArrayList<>();
        for (Team currentTeam : teamsList){
            if (currentTeam.getAgeGroup().equals(ageGroup)){
                resTeams.add(currentTeam);
            }
        }
        return resTeams;
    }
    
    public static List<Team> viewTeamsByGender(String gender){
        List<Team> resTeams = new ArrayList<>();
        for (Team currentTeam : teamsList){
            if (currentTeam.getGender().equals(gender)){
                resTeams.add(currentTeam);
            }
        }
        return resTeams;
    }
        
}
