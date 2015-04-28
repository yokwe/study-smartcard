package mh.smartcard.multos.command;

import mh.smartcard.iso.CLA;
import mh.smartcard.iso.Command;
import mh.smartcard.multos.INS;

// For MULTOS 3-4.4
// 80 00 00 00 0 null 7F
//   2 MULTOS Version Number
//   1 IC Manufacturer ID
//   1 Implementation ID
//   6 MCD ID
//   1 Product ID
//   4 Issuer ID
//   1 MSM Controls Data Date
//   8 MCD Number
//  80 RFU
//   2 Maximum Dynamic Size
//   2 Maximum Public Size
//   2 Maximum DIR File Record Size
//   2 Maximum FCI Record Size
//   2 Maximum ATR Historical Byte Record Size
//   2 Maximum ATR File Record Size
//   2 MULTOS Public Key Certificate Length
//   1 Security Level
//   2 Certification Method ID
//   2 Application Signature Method ID
//   2 Encipherment Descriptor
//   2 Hash Method ID

public class GetMultosData extends Command.Short {
	public static final int NE = 0x7F;
	
	private GetMultosData(byte[] bytes) {
		super(bytes);
	}
	
	public static GetMultosData getInstance() {
		return getInstance(INS.GET_MULTOS_DATA, 0, 0, null, NE);
	}
	
	protected static GetMultosData getInstance(INS ins, int p1, int p2, byte[] data, int ne) {
		final CLA cla = CLA.PROP_80;
		return new GetMultosData(getBytes(cla, ins, p1, p2, data, ne));
	}
}
