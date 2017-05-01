/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package athleticsclub;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import membership.Athlete;
import membership.Membership;

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

        for (int i = 0; i < 10; i++) {
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
        
        for (int i = 0; i < 5; i++) {
            new Membership(namesList.get(i), addressesList.get(i),
            telephonesList.get(i), sexList.get(i), datesList.get(i), true);
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
        
        for (int i = 5; i < 10; i++){
            /*
            new Athlete(namesList.get(i), addressesList.get(i),
            telephonesList.get(i), sexList.get(i), datesList.get(i));
            */
            
            new Athlete("Pedro", "Royal Crescent Road", "676 88 99 28", "M", Date.from(Instant.now()));
        }
        
        System.out.println("MEMBERS: 5 support, 5 athletes");
        for (Membership member : Membership.membersList){
            System.out.println(member);
        }

    }

}
