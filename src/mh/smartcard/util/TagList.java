package mh.smartcard.util;

import mh.smartcard.iso.Tags;

public final class TagList {
	public static final class EMV {
		public static final Tags ACCOUNT_TYPE                              = Tags.getInstance(0x5F57);
		public static final Tags ACQUIRER_ID                               = Tags.getInstance(0x9F01);
		public static final Tags ADDITIONAL_TERMINAL_CAPABILITIES          = Tags.getInstance(0x9F40);
		public static final Tags ADF_NAME                                  = Tags.getInstance(0x4F);
		public static final Tags AFL                                       = Tags.getInstance(0x94);
		public static final Tags AID_TERMINAL                              = Tags.getInstance(0x9F06);
		public static final Tags AMOUNT_AUTHORISED_BINARY                  = Tags.getInstance(0x81);
		public static final Tags AMOUNT_AUTHORISED_NUMERIC                 = Tags.getInstance(0x9F02);
		public static final Tags AMOUNT_OTHER_BINARY                       = Tags.getInstance(0x9F04);
		public static final Tags AMOUNT_OTHER_NUMERIC                      = Tags.getInstance(0x9F03);
		public static final Tags AMOUNT_REFERENCE_CURRENCY                 = Tags.getInstance(0x9F3A);
		public static final Tags APPL_CRYPTOGRAM                           = Tags.getInstance(0x9F26);
		public static final Tags APPL_CURRENCY_CODE                        = Tags.getInstance(0x9F42);
		public static final Tags APPL_CURRENCY_EXPONENT                    = Tags.getInstance(0x9F44);
		public static final Tags APPL_DISC_DATA                            = Tags.getInstance(0x9F05);
		public static final Tags APPL_EFFECTIVE_DATE                       = Tags.getInstance(0x5F25);
		public static final Tags APPL_EXPIRATION_DATE                      = Tags.getInstance(0x5F24);
		public static final Tags APPL_INTERCHANGE_PROFILE                  = Tags.getInstance(0x82);
		public static final Tags APPL_LABEL                                = Tags.getInstance(0x50);
		public static final Tags APPL_PAN                                  = Tags.getInstance(0x5A);
		public static final Tags APPL_PREFERRED_NAME                       = Tags.getInstance(0x9F12);
		public static final Tags APPL_PRIORITY_INDICATOR                   = Tags.getInstance(0x87);
		public static final Tags APPL_TEMP                                 = Tags.getInstance(0x61);
		public static final Tags BIC                                       = Tags.getInstance(0x5F54);
		public static final Tags DDF_NAME                                  = Tags.getInstance(0x9D);
		public static final Tags DF_NAME                                   = Tags.getInstance(0x84);
		public static final Tags DIRECTORY_DISC_TEMP                       = Tags.getInstance(0x73);
		public static final Tags FCI_ISSUER_DISC_DATA                      = Tags.getInstance(0xBF0C);
		public static final Tags FCI_PROP_TEMP                             = Tags.getInstance(0xA5);
		public static final Tags FCI_TEMP                                  = Tags.getInstance(0x6F);
		public static final Tags IBAN                                      = Tags.getInstance(0x5F53);
		public static final Tags IIN                                       = Tags.getInstance(0x42);
		public static final Tags ISSUER_CODE_TABLE_INDEX                   = Tags.getInstance(0x9F11);
		public static final Tags ISSUER_COUNTRY_CODE2                      = Tags.getInstance(0x5F55);
		public static final Tags ISSUER_COUNTRY_CODE3                      = Tags.getInstance(0x5F56);
		public static final Tags ISSUER_URL                                = Tags.getInstance(0x5F50);
		public static final Tags LANG_PREF                                 = Tags.getInstance(0x5F2D);
		public static final Tags LOG_ENTRY                                 = Tags.getInstance(0x9F4D);
		public static final Tags PDOL                                      = Tags.getInstance(0x9F38);
		public static final Tags READ_RECORD_RESPONSE_MESSAGE_TEMP         = Tags.getInstance(0x70);
		public static final Tags RESPONSE_MESSAGE_TEMP_1                   = Tags.getInstance(0x80);
		public static final Tags RESPONSE_MESSAGE_TEMP_2                   = Tags.getInstance(0x77);
		public static final Tags SFI                                       = Tags.getInstance(0x88);
	}
	public static final class GP {
		public static final Tags APPL_EXECUTABLE_LOAD_FILE_AID             = Tags.getInstance(0xC4);
		public static final Tags APPL_PRODUCT_LIFE_CYCLE_DATA              = Tags.getInstance(0x9F6E);
		public static final Tags ASSOC_SECURITY_DOMAIN_AID                 = Tags.getInstance(0xCC);
		public static final Tags CARD_CHIP_DETAIL                          = Tags.getInstance(0x66);
		public static final Tags CARD_CONFIG_DETAIL                        = Tags.getInstance(0x65);
		public static final Tags CARD_ID_SCHEME                            = Tags.getInstance(0x63);
		public static final Tags CARD_MANAGEMENT_TYPE                      = Tags.getInstance(0x60);
		public static final Tags CPLC                                      = Tags.getInstance(0x9F7F);
		public static final Tags EXECUTABLE_LOAD_FILE_VERSION_NUMBER       = Tags.getInstance(0xCE);
		public static final Tags EXECUTABLE_MODULE_AID                     = Tags.getInstance(0x84);
		public static final Tags ISD_TRUST_CERT                            = Tags.getInstance(0x68);
		public static final Tags ISD_TRUST_POINT_CERT                      = Tags.getInstance(0x67);
		public static final Tags KEY_INFO_DATA                             = Tags.getInstance(0xC0);
		public static final Tags KEY_INFO_TEMP                             = Tags.getInstance(0xE0);
		public static final Tags LIEF_CYCLE_STATUS                         = Tags.getInstance(0x9F70);
		public static final Tags MAX_LENGTH_DATA_FIELD                     = Tags.getInstance(0x9F65);
		public static final Tags PRIVILEGES                                = Tags.getInstance(0xC5);
		public static final Tags REGISTRY_RELATED_DATA                     = Tags.getInstance(0xE3);
		public static final Tags SECURE_CHANNEL_PROTOCOL                   = Tags.getInstance(0x64);
		public static final Tags TAA                                       = Tags.getInstance(0x06);
	}
	public static final class ISO {
		public static final Tags ACCOUNT_TYPE                              = Tags.getInstance(0x5F57);
		public static final Tags ADDRESS                                   = Tags.getInstance(0x5F42);
		public static final Tags AFI                                       = Tags.getInstance(0x49);
		public static final Tags ANSWER_TO_RESET                           = Tags.getInstance(0x5F51);
		public static final Tags APPL_EFFECTIVE_DATE                       = Tags.getInstance(0x5F25);
		public static final Tags APPL_EXPIRATION_DATE                      = Tags.getInstance(0x5F24);
		public static final Tags APPL_ID                                   = Tags.getInstance(0x4F);
		public static final Tags APPL_IMAGE                                = Tags.getInstance(0x5F44);
		public static final Tags APPL_IMAGE_TEMP                           = Tags.getInstance(0x6D);
		public static final Tags APPL_LABEL                                = Tags.getInstance(0x50);
		public static final Tags APPL_RELATED_DATA                         = Tags.getInstance(0x6E);
		public static final Tags APPL_TEMP                                 = Tags.getInstance(0x61);
		public static final Tags AUTH_DATA                                 = Tags.getInstance(0x67);
		public static final Tags BIC                                       = Tags.getInstance(0x5F54);
		public static final Tags BIO_DATA_TEMP                             = Tags.getInstance(0x7F2E);
		public static final Tags BIO_INFO_GROUP_TEMP                       = Tags.getInstance(0x7F61);
		public static final Tags BIO_INFO_TEMP                             = Tags.getInstance(0x7F60);
		public static final Tags CARD_CAPABILITIES                         = Tags.getInstance(0x47);
		public static final Tags CARD_DATA                                 = Tags.getInstance(0x66);
		public static final Tags CARD_EFFECTIVE_DATE                       = Tags.getInstance(0x5F26);
		public static final Tags CARD_EXPIRATION_DATE                      = Tags.getInstance(0x59);
		public static final Tags CARD_ISSUERS_DATA                         = Tags.getInstance(0x45);
		public static final Tags CARD_SEQ_NUMBER                           = Tags.getInstance(0x5F34);
		public static final Tags CARD_SERVICE_DATA                         = Tags.getInstance(0x43);
		public static final Tags CARDHOLDER_BIO_DATA                       = Tags.getInstance(0x5F2E);
		public static final Tags CARDHOLDER_CERT                           = Tags.getInstance(0x7F21);
		public static final Tags CARDHOLDER_IMAGE_TEMP                     = Tags.getInstance(0x6C);
		public static final Tags CARDHOLDER_NAME                           = Tags.getInstance(0x5F20);
		public static final Tags CARDHOLDER_NATIONALITY                    = Tags.getInstance(0x5F2C);
		public static final Tags CARDHOLDER_PORTRATE_IMAGE                 = Tags.getInstance(0x5F40);
		public static final Tags CARDHOLDER_PRIVATE_KEY                    = Tags.getInstance(0x5F48);
		public static final Tags CARDHOLDER_PRIVATE_KEY_TEMP               = Tags.getInstance(0x7F48);
		public static final Tags CARDHOLDER_PUBLIC_KEY                     = Tags.getInstance(0x5F49);
		public static final Tags CARDHOLDER_PUBLIC_KEY_TEMP                = Tags.getInstance(0x7F49);
		public static final Tags CARDHOLDER_RELATED_DATA                   = Tags.getInstance(0x65);
		public static final Tags CARDHOLDER_REQ_EXCLUDED_FEATURES          = Tags.getInstance(0x7F23);
		public static final Tags CARDHOLDER_REQ_INCLUDED_FEATURES          = Tags.getInstance(0x7F22);
		public static final Tags CARDHOLDER_SIGNATURE_IMAGE                = Tags.getInstance(0x5F43);
		public static final Tags CERT_CONTENT                              = Tags.getInstance(0x5F4E);
		public static final Tags CERT_CONTENT_TEMP                         = Tags.getInstance(0x7F4E);
		public static final Tags CERT_HOLDER_AUTHORISATION                 = Tags.getInstance(0x5F4C);
		public static final Tags CHANNEL_SECURITY_ATTR                     = Tags.getInstance(0x8E);
		public static final Tags COEXISTENT_TAA                            = Tags.getInstance(0x79);
		public static final Tags COMMAND_TO_PERFORM                        = Tags.getInstance(0x52);
		public static final Tags COMPATIBLE_TAA                            = Tags.getInstance(0x78);
		public static final Tags COUNTRY_CODE                              = Tags.getInstance(0x5F28);
		public static final Tags COUNTRY_CODE_2                            = Tags.getInstance(0x5F55);
		public static final Tags COUNTRY_CODE_3                            = Tags.getInstance(0x5F56);
		public static final Tags COUNTTY_CODE_AND_NATIONAL_DATA            = Tags.getInstance(0x41);
		public static final Tags CRYPT_MECHANISM_ID_TEMP                   = Tags.getInstance(0xAC);
		public static final Tags CURRENCY_CODE                             = Tags.getInstance(0x5F2A);
		public static final Tags CURRENCY_EXPONENT                         = Tags.getInstance(0x5F36);
		public static final Tags DATA_OBJECT_PAIR_TEMP                     = Tags.getInstance(0xA2);
		public static final Tags DATE_OF_BIRTH                             = Tags.getInstance(0x5F2B);
		public static final Tags DF_NAME                                   = Tags.getInstance(0x84);
		public static final Tags DIGITAL_SIGNATURE                         = Tags.getInstance(0x5F3D);
		public static final Tags DIGITAL_SIGNATURE_BLOCK                   = Tags.getInstance(0x7F3D);
		public static final Tags DISC_DATA                                 = Tags.getInstance(0x53);
		public static final Tags DISC_DATA_OBJECTS                         = Tags.getInstance(0x73);
		public static final Tags DISPLAY_CONTROL                           = Tags.getInstance(0x7F20);
		public static final Tags DISPLAY_INDICATORS_83                     = Tags.getInstance(0x83);
		public static final Tags DISPLAY_INDICATORS_84                     = Tags.getInstance(0x84);
		public static final Tags DISPLAY_MESSAGE                           = Tags.getInstance(0x5F45);
		public static final Tags DYNAMIC_AUTH_TEMP                         = Tags.getInstance(0x7C);
		public static final Tags DYNAMIC_EXTERNAL_AUTH                     = Tags.getInstance(0x5F3B);
		public static final Tags DYNAMIC_INTERNAL_AUTH                     = Tags.getInstance(0x5F3A);
		public static final Tags DYNAMIC_MUTUAL_AUTH                       = Tags.getInstance(0x5F3C);
		public static final Tags ELEMENT_LIST                              = Tags.getInstance(0x5F41);
		public static final Tags EXTENDED_HEADER_LIST                      = Tags.getInstance(0x4D);
		public static final Tags FCI_TEMP                                  = Tags.getInstance(0x6F);
		public static final Tags FCP_TEMP                                  = Tags.getInstance(0x62);
		public static final Tags FILE_DESCRIPTOR                           = Tags.getInstance(0x82);
		public static final Tags FILE_ID                                   = Tags.getInstance(0x83);
		public static final Tags FILE_ID_EXTENSION_OF_FCI                  = Tags.getInstance(0x87);
		public static final Tags FILE_ID_SECURITY_ENV                      = Tags.getInstance(0x8D);
		public static final Tags FILE_REF                                  = Tags.getInstance(0x51);
		public static final Tags FMD_TEMP                                  = Tags.getInstance(0x64);
		public static final Tags HEADER_LIST                               = Tags.getInstance(0x5D);
		public static final Tags HISTORIC_BYTES                            = Tags.getInstance(0x5F52);
		public static final Tags IBAN                                      = Tags.getInstance(0x5F53);
		public static final Tags IC_MANUFACTURER_ID                        = Tags.getInstance(0x5F4D);
		public static final Tags IIN                                       = Tags.getInstance(0x42);
		public static final Tags INITIAL_ACCESS_DATA                       = Tags.getInstance(0x44);
		public static final Tags INTERCHANGE_CONTROL                       = Tags.getInstance(0x5F27);
		public static final Tags INTERCHANGE_PROFILE                       = Tags.getInstance(0x5F29);
		public static final Tags LANG_PREF                                 = Tags.getInstance(0x5F2D);
		public static final Tags LIFE_CYCLE_STATUS                         = Tags.getInstance(0x8A);
		public static final Tags LOGIN_DATA                                = Tags.getInstance(0x5E);
		public static final Tags LOGIN_TEMP                                = Tags.getInstance(0x6A);
		public static final Tags MESSAGE_REF                               = Tags.getInstance(0x5F47);
		public static final Tags NAME                                      = Tags.getInstance(0x5B);
		public static final Tags NUMBER                                    = Tags.getInstance(0x81);
		public static final Tags OBJECT_ID                                 = Tags.getInstance(0x06);
		public static final Tags OFFSET_DATA_OBJECT                        = Tags.getInstance(0x54);
		public static final Tags PAN                                       = Tags.getInstance(0x5A);
		public static final Tags PIN_USAGE_POLICY                          = Tags.getInstance(0x5F2F);
		public static final Tags PRE_ISSUING_DATA                          = Tags.getInstance(0x46);
		public static final Tags PROP_INFO                                 = Tags.getInstance(0xA5);
		public static final Tags PROP_INFO_NOT_BER                         = Tags.getInstance(0x85);
		public static final Tags PUBLIC_KEY_OF_CERT_AUTHORITY              = Tags.getInstance(0x5F4A);
		public static final Tags QUALIFIED_NAME                            = Tags.getInstance(0x6B);
		public static final Tags QUALIFIER                                 = Tags.getInstance(0x80);
		public static final Tags SECURE_MESSAGE_TEMP                       = Tags.getInstance(0x7D);
		public static final Tags SECURITY_ATTR_COMPACT                     = Tags.getInstance(0x8C);
		public static final Tags SECURITY_ATTR_DATA_OBJECT                 = Tags.getInstance(0xA0);
		public static final Tags SECURITY_ATTR_EXPANDED                    = Tags.getInstance(0x8B);
		public static final Tags SECURITY_ATTR_EXPANDED_TEMP               = Tags.getInstance(0xAB);
		public static final Tags SECURITY_ATTR_PROP                        = Tags.getInstance(0x86);
		public static final Tags SECURITY_ATTR_PROP_TEMP                   = Tags.getInstance(0xA1);
		public static final Tags SECURITY_ENV_TEMP                         = Tags.getInstance(0x7B);
		public static final Tags SECURITY_SUPPORT_TEMP                     = Tags.getInstance(0x7A);
		public static final Tags SERVICE_CODE                              = Tags.getInstance(0x5F30);
		public static final Tags SEX                                       = Tags.getInstance(0x5F35);
		public static final Tags SFI                                       = Tags.getInstance(0x88);
		public static final Tags SIZE_EXCLUDE_STRUCTURAL_INFO              = Tags.getInstance(0x80);
		public static final Tags SIZE_INCLUDE_STRUCTURAL_INFO              = Tags.getInstance(0x81);
		public static final Tags SPECIAL_USER_REQUIREMENTS                 = Tags.getInstance(0x68);
		public static final Tags STATIC_INTERNAL_AUTH                      = Tags.getInstance(0x5F37);
		public static final Tags STATIC_INTERNAL_AUTH_FIRST_DATA           = Tags.getInstance(0x5F38);
		public static final Tags STATIC_INTERNAL_AUTH_SECOND_DATA          = Tags.getInstance(0x5F39);
		public static final Tags STATUS_INFO                               = Tags.getInstance(0x48);
		public static final Tags TAG_LIST                                  = Tags.getInstance(0x5C);
		public static final Tags TEMP_70                                   = Tags.getInstance(0x70);
		public static final Tags TEMP_71                                   = Tags.getInstance(0x71);
		public static final Tags TEMP_72                                   = Tags.getInstance(0x72);
		public static final Tags TEMP_74                                   = Tags.getInstance(0x74);
		public static final Tags TEMP_75                                   = Tags.getInstance(0x75);
		public static final Tags TEMP_76                                   = Tags.getInstance(0x76);
		public static final Tags TEMP_77                                   = Tags.getInstance(0x77);
		public static final Tags TEMP_7E                                   = Tags.getInstance(0x7E);
		public static final Tags TEXT                                      = Tags.getInstance(0x82);
		public static final Tags TIMER                                     = Tags.getInstance(0x5F46);
		public static final Tags TRACK1_APPL                               = Tags.getInstance(0x56);
		public static final Tags TRACK1_CARD                               = Tags.getInstance(0x5F21);
		public static final Tags TRACK2_APPL                               = Tags.getInstance(0x57);
		public static final Tags TRACK2_CARD                               = Tags.getInstance(0x5F22);
		public static final Tags TRACK3_APPL                               = Tags.getInstance(0x58);
		public static final Tags TRACK3_CARD                               = Tags.getInstance(0x5F23);
		public static final Tags TRANSACTION_COUNTER                       = Tags.getInstance(0x5F32);
		public static final Tags TRANSACTION_DATE                          = Tags.getInstance(0x5F33);
		public static final Tags URL                                       = Tags.getInstance(0x5F50);
		public static final Tags WRAPPER                                   = Tags.getInstance(0x63);
	}




