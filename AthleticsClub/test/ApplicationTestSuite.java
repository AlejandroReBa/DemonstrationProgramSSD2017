/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author Alejandro Reyes (AlejandroReBa)
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({event.EventJUnitTest.class, membership.MembershipJUnitTest.class,
    membership.TeamJUnitTest.class, training.TrainingJUnitTest.class,
    training.TrainingRecordJUnitTest.class})
public class ApplicationTestSuite {

}
