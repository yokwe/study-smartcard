package mh.smartcard.app;

import java.util.List;

import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;

import mh.smartcard.UnexpectedException;
import mh.smartcard.data.Hex;
import mh.smartcard.gp.CPLC;
import mh.smartcard.iso.BER;
import mh.smartcard.iso.Command;
import mh.smartcard.iso.Data;
import mh.smartcard.iso.Element;
import mh.smartcard.iso.HistoricalBytes;
import mh.smartcard.iso.Response;
import mh.smartcard.iso.command.GetData;
import mh.smartcard.iso.command.Select;
import mh.smartcard.iso.command.Select.Occurence;
import mh.smartcard.iso.command.Select.Returns;
import mh.smartcard.multos.MultosData;
import mh.smartcard.multos.command.GetMultosData;
import mh.smartcard.util.AIDList;
import mh.smartcard.util.JCOP;
import mh.smartcard.util.TagList;
import mh.smartcard.util.Util;
import mh.smartcard.yubico.YubicoNeo;
import mh.smartcard.yubico.command.Request;
import mh.smartcard.yubico.command.Request.Operation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class T002 {
	private static final Logger logger = LoggerFactory.getLogger(T002.class);

	public static void main(String[] args) {
		Element.init();

		List<CardTerminal> cardTerminalList = Util.getCardPresentTerminalList();
		if (cardTerminalList.size() == 0) {
			logger.info("No card present in card terminal");
			return;
		}
		
		logger.info("cardTerminalList = {}", cardTerminalList.size());
		for(CardTerminal cardTermial: cardTerminalList) {
			logger.info("====");
			logger.info("CardTerminal {}", cardTermial.getName());
			
			try {
				final Card card = cardTermial.connect("*");
				logger.info("Protocol {}", card.getProtocol());
				
				logger.info("ATR {}", Hex.toString(card.getATR().getBytes()));
				
				HistoricalBytes historicalBytes = HistoricalBytes.getInstance(card.getATR().getHistoricalBytes());
				logger.info("HistoricalBytes {}", historicalBytes.toString());
				
				final CardChannel channel = card.getBasicChannel();
				
				// Hardware
				//   CPLC
				{
					Command command           = GetData.getInstance(TagList.GP.CPLC);
					ResponseAPDU responseAPDU = channel.transmit(new CommandAPDU(command.bytes));
					Response     response     = new Response(responseAPDU.getBytes());

					if (response.status.value == 0x9000) {
						CPLC cplc = new CPLC(response.data);
						logger.info("CPLC {}", cplc.toString());
					}
				}

				// Operating System
				//   Check JCOP
				{
					Command command           = Select.getInstance(AIDList.JCOP_IDENTITY, Occurence.FIRST, Returns.NONE);
					ResponseAPDU responseAPDU = channel.transmit(new CommandAPDU(command.bytes));
					Response     response     = new Response(responseAPDU.getBytes());
					
					if (response.status.value == 0x6A82 && response.data.length != 0) {
						JCOP.Identity identity = new JCOP.Identity(response.data);
						logger.info("JCOP {}", identity.toString());
					}
				}
				//   Check MULTOS
				{
					Command command           = GetMultosData.getInstance();
					ResponseAPDU responseAPDU = channel.transmit(new CommandAPDU(command.bytes));
					Response     response     = new Response(responseAPDU.getBytes());
					
					if (response.status.value == 0x9000) {
						MultosData multosData = new MultosData(response.data);
						logger.info("MULTOS-DATA {}", multosData.toString());
					}
				}
				
				// Standard
				//   GlobalPlatform
				{
					Command      command      = GetData.getInstance(TagList.ISO.CARD_DATA);
					ResponseAPDU responseAPDU = channel.transmit(new CommandAPDU(command.bytes));
					Response     response     = new Response(responseAPDU.getBytes());
					
					if (response.status.value == 0x9000) {
						BER ber = BER.getInstance(TagList.ISO.CARD_DATA, response.data);
						if (ber.bytes.length == response.data.length) {
							Data data = Element.getData(ber);
							logger.info("CARD-DATA {}", data.toString());
						}
					}
				}
				{
					Command      command      = Select.getInstance(AIDList.OPEN_PLATFORM_CARD_MANAGER, Returns.FCI);
					ResponseAPDU responseAPDU = channel.transmit(new CommandAPDU(command.bytes));
					Response     response     = new Response(responseAPDU.getBytes());
					if (response.status.value == 0x9000) {
						BER ber = BER.getInstance(response.data);
						Data data = Element.getData(ber);
						logger.info("GLOBAL-PLATFORM-CARD-MANAGER {}", data.toString());
					}
				}

				// Yubico
				//   Need to select application YUBICO_NEO to use yubico custom instruction
				{
					Command      command      = Select.getInstance(AIDList.YUBICO_NEO, Returns.FCI);
					ResponseAPDU responseAPDU = channel.transmit(new CommandAPDU(command.bytes));
					Response     response     = new Response(responseAPDU.getBytes());
					if (response.status.value == 0x9000) {
						// response.data is proprietary format data (not BER)
						YubicoNeo yubicoNeo = new YubicoNeo(response.data);
						logger.info("YUBICO-NEO {}", yubicoNeo);
						
						{
							Command      command2      = Request.getInstance(Operation.GetSerial);
							ResponseAPDU responseAPDU2 = channel.transmit(new CommandAPDU(command2.bytes));
							Response     response2     = new Response(responseAPDU2.getBytes());
							
							if (response2.status.value == 0x9000) {
								logger.info("YUBICO-SERIAL {}", Util.toInteger(response2.data));
							}
						}
					}
				}
				
				// Try known AID
				{
					for(String number: AIDList.getNumberList()) {
						Command      command      = Select.getInstance(Util.fromHexString(number), Returns.FCI);
						ResponseAPDU responseAPDU = channel.transmit(new CommandAPDU(command.bytes));
						Response     response     = new Response(responseAPDU.getBytes());
						//
						if (response.data.length != 0) {
							String name = AIDList.getName(Util.fromHexString(number));
							logger.info(String.format("AID %04X  %-30s %s", response.status.value, name, number));
							try {
								BER ber = BER.getInstance(response.data);
								Data data = Element.getData(ber);
								logger.info("data  {}", data.toString());
							} catch (UnexpectedException e) {
								//logger.info("UnexpectedException", e);
								logger.info("data  {}", Hex.toString(response.data));
							}
						}
					}
				}
			} catch (CardException e) {
				logger.error("CardException", e);
			}
		}
		logger.info("====");
	}
}
