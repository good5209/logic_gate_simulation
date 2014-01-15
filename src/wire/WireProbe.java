package wire;

/**
 * monitor wire signal change
 */
public class WireProbe implements WireListener {
	private Wire wire;
	
	public WireProbe(Wire wire) throws WireException {
		if (wire != null) {
			this.wire = wire;
			try {
				this.wire.listenSignal(this);
			} catch (WireException e) {
				e.printStackTrace();
			}
			return;
		}
		throw new WireException("wire is null");
	}
	
	@Override
	public void wireSignalChanged() {
		System.out.println("Wire \"" + wire.getName() + "\" change " + wire.getSignal());
	}
}
