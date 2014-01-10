package simulate;

/**
 * representation simulation event
 */
class Work {
	private int time;
	private SimulateAction action;
	
	/**
	 * work, invoke action at time
	 * @param time
	 * @param action
	 */
	public Work(int time, SimulateAction action) throws WorkException {
		if (time >= 0 && action != null) {
			this.time = time;
			this.action = action;
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
	 * invoke action
	 */
	public void invoke() {
		action.invoke();
	}
}
