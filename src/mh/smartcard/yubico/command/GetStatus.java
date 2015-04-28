package mh.smartcard.yubico.command;

import mh.smartcard.iso.CLA;
import mh.smartcard.iso.Command;
import mh.smartcard.yubico.INS;

public class GetStatus extends Command.Short {
	private GetStatus(byte[] bytes) {
		super(bytes);
	}

	public static GetStatus getInstance() {
		CLA cla = CLA.NONE_LAST_0;
		int p1 = 0;
		int p2 = 0;
		int ne = 0;
		byte[] data = null;
		return new GetStatus(getBytes(cla, INS.GET_STATUS, p1, p2, data, ne));
	}
}
