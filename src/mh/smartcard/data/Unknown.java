package mh.smartcard.data;

import mh.smartcard.iso.Data;
import mh.smartcard.iso.TLV;

@Data.Info(name = Unknown.FORMAT)
public class Unknown extends Data {
	public static final String FORMAT = "UNKNOWN";

	public static String toString(byte[] data) {
		return String.format("(%d)%s", data.length, Hex.toString(data));
	}

	public Unknown(String name, TLV tlv) {
		super(name, tlv);
	}

	@Override
	public String toString() {
		return String.format("[%s %s %s]", tlv.tag.toCompactString(), name, toString(tlv.value));
	}
}
