package simulate;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * work queue
 * collect and schedule simulator work
 */
public class WorkQueue {
	/*
	 * work list
	 * first element is biggest time work
	 * last element is smallest time work
	 */
	private LinkedList<Work> queue = new LinkedList<Work>();
	
	/**
	 * add work into queue
	 * @param work
	 */
	public void add(Work work) {
		if (work != null && work.getTime() >= 0) {
			ListIterator<Work> iterator = queue.listIterator();
			/*
			 * usually work time is growing, seek insert point from big time
			 * new work should invoke later then previous and same time work
			 * it will cover previous work effect
			 */
			while (iterator.hasNext()) {
				if (iterator.next().getTime() <= work.getTime()) {
					iterator.previous(); // move cursor at insert point
					break;
				}
			}
			iterator.add(work);
		}
	}
	
	/**
	 * pop next invoke work
	 * @return invoke work
	 */
	public Work pop() {
		if (hasWork()) {
			return queue.removeLast();
		}
		return null;
	}
	
	/**
	 * get next work invoke time
	 * @return next invoke time
	 */
	public int nextTime() {
		if (hasWork()) {
			return queue.getLast().getTime();
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
