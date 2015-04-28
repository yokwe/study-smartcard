package mh.smartcard.iso;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mh.smartcard.UnexpectedException;
import mh.smartcard.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Tags implements Comparable<Tags> {
	private static final Logger logger = LoggerFactory.getLogger(Tags.class);

	public static final String EMPTY            = "-";
	public static final String SIMPLE           = "([0-9A-F]{4}|[0-9A-F]{2})";
	public static final String COMPLEX          = Util.group(SIMPLE  + Util.groupStar(Util.SPACE_PLUS + SIMPLE));
	public static final String COMPLEX_OR_EMPTY = Util.groupOr(Util.group(SIMPLE  + Util.groupStar(Util.SPACE_PLUS + SIMPLE)), EMPTY);

	public static final String LIST             = Util.group(COMPLEX_OR_EMPTY + Util.groupStar(Util.SPACE_STAR + "," + Util.SPACE_STAR + COMPLEX_OR_EMPTY));

	public  final List<Integer> valueList;
	private final String        stringValue;
	private final boolean       isComplex;
	private final boolean       isEmpty;
	
	public static String toTagString(int value) {
		return String.format((value < 0x100 ? "%02X" : "%04X"), value);
	}
	private Tags (List<Integer> values) {
		valueList = Collections.unmodifiableList(values);
		
		if (valueList.isEmpty()) {
			stringValue = EMPTY;
		} else {
			StringBuilder sb = new StringBuilder();
			for(int value: valueList) {
				sb.append(String.format(" %-4s", toTagString(value)));
			}
			stringValue = sb.toString().trim();
		}
		
		isComplex = (2 <= valueList.size());
		isEmpty   = valueList.isEmpty();
	}
	
	private static final Tags TAG_EMPTY = new Tags(new ArrayList<Integer>());
	public static Tags getInstance(List<Integer> values) {
		if (values.isEmpty()) return TAG_EMPTY;
		return new Tags(values);
	}
	public static Tags getInstance(int value) {
		return Tags.getInstance(new ArrayList<>(Arrays.asList(value)));
	}
	public static Tags getInstance(String complexTag) {
		final Matcher matcherComplexOrEmtpyTag = Pattern.compile(COMPLEX_OR_EMPTY).matcher("");
		final Matcher matcherSimpleTag  = Pattern.compile(SIMPLE).matcher("");

		List<Integer> tagList = new ArrayList<>();
		matcherComplexOrEmtpyTag.reset(complexTag);
		if (matcherComplexOrEmtpyTag.find()) {
			final String compelexTag = matcherComplexOrEmtpyTag.group();
			if (complexTag.equals(EMPTY)) {
				// Intentionally do nothing
			} else {
				matcherSimpleTag.reset(compelexTag);
				while(matcherSimpleTag.find()) {
					final String simpleTagString = matcherSimpleTag.group();
					tagList.add(Integer.parseUnsignedInt(simpleTagString, 16) & 0xFFFF);
				}
				// sanity check
				if (matcherComplexOrEmtpyTag.find()) {
					String message = String.format("Must be only one complex tag. complexTag = %s!", complexTag);
					logger.error(message);
					throw new UnexpectedException(message);
				}
			}
		}
		return Tags.getInstance(tagList);
	}
	public static Tags getInstance(Tags tags, int tag) {
		List<Integer> intList = new ArrayList<>();
		if (tags != null) intList.addAll(tags.valueList);
		intList.add(tag);
		return new Tags(intList);
	}

	@Override
	public String toString() {
		return stringValue;
	}
	@Override
	public int compareTo(Tags that) {
		return this.stringValue.compareTo(that.stringValue);
	}
	@Override
	public boolean equals(Object that) {
		if (that instanceof Tags) {
			return this.stringValue.equals(((Tags) that).stringValue);
		}
		return false;
	}
	@Override
	public int hashCode() {
		return stringValue.hashCode();
	}

	public boolean isComplex() {
		return isComplex;
	}
	public boolean isEmpty() {
		return isEmpty;
	}
	public int getFirst() {
		return valueList.get(0);
	}
	public int getLast() {
		return valueList.get(valueList.size() - 1);
	}
	
	public Tags removeFirst() {
		List<Integer> valueList = new ArrayList<>(this.valueList);
		valueList.remove(0);
		return new Tags(valueList);
	}
	
	public String toCompactString() {
		if (isEmpty) {
			return EMPTY;
		} else {
			StringBuilder ret = new StringBuilder();
			for(int n: valueList) ret.append(String.format("-%02X", n));
			return ret.substring(1);
		}
	}
}
