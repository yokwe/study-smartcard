package mh.smartcard.yubico.command;

import mh.smartcard.iso.CLA;
import mh.smartcard.iso.Command;
import mh.smartcard.yubico.INS;

public class GetNDEF extends Command.Short {
	private GetNDEF(byte[] bytes) {
		super(bytes);
	}

	public static GetNDEF getInstance() {
		CLA cla = CLA.NONE_LAST_0;
		int p1 = 0;
		int p2 = 0;
		int ne = 0;
		byte[] data = null;
		return new GetNDEF(getBytes(cla, INS.GET_NDEF, p1, p2, data, ne));
	}
}
