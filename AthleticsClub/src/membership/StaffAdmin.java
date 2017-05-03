/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package membership;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import static membership.Athlete.athletesList;

/**
 *
 * @author Alejandro Reyes
 */
public class StaffAdmin extends Membership implements Serializable{ //at the moment is the same as support..
    
    //to avoid invalidClasException as deserialization
    //source: https://docs.oracle.com/javase/8/docs/api/java/io/Serializable.html
    private static final long serialVersionUID = 42L;

    public static List<StaffAdmin> staffAdminsList = new ArrayList<>();
    
    public StaffAdmin(String nameIn, String addressIn, String telIn, String sexIn, Date birthIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Administration;
        Membership.membersList.add(this);
        staffAdminsList.add(this);
    }
    
    public static List<StaffAdmin> getStaffAdminsList(){
        return staffAdminsList;
    }
    
    @Override
    public void addItself(){
        //nothing to do here, method used by subclasses
        //but it needs to be initialized here for polymorphism reasons
        staffAdminsList.add(this);
    }
}
