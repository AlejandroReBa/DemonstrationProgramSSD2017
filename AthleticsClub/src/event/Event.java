package event;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */

public class Event {
    //invented
    public enum typeEnum {Football, Basketball, Futsal, Hockey, Voleyball, Handball,
    Athletism, Cycling, Swimming, Badminton, Tennis, Padel, KickBoxing, Boxing};
    public enum genderEnum {Men, Women, Mixed};
    public enum ageGroupRelatedEnum {U13, U15, U17, U20, Senior, Masters};
    enum transportEnum {Car, Bus, Minibus, Train};
    
    private static int incrementalId = 1;
    public static List<Event> eventsList = new ArrayList<>();
    
    private int id;
    private typeEnum type;
    private genderEnum gender;
    private ageGroupRelatedEnum ageGroup;
    private transportEnum transport;
    private Date date;
    //not initialized yet, does not exist negative ID
    private int officialId = -1;

    
    public Event (String typeIn, String genderIn, String ageGroupIn,
            String transportIn){
        this.type = typeEnum.valueOf(typeIn);
        this.gender = genderEnum.valueOf(genderIn);
        this.ageGroup = ageGroupRelatedEnum.valueOf(ageGroupIn);
        this.transport = transportEnum.valueOf(transportIn);
        this.date = Date.from(Instant.now());
        this.officialId = -1;
        this.transport =  transportEnum.Minibus; //by default
        
        this.id = incrementalId;
        incrementalId++; 
        eventsList.add(this);
    }
    
        public Event (String typeIn, String genderIn, String ageGroupIn,
            String transportIn, Date dateIn, int officialIdIn){
        this.type = typeEnum.valueOf(typeIn);
        this.gender = genderEnum.valueOf(genderIn);
        this.ageGroup = ageGroupRelatedEnum.valueOf(ageGroupIn);
        this.transport = transportEnum.valueOf(transportIn);
        this.date = dateIn;
        this.officialId = officialIdIn; 
        this.transport =  transportEnum.Minibus; //by default
        
        this.id = incrementalId;
        incrementalId++; 
        eventsList.add(this);
    }
    
    //attribute that can't be changed
    public int getId(){
        return this.id;
    }
        
    public String getType (){
        return this.type.toString();
    }
    
    public void setType (String typeIn){
        this.type = typeEnum.valueOf(typeIn);
    }
    
    public String getGender (){
        return this.gender.toString();
    }
    
    public void setGender (String genderIn){
        this.gender = genderEnum.valueOf(genderIn);
    }
    
    public String getAgeGroup (){
        return this.ageGroup.toString();
    }
    
    public void setAgeGroup (String ageGroupIn){
        this.ageGroup = ageGroupRelatedEnum.valueOf(ageGroupIn);
    }
    
    public String getTransport (){
        return this.transport.toString();
    }
    
    public void setTransport(String transportIn){
        this.transport = transportEnum.valueOf(transportIn);
    }
    
    public Date getDate (){
        return this.date;
    }
    
    public void setDate (Date dateIn){
        this.date = dateIn;
    }
    
    public int getOfficialId(){
        return this.officialId;
    }
    
    public void setOfficialId(int officialIdIn){
        this.officialId = officialIdIn;
    }
    
    public static Event viewEventById(int id){
        Event resEvent = null;
        for (Event currentEvent : eventsList){
            if (currentEvent.id == id){
                resEvent = currentEvent;
                break;
            }
        }
        return resEvent;
    }
    
    public static List<Event> viewEventsByType(String type){
        List<Event> resEvents = new ArrayList<>();
        for (Event currentEvent : eventsList){
            if (currentEvent.type.toString().equals(type)){
                resEvents.add(currentEvent);
            }
        }
        return resEvents;
    }
    
    public static List<Event> viewEventsByAgeGroup(String ageGroup){
        List<Event> resEvents = new ArrayList<>();
        for (Event currentEvent : eventsList){
            if (currentEvent.ageGroup.toString().equals(ageGroup)){
                resEvents.add(currentEvent);
            }
        }
        return resEvents;
    }
    
    public static List<Event> viewEventsByGender(String gender){
        List<Event> resEvents = new ArrayList<>();
        for (Event currentEvent : eventsList){
            if (currentEvent.gender.toString().equals(gender)){
                resEvents.add(currentEvent);
            }
        }
        return resEvents;
    }
    
    public static List<Event> viewEventsByDate(Date date){
        List<Event> resEvents = new ArrayList<>();
        for (Event currentEvent : eventsList){
            if (currentEvent.date.equals(date)){
                resEvents.add(currentEvent);
            }
        }
        return resEvents;
    }
}
