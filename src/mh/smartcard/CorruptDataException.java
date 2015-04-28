package mh.smartcard;

import mh.smartcard.data.Unknown;

@SuppressWarnings("serial")
public class CorruptDataException extends SmartCardException {
	public CorruptDataException() {
		super();
	}
	public CorruptDataException(String message) {
		super(message);
	}
	public CorruptDataException(String explanation, byte[] data) {
		super(String.format("%s: %s", explanation, Unknown.toString(data)));
	}
	public CorruptDataException(String explanation, String data) {
		super(String.format("%s: %s", explanation, data));
	}
}
