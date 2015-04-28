package mh.smartcard.iso;

import mh.smartcard.UnexpectedException;

public class LCS {
	public final int value;
	public LCS(byte value) {
		this(value & 0xFF);
	}
	public LCS(int value) {
		this.value = value;
	}
	public String toString() {
		if (0x10 <= value) return String.format("PROP-%02X", value);
		if (value == 0b0000_0000) return "NO-INFO";
		if (value == 0b0000_0001) return "CREATION";
		if (value == 0b0000_0011) return "INITIALIZATION";
		if ((value & 0b1111_1101) == 0b0000_0101) return "OPERATIONAL-ACTIVATED";
		if ((value & 0b1111_1101) == 0b0000_0100) return "OPERATIONAL-DEACTIVATED";
		if ((value & 0b1111_1100) == 0b0000_1100) return "TERMINATED";
		throw new UnexpectedException(String.format("%02X", value));
	}
}
