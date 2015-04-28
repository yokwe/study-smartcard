package mh.smartcard.iso;

import mh.smartcard.UnexpectedException;
import mh.smartcard.data.Hex;

public class Response {
	public final byte[] data;
	public final SW1SW2 status;

	public Response(byte[] bytes) {
		if (bytes.length < 2) throw new UnexpectedException(String.format("%d", bytes.length));
		
		data = new byte[bytes.length - 2];
		for(int i = 0; i < data.length; i++) data[i] = bytes[i];
		int sw1 = bytes[bytes.length - 2] & 0xFF;
		int sw2 = bytes[bytes.length - 1] & 0xFF;
		status = new SW1SW2(sw1, sw2);
	}
	@Override
	public String toString() {
		return String.format("[status: %s data: (%d)%s]", status.toString(), data.length, Hex.toString(data));
	}
	@Override
	public boolean equals(Object o) {
		if (o instanceof Response) {
			Response that = (Response)o;
			if (this.status.equals(that.status)) {
				if (this.data.length == that.data.length) {
					for(int i = 0; i < this.data.length; i++) {
						if (this.data[i] != that.data[i]) return false;
					}
					return true;
				}
			}
		}
		return false;
	}
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
}
