/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package membership;

import java.util.Date;

/**
 *
 * @author Alejandro Reyes
 */
public class StaffAdmin extends Membership{ //at the moment is the same as support..

    public StaffAdmin(String nameIn, String addressIn, String telIn, String sexIn, Date birthIn) {
        super(nameIn, addressIn, telIn, sexIn, birthIn, false);
        type = typeEnum.Administration;
    }
}
