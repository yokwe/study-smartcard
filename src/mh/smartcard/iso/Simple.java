package mh.smartcard.iso;

import java.util.ArrayList;
import java.util.List;

import mh.smartcard.UnexpectedException;
import mh.smartcard.util.ByteBuffer;

public class Simple extends TLV {
	private Simple(byte[] bytes, int tag, byte[] value) {
		super(bytes, Tags.getInstance(tag), value);
	}
	public static Simple getInstance(byte[] bytes) {
		return getInstance(new ByteBuffer(bytes));
	}
	public static Simple getInstance(ByteBuffer bb) {
		final int offset = bb.position();
		int tag = bb.get();
		int length = bb.get();
		if (length == 0xFF) {
			length = bb.get();
			length <<= 8;
			length |= bb.get();
		}
		byte[] value = new byte[length];
		for(int i = 0; i < length; i++) value[i] = (byte)bb.get();
		// Sanity check
		if (tag == 0x00 || tag == 0xFF) throw new UnexpectedException(String.format("tag = %02X", tag));
		
		// build tlvBytes
		byte[] tlvBytes = new byte[bb.position() - offset];
		bb.position(offset);
		for(int i = 0; i < tlvBytes.length; i++) tlvBytes[i] = (byte)bb.get();
		return new Simple(tlvBytes, tag, value);
	}
	public static List<Simple> getInstanceAll(ByteBuffer bb) {
		List<Simple> ret = new ArrayList<>();
		while(bb.hasRemaining()) {
			Simple child = getInstance(bb);
			ret.add(child);
		}
		return ret;
	}
	public static List<Simple> getInstanceAll(byte[] bytes) {
		return getInstanceAll(new ByteBuffer(bytes));
	}
}
