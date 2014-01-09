package simulate;

@SuppressWarnings("serial")
class WorkException extends Exception {
	public WorkException() {}
	
	/**
	 * work exception
	 * @param message exception message
	 */
	public WorkException(String message) {
		super(message);
	}
}
