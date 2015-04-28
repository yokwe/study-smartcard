package mh.smartcard;

@SuppressWarnings("serial")
public class UnexpectedException extends SmartCardException {
	public UnexpectedException() {
		super();
	}
	public UnexpectedException(String message) {
		super(message);
	}
	public UnexpectedException(String message, Throwable cause) {
		super(message, cause);
	}
}
