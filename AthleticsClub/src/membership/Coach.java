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
    //to avoid invalidClasException as deserialization
    //source: https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html
    private static final long serialVersionUID = 42L;
    
    public static List<Coach> coachsList = new ArrayList<>();
    //private List<Training> trainingsList;
    private List<Integer> trainingsList;
    //private List<Event> eventsList; //needed? no really
    //private List<Athlete> athletesList;
    private List<Integer> athletesList;

    public Coach(String nameIn, String addressIn, String telIn, String sexIn, Date birthIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Coach;
        this.trainingsList = new ArrayList<>();
        //this.eventsList = new ArrayList<>();
        this.athletesList = new ArrayList<>();
        Membership.membersList.add(this);
        coachsList.add(this);
    }
    
    public Coach(String nameIn, String addressIn, String telIn, String sexIn,
            Date birthIn, String qualificationIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Coach;
        qualification = qualificationsEnum.valueOf(qualificationIn);
        this.trainingsList = new ArrayList<>();
        //this.eventsList = new ArrayList<>();
        this.athletesList = new ArrayList<>();
        Membership.membersList.add(this);
        coachsList.add(this);
    }
    
    /*
    public Coach(String nameIn, String addressIn, String telIn, String sexIn, Date birthIn,
            String qualificationIn, List<Training> trainingsListIn,
            List<Event> eventsListIn, List<Athlete> athletesListIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Coach;
        qualification = qualificationsEnum.valueOf(qualificationIn);
        this.trainingsList = new ArrayList<>(trainingsListIn);
        //this.eventsList = new ArrayList<>(eventsListIn);
        this.athletesList = new ArrayList<>(athletesListIn);
        Membership.membersList.add(this);
        coachsList.add(this);
    }
    */
    
    public static List<Coach> getCoachsList(){
        return coachsList;
    }
    
    public List<Training> getTrainingsList(){
        ArrayList<Training> list = new ArrayList<>();
        ArrayList<Training> completeTrainingList = (ArrayList<Training>)Training.getTrainingsList();
        for (Integer currentID : this.trainingsList){
            list.add((Training)completeTrainingList.get(currentID));
        }
        return list;
    }
    
    public void addTraining(Training trainingIn){
        this.trainingsList.add(trainingIn.getId());
    }
    
    /*
    public List<Event> getEventsList(){
        return this.eventsList;
    }
    
    public void addEvent(Event eventIn){
        this.eventsList.add(eventIn);
    }
    */
    
    public List<Athlete> getAthletesList(){
        ArrayList<Athlete> list = new ArrayList<>();
        ArrayList<Membership> listMembers = (ArrayList<Membership>)Membership.getMembersList();
        for (Integer currentID : this.athletesList){
            list.add((Athlete)listMembers.get(currentID));
        }
        return list;
    }
    
    public void addAthlete(Athlete athleteIn){
        this.athletesList.add(athleteIn.getId());
    }
    
    @Override
    public String toString(){
        String res = super.toString();
        /* toString modified: too much information for interface
        for (Training t : this.trainingsList){
            res += "\nCOACH TRAININGS:\n " + t;
        }
        for (Athlete a : this.athletesList){
            res += "\nATHLETES UNDER SUPERVISION:\n " + a;
        }
        */
        return res;
    }
    
    @Override
    public void addItself(){
        //nothing to do here, method used by subclasses
        //but it needs to be initialized here for polymorphism reasons
        coachsList.add(this);
    }
}
