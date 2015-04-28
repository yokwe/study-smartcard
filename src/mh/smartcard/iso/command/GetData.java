package mh.smartcard.iso.command;

import mh.smartcard.iso.CLA;
import mh.smartcard.iso.Command;
import mh.smartcard.iso.File.ID;
import mh.smartcard.iso.File.SFI;
import mh.smartcard.iso.INS;
import mh.smartcard.iso.Tags;

public class GetData extends Command.Short {
	public static final byte[] TAG_LIST_ALL = {0x5C, 0x00};
	
	private GetData(byte[] bytes) {
		super(bytes);
	}
	
	// returns specified tag
	public static GetData getInstance(Tags tag) {
		final int value = tag.getLast();
		final int p1 = (value & 0xFF00) >> 8;
		final int p2 = value & 0xFF;
		return getInstance(INS.GET_DATA_CA, p1, p2, null, 256);
	}
	// returns all of specified file by fileID
	public static GetData getInstance(ID id) {
		final int p1 = (id.value & 0xFF00) >> 8;
		final int p2 = id.value & 0xFF;
		return getInstance(INS.GET_DATA_CB, p1, p2, TAG_LIST_ALL, 256);
	}
	// returns all of specified sfi
	public static GetData getInstance(SFI sfi) {
		return getInstance(INS.GET_DATA_CB, 0, sfi.value, TAG_LIST_ALL, 256);
	}
	
	protected static GetData getInstance(INS ins, int p1, int p2, byte[] data, int ne) {
		CLA cla = CLA.NONE_LAST_0;
		return new GetData(Command.Short.getBytes(cla, ins, p1, p2, data, ne));
	}
}