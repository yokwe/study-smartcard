package mh.smartcard.iso;

import java.util.ArrayList;
import java.util.List;

import mh.smartcard.UnexpectedException;
import mh.smartcard.data.Hex;
import mh.smartcard.util.ByteBuffer;
import mh.smartcard.util.Util;

public class Command {
	public enum Type {
		TYPE_1("1"), TYPE_2S("2S"), TYPE_2E("2E"), TYPE_3S("3S"), TYPE_3E("3E"), TYPE_4S("4S"), TYPE_4E("4E");
		public final String name;
		Type(String name) {
			this.name = name;
		}
		public String toString() {
			return name;
		}
	}
	
	public final byte[]  bytes;
	public final boolean isShort;
	public final Type    type;
	
	public final CLA     cla;
	public final int     ins;
	public final int     p1;
	public final int     p2;
	public final byte[]  data;
	public final int     ne;
	
	public static class Short extends Command {
		public static final boolean isShort = true;
		protected Short(byte[] bytes) {
			super(bytes, isShort);
		}
		public static Short getInstance(byte[] bytes) {
			return new Short(bytes);
		}
		public static byte[] getBytes(CLA cla, INS ins, int p1, int p2, byte[] data, int ne) {
			return getBytes(cla, ins, p1, p2, data, ne, isShort);
		}
	}
	public static class Extended extends Command {
		public static final boolean isShort = false;
		protected Extended(byte[] bytes) {
			super(bytes, isShort);
		}
		public static Extended getInstance(byte[] bytes) {
			return new Extended(bytes);
		}
		public static byte[] getBytes(CLA cla, INS ins, int p1, int p2, byte[] data, int ne) {
			return getBytes(cla, ins, p1, p2, data, ne, isShort);
		}
	}
	
	private Command(byte[] bytes, boolean isShort) {
		this.bytes = bytes;
		this.isShort = isShort;
		
		ByteBuffer bb = new ByteBuffer(bytes);
		// sanity check
		if (bytes.length < 4) throw new UnexpectedException(String.format("%d", bytes.length));
		
		cla = CLA.getInstance(bb.get());
		ins = bb.get();
		p1  = bb.get();
		p2  = bb.get();
		
		if (bytes.length == 4) {
			// Case 1
			type = Type.TYPE_1;
			data = new byte[0];
			ne = 0;
			return;
		}
		final int c5 = bb.get();
		if (isShort) {
			// Case 2S
			if (bytes.length == 5) {
				type = Type.TYPE_2S;
				data = new byte[0];
				ne = (c5 == 0) ? 256 : c5;
				return;
			}
			// Case 3S
			if (bytes.length == (5 + c5)) {
				if (c5 == 0) throw new UnexpectedException(String.format("%d", c5));
				type = Type.TYPE_3S;
				data = new byte[c5];
				for(int i = 0; i < c5; i++) data[i] = (byte)bb.get();
				ne = 0;
				return;
			}
			// Case 4S
			if (bytes.length == (6 + c5)) {
				if (c5 == 0) throw new UnexpectedException(String.format("%d", c5));
				type = Type.TYPE_4S;
				data = new byte[c5];
				for(int i = 0; i < c5; i++) data[i] = (byte)bb.get();
				final int le = bb.get();
				ne = (le == 0) ? 256 : le;
				return;
			}
			throw new UnexpectedException(Hex.toString(bytes));
		} else {
			if (bytes.length < 7) throw new UnexpectedException(String.format("%d", bytes.length));
			final int c6 = bb.get();
			final int c7 = bb.get();
			final int c6c7 = (c6 << 8) | c7;
			// Case 2E
			if (bytes.length == 7) {
				if (c5 != 0) throw new UnexpectedException(String.format("%d", c5));
				type = Type.TYPE_2E;
				data = new byte[0];
				ne = (c6c7 == 0) ? 65536 : c6c7;
				return;
			}
			//
			// Case 3E
			if (bytes.length == (7 + c6c7)) {
				if (c5 != 0) throw new UnexpectedException(String.format("%d", c5));
				if (c6c7 == 0) throw new UnexpectedException(String.format("%d", c6c7));
				type = Type.TYPE_3E;
				data = new byte[c6c7];
				for(int i = 0; i < c6c7; i++) data[i] = (byte)bb.get();
				ne = 0;
				return;
			}
			// case 4E
			if (bytes.length == (9 + c6c7)) {
				if (c5 != 0) throw new UnexpectedException(String.format("%d", c5));
				if (c6c7 == 0) throw new UnexpectedException(String.format("%d", c6c7));
				type = Type.TYPE_4E;
				data = new byte[c6c7];
				for(int i = 0; i < c6c7; i++) data[i] = (byte)bb.get();
				final int leh = bb.get();
				final int lel = bb.get();
				final int le = (leh << 8) | lel;
				ne = (le == 0) ? 65536 : le;
				return;
			}
			throw new UnexpectedException(Hex.toString(bytes));
		}
	}
	
