package mh.smartcard.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import mh.smartcard.data.Hex;

public final class AIDList {
		public static final String NAME_PSE                                        = "PSE";
		public static final String NUMBER_PSE                                      = "315041592E5359532E4444463031";

		public static final String NAME_PPSE                                       = "PPSE";
		public static final String NUMBER_PPSE                                     = "325041592E5359532E4444463031";

		public static final String NAME_VISA                                       = "VISA";
		public static final String NUMBER_VISA                                     = "A000000003";

		public static final String NAME_OPEN_PLATFORM_CARD_MANAGER                 = "OPEN-PLATFORM-CARD-MANAGER";
		public static final String NUMBER_OPEN_PLATFORM_CARD_MANAGER               = "A000000003000000";

		public static final String NAME_VISA_CREDIT_DEBIT                          = "VISA-CREDIT-DEBIT";
		public static final String NUMBER_VISA_CREDIT_DEBIT                        = "A0000000031010";

		public static final String NAME_VISA_ELECTRON                              = "VISA-ELECTRON";
		public static final String NUMBER_VISA_ELECTRON                            = "A0000000032010";

		public static final String NAME_VISA_PLUS                                  = "VISA-PLUS";
		public static final String NUMBER_VISA_PLUS                                = "A0000000038010";

		public static final String NAME_MASTER                                     = "MASTER";
		public static final String NUMBER_MASTER                                   = "A000000004";

		public static final String NAME_MASTER_CREDIT_DEBIT                        = "MASTER-CREDIT-DEBIT";
		public static final String NUMBER_MASTER_CREDIT_DEBIT                      = "A0000000041010";

		public static final String NAME_MAESTRO                                    = "MAESTRO";
		public static final String NUMBER_MAESTRO                                  = "A0000000043060";

		public static final String NAME_CIRRUS                                     = "CIRRUS";
		public static final String NUMBER_CIRRUS                                   = "A0000000046000";

		public static final String NAME_AMEX                                       = "AMEX";
		public static final String NUMBER_AMEX                                     = "A000000025";

		public static final String NAME_SUN                                        = "SUN";
		public static final String NUMBER_SUN                                      = "A000000062";

		public static final String NAME_JAVA_CARD_STATIC                           = "JAVA-CARD-STATIC";
		public static final String NUMBER_JAVA_CARD_STATIC                         = "A000000062EE";

		public static final String NAME_JCB                                        = "JCB";
		public static final String NUMBER_JCB                                      = "A000000065";

		public static final String NAME_JCB_CREDIT                                 = "JCB-CREDIT";
		public static final String NUMBER_JCB_CREDIT                               = "A0000000651010";

		public static final String NAME_NTT_COMMUNICATION                          = "NTT-COMMUNICATION";
		public static final String NUMBER_NTT_COMMUNICATION                        = "A000000115";

		public static final String NAME_NTT_00010306                               = "NTT-00010306";
		public static final String NUMBER_NTT_00010306                             = "A00000011500010306";

		public static final String NAME_NTT_00020106                               = "NTT-00020106";
		public static final String NUMBER_NTT_00020106                             = "A00000011500020106";

		public static final String NAME_NTT_000C0106                               = "NTT-000C0106";
		public static final String NUMBER_NTT_000C0106                             = "A000000115000C0106";

		public static final String NAME_MULTOS                                     = "MULTOS";
		public static final String NUMBER_MULTOS                                   = "A000000144";

		public static final String NAME_MULTOS_COMMAND_DISPATCHER                  = "MULTOS-COMMAND-DISPATCHER";
		public static final String NUMBER_MULTOS_COMMAND_DISPATCHER                = "A000000144475031";

		public static final String NAME_MULTOS_CARD_MANAGER_SERIVICE_PROVIDER      = "MULTOS-CARD-MANAGER-SERIVICE-PROVIDER";
		public static final String NUMBER_MULTOS_CARD_MANAGER_SERIVICE_PROVIDER    = "A000000144475032";

		public static final String NAME_GLOBAL_PLATFORM                            = "GLOBAL-PLATFORM";
		public static final String NUMBER_GLOBAL_PLATFORM                          = "A000000151";

		public static final String NAME_GLOBAL_PLATFORM_ISD                        = "GLOBAL-PLATFORM-ISD";
		public static final String NUMBER_GLOBAL_PLATFORM_ISD                      = "A0000001510000";

