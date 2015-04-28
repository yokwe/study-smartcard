package mh.smartcard.data;

import java.util.ArrayList;
import java.util.List;

import mh.smartcard.UnexpectedException;
import mh.smartcard.iso.BER;
import mh.smartcard.iso.Data;
import mh.smartcard.iso.Element;
import mh.smartcard.iso.TLV;
import mh.smartcard.iso.Tags;
import mh.smartcard.util.Util;

@Data.Info(name = Template.FORMAT)
public class Template extends Data {
	public static final String FORMAT = "TEMP";

	private List<Data> children = new ArrayList<>();

	public Template(String name, TLV tlv) {
		super(name, tlv);
		//
		if (tlv instanceof BER) {
			BER ber = (BER)tlv;
			if (ber.isConstructed) {
				for(BER child: ber.children) {
					children.add(Element.getData(child));
				}
			} else {
				String message = String.format("BER is not constructed %s %s", name, tlv.toString());
				logger.error(message);
				throw new UnexpectedException(message);
			}
		} else {
			String message = String.format("Must be instance of BER %s %s", name, tlv.toString());
			logger.error(message);
			throw new UnexpectedException(message);
		}
	}

	@Override
	public String toString() {
		List<String> elements = new ArrayList<>();
		for(Data child: children) {
			elements.add(child.toString());
		}
		return String.format("[%s %s %s]", Tags.toTagString(tlv.tag.getLast()), name, Util.join(elements));
	}
}
