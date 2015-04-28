package mh.smartcard.iso;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class Data {
	protected static final Logger logger = LoggerFactory.getLogger(Data.class);

	@Retention(RetentionPolicy.RUNTIME)
	@Target(ElementType.TYPE)
	public @interface Info {
		String name();
	}
	
	public final String name;
	public final TLV    tlv;
	
	protected Data(String name, TLV tlv) {
		this.name   = name;
		this.tlv    = tlv;
	}
	
	@Override
	public abstract String toString();
}
