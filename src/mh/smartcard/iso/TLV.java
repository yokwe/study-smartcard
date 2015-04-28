package mh.smartcard.iso;

import mh.smartcard.UnexpectedException;
import mh.smartcard.data.Hex;

public class TLV {
	public final byte[] bytes;
	public final Tags   tag;
	public final byte[] value;
	
	protected TLV(byte[] bytes, Tags tag, byte[] value) {
		this.bytes = bytes;
		this.tag   = tag;
		this.value = value;
	}
	public String toString() {
		return String.format("[%s %s]", tag, Hex.toString(value));
	}
	public static byte[] toByteArray(Tags tag, byte[] data) {
		if (0xFF < data.length) {
			String message = String.format("data.length = %02X", data.length);
			throw new UnexpectedException(message);
		}
		if (tag.valueList.size() != 1) {
			String message = String.format("tag.values.size() = %d", tag.valueList.size());
			throw new UnexpectedException(message);
		}
		final int value = tag.getFirst();
		byte[] ret;
		int pos = 0;
		if (0xFFFF < value) {
			String message = String.format("v = %04X", data.length);
			throw new UnexpectedException(message);
		}
		if (value <= 0xFF) {
			ret = new byte[1 + 1 + data.length];
			ret[pos++] = (byte)value;
		} else {
			ret = new byte[2 + 1 + data.length];
			ret[pos++] = (byte)(value >> 8);
			ret[pos++] = (byte)value;
		}
		ret[pos++] = (byte)(data.length);
		for(int n: data) ret[pos++] = (byte)n;
		return ret;
	}
}
