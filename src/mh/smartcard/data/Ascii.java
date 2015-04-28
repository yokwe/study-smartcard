package mh.smartcard.data;

import mh.smartcard.iso.Data;
import mh.smartcard.iso.TLV;
import mh.smartcard.iso.Tags;
import mh.smartcard.util.ByteBuffer;

@Data.Info(name = Ascii.FORMAT)
public class Ascii extends Data {
	public static final String FORMAT = "ASCII";
	
	public static String toString(byte[] data) {
		ByteBuffer bb = new ByteBuffer(data);
		StringBuilder ret = new StringBuilder();
		ret.append('"');
		while(bb.hasRemaining()) {
			final int c = bb.get();
			String oneChar = String.format(((0x20 <= c && c <= 0x7f) ? "%c" : "\\x%02X"), c);
			ret.append(oneChar);
		}
		ret.append('"');
		return ret.toString();
	}

	public Ascii(String name, TLV tlv) {
		super(name, tlv);
	}

	@Override
	public String toString() {
		return String.format("[%s %s %s]", Tags.toTagString(tlv.tag.getLast()), name, toString(tlv.value));
	}
}
