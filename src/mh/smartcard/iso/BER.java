package mh.smartcard.iso;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import mh.smartcard.UnexpectedException;
import mh.smartcard.data.Hex;
import mh.smartcard.util.ByteBuffer;
import mh.smartcard.util.Util;

public class BER extends TLV {
	public enum Type {
		UNIVERSAL(0), APPL(1), CONTEXT(2), PRIVATE(3);
		final int value;
		Type(int value) {
			this.value = value;
		}
		public static Type getInstance(int value) {
			for(Type t: values()) {
				if (t.value == value) return t;
			}
			throw new UnexpectedException(String.format("%d", value));
		}
	}
	
	private static BER getInstance(Tags parent, ByteBuffer bb) {
		final int offset = bb.position();
		final int firstByte = bb.get();
		if (firstByte == 0b0000_0000) throw new UnexpectedException(String.format("%02X", firstByte));
		final Type type = Type.getInstance((firstByte & 0b1100_0000) >> 6);
		final boolean isConstructed = (firstByte & 0b0010_0000) != 0;
		final int tag;
		if ((firstByte & 0b0001_1111) != 0b0001_1111) {
			// Single byte - 0 to 30
			tag = firstByte;
		} else {
			final int secondByte = bb.get();
			//if ((0 <= secondByte) && (secondByte <= 0x1E)) throw new UnexpectedException(String.format("%02X", secondByte));
			if (secondByte == 0b1000_0000) throw new UnexpectedException(String.format("%02X", secondByte));
			if ((secondByte & 0b1000_0000) == 0) {
				// Two byte - 31 to 127
				tag = (firstByte << 8) | secondByte;
			} else {
				// Three byte
				final int thirdByte = bb.get();
				if ((thirdByte & 0b1000_0000) != 0) throw new UnexpectedException(String.format("%02X", thirdByte));
				tag = (firstByte << 16) | (secondByte << 8) | thirdByte;
			}
		}
		final int length;
		final int length1 = bb.get();
		if (0x00 <= length1 && length1 <= 0x7F) {
			// 1 byte
			length = length1;
		} else if (length1 == 0x81) {
			// 2 byte
			final int length2 = bb.get();
			length = length2;
		} else if (length1 == 0x82) {
			// 3 byte
			final int length2 = bb.get();
			final int length3 = bb.get();
			length = (length2 << 8) | length3;
		} else {
			throw new UnexpectedException(String.format("%02X", length1));
		}
		// Sanity check
		final byte[] value = new byte[length];
		for(int i = 0; i < length; i++) value[i] = (byte)bb.get();
		
		// Sanity check for Universal Type
		if (type == Type.UNIVERSAL) {
			if (0x1F <= tag) throw new UnexpectedException(String.format("tag = %02X", tag));
		}
		
		final Tags parentTag = Tags.getInstance(parent, tag);
		final List<BER> children = new ArrayList<>();
		// if this is constructed BER, encode value as list of BER
		if (isConstructed) {
			ByteBuffer cbb = new ByteBuffer(value);
			while(cbb.hasRemaining()) {
				BER child = getInstance(parentTag, cbb);
				children.add(child);
			}
		}
		
		// build bytes
		final byte[] bytes = new byte[bb.position() - offset];
		bb.position(offset);
		for(int i = 0; i < bytes.length; i++) bytes[i] = (byte)bb.get();
		return new BER(bytes, parentTag, value, type, isConstructed, children);
	}
	public static BER getInstance(byte[] bytes) {
		final ByteBuffer bb = new ByteBuffer(bytes);
		final BER ret = getInstance(null, bb);
		// Sanity check
		if (bb.hasRemaining()) {
			String message = String.format("pos = %d  bytes = %s", bb.position(), Hex.toString(bytes));
			throw new UnexpectedException(message);
		}
		return ret;
	}
	public static BER getInstance(Tags parent, byte[] bytes) {
		final ByteBuffer bb = new ByteBuffer(bytes);
		final BER ret = getInstance(parent, bb);
		// Sanity check
		if (bb.hasRemaining()) {
			String message = String.format("pos = %d  bytes = %s", bb.position(), Hex.toString(bytes));
			throw new UnexpectedException(message);
		}
		return ret;
	}
	public static List<BER> getInstanceAll(Tags parent, byte[] bytes) {
		ByteBuffer bb = new ByteBuffer(bytes);
		List<BER> ret = new ArrayList<>();
		while(bb.hasRemaining()) {
			BER child = getInstance(parent, bb);
			ret.add(child);
		}
		return ret;
	}

	
	public final Type      type;
	public final boolean   isConstructed;
	public final List<BER> children;
	
	private BER(byte[] bytes, Tags tag, byte[] value, Type type, boolean isConstructed, List<BER> children) {
		super(bytes, tag, value);
		this.type          = type;
		this.isConstructed = isConstructed;
		this.children      = Collections.unmodifiableList(children);
	}
	
	@Override
	public String toString() {
		StringBuilder ret = new StringBuilder();
		if (isConstructed) {
			List<String> elements = new ArrayList<>();
			for(BER child: children) elements.add(child.toString());
			int size = elements.size();
			
			if (size == 1) {
				ret.append(elements.get(0));
			} else {
				ret.append(String.format("(%d)%s", elements.size(), Util.join(elements)));
			}
		} else {
			ret.append(Hex.toString(value));
		}
		return String.format("[%s %s %s]", Tags.toTagString(tag.getLast()), type, ret);
	}
}
