package mh.smartcard.emv;

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

	public static final INS GET_PROCESSING_OPTIONS = INS.getInstance(0xA8, "EMV::GET_PROCESSING_OPTIONS");
	
	private INS(int value, String name) {
		super(value, name);
	}
}
