

import junit.framework.Test;
import junit.framework.TestSuite;

import wire.*;
import gate.*;
import simulate.*;
import module.*;

public class AllTests {

	public static Test suite() {
		TestSuite suite = new TestSuite();
		suite.addTestSuite(WireTest.class);
		suite.addTestSuite(WireArrayTest.class);
		suite.addTestSuite(WireProbeTest.class);
		
		suite.addTestSuite(DummyGateTest.class);
		suite.addTestSuite(AndGateTest.class);
		suite.addTestSuite(OrGateTest.class);
		suite.addTestSuite(NotGateTest.class);
		suite.addTestSuite(NorGateTest.class);
		suite.addTestSuite(XorGateTest.class);
		
		suite.addTestSuite(WorkTest.class);
		suite.addTestSuite(WorkQueueTest.class);
		suite.addTestSuite(SimulatorTest.class);
		
		suite.addTestSuite(HalfAdderTest.class);
		suite.addTestSuite(FullAdderTest.class);
		suite.addTestSuite(RippleCarryAdderTest.class);
		return suite;
	}
}
