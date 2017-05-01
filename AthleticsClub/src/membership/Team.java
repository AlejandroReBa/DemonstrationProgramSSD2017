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
    private List<Athlete> athletesList;
    private List<Event> eventsList;
    private int captainId;
    private int coachId;
    
    private Event.typeEnum type;
    private Event.genderEnum gender;
    private Event.ageGroupRelatedEnum ageGroup;
    
    public Team (int captainIdIn, int coachIdIn, String typeIn, String genderIn, String ageGroupIn){
        this.captainId = captainIdIn;
        this.coachId = coachIdIn;
        this.type = Event.typeEnum.valueOf(typeIn);
        this.gender = Event.genderEnum.valueOf(genderIn);
        this.ageGroup = Event.ageGroupRelatedEnum.valueOf(ageGroupIn);
        
        this.athletesList = new ArrayList<>();
        this.eventsList = new ArrayList<>();
        
        this.id = incrementalId;
        incrementalId++; 
        teamsList.add(this);
    }
    
        public Team (int captainIdIn, int coachIdIn, String typeIn, String genderIn, String ageGroupIn,
                List<Athlete> athletesListIn, List<Event> eventsListIn){
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
}
