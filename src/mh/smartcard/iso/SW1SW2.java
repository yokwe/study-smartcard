package mh.smartcard.iso;

import java.util.Map;

import mh.smartcard.util.Util;

public class SW1SW2 {
	private static final Map<String, String> map = Util.loadProperties(SW1SW2.class);
	
	public final int sw1;
	public final int sw2;
	public final int value;
	
	public SW1SW2(byte sw1, byte sw2) {
		this(sw1 & 0xFF, sw2 & 0xFF);
	}
	public SW1SW2(int sw1, int sw2) {
		this.sw1 = sw1;
		this.sw2 = sw2;
		this.value = this.sw1 << 8 | this.sw2;
	}
	@Override
	public boolean equals(Object that) {
		if (that instanceof SW1SW2) {
			return this.value == ((SW1SW2)that).value;
		} else {
			return false;
		}
	}
	@Override
	public int hashCode() {
		return value;
	}
	@Override
	public String toString() {
		{
			String key = String.format("%04X", value);
			if (map.containsKey(key)) {
				return String.format("[%s %s]", key, map.get(key));
			}
		}
				
		// 61XX Normal processing - SW2 encodes the number of data bytes still available
		if (sw1 == 0x61) return String.format("[%04X NORMAL-DATA-AVAILABLE-%d]", value, sw2);
		// 62XX Warning processing - State of non-volatile memory is unchanged
		if (sw1 == 0x62 && (0x02 <= sw2 && sw2 <= 0x80)) return String.format("[%04X WARN CARD-QUERY-%02X]", value, sw2);
		// 64XX Execution error - State of non-volatile memory is unchanged
		if (sw1 == 0x64 && (0x02 <= sw2 && sw2 <= 0x80)) return String.format("[%04X ERROR CARD-ABORT-%02X]", value, sw2);
		// 6CXX Checking error - Wrong Le field: SW2 encode the exact number of available data bytes
		if (sw1 == 0x6C) return String.format("[%04X ERROR WRONG-Le-FIELD-%d]", value, sw2);
		
		// PROPRIETARY
		switch(sw1) {
		case 0x67:
		case 0x6B:
		case 0x6D:
		case 0x6E:
		case 0x6F:
			return String.format("[%04X PROPRIETARY]", value);
		default:
			if ((sw1 & 0xF0) == 0x90) return String.format("[%04X PROPRIETARY]", value);
		}
		
		//throw new UnexpectedException(String.format("%04X", sw1sw2));
		return String.format("[%04X RESERVE]", value);
	}
}
