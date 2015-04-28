package mh.smartcard;

@SuppressWarnings("serial")
public class SmartCardException extends RuntimeException {
	public SmartCardException() {
		super();
	}
	public SmartCardException(String message) {
		super(message);
	}
	public SmartCardException(String message, Throwable cause) {
		super(message, cause);
	}
}
