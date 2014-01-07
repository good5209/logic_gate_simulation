package wire;

/**
 * monitor wire signal change
 */
public class WireProbe implements WireListener {
	private Wire wire;
	
	public WireProbe(Wire wire) {
		this.wire = wire;
		this.wire.listenSignal(this);
	}
	
	@Override
	public void wireSignalChanged() {
		System.out.println("Wire \"" + wire.getName() + "\" change " + wire.getSignal());
	}
}
