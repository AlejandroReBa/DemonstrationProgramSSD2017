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

/**
 *
 * @author Alejandro Reyes
 */
public class Official extends Membership {
    private List<Event> eventsList;

    public Official(String nameIn, String addressIn, String telIn, String sexIn, Date birthIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Official;
        this.eventsList = new ArrayList<>();
        Membership.membersList.add(this);
    }
    
    
    public Official(String nameIn, String addressIn, String telIn, String sexIn,
            Date birthIn, List<Event> eventsListIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Official;
        this.eventsList = eventsListIn;
        Membership.membersList.add(this);
    }
    
    public List<Event> getEventsList(){
        return this.eventsList;
    }
    
    public void addEvent(Event eventIn){
        this.eventsList.add(eventIn);
    }
}
