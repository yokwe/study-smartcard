package mh.smartcard.iso;

import java.util.ArrayList;
import java.util.List;

import mh.smartcard.UnexpectedException;
import mh.smartcard.data.Ascii;
import mh.smartcard.data.Hex;
import mh.smartcard.util.ByteBuffer;
import mh.smartcard.util.Util;

public abstract class HistoricalBytes {
	public enum Category {
		EMPTY(-1),
		PROP(-2), // Proprietary
		RESERVE(-3),
		//
		STATUS_INDICATOR(0),
		DIR_DATA(1),
		COMPACT(0x80);
		
		public final int value;
		private Category(int value) {
			this.value = value;
		}
		public static Category getInstance(byte value) {
			return getInstance(value & 0xff);
		}
		public static Category getInstance(int value) {
			for(Category t: values()) {
				if (t.value == value) return t;
			}
			if (0x81 <= value && value <= 0x8F) return RESERVE;
			return PROP;
		}
	}
	
	public static class EmptyData extends HistoricalBytes {
		private EmptyData(Category category) {
			super(category);
		}
		@Override
		public String toString() {
			return "[]";
		}
	}
	public static class ProprietaryData extends HistoricalBytes {
		public final String value;
		private ProprietaryData(Category category, byte[] bytes) {
			super(category);
			value = Ascii.toString(bytes);
		}
		@Override
		public String toString() {
			return String.format("[%s %s]", category, value);
		}
	}

	public static class ReserveData extends HistoricalBytes {
		public final String value;
		private ReserveData(Category category, byte[] bytes) {
			super(category);
			value = String.format("(%d)%s", bytes.length, Hex.toString(bytes));
		}
		@Override
		public String toString() {
			return String.format("[%s %s]", category, value);
		}
	}

	public static class CompactData extends HistoricalBytes {
		public final List<Compact> compactList = new ArrayList<>();
		private CompactData(Category category, byte[] bytes) {
			super(category);
			
			ByteBuffer bb = new ByteBuffer(bytes);
			bb.position(1);
			for(Compact compact: Compact.getInstanceAll(bb)) compactList.add(compact);
		}
		@Override
		public String toString() {
			List<String> elements = new ArrayList<>();
			for(Compact compact: compactList) {
				Data data = Element.getData(compact);
				elements.add(data.toString());
			}
			return String.format("[%s %s]", category, Util.join(elements));
		}
	}

	public static class StatusIndicatorData extends HistoricalBytes {
		public final List<Compact> compactList = new ArrayList<>();
		public final StatusIndicator statusIndicator;
		private StatusIndicatorData(Category category, byte[] bytes) {
			super(category);
			
			if (bytes.length < (1 + 3)) throw new UnexpectedException(String.format("bytes.length = %d", bytes.length));
			byte[] statusIndicatorBytes = new byte[3];
			byte[] compactBytes = new byte[bytes.length - 1 - statusIndicatorBytes.length];
			
			{
				ByteBuffer bb = new ByteBuffer(bytes);
				bb.position(1);
				for(int i = 0; i < compactBytes.length; i++) {
					compactBytes[i] = (byte)bb.get();
				}
				for(int i = 0; i < statusIndicatorBytes.length; i++) {
					statusIndicatorBytes[i] = (byte)bb.get();
				}
			}
			
			{
				ByteBuffer bb = new ByteBuffer(compactBytes);
				for(Compact compact: Compact.getInstanceAll(bb)) compactList.add(compact);
			}
			
			statusIndicator = new StatusIndicator(statusIndicatorBytes);
			
		}
		@Override
		public String toString() {
			List<String> elements = new ArrayList<>();
			for(Compact compact: compactList) {
				Data data = Element.getData(compact);
				elements.add(data.toString());
			}
			return String.format("[%s %s %s]", category, Util.join(elements), statusIndicator);
		}
	}

	public static HistoricalBytes getInstance(byte[] bytes) {
		Category category = (bytes.length == 0) ? Category.EMPTY : Category.getInstance(bytes[0]);
		switch(category) {
		case EMPTY: return new EmptyData(category);
		case PROP: return new ProprietaryData(category, bytes);
		case RESERVE:
		case DIR_DATA:
			return new ReserveData(category, bytes);
		case COMPACT:
			return new CompactData(category, bytes);
		case STATUS_INDICATOR:
			return new StatusIndicatorData(category, bytes);
		}
		return null;
	}

	public final Category category;
	
	public HistoricalBytes(Category category) {
		this.category = category;
	}
	
	@Override public abstract String toString();
}
