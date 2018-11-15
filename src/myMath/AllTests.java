package myMath;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;
/**
 * Junit to test all Junits
 * @author Shayke Shok and Itay Grinblat
 */
@RunWith(Suite.class)
@SuiteClasses({ TestMonom.class, TestPolynom.class })
public class AllTests {

}