		public static final String NAME_IBM                                        = "IBM";
		public static final String NUMBER_IBM                                      = "A000000167";

		public static final String NAME_JCOP_IDENTITY                              = "JCOP-IDENTITY";
		public static final String NUMBER_JCOP_IDENTITY                            = "A000000167413000FF";

		public static final String NAME_DNP                                        = "DNP";
		public static final String NUMBER_DNP                                      = "A000000168";

		public static final String NAME_DNP_8001000200490001                       = "DNP-8001000200490001";
		public static final String NUMBER_DNP_8001000200490001                     = "A0000001688001000200490001";

		public static final String NAME_NATIONAL_POLICE_AGENCY_OF_JAPAN            = "NATIONAL-POLICE-AGENCY-OF-JAPAN";
		public static final String NUMBER_NATIONAL_POLICE_AGENCY_OF_JAPAN          = "A000000231";

		public static final String NAME_ISO_JTC1_SC17_WG10                         = "ISO-JTC1-SC17-WG10";
		public static final String NUMBER_ISO_JTC1_SC17_WG10                       = "A000000248";

		public static final String NAME_NIST                                       = "NIST";
		public static final String NUMBER_NIST                                     = "A000000308";

		public static final String NAME_PIV                                        = "PIV";
		public static final String NUMBER_PIV                                      = "A000000308000010000100";

		public static final String NAME_YUBICO                                     = "YUBICO";
		public static final String NUMBER_YUBICO                                   = "A000000527";

		public static final String NAME_YUBICO_U2F                                 = "YUBICO-U2F";
		public static final String NUMBER_YUBICO_U2F                               = "A000000527100201";

		public static final String NAME_YUBICO_NEO                                 = "YUBICO-NEO";
		public static final String NUMBER_YUBICO_NEO                               = "A000000527200101";

		public static final String NAME_YUBICO_OATH                                = "YUBICO-OATH";
		public static final String NUMBER_YUBICO_OATH                              = "A000000527210101";

		public static final String NAME_GIESECKE_DEVRIENT                          = "GIESECKE_DEVRIENT";
		public static final String NUMBER_GIESECKE_DEVRIENT                        = "D276000005";

		public static final String NAME_NFC_FORUM                                  = "NFC-FORUM";
		public static final String NUMBER_NFC_FORUM                                = "D276000085";

		public static final String NAME_NDEF                                       = "NDEF";
		public static final String NUMBER_NDEF                                     = "D2760000850101";

		public static final String NAME_FSF_EUROPE                                 = "FSF-EUROPE";
		public static final String NUMBER_FSF_EUROPE                               = "D276000124";

		public static final String NAME_OPEN_PGP                                   = "OPEN-PGP";
		public static final String NUMBER_OPEN_PGP                                 = "D27600012401";

		public static final String NAME_OPEN_PGP_YUBIKEY                           = "OPEN-PGP-YUBIKEY";
		public static final String NUMBER_OPEN_PGP_YUBIKEY                         = "D2760001240102000006030238360000";

		public static final String NAME_UNKNOWN_D392001061                         = "UNKNOWN-D392001061";
		public static final String NUMBER_UNKNOWN_D392001061                       = "D392001061";

		public static final String NAME_AEON_CASHCARD                              = "AEON-CASHCARD";
		public static final String NUMBER_AEON_CASHCARD                            = "D3920010612A00010040";

		public static final String NAME_MIZUNO_CASHCARD                            = "MIZUNO-CASHCARD";
		public static final String NUMBER_MIZUNO_CASHCARD                          = "D3920010612A0A010001";

		public static final String NAME_UFJ_CASHCARD                               = "UFJ-CASHCARD";
		public static final String NUMBER_UFJ_CASHCARD                             = "D3920010612A0A010005";

		public static final String NAME_SUMISHIN_SBI_NETBANK_CASHCARD              = "SUMISHIN-SBI-NETBANK-CASHCARD";
		public static final String NUMBER_SUMISHIN_SBI_NETBANK_CASHCARD            = "D3920010612A0A010038";

		public static final String NAME_MIZUHO_FVID010000000100                    = "MIZUHO-FVID010000000100";
		public static final String NUMBER_MIZUHO_FVID010000000100                  = "D3920010612C00010001000000000000";

		public static final String NAME_UFJ_PALM_VEIN_20                           = "UFJ-PALM-VEIN-20";
		public static final String NUMBER_UFJ_PALM_VEIN_20                         = "D3920010612C01010005";

