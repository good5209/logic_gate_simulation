package simulate;

/**
 * representation simulation event
 */
abstract class Work {
	private int time;
	
	/**
	 * work invoke at time
	 * @param time
	 */
	public Work(int time) throws WorkException {
		if (time >= 0) {
			this.time = time;
			return;
		}
		throw new WorkException("work time less than zero");
	}
	
	/**
	 * get invoke time
	 * @return delay time
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * invoke work action
	 */
	abstract public void invoke();
}
