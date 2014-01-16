package simulate;

/**
 * simulation component
 */
public interface SimulateComponent {
	/**
	 * add component to simulator
	 * @param simulator
	 */
	public void addOnSimulator(Simulator simulator) throws SimulateComponentException;
}
