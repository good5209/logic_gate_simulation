package simulate;

import junit.framework.TestCase;

public class WorkQueueTest extends TestCase {
	public void testWorkQueue() {
		new WorkQueue();
	}
	
	public void testAdd() {
		WorkQueue queue = new WorkQueue();
		queue.add(null);
		assertEquals(false, queue.hasWork());
		
		queue.add(new Work(0) {
			@Override
			public void invokeWork() {}
		});
		assertEquals(true, queue.hasWork());
	}
	
	public void testPop() {
		WorkQueue queue = new WorkQueue();
		assertEquals(null, queue.pop());
		
		Work works[] = {
			new Work(0) { // at 0
				@Override
				public void invokeWork() {}
			},
			new Work(2) { // at 2
				@Override
				public void invokeWork() {}
			},
			new Work(1) { // at 1
				@Override
				public void invokeWork() {}
			},
			new Work(2) { // at 3
				@Override
				public void invokeWork() {}
			},
			new Work(3) { // at 5
				@Override
				public void invokeWork() {}
			},
			new Work(2) { // at 4
				@Override
				public void invokeWork() {}
			}
		};
		for (Work each : works) {
			queue.add(each);
		}
		assertEquals(works[0], queue.pop());
		assertEquals(works[2], queue.pop());
		assertEquals(works[1], queue.pop());
		assertEquals(works[3], queue.pop());
		assertEquals(works[5], queue.pop());
		assertEquals(works[4], queue.pop());
		assertEquals(false, queue.hasWork());
	}
	
	public void testNextTime() {
		WorkQueue queue = new WorkQueue();
		assertEquals(-1, queue.nextTime());
		
		queue.add(null);
		assertEquals(-1, queue.nextTime());
		
		queue.add(new Work(0) {
			@Override
			public void invokeWork() {}
		});
		queue.add(new Work(0) {
			@Override
			public void invokeWork() {}
		});
		queue.add(new Work(1) {
			@Override
			public void invokeWork() {}
		});
		queue.add(new Work(2) {
			@Override
			public void invokeWork() {}
		});
		assertEquals(0, queue.nextTime());
		queue.pop();
		assertEquals(0, queue.nextTime());
		queue.pop();
		assertEquals(1, queue.nextTime());
		queue.pop();
		assertEquals(2, queue.nextTime());
		queue.pop();
		assertEquals(-1, queue.nextTime());
	}
	
	public void testHasWork() {
		WorkQueue queue = new WorkQueue();
		assertEquals(false, queue.hasWork());
		
		queue.add(null);
		assertEquals(false, queue.hasWork());
		
		queue.add(new Work(0) {
			@Override
			public void invokeWork() {}
		});
		assertEquals(true, queue.hasWork());
		queue.add(new Work(1) {
			@Override
			public void invokeWork() {}
		});
		assertEquals(true, queue.hasWork());
		
		queue.pop();
		assertEquals(true, queue.hasWork());
		queue.pop();
		assertEquals(false, queue.hasWork());
	}
}
