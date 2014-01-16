package simulate;

import junit.framework.TestCase;

public class WorkTest extends TestCase {
	public void testWork() {
		try {
			new Work(-1, null);
			assertFalse("An WorkException should be thrown in Work", true);
		} catch (WorkException e) {
			assertFalse(false);
		}
		
		try {
			new Work(-1, new SimulateAction() {
				@Override
				public void invoke() {}
			});
			assertFalse("An WorkException should be thrown in Work", true);
		} catch (WorkException e) {
			assertFalse(false);
		}
		
		try {
			new Work(0, null);
			assertFalse("An WorkException should be thrown in Work", true);
		} catch (WorkException e) {
			assertFalse(false);
		}
		
		try {
			new Work(0, new SimulateAction() {
				@Override
				public void invoke() {}
			});
			new Work(1, new SimulateAction() {
				@Override
				public void invoke() {}
			});
			assertTrue(true);
		} catch (WorkException e) {
			assertTrue("No WorkException should be thrown in Work", false);
		}
	}
	
	public void testGetTime() throws WorkException {
		Work work = new Work(0, new SimulateAction() {
			@Override
			public void invoke() {}
		});
		assertEquals(0, work.getTime());
		
		work = new Work(3, new SimulateAction() {
			@Override
			public void invoke() {}
		});
		assertEquals(3, work.getTime());
	}
	
	public void testInvoke() {
		try {
			Work work = new Work(0, new SimulateAction() {
				@Override
				public void invoke() {
					throw new RuntimeException();
				}
			});
			assertTrue(true);
			work.invoke();
			assertTrue("An RuntimeException should be thrown in invokeWork", false);
		} catch (WorkException e) {
			assertTrue("No WorkException should be thrown in Work", false);
		} catch (RuntimeException e) {
			assertTrue(true);
		}
	}
}
