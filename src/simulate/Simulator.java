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
	public void addComponent(SimulateComponent component) {
		if (component != null) {
			component.addOnSimulator(this);
		}
	}
	
	/**
	 * add gate action
	 * @param delay invoke delay time
	 * @param action invoke action
	 */
	public void addGateAction(int delay, final GateAction action) {
		if (delay >= 0 && action != null) {
			workQueue.add(new Work(time + delay) {
				@Override
				public void invokeWork() {
					action.invokeAction();
				}
			});
		}
		// TODO add exception
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
			Work work = workQueue.pop();
			time = work.getTime();
			System.out.println("Work at " + time);
			work.invokeWork();
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
	public void runUntil(int limitTime) {
		if (limitTime >= 0) {
			int maxTime = time + limitTime;
			System.out.println("=== Simulation start ===");
			while (hasWork()) {
				if (workQueue.nextTime() > maxTime) { // next work exceed time limit
					time = maxTime;
					break;
				}
				next();
			}
			if (hasWork()) {
				System.out.println("*** Simulation suspend ***\n");
			} else {
				System.out.println("=== Simulation finish ===\n");
			}
		}
	}
}
