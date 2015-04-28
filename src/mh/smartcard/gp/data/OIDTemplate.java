package mh.smartcard.gp.data;

import java.util.ArrayList;
import java.util.List;

import mh.smartcard.UnexpectedException;
import mh.smartcard.iso.BER;
import mh.smartcard.iso.Data;
import mh.smartcard.iso.TLV;
import mh.smartcard.iso.Tags;
import mh.smartcard.iso.data.OID;
import mh.smartcard.util.TagList;

@Data.Info(name = OIDTemplate.FORMAT)
public class OIDTemplate extends Data {
	public static final String FORMAT = "GP::OID-TEMP";

	public final OID oid;

	public OIDTemplate(String name, TLV tlv) {
		super(name, tlv);
		//
		if (tlv instanceof BER) {
			BER ber = (BER)tlv;
			if (ber.isConstructed) {
				List<BER> children = new ArrayList<>();
				for(BER child: ber.children) {
					children.add(child);
				}
				if (children.size() != 1) {
					String message = String.format("Number of child must be one %s %s", name, tlv.toString());
					logger.error(message);
					throw new UnexpectedException(message);
				}
				BER child = children.get(0);
				if (child.tag.getLast() != TagList.ISO.OBJECT_ID.getLast()) {
					String message = String.format("Child must be OBEJECT_ID %s %s", name, child.toString());
					logger.error(message);
					throw new UnexpectedException(message);
				}
				oid = new OID(FORMAT, child);
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
		return String.format("[%s %s %s]", Tags.toTagString(tlv.tag.getLast()), name, oid.getValue());
	}
}
