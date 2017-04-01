package athleticsclub.event;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Alejandro Reyes
 */

public class Event {
    private int id;
    private String type;
    private String gender;
    private String ageGroup;
    private String transport;
    private Date date;
    public static List<Event> eventList = new ArrayList<>();
    private int official;
    
    public Event (String typeIn, String genderIn, String ageGroupIn,
            String transportIn, Date dateIn){
        this.type = typeIn;
        this.gender = genderIn;
        this.ageGroup = ageGroupIn;
        this.transport = transportIn;
        this.date = dateIn;
        
        eventList.add(this);
    }
    
    public String getType (){
        return null;
    }
    
    public String getGender (){
        return null;
    }
    
    public String getAgeGroup (){
        return null;
    }
    
    public String getTransport (){
        return null;
    }
    
    public Date getDate (){
        return null;
    }
    
    public Event viewEventById(int id){
        return null;
    }
    
    public List<Event> viewEventByType(String type){
        return null;
    }
    
    public List<Event> viewEventByAgeGroup(String ageGroup){
        return null;
    }
    
    public List<Event> viewEventByGender(String gender){
        return null;
    }
    
    public List<Event> viewEventByDate(Date date){
        return null;
    }
}
