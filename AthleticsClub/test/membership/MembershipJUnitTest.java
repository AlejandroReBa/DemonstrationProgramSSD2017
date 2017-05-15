/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package membership;

import java.util.ArrayList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */
public class MembershipJUnitTest {
    
    public MembershipJUnitTest() {
    }
    
@After
    public void runAfterTestMethod() {
        Membership.membersList = new ArrayList<>();
        Membership.incrementalId = 0;
    }

    //createOrganisation test methods
    @Test
    public void atTheBeginningSizeOfMembersListListIsZero() {
        assertEquals(0, Membership.getMembersList().size());
    }

    @Test
    public void createFirstMemberSizeOfMemberListIsOne() {
        new Membership("Member1");
        assertEquals(1, Membership.getMembersList().size());
    }


    @Test
    public void createSecondMembershipSizeOfMembersListIsTwo() {
        new Membership("Member1");
        new Membership("Member2");
        assertEquals(2, Membership.getMembersList().size());
    }


    @Test
    public void createANewMembershipIncrementsSizeOfMembersListInOne() {
        new Membership("Member1");
        new Membership("Member2");
        new Membership("Member3");
        int sizeBefore = Membership.getMembersList().size();
        new Membership("Member4");
        assertEquals(sizeBefore + 1, Membership.getMembersList().size());
    }

    //findOrganisation test methods
    @Test
    public void findExistingMembershipWithSizeOfMembersListIsOne() {
        Membership member = new Membership("Member2");
        assertEquals(member, Membership.viewMembershipById(0));
    }

    @Test
    public void findExistingMembershipWithSizeOfMembersListIsMoreThanOne() {
        Membership member = new Membership("Member1");
        Membership member2 = new Membership("Member2");
        Membership member3 = new Membership("Member3");
        assertEquals(member2,Membership.viewMembershipById(1));
    }

    @Test
    public void findNoExistingMembershipWithSizeOfMembersListIsZeroReturnsNull() {
        assertEquals(Membership.viewMembershipById(2), null);
    }

    @Test
    public void findNoExistingMembershipWithSizeOfMembersListIsOneReturnsNull() {
        Membership member = new Membership("Member1");
        assertEquals(Membership.viewMembershipById(2), null);
    }

    @Test
    public void findNoExistingMembershipWithSizeOfMembersListIsMoreThanOneReturnsNull() {
        Membership member = new Membership("Member1");
        Membership member2 = new Membership("Member2");
        Membership member3 = new Membership("Member3");
        assertEquals(Membership.viewMembershipById(6), null);
    }

    //findAllOrganisation test methods
    @Test
    public void findAllMembershipWithSizeOfMembersListIsZeroReturnsEmptyCollection() {
        assertTrue(Membership.getMembersList().isEmpty());
    }

    @Test
    public void findAllMembershipWithSizeOfMembersListIsOneReturnsCollectionOfSize1() {
        Membership member = new Membership("Member1");
        assertEquals(Membership.getMembersList().size(), 1);
    }

    @Test
    public void findAllMembershipWithSizeOfMembersListIsMoreThanOneReturnsCollectionWithTheSameSize() {
        Membership member = new Membership("Member1");
        Membership member2 = new Membership("Member2");
        Membership member3 = new Membership("Member3");
        
        assertEquals(Membership.getMembersList().size(), 3);
    }
    
}
