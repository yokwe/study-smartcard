package mh.smartcard.iso.data;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import mh.smartcard.iso.Data;
import mh.smartcard.iso.TLV;
import mh.smartcard.iso.Tags;
import mh.smartcard.util.ByteBuffer;
import mh.smartcard.util.Util;

@Data.Info(name = OID.FORMAT)
public final class OID extends Data {
	public static final String FORMAT = "ISO::OID";
	private static final Map<String, String> oidMap = Util.loadProperties(OID.class);
	
	private static String getString(List<Integer>intList) {
		if (intList.size() == 0) return "";
		//
		StringBuilder ret = new StringBuilder();
		for(int v: intList) ret.append(String.format(".%d", v));
		return ret.substring(1);
	}
	private static List<Integer> decode(byte[] bytes) {
		List<Integer> ret = new ArrayList<>();
		ByteBuffer    bb  = new ByteBuffer(bytes);
		// Special for first byte
		{
			int firstByte = bb.get();
			ret.add(firstByte / 40);
			ret.add(firstByte % 40);
		}
		while(bb.hasRemaining()) {
			int n = 0;
			for(;;) {
				int t = bb.get();
				n = (n << 7) | (t & 0b0111_1111);
				if ((t & 0b1000_0000) == 0) break;
			}
			ret.add(n);
		}
		return ret;
	}

	private final List<Integer> intList;
	private final String        string;
	public OID(String name, TLV tlv) {
		super(name, tlv);
		this.intList = Collections.unmodifiableList(decode(tlv.value));
		this.string  = getString(this.intList);
	}
		
	private OID(String name, TLV tlv, List<Integer> intList) {
		super(name, tlv);
		this.intList = Collections.unmodifiableList(intList);
		this.string  = getString(this.intList);
	}

	public OID getParent() {
		if (this.intList.size() == 1) return null;
		
		List<Integer> intList = new ArrayList<>(this.intList);
		intList.remove(intList.size() - 1);
		return new OID(name, tlv, intList);
	}

	public String getValue() {
		OID target = this;
		for(;;) {
			String name = oidMap.get(target.string);
			if (name != null) {
				final int fromIndex = target.intList.size();
				final int toIndex = this.intList.size();
				if (fromIndex == toIndex) {
					return name;
				} else {
					return name + "." + getString(this.intList.subList(fromIndex, toIndex));
				}
			}
			target = target.getParent();
			if (target == null) break;
		}
		return this.string;
	}

	@Override
	public String toString() {
		return String.format("[%s %s %s]", Tags.toTagString(tlv.tag.getLast()), name, getValue());
	}
}
