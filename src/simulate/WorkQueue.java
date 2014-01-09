package simulate;

import java.util.LinkedList;
import java.util.ListIterator;

/**
 * work queue
 * collect and schedule simulator work
 */
class WorkQueue {
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
	public void add(Work work) throws WorkException {
		if (work != null) {
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
			return;
		}
		throw new WorkException("add work failure");
	}
	
	/**
	 * pop next invoke work
	 * @return invoke work
	 */
	public Work pop() throws WorkException {
		if (hasWork()) {
			return queue.removeLast();
		}
		throw new WorkException("work queue is empty");
	}
	
	/**
	 * get next work invoke time
	 * @return next invoke time
	 */
	public int nextTime() throws WorkException {
		if (hasWork()) {
			return queue.getLast().getTime();
		}
		throw new WorkException("work queue is empty");
	}
	
	/**
	 * queue has more work
	 * @return has work
	 */
	public boolean hasWork() {
		return !queue.isEmpty();
	}
}