	// 06                    ISO::OBJECT-ID
	// 41                    ISO::COUNTTY-CODE-AND-NATIONAL-DATA
	// 42                    ISO::IIN
	// 43                    ISO::CARD-SERVICE-DATA
	// 44                    ISO::INITIAL-ACCESS-DATA
	// 45                    ISO::CARD-ISSUERS-DATA
	// 46                    ISO::PRE-ISSUING-DATA
	// 47                    ISO::CARD-CAPABILITIES
	// 48                    ISO::STATUS-INFO
	// 49                    ISO::AFI
	// 4D                    ISO::EXTENDED-HEADER-LIST
	// 4F                    ISO::APPL-ID
	// 53                    ISO::DISC-DATA
	// 54                    ISO::OFFSET-DATA-OBJECT
	// 5C                    ISO::TAG-LIST
	// 5D                    ISO::HEADER-LIST
	// 5F41                  ISO::ELEMENT-LIST
	// 5F4C                  ISO::CERT-HOLDER-AUTHORISATION
	// 5F4D                  ISO::IC-MANUFACTURER-ID
	// 5F50                  ISO::URL
	// 5F51                  ISO::ANSWER-TO-RESET
	// 5F52                  ISO::HISTORIC-BYTES
	// 5F57                  EMV::ACCOUNT-TYPE
	// 61                    ISO::APPL-TEMP
	// 61   4F               ISO::APPL-ID
	// 61   50               ISO::APPL-LABEL
	// 61   51               ISO::FILE-REF
	// 61   52               ISO::COMMAND-TO-PERFORM
	// 62                    ISO::FCP-TEMP
	// 62   80               ISO::SIZE-EXCLUDE-STRUCTURAL-INFO
	// 62   81               ISO::SIZE-INCLUDE-STRUCTURAL-INFO
	// 62   82               ISO::FILE-DESCRIPTOR
	// 62   83               ISO::FILE-ID
	// 62   84               ISO::DF-NAME
	// 62   85               ISO::PROP-INFO-NOT-BER
	// 62   86               ISO::SECURITY-ATTR-PROP
	// 62   87               ISO::FILE-ID-EXTENSION-OF-FCI
	// 62   88               ISO::SFI
	// 62   8A               ISO::LIFE-CYCLE-STATUS
	// 62   8B               ISO::SECURITY-ATTR-EXPANDED
	// 62   8C               ISO::SECURITY-ATTR-COMPACT
	// 62   8D               ISO::FILE-ID-SECURITY-ENV
	// 62   8E               ISO::CHANNEL-SECURITY-ATTR
	// 62   A0               ISO::SECURITY-ATTR-DATA-OBJECT
	// 62   A1               ISO::SECURITY-ATTR-PROP-TEMP
	// 62   A2               ISO::DATA-OBJECT-PAIR-TEMP
	// 62   A5               ISO::PROP-INFO
	// 62   AB               ISO::SECURITY-ATTR-EXPANDED-TEMP
	// 62   AC               ISO::CRYPT-MECHANISM-ID-TEMP
	// 63                    ISO::WRAPPER
	// 64                    ISO::FMD-TEMP
	// 65                    ISO::CARDHOLDER-RELATED-DATA
	// 65   5B               ISO::NAME
	// 65   5F20             ISO::CARDHOLDER-NAME
	// 65   5F2B             ISO::DATE-OF-BIRTH
	// 65   5F2C             ISO::CARDHOLDER-NATIONALITY
	// 65   5F2D             ISO::LANG-PREF
	// 65   5F2E             ISO::CARDHOLDER-BIO-DATA
	// 65   5F35             ISO::SEX
	// 65   5F42             ISO::ADDRESS
	// 65   5F48             ISO::CARDHOLDER-PRIVATE-KEY
	// 65   5F49             ISO::CARDHOLDER-PUBLIC-KEY
	// 65   5F4A             ISO::PUBLIC-KEY-OF-CERT-AUTHORITY
	// 65   68               ISO::SPECIAL-USER-REQUIREMENTS
	// 65   6B               ISO::QUALIFIED-NAME
	// 65   6C               ISO::CARDHOLDER-IMAGE-TEMP
	// 65   6C   5F40        ISO::CARDHOLDER-PORTRATE-IMAGE
	// 65   6C   5F43        ISO::CARDHOLDER-SIGNATURE-IMAGE
	// 65   7F21             ISO::CARDHOLDER-CERT
	// 65   7F21 5F4E        ISO::CERT-CONTENT
	// 65   7F22             ISO::CARDHOLDER-REQ-INCLUDED-FEATURES
	// 65   7F23             ISO::CARDHOLDER-REQ-EXCLUDED-FEATURES
	// 65   7F48             ISO::CARDHOLDER-PRIVATE-KEY-TEMP
	// 65   7F49             ISO::CARDHOLDER-PUBLIC-KEY-TEMP
	// 66                    ISO::CARD-DATA
	// 66   41               ISO::COUNTTY-CODE-AND-NATIONAL-DATA
	// 66   44               ISO::INITIAL-ACCESS-DATA
	// 66   45               ISO::CARD-ISSUERS-DATA
	// 66   47               ISO::CARD-CAPABILITIES
	// 66   59               ISO::CARD-EXPIRATION-DATE
	// 66   5F21             ISO::TRACK1-CARD
	// 66   5F22             ISO::TRACK2-CARD
	// 66   5F23             ISO::TRACK3-CARD
	// 66   5F26             ISO::CARD-EFFECTIVE-DATE
	// 66   5F27             ISO::INTERCHANGE-CONTROL
	// 66   5F28             ISO::COUNTRY-CODE
	// 66   5F34             ISO::CARD-SEQ-NUMBER
	// 66   5F45             ISO::DISPLAY-MESSAGE
	// 66   5F46             ISO::TIMER
	// 66   5F47             ISO::MESSAGE-REF
	// 66   5F55             ISO::COUNTRY-CODE-2
	// 66   5F56             ISO::COUNTRY-CODE-3
	// 66   67               ISO::AUTH-DATA
	// 66   67   5F29        ISO::INTERCHANGE-PROFILE
	// 66   67   5F37        ISO::STATIC-INTERNAL-AUTH
	// 66   67   5F38        ISO::STATIC-INTERNAL-AUTH-FIRST-DATA
	// 66   67   5F39        ISO::STATIC-INTERNAL-AUTH-SECOND-DATA
	// 66   67   5F3A        ISO::DYNAMIC-INTERNAL-AUTH
	// 66   67   5F3B        ISO::DYNAMIC-EXTERNAL-AUTH
	// 66   67   5F3C        ISO::DYNAMIC-MUTUAL-AUTH
	// 66   73   06          GP::TAA
	// 66   73   60          GP::CARD-MANAGEMENT-TYPE
	// 66   73   63          GP::CARD-ID-SCHEME
	// 66   73   64          GP::SECURE-CHANNEL-PROTOCOL
	// 66   73   65          GP::CARD-CONFIG-DETAIL
	// 66   73   66          GP::CARD-CHIP-DETAIL
	// 66   73   67          GP::ISD-TRUST-POINT-CERT
	// 66   73   68          GP::ISD-TRUST-CERT
	// 66   7F20             ISO::DISPLAY-CONTROL
	// 6E                    ISO::APPL-RELATED-DATA
	// 6E   4F               ISO::APPL-ID
	// 6E   50               ISO::APPL-LABEL
	// 6E   56               ISO::TRACK1-APPL
	// 6E   57               ISO::TRACK2-APPL
	// 6E   58               ISO::TRACK3-APPL
	// 6E   5A               ISO::PAN
	// 6E   5E               ISO::LOGIN-DATA
	// 6E   5F24             ISO::APPL-EXPIRATION-DATE
	// 6E   5F25             ISO::APPL-EFFECTIVE-DATE
	// 6E   5F2A             ISO::CURRENCY-CODE
	// 6E   5F2F             ISO::PIN-USAGE-POLICY
	// 6E   5F30             ISO::SERVICE-CODE
	// 6E   5F32             ISO::TRANSACTION-COUNTER
	// 6E   5F33             ISO::TRANSACTION-DATE
	// 6E   5F36             ISO::CURRENCY-EXPONENT
	// 6E   5F53             ISO::IBAN
	// 6E   5F54             ISO::BIC
	// 6E   5F57             ISO::ACCOUNT-TYPE
	// 6E   6A               ISO::LOGIN-TEMP
	// 6E   6A   80          ISO::QUALIFIER
	// 6E   6A   81          ISO::NUMBER
	// 6E   6A   82          ISO::TEXT
	// 6E   6A   83          ISO::DISPLAY-INDICATORS-83
	// 6E   6A   84          ISO::DISPLAY-INDICATORS-84
	// 6E   6D               ISO::APPL-IMAGE-TEMP
	// 6E   6D   5F44        ISO::APPL-IMAGE
	// 6F                    EMV::FCI-TEMP
	// 6F                    ISO::FCI-TEMP
	// 6F   80               ISO::SIZE-EXCLUDE-STRUCTURAL-INFO
	// 6F   81               ISO::SIZE-INCLUDE-STRUCTURAL-INFO
	// 6F   82               ISO::FILE-DESCRIPTOR
	// 6F   83               ISO::FILE-ID
	// 6F   84               EMV::DF-NAME
	// 6F   84               ISO::DF-NAME
	// 6F   85               ISO::PROP-INFO-NOT-BER
	// 6F   86               ISO::SECURITY-ATTR-PROP
	// 6F   87               ISO::FILE-ID-EXTENSION-OF-FCI
	// 6F   88               ISO::SFI
	// 6F   8A               ISO::LIFE-CYCLE-STATUS
	// 6F   8B               ISO::SECURITY-ATTR-EXPANDED
	// 6F   8C               ISO::SECURITY-ATTR-COMPACT
	// 6F   8D               ISO::FILE-ID-SECURITY-ENV
	// 6F   8E               ISO::CHANNEL-SECURITY-ATTR
	// 6F   A0               ISO::SECURITY-ATTR-DATA-OBJECT
	// 6F   A1               ISO::SECURITY-ATTR-PROP-TEMP
	// 6F   A2               ISO::DATA-OBJECT-PAIR-TEMP
	// 6F   A5               EMV::FCI-PROP-TEMP
	// 6F   A5               ISO::PROP-INFO
	// 6F   A5   50          EMV::APPL-LABEL
	// 6F   A5   5F2D        EMV::LANG-PREF
	// 6F   A5   87          EMV::APPL-PRIORITY-INDICATOR
	// 6F   A5   88          EMV::SFI
	// 6F   A5   9F11        EMV::ISSUER-CODE-TABLE-INDEX
	// 6F   A5   9F12        EMV::APPL-PREFERRED-NAME
	// 6F   A5   9F38        EMV::PDOL
	// 6F   A5   9F65        GP::MAX-LENGTH-DATA-FIELD
	// 6F   A5   9F6E        GP::APPL-PRODUCT-LIFE-CYCLE-DATA
	// 6F   A5   BF0C        EMV::FCI-ISSUER-DISC-DATA
	// 6F   A5   BF0C 42     EMV::IIN
	// 6F   A5   BF0C 5F50   EMV::ISSUER-URL
	// 6F   A5   BF0C 5F53   EMV::IBAN
	// 6F   A5   BF0C 5F54   EMV::BIC
	// 6F   A5   BF0C 5F55   EMV::ISSUER-COUNTRY-CODE2
	// 6F   A5   BF0C 5F56   EMV::ISSUER-COUNTRY-CODE3
	// 6F   A5   BF0C 9F4D   EMV::LOG-ENTRY
	// 6F   AB               ISO::SECURITY-ATTR-EXPANDED-TEMP
	// 6F   AC               ISO::CRYPT-MECHANISM-ID-TEMP
	// 70                    EMV::READ-RECORD-RESPONSE-MESSAGE-TEMP
	// 70                    ISO::TEMP-70
	// 70   5A               EMV::APPL-PAN
	// 70   5F24             EMV::APPL-EXPIRATION-DATE
	// 70   5F25             EMV::APPL-EFFECTIVE-DATE
	// 70   61               EMV::APPL-TEMP
	// 70   61   4F          EMV::ADF-NAME
	// 70   61   50          EMV::APPL-LABEL
	// 70   61   73          EMV::DIRECTORY-DISC-TEMP
	// 70   61   73   42     EMV::IIN
	// 70   61   73   5F50   EMV::ISSUER-URL
	// 70   61   73   5F53   EMV::IBAN
	// 70   61   73   5F54   EMV::BIC
	// 70   61   73   5F55   EMV::ISSUER-COUNTRY-CODE2
	// 70   61   73   5F56   EMV::ISSUER-COUNTRY-CODE3
	// 70   61   73   9F4D   EMV::LOG-ENTRY
	// 70   61   87          EMV::APPL-PRIORITY-INDICATOR
	// 70   61   9D          EMV::DDF-NAME
	// 70   61   9F12        EMV::APPL-PREFERRED-NAME
	// 70   9F05             EMV::APPL-DISC-DATA
	// 70   9F42             EMV::APPL-CURRENCY-CODE
	// 70   9F44             EMV::APPL-CURRENCY-EXPONENT
	// 71                    ISO::TEMP-71
	// 72                    ISO::TEMP-72
	// 73                    ISO::DISC-DATA-OBJECTS
	// 73   06               GP::TAA
	// 73   60               GP::CARD-MANAGEMENT-TYPE
	// 73   63               GP::CARD-ID-SCHEME
	// 73   64               GP::SECURE-CHANNEL-PROTOCOL
	// 73   65               GP::CARD-CONFIG-DETAIL
	// 73   66               GP::CARD-CHIP-DETAIL
	// 73   67               GP::ISD-TRUST-POINT-CERT
	// 73   68               GP::ISD-TRUST-CERT
	// 74                    ISO::TEMP-74
	// 75                    ISO::TEMP-75
	// 76                    ISO::TEMP-76
	// 77                    EMV::RESPONSE-MESSAGE-TEMP-2
	// 77                    ISO::TEMP-77
	// 77   5A               EMV::APPL-PAN
	// 77   5F24             EMV::APPL-EXPIRATION-DATE
	// 77   5F25             EMV::APPL-EFFECTIVE-DATE
	// 77   82               EMV::APPL-INTERCHANGE-PROFILE
	// 77   94               EMV::AFL
	// 77   9F05             EMV::APPL-DISC-DATA
	// 77   9F26             EMV::APPL-CRYPTOGRAM
	// 77   9F42             EMV::APPL-CURRENCY-CODE
	// 77   9F44             EMV::APPL-CURRENCY-EXPONENT
	// 78                    ISO::COMPATIBLE-TAA
	// 79                    ISO::COEXISTENT-TAA
	// 7A                    ISO::SECURITY-SUPPORT-TEMP
	// 7B                    ISO::SECURITY-ENV-TEMP
	// 7C                    ISO::DYNAMIC-AUTH-TEMP
	// 7D                    ISO::SECURE-MESSAGE-TEMP
	// 7E                    ISO::TEMP-7E
	// 7F3D                  ISO::DIGITAL-SIGNATURE-BLOCK
	// 7F3D 5F3D             ISO::DIGITAL-SIGNATURE
	// 7F4E                  ISO::CERT-CONTENT-TEMP
	// 7F60                  ISO::BIO-INFO-TEMP
	// 7F60 7F2E             ISO::BIO-DATA-TEMP
	// 7F61                  ISO::BIO-INFO-GROUP-TEMP
	// 80                    EMV::RESPONSE-MESSAGE-TEMP-1
	// 80   82               EMV::APPL-INTERCHANGE-PROFILE
	// 80   94               EMV::AFL
	// 80   9F26             EMV::APPL-CRYPTOGRAM
	// 81                    EMV::AMOUNT-AUTHORISED-BINARY
	// 9F01                  EMV::ACQUIRER-ID
	// 9F02                  EMV::AMOUNT-AUTHORISED-NUMERIC
	// 9F03                  EMV::AMOUNT-OTHER-NUMERIC
	// 9F04                  EMV::AMOUNT-OTHER-BINARY
	// 9F06                  EMV::AID-TERMINAL
	// 9F3A                  EMV::AMOUNT-REFERENCE-CURRENCY
	// 9F40                  EMV::ADDITIONAL-TERMINAL-CAPABILITIES
	// 9F7F                  GP::CPLC
	// E0                    GP::KEY-INFO-TEMP
	// E0   C0               GP::KEY-INFO-DATA
	// E3                    GP::REGISTRY-RELATED-DATA
	// E3   84               GP::EXECUTABLE-MODULE-AID
	// E3   9F70             GP::LIEF-CYCLE-STATUS
	// E3   C4               GP::APPL-EXECUTABLE-LOAD-FILE-AID
	// E3   C5               GP::PRIVILEGES
	// E3   CC               GP::ASSOC-SECURITY-DOMAIN-AID
	// E3   CE               GP::EXECUTABLE-LOAD-FILE-VERSION-NUMBER
}
