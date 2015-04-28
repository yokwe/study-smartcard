package mh.smartcard.iso;

import mh.smartcard.UnexpectedException;

public class File {
	// EF.ARR  access rule reference file
	// EF.ATR  Answer-to-Reset file
	// EF.ATR — This EF indicates operating characteristics of the card. It contains a set of interindustry data 
	// objects which cannot be nested in EF.DIR, either because not relevant to application selection, or because there is no EF.DIR.
	
	// EF.ATR shall have the MF as parent file: the path '3F002F01' references EF.ATR


	// EF.DIR  directory file
	// EF.DIR — This EF indicates a list of applications supported by the card. It contains a set of application 
	// templates and / or application identifier data objects, in any order. It determines which commands shall be 
	// performed in order to select the indicated applications.

	// EF.DIR shall have the MF as parent file: the path '3F002F00' references EF.DIR.
	// At MF level, the short EF identifier 30, i.e., 11110 in binary, references EF.DIR if present.
	
	public static class ID {
		public final int value;
		private ID(int value) {
			this.value = value;
		}
		public static ID getInstance(int value) {
			return new ID(value);
		}
		
		public static final ID CURRENT_DF = getInstance(0x3FFF);
		public static final ID CURRENT_EF = getInstance(0x0000);
		public static final ID MF         = getInstance(0x3F00);
		public static final ID EF_ATR     = getInstance(0x2F01);
		public static final ID EF_DIR     = getInstance(0x2F00);
		
		// JICSAP
		public static final ID JICSAP_CARD         = getInstance(0x001E);
		public static final ID JICSAP_APPL         = getInstance(0x2F10);
		public static final ID JICSAP_MANUFACTURER = getInstance(0x2F11);
	}
	
	public static class SFI {
		private static final SFI[] all = new SFI[0x1F];
		static {
			for(int i = 0; i < all.length; i++) all[i] = new SFI(i);
		}
		public static SFI getInstance(int value) {
			if (0 <= value && value <= all.length) {
				return all[value];
			} else {
				String message = String.format("value = %02X", value);
				throw new UnexpectedException(message);
			}
		}

		public static final SFI EF_CURRENT = getInstance(0x00);
		public static final SFI EF_DIR     = getInstance(0x1E);
		
		public final int value;
		private SFI(int value) {
			this.value = value;
		}
		public String toString() {
			return String.format("%02X", value);
		}
	}
}
