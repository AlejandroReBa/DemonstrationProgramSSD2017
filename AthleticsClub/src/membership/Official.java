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

/**
 *
 * @author Alejandro Reyes
 */
public class Official extends Membership implements Serializable{
    //to avoid invalidClasException as deserialization
    //source: https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html
    private static final long serialVersionUID = 42L;
    
    public static List<Official> officialsList = new ArrayList<>();
    private List<Event> eventsList;

    public Official(String nameIn, String addressIn, String telIn, String sexIn, Date birthIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Official;
        this.eventsList = new ArrayList<>();
        Membership.membersList.add(this);
        officialsList.add(this);
    }
    
    
    public Official(String nameIn, String addressIn, String telIn, String sexIn,
            Date birthIn, List<Event> eventsListIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Official;
        this.eventsList = new ArrayList<>(eventsListIn);
        Membership.membersList.add(this);
        officialsList.add(this);
    }
    
    public static List<Official> getOfficialsList(){
        return officialsList;
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
        
        for (Event e : this.eventsList){
            res += "\nOFFICIAL EVENTS:\n " + e;
        }
        
        return res;
    }
    
    @Override
    public void addItself(){
        //nothing to do here, method used by subclasses
        //but it needs to be initialized here for polymorphism reasons
        officialsList.add(this);
    }
}
