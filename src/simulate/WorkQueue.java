package simulate;

import java.util.Vector;

/**
 * work queue
 * collocate and schedule simualtor work
 */
public class WorkQueue {
	private Vector<Work> queue = new Vector<Work>();
	
	/**
	 * add work into queue
	 * @param work
	 */
	public void add(Work work) {
		if (work != null) {
			for (int i = queue.size() - 1; i >= 0; i--) { // seek from queue tail
				// new work should insert after previous or same time work
				// later add work will cover previous work effect
				if (queue.get(i).getTime() <= work.getTime()) {
					queue.add(i + 1, work);
					return;
				}
			}
			queue.add(0, work); // work smaller than whole queue, add to queue head
		}
	}
	
	/**
	 * pop next invoke work
	 * @return invoke work
	 */
	public Work pop() {
		if (hasWork()) {
			return queue.remove(0);
		}
		return null;
	}
	
	/**
	 * get next work invoke time
	 * @return next invoke time
	 */
	public int nextTime() {
		if (hasWork()) {
			return queue.firstElement().getTime();
		}
		return -1;
	}
	
	/**
	 * queue has more work
	 * @return has work
	 */
	public boolean hasWork() {
		return !queue.isEmpty();
	}
}
