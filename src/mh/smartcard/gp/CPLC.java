package mh.smartcard.gp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import mh.smartcard.UnexpectedException;
import mh.smartcard.util.ByteBuffer;
import mh.smartcard.util.Util;

public class CPLC {
	private static final Map<String, String> map = Util.loadProperties(CPLC.class);
	
	private static String getDateString(int yddd) {
		if (yddd == 0) return "0000-00-00";
		final int y    = (yddd >> 12);
		final int d100 = ((yddd & 0x0FFF) >> 8) & 0x000F;
		final int d10  = ((yddd & 0x0FFF) >> 4) & 0x000F;
		final int d1   = ((yddd & 0x0FFF) >> 0) & 0x000F;
		final int days = d100 * 100 + d10 * 10 + d1;
		
		GregorianCalendar target = new GregorianCalendar();
		final int YEAR = target.get(Calendar.YEAR);
		final int YEAR_000Y = YEAR % 10;
		final int YEAR_YYY0 = YEAR - YEAR_000Y;
		final int DATE_OF_YEAR = target.get(Calendar.DAY_OF_YEAR);
		
		int year;
		if (y == YEAR_000Y) {
			if (days <= DATE_OF_YEAR) {
				year = y + YEAR_YYY0;
			} else {
				year = y + YEAR_YYY0 - 10;
			}
		} else if (y < YEAR_000Y) {
			year = y + YEAR_YYY0;
		} else {
			year = y + YEAR_YYY0 - 10;
		}
		target.set(year, 1, 1);
		target.set(Calendar.DAY_OF_YEAR, days - 1);
		
		final int yyyy = target.get(Calendar.YEAR);
		final int mm   = target.get(Calendar.MONTH) + 1;
		final int dd   = target.get(Calendar.DAY_OF_MONTH);
		return String.format("%04d-%02d-%02d", yyyy, mm, dd);
	}
	

	
	final int icFabricator;
	final int icType;
	final int osID;
	final int osReleaseData;
	final int osReleaseLevel;
	final int icFabricationDate;
	final int icSerialNumber;
	final int icBatchID;
	final int icModuleFabricator;
	final int icModulePackagingtDate;
	final int icManufacturer;
	final int icEmbeddingDate;
	final int icPrePersonalizer;
	final int icPrePersonalizationEquipmentDate;
	final int icPrePersonalizationEquipmentID;
	final int icPersonalizer;
	final int icPersonalizationDate;
	final int icPersonalizationEquipmentID;

	public CPLC(byte[] bytes) {
		ByteBuffer bb = new ByteBuffer(bytes);
		icFabricator                      = bb.getShort();
		icType                            = bb.getShort();
		osID                              = bb.getShort();
		osReleaseData                     = bb.getShort();
		osReleaseLevel                    = bb.getShort();
		icFabricationDate                 = bb.getShort();
		icSerialNumber                    = bb.getInt();
		icBatchID                         = bb.getShort();
		icModuleFabricator                = bb.getShort();
		icModulePackagingtDate            = bb.getShort();
		icManufacturer                    = bb.getShort();
		icEmbeddingDate                   = bb.getShort();
		icPrePersonalizer                 = bb.getShort();
		icPrePersonalizationEquipmentDate = bb.getShort();
		icPrePersonalizationEquipmentID   = bb.getInt();
		icPersonalizer                    = bb.getShort();
		icPersonalizationDate             = bb.getShort();
		icPersonalizationEquipmentID      = bb.getInt();
		
		if (bb.hasRemaining()) {
			String message = String.format("bytes.length = %d  bb.position = %d", bytes.length, bb.position());
			throw new UnexpectedException(message);
		}
	}
	
	public String toString() {				
		List<String> elements = new ArrayList<>();
		// IC-FAB
		String icFabricator = String.format("%04X", this.icFabricator);
		{
			String key = "IF-FAB-" + icFabricator;
			if (map.containsKey(key)) {
				icFabricator = map.get(key);
			}
		}
		elements.add(String.format("[%s %s %s]", "IC-FABRICATION", icFabricator, getDateString(icFabricationDate)));
		elements.add(String.format("[%s %08X]", "IC-SERIAL-NUMBER", icSerialNumber));
		elements.add(String.format("[%s %04X]", "IC-BATCH-ID", icBatchID));
		// IC-TYPE
		String icType = String.format("%04X", this.icType);
		{
			String key = "IC-TYPE-" + icType;
			if (map.containsKey(key)) {
				icType = map.get(key);
			}
		}
		elements.add(String.format("[%s %s]", "IC-TYPE", icType));
		// OS-ID
		String osID = String.format("%04X", this.osID);
		{
			String key = "OS-ID-" + osID;
			if (map.containsKey(key)) {
				osID = map.get(key);
			}
		}
		elements.add(String.format("[%s %s %s %04X]", "OS", osID, getDateString(osReleaseData), osReleaseLevel));
		// IC-MODULE
		String icModuleFabricator = String.format("%04X", this.icModuleFabricator);
		{
			String key = "IC-MODULE-" + icModuleFabricator;
			if (map.containsKey(key)) {
				icModuleFabricator = map.get(key);
			}
		}
		elements.add(String.format("[%s %s %s]", "IC-MODULE", icModuleFabricator, getDateString(icModulePackagingtDate)));
		elements.add(String.format("[%s %04X]", "IC-MANUFACTURER", icManufacturer));
		elements.add(String.format("[%s %s]",   "IC-EMBEDDING-DATE", getDateString(icEmbeddingDate)));
		elements.add(String.format("[%s %04X %s %08X]", "IC-PRE-PERSONALIZATION", icPrePersonalizer, getDateString(icPrePersonalizationEquipmentDate), icPrePersonalizationEquipmentID));
		elements.add(String.format("[%s %04X %s %08X]", "IC-PERSONALIZATION", icPersonalizer, getDateString(icPersonalizationDate), icPersonalizationEquipmentID));

		return String.format("[%s]", Util.join(elements));
	}
}
