package simulate;

import gate.GateAction;

/**
 * logic component simulator
 */
public class Simulator {
	private int time;
	private WorkQueue workQueue;
	
	public Simulator() {
		time = 0;
		workQueue = new WorkQueue();
	}
	
	/**
	 * add simulation component
	 * @param component
	 */
	public void addComponent(SimulateComponent component) throws SimulateException {
		if (component != null) {
			component.addOnSimulator(this);
			return;
		}
		throw new SimulateException("add component is null");
	}
	
	/**
	 * add gate action
	 * @param delay invoke delay time
	 * @param action invoke action
	 */
	public void addGateAction(int delay, final GateAction action) throws SimulateException {
		if (delay >= 0 && action != null) {
			try {
				workQueue.add(new Work(time + delay) {
					@Override
					public void invokeWork() {
						action.invokeAction();
					}
				});
				return;
			} catch (WorkException e) {
				e.printStackTrace();
			}
		}
		throw new SimulateException("add gate action failure");
	}
	
	/**
	 * has remain work
	 * @return
	 */
	public boolean hasWork() {
		return workQueue.hasWork();
	}
	
	/**
	 * get simulator now time
	 * @return time
	 */
	public int getTime() {
		return time;
	}
	
	/**
	 * simulate next work
	 */
	public void next() {
		if (hasWork()) {
			Work work;
			try {
				work = workQueue.pop();
				time = work.getTime();
				System.out.println("Work at " + time);
				work.invokeWork();
			} catch (WorkException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * start simulator until no work remain
	 */
	public void run() {
		System.out.println("=== Simulation start ===");
		while (hasWork()) {
			next();
		}
		System.out.println("=== Simulation finish ===\n");
	}
	
	/**
	 * start simulator until limit time
	 * @param limitTime
	 */
	public void runUntil(int limitTime) throws SimulateException {
		if (limitTime >= 0) {
			int maxTime = time + limitTime;
			System.out.println("=== Simulation start ===");
			while (hasWork()) {
				try {
					if (workQueue.nextTime() > maxTime) { // next work exceed time limit
						time = maxTime;
						break;
					}
					next();
				} catch (WorkException e) {
					throw new SimulateException("runUntil internal error");
				}
			}
			if (hasWork()) {
				System.out.println("*** Simulation suspend ***\n");
			} else {
				System.out.println("=== Simulation finish ===\n");
			}
			return;
		}
		throw new SimulateException("runUntil time less than zero");
	}
}
