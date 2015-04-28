package mh.smartcard.util;

import java.util.ArrayList;
import java.util.List;

import mh.smartcard.UnexpectedException;
import mh.smartcard.data.Ascii;
import mh.smartcard.data.Hex;

public class JCOP {
	public static class Identity {
		//     00000000000000000000111111111111111111
		//     00112233445566778899001122334455667788
		// (19)03C10138000000004E58313330410103C310EA
		//     04310033000000004E5830313143000339F873
		//     03  - fabKey
		//       C1  - patchID
		//         01  - target
		//           38  - maskID
		//             00000000  - customMask
		//                     4E5831333041  - maskName (NX130A)
		//                                 01  - fuse
		//                                   03  -- length of romInfo
		//                                     C310EA- romInfo

		public final int    fabKey;
		public final int    patchID;
		public final int    target;
		public final int    maskID;
		public final String customMask;
		public final String maskName;
		public final int    fuse;
		public final String romInfo;
		
		public Identity(byte[] bytes) {
			ByteBuffer bb = new ByteBuffer(bytes);
			
			fabKey     = bb.get();
			patchID    = bb.get();
			target     = bb.get();
			maskID     = bb.get();
			customMask = Hex.toString(bb.getByteArray(4));
			maskName   = Ascii.toString(bb.getByteArray(6));
			fuse       = bb.get();
			int length = bb.get();
			romInfo    = Hex.toString(bb.getByteArray(length));
			
			// Sanity check
			if (bb.hasRemaining()) {
				throw new UnexpectedException(String.format("pos = %d  bytes = %s", bb.position(), Hex.toString(bytes)));
			}
		}
		
		@Override
		public String toString() {
			List<String> elements = new ArrayList<>();

			elements.add(String.format("[%s %02X]", "FAB-KEY",     fabKey));
			elements.add(String.format("[%s %02X]", "PATCH-ID",    patchID));
			elements.add(String.format("[%s %02X]", "TARGET",      target));
			elements.add(String.format("[%s %02X]", "MASK-ID",     maskID));
			elements.add(String.format("[%s %s]",   "CUSTOM-MASK", customMask));
			elements.add(String.format("[%s %s]",   "MASK-NAME",   maskName));
			elements.add(String.format("[%s %02X]", "FUSE",        fuse));
			elements.add(String.format("[%s %s]",   "ROM-INFO",    romInfo));

			return Util.join(elements);
		}
	}
}
