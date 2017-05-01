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

public class Membership {
    enum typeEnum {Athlete, Coach, Official, Administration, Support};
    enum ageGroupEnum {U13, U15, U17, U20, Senior, Masters};
    enum qualificationsEnum {Coaching, Official};
    enum sexEnum {F, M, Other};
    
    private static int incrementalId = 1; //unique place to incrementalId
    protected static List<Membership> membersList = new ArrayList<>();
    
    private typeEnum type = typeEnum.Support;
    
    private int id;
    private String name;
    private String address;
    private String telephone;
    private sexEnum sex;
    private Date birth;
    
    //constructor for support
    public Membership(String nameIn, String addressIn, String telIn,
            String sexIn, Date birthIn, boolean addToMembers){
        this.name = nameIn;
        this.address = addressIn;
        this.telephone = telIn;
        this.sex = sexEnum.valueOf(sexIn);
        this.birth = birthIn;
        
        this.id = incrementalId;
        incrementalId++;
        //without this, extended classes would be added previous fully
        //initialization
        if (addToMembers){ 
        membersList.add(this);
        }
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
    public Date getBirth(){
        return this.birth;
    }
    
    
}
