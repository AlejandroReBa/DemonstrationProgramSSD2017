/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package membership;

import event.Event;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import static membership.Membership.incrementalId;

/**
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */
public class Team implements Serializable{
    private static final long serialVersionUID = 7133659194365448172L;
    private static int incrementalId = 0;
    public static List<Team> teamsList = new ArrayList<>();
    
    private int id;
    private String name;
    //private List<Athlete> athletesList;
    private List<Integer> athletesList;
    //private List<Event> eventsList;
    private List<Integer> eventsList;
    private int captainId; //has to be an official with no qualification
    
    //private Event.typeEnum type; you dont have a type because members
    //of a team can belong to distinct disciplines
    private Event.genderEnum gender;
    private Event.ageGroupRelatedEnum ageGroup;
    
    /*
    public Team (String nameIn, int captainIdIn, String genderIn, String ageGroupIn, List<Athlete> athletesListIn){
        this.name = nameIn;
        this.captainId = captainIdIn;
        this.gender = Event.genderEnum.valueOf(genderIn);
        this.ageGroup = Event.ageGroupRelatedEnum.valueOf(ageGroupIn);
        
        this.athletesList = new ArrayList<Athlete>(athletesListIn);
        this.eventsList = new ArrayList<>();
        
        this.id = incrementalId;
        incrementalId++; 
        teamsList.add(this);
    }
*/
    
    /*
        public Team (String nameIn, int captainIdIn, String genderIn, String ageGroupIn,
                List<Athlete> athletesListIn, List<Event> eventsListIn){
        this.name = nameIn;
        this.captainId = captainIdIn;
        this.gender = Event.genderEnum.valueOf(genderIn);
        this.ageGroup = Event.ageGroupRelatedEnum.valueOf(ageGroupIn);
        
        this.athletesList = new ArrayList<Athlete>(athletesListIn);
        this.eventsList = eventsListIn;
        
        this.id = incrementalId;
        incrementalId++; 
        teamsList.add(this);
    }
*/
    
    public Team (String nameIn, String genderIn, String ageGroupIn, int captainIdIn){
        this.name = nameIn;
        this.captainId = captainIdIn;
        this.gender = Event.genderEnum.valueOf(genderIn);
        this.ageGroup = Event.ageGroupRelatedEnum.valueOf(ageGroupIn);
        
        this.athletesList = new ArrayList<>();
        this.eventsList = new ArrayList<>();
        
        this.id = incrementalId;
        incrementalId++; 
        teamsList.add(this);
    }
        
    public static List<Team> getTeams(){
        return teamsList;
    }    
    
    public int getId(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName (String nameIn){
        this.name = nameIn;
    }
    
    public void addAthlete(Athlete atheleteIn){
        this.athletesList.add(atheleteIn.getId());
    }
    
    /*not needed at the moment
    public void deleteAthlete(int athleteId){
        int athleteIndex = -1;
        int index = 0;
        
        for (Athlete currentAthlete : this.athletesList){
            if (currentAthlete.getId() == athleteId){
                athleteIndex = index;
                break;
            }else{
                index++;
            }
        }
        if (athleteIndex != -1){
            this.athletesList.remove(athleteIndex);
        }
    }
    */
    
    public void setMembersOfTeam(List<Athlete> members){
        this.athletesList = new ArrayList<>();
        for (Athlete ath : members){
            this.athletesList.add(ath.getId());
        }
        //this.athletesList = new ArrayList<Athlete>(members);
    }
        
    public List<Athlete> getAthletesList(){
        ArrayList<Athlete> list = new ArrayList<>();
        ArrayList<Membership> listMembers = (ArrayList<Membership>)Membership.getMembersList();
        System.out.println ("GOOOOOOOOOO nano");
        for (Integer currentID : this.athletesList){
            System.out.println ("GOOOOOOOOOO");
            list.add((Athlete)listMembers.get(currentID));
        }
        return list;
    }
        
    public int getCaptainId(){
        return this.captainId;
    }
    
    public void changeCaptainId(int captainIdIn){
        this.captainId = captainIdIn;
    }
    
    public String getGender (){
        return this.gender.toString();
    }
    
    public void setGender (String genderIn){
        this.gender = Event.genderEnum.valueOf(genderIn);
    }
    
    public String getAgeGroup (){
        return this.ageGroup.toString();
    }
    
    public void setAgeGroup (String ageGroupIn){
        this.ageGroup = Event.ageGroupRelatedEnum.valueOf(ageGroupIn);
    }
    
    public String toString(){
        //captain should be an official!!!
        Official cap = (Official)Membership.getMembersList().get(this.captainId); //crash if id doesn't belong to an athlete
        String res = "Team Name: " + this.name + ", Captain: " + cap.getName() +
                ", Gender: " + this.gender.toString() + ", Age Group: " +
                this.ageGroup.toString() + ".\nAthletes List: \n";
        for (Athlete ath : this.getAthletesList()){
            res += ath.getName() + ",";
        }
        
        return res;
    }
    

    //to add the object to teamsList. When deserializes constructor is not called
    //therefore this is the only manner of doing it. Dont needed
    //I change the value of static List<Team> when deserialize these objects
    public void addItself(){
        teamsList.add(this);
    }
    
     public static List<Team> viewTeamsByName(String name){
        List<Team> resTeams = new ArrayList<>();
        int lastCharIndex = name.length();
        for (Team currentTeam : teamsList){
            String currentName = currentTeam.getName();
            if (currentName.length() >= name.length() &&
                    currentName.toLowerCase().substring(0, lastCharIndex)
                    .equals(name.toLowerCase())){
                resTeams.add(currentTeam);
            }
            
        }
        
        return resTeams;
    }
    
    public static List<Team> viewTeamsByAgeGroup(String ageGroup){
        List<Team> resTeams = new ArrayList<>();
        for (Team currentTeam : teamsList){
            if (currentTeam.getAgeGroup().equals(ageGroup)){
                resTeams.add(currentTeam);
            }
        }
        return resTeams;
    }
    
    public static List<Team> viewTeamsByGender(String gender){
        List<Team> resTeams = new ArrayList<>();
        for (Team currentTeam : teamsList){
            if (currentTeam.getGender().equals(gender)){
                resTeams.add(currentTeam);
            }
        }
        return resTeams;
    }
    
    
     //to compare if two Teams are the same
    //useful when you need to check if a team is already in a event
    //ref: http://stackoverflow.com/questions/27581/what-issues-should-be-considered-when-overriding-equals-and-hashcode-in-java
    @Override
    public boolean equals(Object obj){
        boolean res = false;
        if(obj instanceof Team){
            Team team = (Team) obj;
            if (this.getId() == (team.getId()))
                {
                  res = true;
                }
        }
        return res;
    }
    //
    
    //if two objects returns true at equals, they must have the same hashCode
    @Override
    public int hashCode(){        
        int hash = 7;
        hash = 31 * hash + this.getId();        
        return hash;
    }
    
    //needed to increment incrementalId variable
    //and add the deserialized Team to teamsList    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        incrementalId++;
    }

        
}