		public static final String NAME_UNKNOWN_D392100031                         = "UNKNOWN-D392100031";
		public static final String NUMBER_UNKNOWN_D392100031                       = "D392100031";

		public static final String NAME_JUUKI_01                                   = "JUUKI-01";
		public static final String NUMBER_JUUKI_01                                 = "D3921000310001010401";

		public static final String NAME_JUUKI_02                                   = "JUUKI-02";
		public static final String NUMBER_JUUKI_02                                 = "D3921000310001010402";

		public static final String NAME_UNKNOWN_D392F00023                         = "UNKNOWN-D392F00023";
		public static final String NUMBER_UNKNOWN_D392F00023                       = "D392F00023";

		public static final String NAME_UTOKYO_0100                                = "UTOKYO-0100";
		public static final String NUMBER_UTOKYO_0100                              = "D392F000230100010000000000000000";

		public static final String NAME_UTOKYO_0110                                = "UTOKYO-0110";
		public static final String NUMBER_UTOKYO_0110                              = "D392F000230110010000000000000000";

		public static final String NAME_UNKNOWN_D392F00026                         = "UNKNOWN-D392F00026";
		public static final String NUMBER_UNKNOWN_D392F00026                       = "D392F00026";

		public static final String NAME_UNKNONW_D392F000260100000001               = "UNKNONW-D392F000260100000001";
		public static final String NUMBER_UNKNONW_D392F000260100000001             = "D392F000260100000001";

		public static final String NAME_UNKNOWN_D392F00041                         = "UNKNOWN-D392F00041";
		public static final String NUMBER_UNKNOWN_D392F00041                       = "D392F00041";

		public static final String NAME_YUUCHO_CASHCARD                            = "YUUCHO-CASHCARD";
		public static final String NUMBER_YUUCHO_CASHCARD                          = "D392F00041099900";

		public static final String NAME_UNKNOWN_D392F0004F                         = "UNKNOWN-D392F0004F";
		public static final String NUMBER_UNKNOWN_D392F0004F                       = "D392F0004F";

		public static final String NAME_ZAIRYUU_DF1                                = "ZAIRYUU-DF1";
		public static final String NUMBER_ZAIRYUU_DF1                              = "D392F0004F0200000000000000000000";

		public static final String NAME_ZAIRYUU_DF2                                = "ZAIRYUU-DF2";
		public static final String NUMBER_ZAIRYUU_DF2                              = "D392F0004F0300000000000000000000";

		public static final String NAME_ZAIRYUU_DF3                                = "ZAIRYUU-DF3";
		public static final String NUMBER_ZAIRYUU_DF3                              = "D392F0004F0500000000000000000000";

