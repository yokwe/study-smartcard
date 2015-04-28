package mh.smartcard.iso;

import mh.smartcard.UnexpectedException;
import mh.smartcard.data.Hex;

public class StatusIndicator {
	public final byte[] bytes;
	public StatusIndicator(byte[] bytes) {
		this.bytes = bytes;
	}
	public String toString() {
		switch(bytes.length) {
		case 1:
			return new LCS(bytes[0]).toString();
		case 2:
			return new SW1SW2(bytes[0], bytes[1]).toString();
		case 3:
			return String.format("[[LCS %s] [SW1SW2 %s]]", new LCS(bytes[0]).toString(), new SW1SW2(bytes[1], bytes[2]).toString());
		default:
			throw new UnexpectedException(Hex.toString(bytes));
		}
	}
}
