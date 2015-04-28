package mh.smartcard.util;

import mh.smartcard.UnexpectedException;
import mh.smartcard.data.Hex;

public final class ByteBuffer {
	//private static final Logger logger = LoggerFactory.getLogger(ByteBuffer.class);

	private final byte[] value;
	private int          pos;
	public ByteBuffer(byte[] value) {
		this.value  = value;
		this.pos    = 0;
	}
	public ByteBuffer rewind() {
		pos = 0;
		return this;
	}
	public ByteBuffer position(int pos) {
		if (pos < 0 || value.length < pos) {
			String message = String.format("new position is out of range.  pos = %d", pos);
			//logger.error(message);
			throw new UnexpectedException(message);
		}

		this.pos = pos;
		return this;
	}
	public int position() {
		return pos;
	}
	public int remaining() {
		return value.length - pos;
	}
	public boolean hasRemaining() {
		return remaining() != 0;
	}
	public int get() {
		if (hasRemaining()) {
			return value[pos++] & 0xFF;
		} else {
			String message = String.format("position exceeds end of element %s", Hex.toString(value));
			//logger.error(message);
			throw new UnexpectedException(message);
		}
	}
	public byte[] getByteArray(int length) {
		byte[] ret = new byte[length];
		for(int i = 0; i < ret.length; i++) {
			ret[i] = (byte)get();
		}
		return ret;
	}
	public int getShort() {
		final int b0 = get();
		final int b1 = get();
		return (b0 << 8) | b1;
	}
	public int getInt() {
		final int b0 = get();
		final int b1 = get();
		final int b2 = get();
		final int b3 = get();
		return (b0 << 24) | (b1 << 16) | (b2 << 8) | b3;
	}
}