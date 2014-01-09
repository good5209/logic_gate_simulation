package simulate;

import junit.framework.TestCase;

public class WorkTest extends TestCase {
	public void testWork() {
		try {
			new Work(-1) {
				@Override
				public void invokeWork() {}
			};
			assertFalse("An WorkException should be thrown in Work", true);
		} catch (WorkException e) {
			assertFalse(false);
		}
		
		try {
			new Work(0) {
				@Override
				public void invokeWork() {}
			};
			new Work(1) {
				@Override
				public void invokeWork() {}
			};
			assertTrue(true);
		} catch (WorkException e) {
			assertTrue("No WorkException should be thrown in Work", false);
		}
	}
	
	public void testGetTime() throws WorkException {
		Work work = new Work(0) {
			@Override
			public void invokeWork() {}
		};
		assertEquals(0, work.getTime());
		
		work = new Work(3) {
			@Override
			public void invokeWork() {}
		};
		assertEquals(3, work.getTime());
	}
	
	public void testInvokeWork() {
		try {
			Work work = new Work(0) {
				@Override
				public void invokeWork() {
					throw new RuntimeException();
				}
			};
			work.invokeWork();
			assertFalse("An RuntimeException should be thrown in invokeWork", true);
		} catch (Exception e) {
			assertFalse(false);
		}
	}
}
