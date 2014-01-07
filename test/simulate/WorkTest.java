package simulate;

import junit.framework.TestCase;

public class WorkTest extends TestCase {
	public void testWork() {
		new Work(1) {
			@Override
			public void invokeWork() {}
		};
	}
	
	public void testGetDelay() {
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
