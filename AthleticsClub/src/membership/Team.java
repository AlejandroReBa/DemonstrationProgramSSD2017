/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package membership;

import event.Event;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Alejandro Reyes
 */
public class Team {
    private static int incrementalId = 1;
    private static List<Team> teamsList = new ArrayList<>();
    
    private int id;
    private String name;
    private List<Athlete> athletesList;
    private List<Event> eventsList;
    private int captainId;
    private int coachId;
    
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
        
        this.athletesList = athletesListIn;
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
        
        this.athletesList = athletesListIn;
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
    
    public String toString(){
        Athlete cap = (Athlete)Membership.getMembersList().get(this.captainId); //crash if id doesn't belong to an athlete
        Coach coach = (Coach)Membership.getMembersList().get(this.coachId);
        String res = "TEAM. Name: " + this.name + ", Captain: " + cap.getName() +
                ", Coach: " + coach.getName() + ", Type:" + this.type.toString() +
                ", Gender: " + this.gender.toString() + ", Age Group: " +
                this.ageGroup.toString() + ".\nAthletes List: \n";
        for (Athlete ath : this.athletesList){
            res += ath + "\n";
        }
        
        return res;
    }
        
}
