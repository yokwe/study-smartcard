package mh.smartcard.iso;

import mh.smartcard.UnexpectedException;

public class CLA {
	public enum SecureMessaging { NONE, PROP, NO_COMMAND_HEADER, COMMAND_HEADER };
	public enum CommandChaining { LAST, NOT_LAST };
	
	public static final CLA NONE_LAST_0 = getInstance(SecureMessaging.NONE, CommandChaining.LAST, 0);
	public static final CLA PROP_80     = new CLA(0x80);
	
	public final int value;
	private CLA(int value) {
		this.value = value;
	}
	
	public static CLA getInstance(SecureMessaging secureMessaging, CommandChaining commandChaining, int channelNo) {
		if (0 <= channelNo && channelNo <= 3) {
			int ret = channelNo;
			if (commandChaining == CommandChaining.NOT_LAST) ret |= 0b0001_0000;
			switch(secureMessaging) {
			case NONE:
				break;
			case PROP:
				ret |= 0b0000_0100;
				break;
			case NO_COMMAND_HEADER:
				ret |= 0b0000_1000;
				break;
			case COMMAND_HEADER:
				ret |= 0b0000_1100;
				break;
			default:
				throw new UnexpectedException(String.format("secureMessaging = %s", secureMessaging));
			}
			return new CLA(ret);
		} else if (4 <= channelNo && channelNo <= 19) {
			int ret = 0b0100_0000 | (channelNo - 4);
			if (commandChaining == CommandChaining.NOT_LAST) ret |= 0b0001_0000;
			switch(secureMessaging) {
			case NONE:
				break;
			case NO_COMMAND_HEADER:
				ret |= 0b0001_0000;
				break;
			default:
				throw new UnexpectedException(String.format("secureMessaging = %s", secureMessaging));
			}
			return new CLA(ret);
		} else {
			throw new UnexpectedException(String.format("channelNo = %d", channelNo));
		}
	}
	public static CLA getInstance(int value) {
		if ((value & 0b1000_0000) == 0) {
			if ((value & 0b1110_0000) == 0b0000_0000) {
				// Table 2 - First interindustry values of CLA
			} else if ((value & 0b110_0000) == 0b0100_0000) {
				// Table 3 - Further interindustry values of CLA
			} else {
				throw new UnexpectedException(String.format("%02X", value));
			}
		} else {
			// Proprietary
			if (value == 0xFF) throw new UnexpectedException(String.format("%02X", value));
		}
		return new CLA(value);
	}
	
	
	private static final String[] typeString          = {"NONE", "PROP", "NO_COMMAND_HEADER", "COMMAND_HEADER"};
	private static final String[] lastCommandString   = {"LAST", "NOT_LAST"};
	private static final String[] secureMessageString = {"NONE", "NO_COMMAND_HEADER"};
	public String toString() {
		if ((value & 0b1000_0000) == 0) {
			if ((value & 0b1110_0000) == 0b0000_0000) {
				int typeNumber        = (value & 0b0000_1100) >> 2;
				int lastCommandNumber = (value & 0b0001_0000) >> 4;
				int channelNumber     = value & 0b0000_0011;
				return String.format("[%s %s %d]", typeString[typeNumber], lastCommandString[lastCommandNumber], channelNumber);
			}
			if ((value & 0b1100_0000) == 0b0100_0000) {
				int secureMessageNumber = (value & 0b0010_0000) >> 5;
				int lastCommandNumber   = (value & 0b0001_0000) >> 4;
				int channelNumber       = (value & 0b0000_1111) + 4;
				return String.format("[%s %s %d]", secureMessageString[secureMessageNumber], lastCommandString[lastCommandNumber], channelNumber);
			}
			throw new UnexpectedException(String.format("%02X", value));
		} else {
			if (value == 0b1111_1111) throw new UnexpectedException(String.format("%02X", value));
			return String.format("[PROP %02X]", value);
		}
	}
}
