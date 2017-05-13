package event;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import membership.Membership;
import membership.Official;
import membership.Team;

/**
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */

public class Event implements Serializable{
    //to avoid invalidClasException as deserialization
    //source: https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html
    private static final long serialVersionUID = 42L;
    
    //MensLeague, LadiesLeague, YouthLeague, CrossCountryLeague,
    //RoadRace, CountyChampionships, nationalChampionships
    //public enum typeEnum {Football, Basketball, Futsal, Hockey, Voleyball, Handball,
    //Athletism, Cycling, Swimming, Badminton, Tennis, Padel, KickBoxing, Boxing};
    public enum typeEnum {MensLeague, LadiesLeague, YouthLeague, CrossCountryLeague,
    RoadRace, CountyChampionships, NationalChampionships};
    public enum genderEnum {Men, Women, Mixed};
    public enum ageGroupRelatedEnum {U13, U15, U17, U20, Senior, Masters};
    public enum transportEnum {Car, Bus, Minibus, Train, Other};
    
    private static int incrementalId = 0;
    public static List<Event> eventsList = new ArrayList<>();
    
    private List<Team> participants; ///do we have to include participants?
    private int id;
    private String name;
    private typeEnum type;
    private genderEnum gender;
    private ageGroupRelatedEnum ageGroup;
    private transportEnum transport;
    private Date date;
    //not initialized yet, does not exist negative ID
    private int officialId = -1; //should be more than one... but for now..

    
    public Event (String nameIn, String typeIn, String genderIn, String ageGroupIn){
        this.name = nameIn;
        this.type = typeEnum.valueOf(typeIn);
        this.gender = genderEnum.valueOf(genderIn);
        this.ageGroup = ageGroupRelatedEnum.valueOf(ageGroupIn);
        this.date = Date.from(Instant.now());
        this.officialId = -1;
        this.transport =  transportEnum.Minibus; //by default
        this.participants = new ArrayList<>();
        
        this.id = incrementalId;
        incrementalId++; 
        eventsList.add(this);
    }
    
    public Event (String nameIn, String typeIn, String genderIn, String ageGroupIn,
            String transportIn, Date dateIn){
        this.name = nameIn;
        this.type = typeEnum.valueOf(typeIn);
        this.gender = genderEnum.valueOf(genderIn);
        this.ageGroup = ageGroupRelatedEnum.valueOf(ageGroupIn);
        this.date = dateIn;
        this.officialId = -1;
        this.transport =  transportEnum.valueOf(transportIn);
        this.participants = new ArrayList<>();
        
        this.id = incrementalId;
        incrementalId++; 
        eventsList.add(this);
    }
        
        
    public Event (String nameIn, String typeIn, String genderIn, String ageGroupIn,
            String transportIn, Date dateIn, int officialIdIn, List<Team> participantsIn){
        this.name = nameIn;
        this.type = typeEnum.valueOf(typeIn);
        this.gender = genderEnum.valueOf(genderIn);
        this.ageGroup = ageGroupRelatedEnum.valueOf(ageGroupIn);
        this.date = dateIn;
        this.officialId = officialIdIn; 
        this.transport =  transportEnum.valueOf(transportIn);
        this.participants = new ArrayList<>(participantsIn);
        
        this.id = incrementalId;
        incrementalId++; 
        eventsList.add(this);
    }
    
    public static List<Event> getEvents(){
        return eventsList;
    }
     
    public List<Team> getParticipants(){
        return this.participants;
    }
    
    public void setParticipants(ArrayList<Team> participantsIn){
        this.participants = new ArrayList<Team> (participantsIn);
    }
    
    public void addParticipant(Team participant){
        this.participants.add(participant);
    }
        
    //attribute that can't be changed
    public int getId(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName (String nameIn){
        this.name = nameIn;
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
    
    
    public static List<Event> viewEventByName(String name){
        List<Event> resEvents = new ArrayList<>();
        int lastCharIndex = name.length();
        for (Event currentEvent : eventsList){
            String currentName = currentEvent.getName();
            if (currentName.length() >= name.length() &&
                    currentName.toLowerCase().substring(0, lastCharIndex)
                    .equals(name.toLowerCase())){
                resEvents.add(currentEvent);
            }
            
        }
        
        return resEvents;
    }
    
    public static List<Event> viewEventsByType(String type){
        List<Event> resEvents = new ArrayList<>();
        for (Event currentEvent : eventsList){
            if (currentEvent.getType().equals(type)){
                resEvents.add(currentEvent);
            }
        }
        return resEvents;
    }
    
    public static List<Event> viewEventsByAgeGroup(String ageGroup){
        List<Event> resEvents = new ArrayList<>();
        for (Event currentEvent : eventsList){
            if (currentEvent.getAgeGroup().equals(ageGroup)){
                resEvents.add(currentEvent);
            }
        }
        return resEvents;
    }
    
    public static List<Event> viewEventsByGender(String gender){
        List<Event> resEvents = new ArrayList<>();
        for (Event currentEvent : eventsList){
            if (currentEvent.getGender().equals(gender)){
                resEvents.add(currentEvent);
            }
        }
        return resEvents;
    }
    
    public static List<Event> viewEventsByDate(Date date){
        List<Event> resEvents = new ArrayList<>();
        for (Event currentEvent : eventsList){ //fix to compare dates.......
            //java.time.LocalDateTime first = new java.time.LocalDateTime(currentEvent.getDate());
            //java.time.LocalDate = 
            //java.time.DateTimeComparator.getDateOnlyInstance();
            if (currentEvent.getDate().equals(date)){
                resEvents.add(currentEvent);
            }
        }
        return resEvents;
    }
    
    public String toString(){
        String officialName = "No official assigned";
        if (this.officialId > -1){
            officialName = ((Official) Membership.getMembersList().get(this.officialId)).getName();
        }
        String res = "Event Name: " + this.name + ", Type: " + this.type.toString()
                + ", Gender: " + this.gender.toString() + ", Age Group: " +
                this.ageGroup.toString() + ", Date: " + this.date +
                ", Official: " + officialName + ", Transport: " + this.transport.toString()
                + ".\nTeams:\n";
        for (Team t : this.participants){
            Membership captain = Membership.getMembersList().get(t.getCaptainId());
            res += "Team name: " + t.getName() + " - Captain: " + captain.getName() + "\n";
        }
        return res;
    }
    
     //needed to increment incrementalId variable
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        incrementalId++;
    }
    
    //and add the deserialized Event to events List 
    public void addItself(){
        eventsList.add(this);
    }
    
    
}
