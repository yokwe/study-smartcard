package mh.smartcard.multos;

import java.util.ArrayList;
import java.util.List;

import mh.smartcard.UnexpectedException;
import mh.smartcard.data.Hex;
import mh.smartcard.util.ByteBuffer;
import mh.smartcard.util.Util;

public class MultosData {
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
		final int    multosVersion;
		final int    icManufacturerID;
		final int    implementationID;
		final String mcdID;
		final int    productID;
		final String issuerID;
		final int    msmControlDataDate;
		final String mcdNumber;
		final String rfu;
		final int    maxDynamicSize;
		final int    maxPublicSize;
		final int    maxDirFileRecordSize;
		final int    maxFCIRecordSize;
		final int    maxATRHistoricalByteRecordSize;
		final int    maxATRFileRecordSize;
		final int    multosPublicKeyCertSize;
		final int    securityLevel;
		final int    certMethodID;
		final int    applSignatureMethodID;
		final int    enciphermentDescriptor;
		final int    hashMethodID;
		

		public MultosData(byte[] bytes) {
			ByteBuffer bb = new ByteBuffer(bytes);
			
			multosVersion                  = bb.getShort();
			icManufacturerID               = bb.get();
			implementationID               = bb.get();
			mcdID                          = Hex.toString(bb.getByteArray(6));
			productID                      = bb.get();
			issuerID                       = Hex.toString(bb.getByteArray(4));
			msmControlDataDate             = bb.get();
			mcdNumber                      = Hex.toString(bb.getByteArray(8));
			rfu                            = Hex.toString(bb.getByteArray(80));
			maxDynamicSize                 = bb.getShort();
			maxPublicSize                  = bb.getShort();
			maxDirFileRecordSize           = bb.getShort();
			maxFCIRecordSize               = bb.getShort();
			maxATRHistoricalByteRecordSize = bb.getShort();
			maxATRFileRecordSize           = bb.getShort();
			multosPublicKeyCertSize        = bb.getShort();
			securityLevel                  = bb.get();
			certMethodID                   = bb.getShort();
			applSignatureMethodID          = bb.getShort();
			enciphermentDescriptor         = bb.getShort();
			hashMethodID                   = bb.getShort();
			
			// Sanity check
			if (bb.hasRemaining()) {
				throw new UnexpectedException(String.format("pos = %d  bytes = (%d)%s", bb.position(), bytes.length, Hex.toString(bytes)));
			}
		}
		
		@Override
		public String toString() {
			List<String> elements = new ArrayList<>();

			elements.add(String.format("[%s %04X]", "MULTOS-VERSION",              multosVersion));
			elements.add(String.format("[%s %02X]", "IC-MANUFACTURER-ID",          icManufacturerID));
			elements.add(String.format("[%s %02X]", "IMPLEMENTATION-ID",           implementationID));
			elements.add(String.format("[%s %s]",   "MCD-ID",                      mcdID));
			elements.add(String.format("[%s %02X]", "PRODUCT-ID",                  productID));
			elements.add(String.format("[%s %s]",   "ISSUER-ID",                   issuerID));
			elements.add(String.format("[%s %02X]", "MSM-CONTROL-DATA-DATE",       msmControlDataDate));
			elements.add(String.format("[%s %s]",   "MCD-NUMBER",                  mcdNumber));
//			elements.add(String.format("[%s %s]",   "RFU",                         rfu));
			elements.add(String.format("[%s %04X]", "MAX-DYNAMIC-SIZE",            maxDynamicSize));
			elements.add(String.format("[%s %04X]", "MAX-PUBLIC-SIZE",             maxPublicSize));
			elements.add(String.format("[%s %04X]", "MAX-DIR-FILE-RECORD-SIZE",    maxDirFileRecordSize));
			elements.add(String.format("[%s %04X]", "MAX-FCI-RECORD-SIZE",         maxFCIRecordSize));
			elements.add(String.format("[%s %04X]", "MAX-HISTORICAL-BYTE-SIZE",    maxATRHistoricalByteRecordSize));
			elements.add(String.format("[%s %04X]", "MAX-ATR-FILE-RECORD-SIZE",    maxATRFileRecordSize));
			elements.add(String.format("[%s %04X]", "MULTOS-PUBLIC-KEY-CERT-SIZE", multosPublicKeyCertSize));
			elements.add(String.format("[%s %02X]", "SECURITY-LEVEL",              securityLevel));
			elements.add(String.format("[%s %04X]", "CERT-METHOD-ID",              certMethodID));
			elements.add(String.format("[%s %04X]", "APPL-SIGNATURE-METHOD-ID",    applSignatureMethodID));
			elements.add(String.format("[%s %04X]", "ENCIPHERMENT-DESCRIPTOR",     enciphermentDescriptor));
			elements.add(String.format("[%s %04X]", "HASH-METHOD-ID",              hashMethodID));

			return Util.join(elements);
		}
	}