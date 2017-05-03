/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athleticsclub;

import event.Event;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import membership.Athlete;
import membership.Coach;
import membership.Membership;
import membership.Official;
import membership.StaffAdmin;
import membership.Team;
import training.Training;

/**
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */
public class AthleticsClub {

    //just for generate random identifiers; http://stackoverflow.com/a/5025666/4733587
    protected static class generateNames {

        final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        final String lexicon2 = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        final String lexicon3 = "0123456789";

        final java.util.Random rand = new java.util.Random();

// consider using a Map<String,Boolean> to say whether the identifier is being used or not 
        final Set<String> identifiers = new HashSet<String>();

        public String randomIdentifier(int lex) {
            if (lex == 1) {
                StringBuilder builder = new StringBuilder();
                while (builder.toString().length() == 0) {
                    int length = rand.nextInt(5) + 5;
                    for (int i = 0; i < length; i++) {
                        builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
                    }
                    if (identifiers.contains(builder.toString())) {
                        builder = new StringBuilder();
                    }
                }
                return builder.toString();
                //just for generate random identifiers;
            } else if (lex == 2) {
               StringBuilder builder = new StringBuilder();
                while (builder.toString().length() == 0) {
                    int length = rand.nextInt(5) + 5;
                    for (int i = 0; i < length; i++) {
                        builder.append(lexicon2.charAt(rand.nextInt(lexicon2.length())));
                    }
                    if (identifiers.contains(builder.toString())) {
                        builder = new StringBuilder();
                    }
                }
                return builder.toString();
            } else {
                StringBuilder builder = new StringBuilder();
                while (builder.toString().length() == 0) {
                    int length = rand.nextInt(5) + 5;
                    for (int i = 0; i < length; i++) {
                        builder.append(lexicon3.charAt(rand.nextInt(lexicon3.length())));
                    }
                    if (identifiers.contains(builder.toString())) {
                        builder = new StringBuilder();
                    }
                }
                return builder.toString();
            }
        }
    }

    public static void main(String[] args) {
        // TODO code application logic here
        generateNames gn = new generateNames();
        List<String> namesList = new ArrayList<>();
        List<String> addressesList = new ArrayList<>();
        List<String> telephonesList = new ArrayList<>();
        List<String> sexList = new ArrayList<>();
        List<Date> datesList = new ArrayList<>();

        for (int i = 0; i < 35; i++) {
            namesList.add(gn.randomIdentifier(1));
            addressesList.add(gn.randomIdentifier(2));
            telephonesList.add(gn.randomIdentifier(3));
            if (i%2 == 0){
                sexList.add("M");
            }else{
                sexList.add("F");
            }
            datesList.add(Date.from(Instant.now()));
        }
        
        //create members
        for (int i = 0; i < 5; i++) {
            new Membership(namesList.get(i), addressesList.get(i),
            telephonesList.get(i), sexList.get(i), datesList.get(i), true);
            
            new Athlete(namesList.get(i+5), addressesList.get(i+5),
            telephonesList.get(i+5), sexList.get(i+5), datesList.get(i+5));
            
            new Coach(namesList.get(i+10), addressesList.get(i+10),
            telephonesList.get(i+10), sexList.get(i+10), datesList.get(i+10));
            
            new Official(namesList.get(i+15), addressesList.get(i+15),
            telephonesList.get(i+15), sexList.get(i+15), datesList.get(i+15));
            
            new StaffAdmin(namesList.get(i+20), addressesList.get(i+20),
            telephonesList.get(i+20), sexList.get(i+20), datesList.get(i+20));
            
            new Athlete(namesList.get(i+25), addressesList.get(i+25),
            telephonesList.get(i+25), sexList.get(i+25), datesList.get(i+25));
            
            new Athlete(namesList.get(i+30), addressesList.get(i+30),
            telephonesList.get(i+30), sexList.get(i+30), datesList.get(i+30));
            /*
            System.out.println("new membership");
            System.out.println("name: " + namesList.get(i));
            System.out.println("address: " + addressesList.get(i));
            System.out.println("telephone: " + telephonesList.get(i));
            System.out.println("sex: " + sexList.get(i));
            System.out.println("dates: " + datesList.get(i));
            System.out.println();
            */
        }
        
        List<Coach> coachsList = Coach.getCoachsList();
        List<Athlete> athletesList = Athlete.getAthletesList();
        /*debug stuff
        for (Athlete ath : athletesList){
            System.out.println("--------->type:" + ath.getType() + " +id: " + ath.getId());
            System.out.println("SAME ATHLETE ->" + Membership.getMembersList().get(ath.getId()));
        }
        */
        //create teams
        for (int i = 0; i < athletesList.size()/5; i++){
            List<Athlete> members = athletesList.subList(5*i,5*i+5);
            int captainId = members.get(0).getId();
            int coachId = coachsList.get(i).getId();
            if (i == 0){
                new Team("Football Solent", captainId, coachId, "Football", "Men", "U17", members);
            }else if (i == 1){
                new Team("Basketball Solent", captainId, coachId, "Basketball", "Women", "U20", members);
            }else{
                new Team("Swimmers Solent", captainId, coachId, "Swimming", "Mixed", "Senior", members);
            }
        }
        
        //create events
        List<Official> officialsList = Official.getOfficialsList();     
        
        /*
        Event ev1 = new Event("Sunday Match", "Football", "Men", "U15");
        ev1.setDate(Date.from(Instant.now()));
        ev1.setTransport("Train");
        ev1.setOfficialId(officialsList.get(0).getId());
        ev1.addParticipant(team1);
        ev1.addParticipant(team2);
        */
        
        Event ev1 = new Event("Sunday Match", "Football", "Men", "U15", "Bus", Date.from(Instant.now()), officialsList.get(0).getId(), Team.getTeams().subList(0, 2));
        Event ev2 = new Event("Real cup", "Basketball", "Women", "U20", "Car", Date.from(Instant.now()), officialsList.get(1).getId(), Team.getTeams().subList(1, 3));
        Event ev3 = new Event("Fishs in the air", "Swimming", "Mixed", "Masters", "Train", Date.from(Instant.now()), officialsList.get(2).getId(), Team.getTeams().subList(1, 3));
        /*testing serialization-->taking it away for a while. Yes, as I am creating Events using sublists of athletes...it crushes... */
        officialsList.get(0).addEvent(ev1);
        officialsList.get(1).addEvent(ev2);
        officialsList.get(2).addEvent(ev3);
        
        
        //display members
        System.out.println("MEMBERS: 5 support, 15 athletes, 5 coachs, 5 officials and 5 staff Admin");
        for (Membership member : Membership.membersList){
            System.out.println(member);
        }
        
        //create trainings
        //List<Coach> coachsList = Coach.getCoachsList(); already created
        Training training1 = new Training("RoadDistance", "Sprinting", "U20", Date.from(Instant.now()), coachsList.get(0).getId());
        Training training2 = new Training("XCountryDistance", "Hurdling", "U17", Date.from(Instant.now()), coachsList.get(1).getId());
        Training training3 = new Training("Track", "Jumping", "U20", Date.from(Instant.now()), coachsList.get(2).getId());
        Training training4 = new Training("Field", "Sprinting", "U15", Date.from(Instant.now()), coachsList.get(3).getId());
        Training training5 = new Training("RoadDistance", "Repetitions", "Senior", Date.from(Instant.now()), coachsList.get(4).getId());
        
        coachsList.get(0).addTraining(training1);
        coachsList.get(1).addTraining(training2);
        coachsList.get(2).addTraining(training3);
        coachsList.get(3).addTraining(training4);
        coachsList.get(4).addTraining(training5);

        //display teams
        System.out.println ("\n------>TEAMS<------");
        for (Team t : Team.getTeams()){
            System.out.println(t);
        }
        
        //display events
        System.out.println("----->EVENTS<-----");
        for (Event e : Event.getEvents()){
            System.out.println(e);
        }
        
        //display trainings
        System.out.println("----->TRAININGS<-----");
        for (Training t : Training.getTrainingsList()){
            System.out.println(t);
        }
        
        //trying to serialize a member.
        //serializeObject(Membership.getMembersList().get(0));
        /*
        serializeObject(new Membership("Pedro", "Road Lagasca",
            "613 28 99 82", "M", Date.from(Instant.now()), true));
        Membership resultMember = deserializeMembership();
        System.out.println("\nDEFINITIVE MEMBERSHIPPPPPPPPPPPP---->" + resultMember);
        */
        
        new Membership("Pedro", "Road Lagasca",
            "613 28 99 82", "M", Date.from(Instant.now()), true);
        serializeMemberships((ArrayList<Membership>)Membership.getMembersList());
        List<Membership> retrievedMemberships = deserializeMemberships();
        
        System.out.println("\nDEFINITIVE MEMBERSHIPPPPPPPPPPPPSSSSS---->");
        for (Membership m : retrievedMemberships){
            System.out.println(m);
            System.out.println("ID-->" + m.getId());
        }
        
        System.out.println("\nENDDDDDD");
    }
    
