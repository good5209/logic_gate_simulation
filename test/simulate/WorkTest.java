package simulate;

import junit.framework.TestCase;

public class WorkTest extends TestCase {
	public void testWork() {
		try {
			new Work(-1) {
				@Override
				public void invoke() {}
			};
			assertFalse("An WorkException should be thrown in Work", true);
		} catch (WorkException e) {
			assertFalse(false);
		}
		
		try {
			new Work(0) {
				@Override
				public void invoke() {}
			};
			new Work(1) {
				@Override
				public void invoke() {}
			};
			assertTrue(true);
		} catch (WorkException e) {
			assertTrue("No WorkException should be thrown in Work", false);
		}
	}
	
	public void testGetTime() throws WorkException {
		Work work = new Work(0) {
			@Override
			public void invoke() {}
		};
		assertEquals(0, work.getTime());
		
		work = new Work(3) {
			@Override
			public void invoke() {}
		};
		assertEquals(3, work.getTime());
	}
	
	public void testInvokeWork() {
		try {
			Work work = new Work(0) {
				@Override
				public void invoke() {
					throw new RuntimeException();
				}
			};
			work.invoke();
			assertFalse("An RuntimeException should be thrown in invokeWork", true);
		} catch (Exception e) {
			assertFalse(false);
		}
	}
}
