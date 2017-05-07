package membership;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */

public class Membership implements Serializable{
    //to avoid invalidClasException as deserialization
    //source: https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html
    private static final long serialVersionUID = 42L;
    
    public enum typeEnum {Athlete, Coach, Official, Administration, Support};
    public enum ageGroupEnum {U13, U15, U17, U20, Senior, Masters, Other};//other for no athletes
    public enum qualificationsEnum {Coaching, Official, None}; //None for no qualified memberships
    public enum sexEnum {F, M, Other};
    
    public static int incrementalId = 0; //unique place to incrementalId
    public static List<Membership> membersList = new ArrayList<>();
    
    protected typeEnum type = typeEnum.Support;
    
    private int id;
    private String name;
    private String address;
    private String telephone;
    private sexEnum sex;
    private ageGroupEnum ageGroup; //for extended classes, not support membership
    private qualificationsEnum qualification; //for extended classes, not support membership
    private Date birthday;
    
    //constructor for support
    public Membership(String nameIn, String addressIn, String telIn,
            String sexIn, Date birthdayIn, boolean addToMembers){
        this.name = nameIn;
        this.address = addressIn;
        this.telephone = telIn;
        this.sex = sexEnum.valueOf(sexIn);
        this.birthday = birthdayIn;
        this.ageGroup = ageGroupEnum.Other;
        this.qualification = qualificationsEnum.None;
        
        this.id = incrementalId;
        incrementalId++;
        //without this, extended classes would be added previous fully
        //initialization
        if (addToMembers){
            membersList.add(this);
        }
    }
    
    public static List<Membership> getMembersList(){
        return membersList;
    }
    
    //attribute that can't be changed
    public int getId(){
        return this.id;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setName(String nameIn){
        this.name = nameIn;
    }
    
    public String getAddress (){
        return this.address;
    }
    
    public void setAddress(String addressIn){
        this.address = addressIn;
    }
    
    public String getTelephone(){
        return this.telephone;
    }
    
    public void setTelephone(String telephoneIn){
        this.telephone = telephoneIn;
    }
    
    //attribute that can't be changed
    public String getSex(){
        return this.sex.toString();
    }
    
    //attribute that can't be changed
    public Date getBirthday(){
        return this.birthday;
    }
    
    public void setBirthday(Date newBirthday){
        this.birthday = newBirthday;
    }
    
    public String getType(){
        return this.type.toString();
    }
    
    public void setType(String typeIn){
        this.type = typeEnum.valueOf(typeIn);
    }
    
    public String getAgeGroup(){
        return this.ageGroup.toString();
    }
    
    public void setAgeGroup(String ageGroupIn){
        this.ageGroup = ageGroupEnum.valueOf(ageGroupIn);
    }
    
    public String getQualification(){
        return this.qualification.toString();
    }
    
    public void setQualification(String qualificationIn){
        this.qualification = qualificationsEnum.valueOf(qualificationIn);
    }
    
    
    @Override
    public String toString(){
        String res = "Type: " + type + ", Name: " + this.name + ", Address: " + this.address
               + ", Telephone: " + this.telephone + ", Sex: " + this.sex
               + ", Birthday: " + this.birthday;
        return res;
    }
    
    //needed to increment incrementalId variable
    //and add the deserialized Member to membersList    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException{
        in.defaultReadObject();
        incrementalId++;
    }

    public void addItself(){
        //nothing to do here, method used by subclasses
        //but it needs to be initialized here for polymorphism reasons
    }
    
    
    public static Membership viewMembershipById(int id){
        Membership resMembership = null;
        for (Membership currentMembership : membersList){
            if (currentMembership.getId() == id){
                resMembership = currentMembership;
                break;
            }
        }
        return resMembership;
    }
    
    public static List<Membership> viewMembershipByName(String name){
        List<Membership> resMemberships = new ArrayList<>();
        int lastCharIndex = name.length();
        for (Membership currentMembership : membersList){
            String currentName = currentMembership.getName();
            if (currentName.length() >= name.length() &&
                    currentName.toLowerCase().substring(0, lastCharIndex)
                    .equals(name.toLowerCase())){
                resMemberships.add(currentMembership);
            }
            
        }
        
        return resMemberships;
    }
    
    
     public static List<Membership> viewMembershipsByType(String type){
        List<Membership> resMemberships = new ArrayList<>();
        for (Membership currentMembership : membersList){
            if (currentMembership.getType().equals(type)){
                resMemberships.add(currentMembership);
            }
        }
        return resMemberships;
    }
    
    public static List<Membership> viewMembershipsByQualification(String qualification){
        List<Membership> resMemberships = new ArrayList<>();
        for (Membership currentMembership : membersList){
            if (currentMembership.getQualification().equals(qualification)){
                resMemberships.add(currentMembership);
            }
        }
        return resMemberships;
    }
     
    public static List<Membership> viewMembershipsBySex(String sex){
        List<Membership> resMemberships = new ArrayList<>();
        for (Membership currentMembership : membersList){
            if (currentMembership.getSex().equals(sex)){
                resMemberships.add(currentMembership);
            }
        }
        return resMemberships;
    }
    
    public static List<Membership> viewMembershipsByAgeGroup(String ageGroup){
        List<Membership> resMemberships = new ArrayList<>();
        for (Membership currentMembership : membersList){
            if (currentMembership.getAgeGroup().equals(ageGroup)){
                resMemberships.add(currentMembership);
            }
        }
        return resMemberships;
    }   
}
