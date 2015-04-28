package mh.smartcard.data;

import mh.smartcard.UnexpectedException;
import mh.smartcard.iso.Data;
import mh.smartcard.iso.TLV;
import mh.smartcard.iso.Tags;

@Data.Info(name = Hex.FORMAT)
public class Hex extends Data {
	public static final String FORMAT = "HEX";
	
	public static String toString(byte[] data) {
		StringBuilder ret = new StringBuilder();
		for(byte b: data) ret.append(String.format("%02X", b));
		return ret.toString();
	}
	
	public Hex(String name, TLV tlv) {
		super(name, tlv);
	}

	@Override
	public String toString() {
		// Sanity check
		if (tlv == null) throw new UnexpectedException("tlv == null");
		return String.format("[%s %s %s]", Tags.toTagString(tlv.tag.getLast()), name, toString(tlv.value));
	}
}
