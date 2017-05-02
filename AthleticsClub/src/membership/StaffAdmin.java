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

/**
 *
 * @author Alejandro Reyes
 */
public class StaffAdmin extends Membership implements Serializable{ //at the moment is the same as support..

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
}
