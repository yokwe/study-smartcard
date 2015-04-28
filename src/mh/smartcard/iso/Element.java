package mh.smartcard.iso;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mh.smartcard.UnexpectedException;
import mh.smartcard.data.Template;
import mh.smartcard.data.Unknown;
import mh.smartcard.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Element {
	private static final Logger logger = LoggerFactory.getLogger(Element.class);

	public static final class Name {
		public final String group;
		public final String name;
		public Name(String group, String name) {
			this.group = group;
			this.name  = name;
		}
		public Name(String groupName) {
			Matcher matcherName = Pattern.compile(Group.NAME_SIMPLE).matcher(groupName);
			if (matcherName.find()) {
				String s1 = matcherName.group();
				if (matcherName.find())	{
					String s2 = matcherName.group();
					if (matcherName.find()) {
						throw new UnexpectedException(String.format("Wrong format = \"%s\"", groupName));
					}
					this.group = s1;
					this.name  = s2;
				} else {
					this.group = null;
					this.name  = s1;
				}
			} else {
				throw new UnexpectedException(String.format("Wrong format = \"%s\"", groupName));
			}
		}
		@Override
		public String toString() {
			if (group == null) return name;
			return group + "::" + name;
		}
		@Override
		public boolean equals(Object o) {
			if (o instanceof Name) {
				Name that = (Name)o;
				if (group == null) {
					return that.group == null && name.equals(that.name);
				} else {
					return group.equals(that.group) && name.equals(that.name);
				}
			}
			return false;
		}
	}
	
		
	public static final Map<String, Class<?>> dataMap;
	private static Map<String, Class<?>> buildDataMap() {
		List<String> classPathList = Util.findResource(Util.ROOT_PACAGE_NAME, ".+" + Pattern.quote(Util.CLASS_SUFFIX));
		
		Map<String, Class<?>> map = new TreeMap<>();
		for(Class<?> clazz: Util.findClass(classPathList)) {
			if (clazz.isAnnotationPresent(Data.Info.class)) {
				Data.Info info = clazz.getAnnotation(Data.Info.class);
				if (Data.class.isAssignableFrom(clazz)) {
					map.put(info.name(), clazz);
				} else {
					logger.error("clazz is not assignable from Data  {}", clazz.getName());
				}
			}
		}
		return Collections.unmodifiableMap(map);
	}
	
	public static final Element UNKNOWN = new Element(0, new Name("UNKNOWN"), 0, Unknown.FORMAT, new ArrayList<>(), "# UNKNOWN");
	public static Element findElement(Tags tag) {
		List<Element> elementList = new ArrayList<>();
		for(Group group: Group.getGroupList()) {
			Element element = group.findElement(tag);
			if (element != null) elementList.add(element);
		}
		if (elementList.size() == 0) {
			String message = String.format("findElement returns UNKNOWN for %s", tag.toCompactString());
			logger.error(message);
			return UNKNOWN;
//			throw new UnexpectedException(message);
		}
		if (elementList.size() == 1) return elementList.get(0);
		
		// Found more than one
		{
			// Return if there is only one exact match
			List<Element> exactMatchList = new ArrayList<>();
			for(Element element: elementList) {
				List<Tags> tagList = element.getTagList();
				if (tagList.contains(tag)) exactMatchList.add(element);
			}
			if (exactMatchList.size() == 1) return exactMatchList.get(0);
			
			if (2 <= exactMatchList.size()) {
				List<String> nameList = new ArrayList<>();
				for(Element e: exactMatchList) nameList.add(e.name.toString());
				String message = String.format("Found more than one exact match for tag %s and found %s", tag.toCompactString(), Util.join(nameList));
				
				// If there is only one ISO element, return it.
				List<Element> isoList = new ArrayList<>();
				for(Element element: exactMatchList) {
					if (element.name.group.equals("ISO")) isoList.add(element);
				}
				if (isoList.size() == 1) {
					logger.debug("{}  returns {}", message, isoList.get(0).name.toString());
					return isoList.get(0);
				}
				
				logger.error(message);
				throw new UnexpectedException(message);
			}
		}
		{
			// Return longest match that is more than one
			// First find size of longest match
			int maxSize = 0;
			for(Element element: elementList) {
				List<Tags> tagList = element.getTagList();
				for(Tags tags: tagList) {
					final int size = tags.valueList.size();
					// No interest for element whose size equals one
					if (size == 1) continue;
					if (maxSize < size) {
						maxSize = size;
					}
				}
			}
			if (0 < maxSize) {
				// Find element that has longest match
				List<Element> ret = new ArrayList<>();
				for(Element element: elementList) {
					List<Tags> tagList = element.getTagList();
					for(Tags tags: tagList) {
						if (tags.valueList.size() == maxSize) {
							if (!ret.contains(element)) ret.add(element);
						}
					}
				}
				// If there is only one element of longest match, return it.
				if (ret.size() == 1) return ret.get(0);
				
				// If there is only one element of ISO, return it.
				{
					List<Element> isoList = new ArrayList<>();
					for(Element element: elementList) {
						if (element.name.group.equals("ISO")) isoList.add(element);
					}
					if (isoList.size() == 1) {
						List<String> nameList = new ArrayList<>();
						for(Element e: ret) nameList.add(e.name.toString());
						String message = String.format("Found more than one longest partial match for tag %s and found %s", tag.toCompactString(), Util.join(nameList));
						logger.debug("{}  returns {}", message, isoList.get(0).name.toString());
						return isoList.get(0);
					}
				}
			}
		}
		{
			List<String> nameList = new ArrayList<>();
			for(Element e: elementList) nameList.add(e.name.toString());
			String message = String.format("Found more than one partial match for tag %s and found %s", tag.toCompactString(), Util.join(nameList));
			
			// If there is only one ISO element, return it.
			List<Element> isoList = new ArrayList<>();
			for(Element element: elementList) {
				if (element.name.group.equals("ISO")) isoList.add(element);
			}
			if (isoList.size() == 1) {
				logger.debug("{}  returns {}", message, isoList.get(0).name.toString());
				return isoList.get(0);
			}
			
			logger.error(message);
			throw new UnexpectedException(message);
		}
	}
	public static Data getData(TLV tlv) {
		final Element  element = findElement(tlv.tag);
		final String   name    = element.name.toString();
		final String   format  = element.format;
		final Class<?> clazz   = dataMap.get(format);
		if (clazz == null) {
			String message = String.format("Cannot find %s in dataMap", format);
//			logger.error(message);
			throw new UnexpectedException(message);
		}
		try {
			Constructor<?> constructor = clazz.getConstructor(String.class, TLV.class);
			Object object = constructor.newInstance(name, tlv);
			if (!(object instanceof Data)) {
				String message = String.format("Object is not instance of Data.  class of object = %s", object.getClass().getName());
//				logger.error(message);
				throw new UnexpectedException(message);
			}
			return (Data)object;
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
			String message = String.format("Exception %s  clazz = %s", e.getClass().getSimpleName(), clazz.getName());
//			logger.error(message);
			throw new UnexpectedException(message, e);
		}
	}
		
	
	public final int        lineNo;
	public final Name       name;
	public final int        tag;
	public final String     format;
	public final List<Tags> template;
	public final String     comment;
	
	public Element(int lineNo, Name name, int tag, String format, List<Tags> template, String comment) {
		this.lineNo   = lineNo;
		this.name     = name;
		this.tag      = tag;
		this.format   = format;
		this.template = Collections.unmodifiableList(template);
		this.comment  = comment;
	}
	
	static {
		// Build dataMap
		dataMap = buildDataMap();

		// Build nameMap
		Group.buildNameMapAll();
		
		// Build tagMap
		Group.buildTagMapAll();
	}
	
	public static void init() {}
	
	@Override
	public String toString() {
		final String template;
		{
			StringBuilder string = new StringBuilder();
			for(Tags tags: this.template) {
				string.append(", " + tags.toString());
			}
			template = string.substring(2);
		}
//		return String.format("%-40s  %4s  %-16s  %14s  %s", name, Tags.toTagString(tag), format, template, comment);
//		return String.format("%-40s  %4s  %-16s  %14s", name, Tags.toTagString(tag), format, template);
		return String.format("%3d  %-40s  %4s  %-20s  %14s", lineNo, name, Tags.toTagString(tag), format, template);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Element) {
			Element that = (Element)o;
			if (name.equals(that.name) && tag == that.tag && format.equals(that.format) && template.equals(that.template)) return true;
		}
		return false;
	}
	
	public boolean isTemplate() {
		return this.format.equals(Template.FORMAT);
	}
	public boolean isTopMost() {
		for(Tags tag: template) if (tag.isEmpty()) {
			return true;
		}
		return false;
	}
	public List<Tags> getTagList() {
		List<Tags> ret = new ArrayList<>();
		for(Tags tags: template) {
			ret.add(Tags.getInstance(tags, tag));
		}
		return ret;
	}
}
