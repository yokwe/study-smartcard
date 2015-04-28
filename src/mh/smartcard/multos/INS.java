package mh.smartcard.multos;

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

	public static final INS GET_MULTOS_DATA       = INS.getInstance(0x00, "MULTOS::GET_MULTOS_DATA");
	public static final INS GET_MANUFACTURER_DATA = INS.getInstance(0x02, "MULTOS::GET_MANUFACTURER_DATA");
	public static final INS GET_CONFIG_DATA       = INS.getInstance(0x10, "MULTOS::GET_CONFIG_DATA");
	
	private INS(int value, String name) {
		super(value, name);
	}
}
