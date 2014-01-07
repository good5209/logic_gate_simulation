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
	public Work(int time) {
		this.time = time;
	}
	
	/**
	 * get invoke time
	 * @return delay time
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * work action
	 */
	abstract public void invokeWork();
}
