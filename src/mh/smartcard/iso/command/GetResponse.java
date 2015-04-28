package mh.smartcard.iso.command;

import mh.smartcard.iso.CLA;
import mh.smartcard.iso.Command;
import mh.smartcard.iso.INS;

public class GetResponse extends Command.Short {
	private GetResponse(byte[] bytes) {
		super(bytes);
	}
	
	public static GetResponse getInstance() {
		return getInstance(INS.GET_RESPONSE, 0, 0, null);
	}
	
	protected static GetResponse getInstance(INS ins, int p1, int p2, byte[] data) {
		CLA cla = CLA.NONE_LAST_0;
		boolean isShort = true;
		int ne = isShort ? 256 : 65536;
		return new GetResponse(getBytes(cla, ins, p1, p2, data, ne));
	}
}