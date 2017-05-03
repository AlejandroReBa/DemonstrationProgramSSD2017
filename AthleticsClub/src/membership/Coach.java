/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package membership;

import event.Event;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static membership.Athlete.athletesList;
import training.Training;

/**
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */
public class Coach extends Membership implements Serializable{ //delete elements for the list not implemented yet, for any class.
    public static List<Coach> coachsList = new ArrayList<>();
    private List<Training> trainingsList;
    private List<Event> eventsList; //needed?
    private List<Athlete> athletesList;

    public Coach(String nameIn, String addressIn, String telIn, String sexIn, Date birthIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Coach;
        this.trainingsList = new ArrayList<>();
        this.eventsList = new ArrayList<>();
        this.athletesList = new ArrayList<>();
        Membership.membersList.add(this);
        coachsList.add(this);
    }
    
    public Coach(String nameIn, String addressIn, String telIn, String sexIn, Date birthIn,
            List<Training> trainingsListIn, List<Event> eventsListIn, List<Athlete> athletesListIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Coach;
        this.trainingsList = trainingsListIn;
        this.eventsList = eventsListIn;
        this.athletesList = athletesListIn;
        Membership.membersList.add(this);
        coachsList.add(this);
    }
    
    public static List<Coach> getCoachsList(){
        return coachsList;
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
    
    @Override
    public String toString(){
        String res = super.toString();
        for (Training t : this.trainingsList){
            res += "\nCOACH TRAININGS:\n " + t;
        }
        for (Athlete a : this.athletesList){
            res += "\nATHLETES UNDER SUPERVISION:\n " + a;
        }
        
        return res;
    }
}