	private static final Map<String, String> map = new TreeMap<>();
	static {
		map.put(NUMBER_PSE,                                     NAME_PSE);
		map.put(NUMBER_PPSE,                                    NAME_PPSE);
		map.put(NUMBER_VISA,                                    NAME_VISA);
		map.put(NUMBER_OPEN_PLATFORM_CARD_MANAGER,              NAME_OPEN_PLATFORM_CARD_MANAGER);
		map.put(NUMBER_VISA_CREDIT_DEBIT,                       NAME_VISA_CREDIT_DEBIT);
		map.put(NUMBER_VISA_ELECTRON,                           NAME_VISA_ELECTRON);
		map.put(NUMBER_VISA_PLUS,                               NAME_VISA_PLUS);
		map.put(NUMBER_MASTER,                                  NAME_MASTER);
		map.put(NUMBER_MASTER_CREDIT_DEBIT,                     NAME_MASTER_CREDIT_DEBIT);
		map.put(NUMBER_MAESTRO,                                 NAME_MAESTRO);
		map.put(NUMBER_CIRRUS,                                  NAME_CIRRUS);
		map.put(NUMBER_AMEX,                                    NAME_AMEX);
		map.put(NUMBER_SUN,                                     NAME_SUN);
		map.put(NUMBER_JAVA_CARD_STATIC,                        NAME_JAVA_CARD_STATIC);
		map.put(NUMBER_JCB,                                     NAME_JCB);
		map.put(NUMBER_JCB_CREDIT,                              NAME_JCB_CREDIT);
		map.put(NUMBER_NTT_COMMUNICATION,                       NAME_NTT_COMMUNICATION);
		map.put(NUMBER_NTT_00010306,                            NAME_NTT_00010306);
		map.put(NUMBER_NTT_00020106,                            NAME_NTT_00020106);
		map.put(NUMBER_NTT_000C0106,                            NAME_NTT_000C0106);
		map.put(NUMBER_MULTOS,                                  NAME_MULTOS);
		map.put(NUMBER_MULTOS_COMMAND_DISPATCHER,               NAME_MULTOS_COMMAND_DISPATCHER);
		map.put(NUMBER_MULTOS_CARD_MANAGER_SERIVICE_PROVIDER,   NAME_MULTOS_CARD_MANAGER_SERIVICE_PROVIDER);
		map.put(NUMBER_GLOBAL_PLATFORM,                         NAME_GLOBAL_PLATFORM);
		map.put(NUMBER_GLOBAL_PLATFORM_ISD,                     NAME_GLOBAL_PLATFORM_ISD);
		map.put(NUMBER_IBM,                                     NAME_IBM);
		map.put(NUMBER_JCOP_IDENTITY,                           NAME_JCOP_IDENTITY);
		map.put(NUMBER_DNP,                                     NAME_DNP);
		map.put(NUMBER_DNP_8001000200490001,                    NAME_DNP_8001000200490001);
		map.put(NUMBER_NATIONAL_POLICE_AGENCY_OF_JAPAN,         NAME_NATIONAL_POLICE_AGENCY_OF_JAPAN);
		map.put(NUMBER_ISO_JTC1_SC17_WG10,                      NAME_ISO_JTC1_SC17_WG10);
		map.put(NUMBER_NIST,                                    NAME_NIST);
		map.put(NUMBER_PIV,                                     NAME_PIV);
		map.put(NUMBER_YUBICO,                                  NAME_YUBICO);
		map.put(NUMBER_YUBICO_U2F,                              NAME_YUBICO_U2F);
		map.put(NUMBER_YUBICO_NEO,                              NAME_YUBICO_NEO);
		map.put(NUMBER_YUBICO_OATH,                             NAME_YUBICO_OATH);
		map.put(NUMBER_GIESECKE_DEVRIENT,                       NAME_GIESECKE_DEVRIENT);
		map.put(NUMBER_NFC_FORUM,                               NAME_NFC_FORUM);
		map.put(NUMBER_NDEF,                                    NAME_NDEF);
		map.put(NUMBER_FSF_EUROPE,                              NAME_FSF_EUROPE);
		map.put(NUMBER_OPEN_PGP,                                NAME_OPEN_PGP);
		map.put(NUMBER_OPEN_PGP_YUBIKEY,                        NAME_OPEN_PGP_YUBIKEY);
		map.put(NUMBER_UNKNOWN_D392001061,                      NAME_UNKNOWN_D392001061);
		map.put(NUMBER_AEON_CASHCARD,                           NAME_AEON_CASHCARD);
		map.put(NUMBER_MIZUNO_CASHCARD,                         NAME_MIZUNO_CASHCARD);
		map.put(NUMBER_UFJ_CASHCARD,                            NAME_UFJ_CASHCARD);
		map.put(NUMBER_SUMISHIN_SBI_NETBANK_CASHCARD,           NAME_SUMISHIN_SBI_NETBANK_CASHCARD);
		map.put(NUMBER_MIZUHO_FVID010000000100,                 NAME_MIZUHO_FVID010000000100);
		map.put(NUMBER_UFJ_PALM_VEIN_20,                        NAME_UFJ_PALM_VEIN_20);
		map.put(NUMBER_UNKNOWN_D392100031,                      NAME_UNKNOWN_D392100031);
		map.put(NUMBER_JUUKI_01,                                NAME_JUUKI_01);
		map.put(NUMBER_JUUKI_02,                                NAME_JUUKI_02);
		map.put(NUMBER_UNKNOWN_D392F00023,                      NAME_UNKNOWN_D392F00023);
		map.put(NUMBER_UTOKYO_0100,                             NAME_UTOKYO_0100);
		map.put(NUMBER_UTOKYO_0110,                             NAME_UTOKYO_0110);
		map.put(NUMBER_UNKNOWN_D392F00026,                      NAME_UNKNOWN_D392F00026);
		map.put(NUMBER_UNKNONW_D392F000260100000001,            NAME_UNKNONW_D392F000260100000001);
		map.put(NUMBER_UNKNOWN_D392F00041,                      NAME_UNKNOWN_D392F00041);
		map.put(NUMBER_YUUCHO_CASHCARD,                         NAME_YUUCHO_CASHCARD);
		map.put(NUMBER_UNKNOWN_D392F0004F,                      NAME_UNKNOWN_D392F0004F);
		map.put(NUMBER_ZAIRYUU_DF1,                             NAME_ZAIRYUU_DF1);
		map.put(NUMBER_ZAIRYUU_DF2,                             NAME_ZAIRYUU_DF2);
		map.put(NUMBER_ZAIRYUU_DF3,                             NAME_ZAIRYUU_DF3);
	}

