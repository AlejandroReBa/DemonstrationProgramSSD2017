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
public class Athlete extends Membership{
    private List<Training> trainingsList;
    private List<Event> eventsList;

    public Athlete(String nameIn, String addressIn, String telIn, String sexIn, Date birthIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Athlete;
        this.trainingsList = new ArrayList<>();
        this.eventsList = new ArrayList<>();
        Membership.membersList.add(this); //perhaps will throw an error
    }
    
    public Athlete(String nameIn, String addressIn, String telIn, String sexIn, Date birthIn, List<Training> trainingsListIn, List<Event> eventsListIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Athlete;
        this.trainingsList = trainingsListIn;
        this.eventsList = eventsListIn;
        Membership.membersList.add(this); //perhaps will throw an error
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
}
