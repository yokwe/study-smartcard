package mh.smartcard.yubico;

import java.util.Map;
import java.util.TreeMap;

import mh.smartcard.UnexpectedException;

public class INS extends mh.smartcard.iso.INS {
	private static final Map<Integer, INS> map = new TreeMap<>();
	private static INS getInstance(int value, String name) {
		INS old = map.get(value);
		if (old == null) {
			INS ret = new INS(value, name);
			map.put(value, ret);
			return ret;
		}
		throw new UnexpectedException(String.format("Duplicate %02X  old = %s  new = %s", value, old.name, name));
	}

	public static final INS GET_NDEF    = INS.getInstance(0x04, "YUBICO::GET_NDEF");
	public static final INS GET_OTP     = INS.getInstance(0x02, "YUBICO::GET_OTP");
	public static final INS REQUEST     = INS.getInstance(0x01, "YUBICO::REQUEST");
	public static final INS GET_STATUS  = INS.getInstance(0x03, "YUBICO::GET_STATUS");
	
	private INS(int value, String name) {
		super(value, name);
	}
}
