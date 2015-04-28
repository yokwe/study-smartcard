package mh.smartcard.iso;

import java.util.ArrayList;
import java.util.List;

import mh.smartcard.CorruptDataException;
import mh.smartcard.util.ByteBuffer;

public class Compact extends TLV {
	// Any interindustry BER - TLV data object consisting of a tag field set to '4X', a length field set to '0Y' and
	// a value field of Y bytes can be converted into a COMPACT - TLV data object consisting of a byte set to 'XY' called
	// “compact header” and a value field of Y bytes.
	
	// "BER 4X" means [APPLICATION PRIMITIVE tag == X]
	public enum CompactTag {
		COUNTRY_CODE(0x41),
		IIN(0x42),
		CARD_SERVIE_DATA(0x43),
		INITIAL_ACCESS_DATA(0x44),
		CARD_ISSUERS_DATA(0x45),
		PRE_ISSUING_DATA(0x46),
		CARD_CAPABILITY(0x47),
		STATUS_INDICATOR(0x48),
		AID(0x4F);
		
		final int value;
		CompactTag(int value) {
			this.value = value;
		}
		public static CompactTag getInstance(int value) {
			for(CompactTag t: values()) {
				if (t.value == value) return t;
			}
			throw new CorruptDataException("Unknow enum value", String.format("value = %02X", value));
		}
	}
	
	private Compact(byte[] bytes, int tag, byte[] value) {
		super(bytes, Tags.getInstance(tag), value);
	}
	public static Compact getInstance(byte[] bytes) {
		return getInstance(new ByteBuffer(bytes));
	}
	public static Compact getInstance(ByteBuffer bb) {
		final int offset = bb.position();
		final int firstByte = bb.get();
		final int tag = 0x40 | (firstByte >> 4); // Make 4X
		final int length = firstByte & 0x0F;
		byte[] value = new byte[length];
		for(int i = 0; i < length; i++) value[i] = (byte)bb.get();
		
		// build tlvBytes
		byte[] tlvBytes = new byte[bb.position() - offset];
		bb.position(offset);
		for(int i = 0; i < tlvBytes.length; i++) tlvBytes[i] = (byte)bb.get();
		return new Compact(tlvBytes, tag, value);
	}
	public static List<Compact> getInstanceAll(ByteBuffer bb) {
		List<Compact> ret = new ArrayList<>();
		while(bb.hasRemaining()) {
			Compact child = getInstance(bb);
			ret.add(child);
		}
		return ret;
	}
}