	public static final byte[] PSE                                      = Util.fromHexString(NUMBER_PSE);
	public static final byte[] PPSE                                     = Util.fromHexString(NUMBER_PPSE);
	public static final byte[] VISA                                     = Util.fromHexString(NUMBER_VISA);
	public static final byte[] OPEN_PLATFORM_CARD_MANAGER               = Util.fromHexString(NUMBER_OPEN_PLATFORM_CARD_MANAGER);
	public static final byte[] VISA_CREDIT_DEBIT                        = Util.fromHexString(NUMBER_VISA_CREDIT_DEBIT);
	public static final byte[] VISA_ELECTRON                            = Util.fromHexString(NUMBER_VISA_ELECTRON);
	public static final byte[] VISA_PLUS                                = Util.fromHexString(NUMBER_VISA_PLUS);
	public static final byte[] MASTER                                   = Util.fromHexString(NUMBER_MASTER);
	public static final byte[] MASTER_CREDIT_DEBIT                      = Util.fromHexString(NUMBER_MASTER_CREDIT_DEBIT);
	public static final byte[] MAESTRO                                  = Util.fromHexString(NUMBER_MAESTRO);
	public static final byte[] CIRRUS                                   = Util.fromHexString(NUMBER_CIRRUS);
	public static final byte[] AMEX                                     = Util.fromHexString(NUMBER_AMEX);
	public static final byte[] SUN                                      = Util.fromHexString(NUMBER_SUN);
	public static final byte[] JAVA_CARD_STATIC                         = Util.fromHexString(NUMBER_JAVA_CARD_STATIC);
	public static final byte[] JCB                                      = Util.fromHexString(NUMBER_JCB);
	public static final byte[] JCB_CREDIT                               = Util.fromHexString(NUMBER_JCB_CREDIT);
	public static final byte[] NTT_COMMUNICATION                        = Util.fromHexString(NUMBER_NTT_COMMUNICATION);
	public static final byte[] NTT_00010306                             = Util.fromHexString(NUMBER_NTT_00010306);
	public static final byte[] NTT_00020106                             = Util.fromHexString(NUMBER_NTT_00020106);
	public static final byte[] NTT_000C0106                             = Util.fromHexString(NUMBER_NTT_000C0106);
	public static final byte[] MULTOS                                   = Util.fromHexString(NUMBER_MULTOS);
	public static final byte[] MULTOS_COMMAND_DISPATCHER                = Util.fromHexString(NUMBER_MULTOS_COMMAND_DISPATCHER);
	public static final byte[] MULTOS_CARD_MANAGER_SERIVICE_PROVIDER    = Util.fromHexString(NUMBER_MULTOS_CARD_MANAGER_SERIVICE_PROVIDER);
	public static final byte[] GLOBAL_PLATFORM                          = Util.fromHexString(NUMBER_GLOBAL_PLATFORM);
	public static final byte[] GLOBAL_PLATFORM_ISD                      = Util.fromHexString(NUMBER_GLOBAL_PLATFORM_ISD);
	public static final byte[] IBM                                      = Util.fromHexString(NUMBER_IBM);
	public static final byte[] JCOP_IDENTITY                            = Util.fromHexString(NUMBER_JCOP_IDENTITY);
	public static final byte[] DNP                                      = Util.fromHexString(NUMBER_DNP);
	public static final byte[] DNP_8001000200490001                     = Util.fromHexString(NUMBER_DNP_8001000200490001);
	public static final byte[] NATIONAL_POLICE_AGENCY_OF_JAPAN          = Util.fromHexString(NUMBER_NATIONAL_POLICE_AGENCY_OF_JAPAN);
	public static final byte[] ISO_JTC1_SC17_WG10                       = Util.fromHexString(NUMBER_ISO_JTC1_SC17_WG10);
	public static final byte[] NIST                                     = Util.fromHexString(NUMBER_NIST);
	public static final byte[] PIV                                      = Util.fromHexString(NUMBER_PIV);
	public static final byte[] YUBICO                                   = Util.fromHexString(NUMBER_YUBICO);
	public static final byte[] YUBICO_U2F                               = Util.fromHexString(NUMBER_YUBICO_U2F);
	public static final byte[] YUBICO_NEO                               = Util.fromHexString(NUMBER_YUBICO_NEO);
	public static final byte[] YUBICO_OATH                              = Util.fromHexString(NUMBER_YUBICO_OATH);
	public static final byte[] GIESECKE_DEVRIENT                        = Util.fromHexString(NUMBER_GIESECKE_DEVRIENT);
	public static final byte[] NFC_FORUM                                = Util.fromHexString(NUMBER_NFC_FORUM);
	public static final byte[] NDEF                                     = Util.fromHexString(NUMBER_NDEF);
	public static final byte[] FSF_EUROPE                               = Util.fromHexString(NUMBER_FSF_EUROPE);
	public static final byte[] OPEN_PGP                                 = Util.fromHexString(NUMBER_OPEN_PGP);
	public static final byte[] OPEN_PGP_YUBIKEY                         = Util.fromHexString(NUMBER_OPEN_PGP_YUBIKEY);
	public static final byte[] UNKNOWN_D392001061                       = Util.fromHexString(NUMBER_UNKNOWN_D392001061);
	public static final byte[] AEON_CASHCARD                            = Util.fromHexString(NUMBER_AEON_CASHCARD);
	public static final byte[] MIZUNO_CASHCARD                          = Util.fromHexString(NUMBER_MIZUNO_CASHCARD);
	public static final byte[] UFJ_CASHCARD                             = Util.fromHexString(NUMBER_UFJ_CASHCARD);
	public static final byte[] SUMISHIN_SBI_NETBANK_CASHCARD            = Util.fromHexString(NUMBER_SUMISHIN_SBI_NETBANK_CASHCARD);
	public static final byte[] MIZUHO_FVID010000000100                  = Util.fromHexString(NUMBER_MIZUHO_FVID010000000100);
	public static final byte[] UFJ_PALM_VEIN_20                         = Util.fromHexString(NUMBER_UFJ_PALM_VEIN_20);
	public static final byte[] UNKNOWN_D392100031                       = Util.fromHexString(NUMBER_UNKNOWN_D392100031);
	public static final byte[] JUUKI_01                                 = Util.fromHexString(NUMBER_JUUKI_01);
	public static final byte[] JUUKI_02                                 = Util.fromHexString(NUMBER_JUUKI_02);
	public static final byte[] UNKNOWN_D392F00023                       = Util.fromHexString(NUMBER_UNKNOWN_D392F00023);
	public static final byte[] UTOKYO_0100                              = Util.fromHexString(NUMBER_UTOKYO_0100);
	public static final byte[] UTOKYO_0110                              = Util.fromHexString(NUMBER_UTOKYO_0110);
	public static final byte[] UNKNOWN_D392F00026                       = Util.fromHexString(NUMBER_UNKNOWN_D392F00026);
	public static final byte[] UNKNONW_D392F000260100000001             = Util.fromHexString(NUMBER_UNKNONW_D392F000260100000001);
	public static final byte[] UNKNOWN_D392F00041                       = Util.fromHexString(NUMBER_UNKNOWN_D392F00041);
	public static final byte[] YUUCHO_CASHCARD                          = Util.fromHexString(NUMBER_YUUCHO_CASHCARD);
	public static final byte[] UNKNOWN_D392F0004F                       = Util.fromHexString(NUMBER_UNKNOWN_D392F0004F);
	public static final byte[] ZAIRYUU_DF1                              = Util.fromHexString(NUMBER_ZAIRYUU_DF1);
	public static final byte[] ZAIRYUU_DF2                              = Util.fromHexString(NUMBER_ZAIRYUU_DF2);
	public static final byte[] ZAIRYUU_DF3                              = Util.fromHexString(NUMBER_ZAIRYUU_DF3);

	public static String getName(byte[] nuberData) {
		String number = Hex.toString(nuberData);
		String ret = map.get(number);
		return (ret != null) ? ret : ("UNKNOWN-" + number);
	}

	public static List<String> getNumberList() {
		List<String> ret = new ArrayList<>();
		for(String number: map.keySet()) ret.add(number);
		return ret;
	}
}
