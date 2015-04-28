package mh.smartcard.iso;

import java.util.Map;
import java.util.TreeMap;

import mh.smartcard.UnexpectedException;

public class INS {
	// ISO 7816 4 2005
	// Odd  INS code take FILE_ID or SFI.
	// Even INS code take other than above.
	
	// If bit 5 is set to 0, then the command is the last or only command of a chain.
	// If bit 5 is set to 1, then the command is no the last command of a chain.
	// xxx5 xxxx
	
	// The value 6X and 9X are invalid.
	
	// If bit 1 is set to 0 (even INS code), then no indication is provided
	// If bit 1 is set to 1 (odd INS code), then BER-TLV encoding shall apply as follows
	//   In un-chained commands with SW1 not set to '61', data fields, if any shall be encoded i BER-TLV.
	//   Command chaining and / or the use of SW1 set to '61' allow the transmission of data strings too log for a single command.

	private static final Map<Integer, INS> map = new TreeMap<>();
	private static INS getInstance(int value, String name) {
		INS old = map.get(value);
		if (old == null) {
			INS ret = new INS(value, name);
			map.put(value, ret);
			return ret;
		}
		throw new UnexpectedException(String.format("Duplicate %02X  old = %s  new = %s", value, old.name, name));
	}
		
	public static final INS ACTIVATE_FILE                    = INS.getInstance(0x44, "ACTIVATE_FILE");
	public static final INS APPEND_RECORD                    = INS.getInstance(0xE2, "APPEND_RECORD");
	public static final INS CHANGE_REFERENCE_DATA            = INS.getInstance(0x24, "CHANGE_REFERENCE_DATA");
	public static final INS CREATE_FILE                      = INS.getInstance(0xE0, "CREATE_FILE");
	public static final INS DEACTIVATE_FILE                  = INS.getInstance(0x04, "DEACTIVATE_FILE");
	public static final INS DELETE_FILE                      = INS.getInstance(0xE4, "DELETE_FILE");
	public static final INS DISABLE_VERIFICATION_REQUIREMENT = INS.getInstance(0x26, "DISABLE_VERIFICATION_REQUIREMENT");
	public static final INS ENABLE_VERIFICATION_REQUIREMENT  = INS.getInstance(0x28, "ENABLE_VERIFICATION_REQUIREMENT");
	public static final INS ENVELOPE_C2                      = INS.getInstance(0xC2, "ENVELOPE");
	public static final INS ENVELOPE_C3                      = INS.getInstance(0xC3, "ENVELOPE");
	public static final INS ERASE_BINARY_0E                  = INS.getInstance(0x0E, "ERASE_BINARY");
	public static final INS EARSE_BINARY_0F                  = INS.getInstance(0x0F, "EARSE_BINARY");           // FILE ID or SFI
	public static final INS ERASE_RECORD                     = INS.getInstance(0x0C, "ERASE_RECORD");
	public static final INS EXTERNAL_AUTHENTICATION          = INS.getInstance(0x82, "EXTERNAL_AUTHENTICATION");
	public static final INS GENERAL_AUTHENTICATION_86        = INS.getInstance(0x86, "GENERAL_AUTHENTICATION");
	public static final INS GENERAL_AUTHENTICATION_87        = INS.getInstance(0x87, "GENERAL_AUTHENTICATION"); // FILE ID or SFI
	public static final INS GENERAL_ASYMMETRIC_KEY_PAIR      = INS.getInstance(0x46, "GENERAL_ASYMMETRIC_KEY_PAIR");
	public static final INS GET_CHALLENGE                    = INS.getInstance(0x84, "GET_CHALLENGE");
	public static final INS GET_DATA_CA                      = INS.getInstance(0xCA, "GET_DATA");
	public static final INS GET_DATA_CB                      = INS.getInstance(0xCB, "GET_DATA");               // FILE ID or SFI
	public static final INS GET_RESPONSE                     = INS.getInstance(0xC0, "GET_RESPONSE");
	public static final INS INTERNAL_AUTHENTICATION          = INS.getInstance(0x88, "INTERNAL_AUTHENTICATION");
	public static final INS MANAGE_CHANNEL                   = INS.getInstance(0x70, "MANAGE_CHANNEL");
	public static final INS MANAGE_SECURITY_ENVIRONMENT      = INS.getInstance(0x22, "MANAGE_SECURITY_ENVIRONMENT");
	public static final INS PERFORM_SCQL_OPERATION           = INS.getInstance(0x10, "PERFORM_SCQL_OPERATION");
	public static final INS PERFORM_SECURITY_OPERATION       = INS.getInstance(0x2A, "PERFORM_SECURITY_OPERATION");
	public static final INS PEFORM_TRANSACTION_OPERATION     = INS.getInstance(0x12, "PEFORM_TRANSACTION_OPERATION");
	public static final INS PERFORM_USER_OPERATION           = INS.getInstance(0x14, "PERFORM_USER_OPERATION");
	public static final INS PUT_DATA_DA                      = INS.getInstance(0xDA, "PUT_DATA");
	public static final INS PUT_DATA_DB                      = INS.getInstance(0xDB, "PUT_DATA");               // FILE ID or SFI
	public static final INS READ_BINARY_B0                   = INS.getInstance(0xB0, "READ_BINARY");
	public static final INS READ_BINARY_B1                   = INS.getInstance(0xB1, "READ_BINARY");            // FILE ID or SFI
	public static final INS READ_RECORD_B2                   = INS.getInstance(0xB2, "READ_RECORD");
	public static final INS READ_RECORD_B3                   = INS.getInstance(0xB3, "READ_RECORD");            // FILE ID or SFI
	public static final INS RESET_RETRY_COUNTER              = INS.getInstance(0x2C, "RESET_RETRY_COUNTER");
	public static final INS SEARCH_BINARY_A0                 = INS.getInstance(0xA0, "SEARCH_BINARY");
	public static final INS SEARCH_BINARY_A1                 = INS.getInstance(0xA1, "SEARCH_BINARY");          // FILE ID or SFI
	public static final INS SEARCH_RECORD                    = INS.getInstance(0xA2, "SEARCH_RECORD");
	public static final INS SELECT                           = INS.getInstance(0xA4, "SELECT");
	public static final INS TERMINATE_CARD_USAGE             = INS.getInstance(0xFE, "TERMINATE_CARD_USAGE");
	public static final INS TERMINATE_E6                     = INS.getInstance(0xE6, "TERMINATE");
	public static final INS TERMINATE_E8                     = INS.getInstance(0xE8, "TERMINATE");
	public static final INS UPDATE_BINARY_D6                 = INS.getInstance(0xD6, "UPDATE_BINARY");
	public static final INS UPDATE_BINARY_D7                 = INS.getInstance(0xD7, "UPDATE_BINARY");          // FILE ID or SFI
	public static final INS UPDATE_RECORD_DC                 = INS.getInstance(0xDC, "UPDATE_RECORD");
	public static final INS UPDATE_RECORD_DD                 = INS.getInstance(0xDD, "UPDATE_RECORD");          // FILE ID or SFI
	public static final INS VERIFY_20                        = INS.getInstance(0x20, "VERIFY");
	public static final INS VEIRFY_21                        = INS.getInstance(0x21, "VEIRFY");                 // FILE ID or SFI
	public static final INS WRITE_BINARY_D0                  = INS.getInstance(0xD0, "WRITE_BINARY");
	public static final INS WRITE_BINARY_D1                  = INS.getInstance(0xD1, "WRITE_BINARY");           // FILE ID or SFI
	public static final INS WRITE_REDORD                     = INS.getInstance(0xD2, "WRITE_REDORD");

	public final int value;
	public final String name;
	protected INS(int value, String name) {
		this.value = value;
		this.name  = name;
	}
	@Override
	public String toString() {
		return String.format("[%02X %s]", value, name);
	}
	@Override
	public boolean equals(Object o) {
		if (o instanceof INS) {
			return this.value == ((INS)o).value;
		}
		return false;
	}
}
