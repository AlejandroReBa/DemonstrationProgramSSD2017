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
    private List<Event> eventsList;

//------------------>>

//.........----------->    //add constructor with age group and qualifications....
    //or just setter... at moment other ageGroup on no qualifications..
    public Athlete(String nameIn, String addressIn, String telIn, String sexIn, Date birthIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Athlete;
        this.trainingsList = new ArrayList<>();
        this.eventsList = new ArrayList<>();
        Membership.membersList.add(this);
        athletesList.add(this);
        
    }
    
    public Athlete(String nameIn, String addressIn, String telIn, String sexIn,
            Date birthIn, String ageGroupIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Athlete;
        this.trainingsList = new ArrayList<>();
        this.eventsList = new ArrayList<>();
        ageGroup = ageGroupEnum.valueOf(ageGroupIn);
        Membership.membersList.add(this);
        athletesList.add(this);
        
    }
    
    public Athlete(String nameIn, String addressIn, String telIn, String sexIn,
            Date birthIn, String ageGroupIn, List<Training> trainingsListIn,
            List<Event> eventsListIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Athlete;
        this.trainingsList = new ArrayList<>(trainingsListIn);
        this.eventsList = new ArrayList<>(eventsListIn);
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
    
    public List<Event> getEventsList(){
        return this.eventsList;
    }
    
    public void addEvent(Event eventIn){
        this.eventsList.add(eventIn);
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
        //nothing to do here, method used by subclasses
        //but it needs to be initialized here for polymorphism reasons
        athletesList.add(this);
    }
}