	private static byte[] getBytes(CLA cla, INS ins, int p1, int p2, byte[] data, int ne, boolean isShort) {
		if (data == null) data = new byte[0];
		byte[] bytes;
		
		if (data.length == 0 && ne == 0) {
			// Case 1
			bytes = new byte[4];
			bytes[0] = (byte)cla.value;
			bytes[1] = (byte)ins.value;
			bytes[2] = (byte)p1;
			bytes[3] = (byte)p2;
		} else {
			if (isShort) {
				if (data.length == 0 && ne != 0) {
					if (ne < 0 || 256 < ne) throw new UnexpectedException(String.format("ne = %d", ne));
					// Case 2S
					bytes = new byte[5];
					bytes[0] = (byte)cla.value;
					bytes[1] = (byte)ins.value;
					bytes[2] = (byte)p1;
					bytes[3] = (byte)p2;
					//
					int n = (ne == 256) ? 0 : ne;
					bytes[4] = (byte)n;
				} else if (data.length != 0 && ne == 0) {
					// Case 3S
					bytes = new byte[5 + data.length];
					bytes[0] = (byte)cla.value;
					bytes[1] = (byte)ins.value;
					bytes[2] = (byte)p1;
					bytes[3] = (byte)p2;
					bytes[4] = (byte)(data.length);
					for(int i = 0; i < data.length; i++) bytes[5 + i] = data[i];
				} else if (data.length != 0 && ne != 0) {
					if (ne < 0 || 256 < ne) throw new UnexpectedException(String.format("ne = %d", ne));
					// Case 4S
					bytes = new byte[6 + data.length];
					bytes[0] = (byte)cla.value;
					bytes[1] = (byte)ins.value;
					bytes[2] = (byte)p1;
					bytes[3] = (byte)p2;
					bytes[4] = (byte)(data.length);
					for(int i = 0; i < data.length; i++) bytes[5 + i] = data[i];
					//
					int n = (ne == 256) ? 0 : ne;
					bytes[5 + data.length] = (byte)n;
				} else {
					throw new UnexpectedException("Should not happen");
				}
			} else {
				if (data.length == 0 && ne != 0) {
					if (ne < 0 || 65536 < ne) throw new UnexpectedException(String.format("ne = %d", ne));
					// Case 2E
					bytes = new byte[7];
					bytes[0] = (byte)cla.value;
					bytes[1] = (byte)ins.value;
					bytes[2] = (byte)p1;
					bytes[3] = (byte)p2;
					bytes[4] = 0;
					//
					int n = (ne == 65536) ? 0 : ne;
					bytes[5] = (byte)(n >> 8);
					bytes[6] = (byte)n;
				} else if (data.length != 0 && ne == 0) {
					// Case 3E
					bytes = new byte[7 + data.length];
					bytes[0] = (byte)cla.value;
					bytes[1] = (byte)ins.value;
					bytes[2] = (byte)p1;
					bytes[3] = (byte)p2;
					bytes[4] = 0;
					bytes[5] = (byte)(data.length >> 8);
					bytes[6] = (byte)data.length;
					for(int i = 0; i < data.length; i++) bytes[7 + i] = data[i];
				} else if (data.length != 0 && ne != 0) {
					// Case 4E
					bytes = new byte[9 + data.length];
					bytes[0] = (byte)cla.value;
					bytes[1] = (byte)ins.value;
					bytes[2] = (byte)p1;
					bytes[3] = (byte)p2;
					bytes[4] = 0;
					bytes[5] = (byte)(data.length >> 8);
					bytes[6] = (byte)data.length;
					for(int i = 0; i < data.length; i++) bytes[7 + i] = data[i];
					//
					int n = (ne == 65536) ? 0 : ne;
					bytes[7 + data.length] = (byte)(n >> 8);
					bytes[8 + data.length] = (byte)n;
				} else {
					throw new UnexpectedException("Should not happen");
				}
			}
		}
		return bytes;
	}
	
	@Override
	public String toString() {
		List<String>elements = new ArrayList<>();
		elements.add(String.format("type: %s", type));
		elements.add(String.format("cla: %s", cla));
		elements.add(String.format("ins: %02X", ins));
		elements.add(String.format("p1p2: %02X%02X", p1, p2));
		elements.add(String.format("data: %s", Hex.toString(data)));
		elements.add(String.format("ne: %d", ne));
		return Util.join(elements);
	}
}
