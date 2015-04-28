package mh.smartcard.yubico.command;

import mh.smartcard.iso.CLA;
import mh.smartcard.iso.Command;
import mh.smartcard.yubico.INS;

public class Request extends Command.Short {
	private Request(byte[] bytes) {
		super(bytes);
	}
	
	// From https://github.com/Yubico/libykneomgr/blob/master/lib/devs.c
	public enum Operation {
		GetSerial(0x1000),
		// memcpy (buf, "\x00\x01\x10\x00", 4);

		SetMode  (0x1100);
		// uint8_t mode_apdu[] = "\x00\x01\x11\x00\x01\xFF";
		// Fifth byte contains new mode => mode_apdu[5] = mode;
		
		public final int value;
		Operation(int value) {
			this.value = value;
		}
	}
	
	public static Request getInstance(Operation operation) {
		CLA cla = CLA.NONE_LAST_0;
		int p1 = (operation.value & 0xFF00) >> 8;
		int p2 = (operation.value & 0x00FF);
		int ne = 0;
		byte[] data = null;
		return new Request(getBytes(cla, INS.REQUEST, p1, p2, data, ne));
	}
}