    //NOW --> HOW TO SET THE DESERIALIZED ARRAYLIST INTO STATIC VALUE
    //MEMBERSHIP LISTS, ATHLETES LIST, ETC...
    //AND TRY TO SERIALIZE A COMPLETE OBJECT LIKE ATHLETE WITH A LIST
    //OF EVENTS, TRAININGS, ETC, AND SEE IF IT IS WORKING WELL...
    //BUT DONT THINK IT WILL
    
    
    
    //reference: https://www.tutorialspoint.com/java/java_serialization.htm
    //class to serialize objects
    private static void serializeObject(Object ob){
        try {
         FileOutputStream fileOut =
         new FileOutputStream("object.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(ob);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in object.ser");
      }catch(IOException i) {
         i.printStackTrace();
      }
    }
    
    //class to deserialize Membership
    private static Membership deserializeMembership(){
        Membership member = null;
      try {
         FileInputStream fileIn = new FileInputStream("object.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         member = (Membership) in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i) {
         i.printStackTrace();
      }catch(ClassNotFoundException c) {
         System.out.println("Membership class not found");
         c.printStackTrace();
      }
      
      return member;
    }
    
    
    //serialize List<Membership> (including athletes, coachs...)
    private static void serializeMemberships(ArrayList<Membership> membershipsList){
        try {
         FileOutputStream fileOut =
         new FileOutputStream("memberships.ser");
         ObjectOutputStream out = new ObjectOutputStream(fileOut);
         out.writeObject(membershipsList);
         out.close();
         fileOut.close();
         System.out.printf("Serialized data is saved in memberships.ser");
      }catch(IOException i) {
          System.err.println("-----------------------------> FAIL ERROR NOT SERIALIZEDD JO");
         i.printStackTrace();
      }
    }
    
    //deserialize List<Membership> (including athletes, coachs...)
    private static ArrayList<Membership> deserializeMemberships(){
        ArrayList<Membership> membershipsList = null;
      try {
         FileInputStream fileIn = new FileInputStream("memberships.ser");
         ObjectInputStream in = new ObjectInputStream(fileIn);
         membershipsList = (ArrayList<Membership>)in.readObject();
         in.close();
         fileIn.close();
      }catch(IOException i) {
         i.printStackTrace();
      }catch(ClassNotFoundException c) {
         System.out.println("Membership class not found");
         c.printStackTrace();
      }
      
      return membershipsList;
    }

}
