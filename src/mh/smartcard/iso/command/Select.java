package mh.smartcard.iso.command;

import mh.smartcard.iso.CLA;
import mh.smartcard.iso.Command;
import mh.smartcard.iso.File;
import mh.smartcard.iso.INS;

//MULTOS support ONLY format described below.
//   00 A4 00 00 null -- Master File
//   00 A4 00 00 (02)3F00 -- Master File      9000
//   00 A4 00 00 (02)2F00 -- Directory File   9000
//   00 A4 00 00 (02)2F01 -- ATR File         9000
//   00 A4 00 0C (02)2F00 -- Directory File   9000
//   00 A4 00 0C (02)2F01 -- ATR File         9000
//   00 A4 04 00 AID      -- Application (DF) 9000 + FCI
//   00 A4 04 02 AID      -- Application (DF) 9000 + FCI
//   00 A4 04 0C AID      -- Application (DF) 9000
//   00 A4 08 00 (02)3F00 -- Master File      9000
//   00 A4 08 00 (02)2F00 -- Directory File   9000
//   0C A4 08 00 (02)3F00 -- Master File      9000
//   0C A4 08 00 (02)2F00 -- Directory File   9000

public class Select extends Command.Short {
	public static enum Selection {
		FILE_ID      (0b0000_0000),
		CHILD_DF     (0b0000_0001),
		CHILD_EF     (0b0000_0010),
		PARENT_DF    (0b0000_0011),
		DF_NAME      (0b0000_0100),
		PATH_ABSOLUTE(0b0000_1000),
		PATH_RELATIVE(0b0000_1001);
		public final int value;
		Selection(int value) {
			this.value = value;
		}
	}
	public static enum Occurence {
		FIRST   (0b0000_0000),
		LAST    (0b0000_0001),
		NEXT    (0b0000_0010),
		PREVIOUS(0b0000_0011);
		public final int value;
		Occurence(int value) {
			this.value = value;
		}
	}
	public static enum Returns {
		FCI (0b0000_0000),
		FCP (0b0000_0100),
		FMD (0b0000_1000),
		NONE(0b0000_1100);
		public final int value;
		Returns(int value) {
			this.value = value;
		}
	}
	
	private Select(byte[] bytes) {
		super(bytes);
	}
	public static Select getInstance(Selection selection, Occurence occurence, Returns returns, byte[] data) {
		CLA cla = CLA.NONE_LAST_0;
		INS ins = INS.SELECT;
		int ne = (returns == Returns.NONE) ? 0 : 256;
		return new Select(Command.Short.getBytes(cla, ins, selection.value, (occurence.value | returns.value), data, ne));
	}
	public static Select getInstance(File.ID id, Returns returns) {
		byte[] data = {(byte)(id.value >> 8), (byte)id.value};
		return getInstance(Selection.FILE_ID, Occurence.FIRST, returns, data);
	}
	//
	public static Select getInstance(byte[] name, Occurence occurence, Returns returns) {
		return getInstance(Selection.DF_NAME, occurence, returns, name);
	}
	public static Select getInstance(byte[] name, Returns returns) {
		return getInstance(name, Occurence.FIRST, returns);
	}
}
