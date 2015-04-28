package mh.smartcard.yubico.command;

import mh.smartcard.iso.CLA;
import mh.smartcard.iso.Command;
import mh.smartcard.yubico.INS;

public class GetOTP extends Command.Short {
	public enum Slot {
		SLOT_1(0x00),
		SLOT_2(0x01);
		public final int value;
		Slot(int value) {
			this.value = value;
		}
	};
	
	private GetOTP(byte[] bytes) {
		super(bytes);
	}

	public static GetOTP getInstance(Slot slot) {
		CLA cla = CLA.NONE_LAST_0;
		int p1 = slot.value;
		int p2 = 0;
		int ne = 0;
		byte[] data = null;
		return new GetOTP(getBytes(cla, INS.GET_OTP, p1, p2, data, ne));
	}
}
