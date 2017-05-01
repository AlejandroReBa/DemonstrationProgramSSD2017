/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package membership;

import event.Event;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import training.Training;

/**
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */
public class Coach extends Membership{ //delete elements for the list not implemented yet, for any class.
    private List<Training> trainingsList;
    private List<Event> eventsList;
    private List<Athlete> athletesList;

    public Coach(String nameIn, String addressIn, String telIn, String sexIn, Date birthIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Coach;
        this.trainingsList = new ArrayList<>();
        this.eventsList = new ArrayList<>();
        this.athletesList = new ArrayList<>();
        Membership.membersList.add(this);
    }
    
    public Coach(String nameIn, String addressIn, String telIn, String sexIn, Date birthIn,
            List<Training> trainingsListIn, List<Event> eventsListIn, List<Athlete> athletesListIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Coach;
        this.trainingsList = trainingsListIn;
        this.eventsList = eventsListIn;
        this.athletesList = athletesListIn;
        Membership.membersList.add(this);
    }
    
    public List<Training> getTrainingsList(){
        return this.trainingsList;
    }
    
    public void addTraining(Training trainingIn){
        this.trainingsList.add(trainingIn);
    }
    
    public List<Event> getEventsList(){
        return this.eventsList;
    }
    
    public void addEvent(Event eventIn){
        this.eventsList.add(eventIn);
    }
    
    public List<Athlete> getAthletesList(){
        return this.athletesList;
    }
    
    public void addAthlete(Athlete athleteIn){
        this.athletesList.add(athleteIn);
    }
}
