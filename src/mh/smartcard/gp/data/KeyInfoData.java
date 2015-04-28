package mh.smartcard.gp.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import mh.smartcard.UnexpectedException;
import mh.smartcard.iso.Data;
import mh.smartcard.iso.TLV;
import mh.smartcard.iso.Tags;
import mh.smartcard.util.ByteBuffer;
import mh.smartcard.util.Util;

@Data.Info(name = KeyInfoData.FORMAT)
public class KeyInfoData extends Data {
	public static final String FORMAT = "GP::KEY-INFO-DATA";
	
	private static Map<Integer, String>idNameMap = new TreeMap<>();
	static {
		idNameMap.put(1, "ENC");
		idNameMap.put(2, "MAC");
		idNameMap.put(3, "KEK");
		idNameMap.put(4, "RMAC");
	}
	private static String getIDName(final int value) {
		String ret = idNameMap.get(value);
		if (ret != null) return ret;
		return String.format("%02d", value);
	}
	
	private static Map<Integer, String>idTypeMap = new TreeMap<>();
	static {
		idTypeMap.put(0x80, "DES");
		idTypeMap.put(0x82, "3DES");
		idTypeMap.put(0x83, "DES-ECB");
		idTypeMap.put(0x84, "DES-CBC");
		idTypeMap.put(0x88, "AES");
		idTypeMap.put(0x90, "HMAC-SHA1");
		idTypeMap.put(0x91, "HMAC-SHA1-160");
		idTypeMap.put(0xA0, "RSA-PUBLIC-E");
		idTypeMap.put(0xA1, "RSA-PUBLIC-N");
		idTypeMap.put(0xA2, "RSA-PRIVATE-N");
		idTypeMap.put(0xA3, "RSA-PRIVATE-D");
		idTypeMap.put(0xA4, "RSA-PRIVATE-P");
		idTypeMap.put(0xA5, "RSA-PRIVATE-Q");
		idTypeMap.put(0xA6, "RSA-PRIVATE-PQ");
		idTypeMap.put(0xA6, "RSA-PRIVATE-DP1");
		idTypeMap.put(0xA7, "RSA-PRIVATE-DQ1");
	}
	private static String getTypeName(final int value) {
		String ret = idTypeMap.get(value);
		if (ret != null) return ret;
		if (value < 0x80) return String.format("PRIVATE-%02X", value);
		if (value == 0xFF) {
			throw new UnexpectedException("EXTENDED");
			//return "EXTENDED";
		}
		return String.format("RESERVE-%02X", value);
	}
	
	public static class TypeLength {
		final int type;
		final int length;
		public TypeLength(int type, int length) {
			this.type   = type;
			this.length = length;
		}
		@Override
		public String toString() {
			return String.format("%s %d", getTypeName(type), length);
		}
	}
	final int id;
	final int version;
	final List<TypeLength> typeLengthList = new ArrayList<>();
	
	public KeyInfoData(String name, TLV tlv) {
		super(name, tlv);
		
		ByteBuffer bb = new ByteBuffer(tlv.value);
		id = bb.get();
		version = bb.get();
		
		while(bb.hasRemaining()) {
			int type = bb.get();
			int length = bb.get();
			typeLengthList.add(new TypeLength(type, length));
		}
	}

	@Override
	public String toString() {
		List<String> elements = new ArrayList<>();
		elements.add(String.format("id %s", getIDName(id)));
		elements.add(String.format("version %d", version));
		
		List<String> keyLengthStringList = new ArrayList<>();
		for(TypeLength typeLength: typeLengthList) keyLengthStringList.add(typeLength.toString());
		elements.add(Util.join(keyLengthStringList));
		return String.format("[%s %s %s]", Tags.toTagString(tlv.tag.getLast()), name, Util.join(elements));
	}
}
