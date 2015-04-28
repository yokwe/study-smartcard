package mh.smartcard.iso;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mh.smartcard.UnexpectedException;
import mh.smartcard.iso.Element.Name;
import mh.smartcard.util.Util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Group {
	private static final Logger logger = LoggerFactory.getLogger(Group.class);
	
	public static final String FILE_NAME       = "Element.data";
	
	public static final String NAME_SIMPLE = "[A-Z][A-Z0-9-]*";
	public static final String NAME_PREFIX = Util.groupOpt(NAME_SIMPLE + "::");
	public static final String NAME_TAG    = Util.group(NAME_PREFIX + NAME_SIMPLE);
	public static final String LINE        =
			"(?<name>"     + NAME_TAG    + ")" + Util.SPACE_PLUS +
			"(?<tag>"      + Tags.SIMPLE + ")" + Util.SPACE_PLUS +
			"(?<format>"   + NAME_TAG    + ")" + Util.SPACE_PLUS +
			"(?<template>" + Tags.LIST   + ")" + Util.SPACE_STAR +
			"(?<comment>(#.+)?)";
	
	public static final String GROUP_NAME_ISO   = "ISO";


	private static final Map<String, Group> map = new TreeMap<>();
	
	public static List<Group> getGroupList() {
		List<Group> ret = new ArrayList<>(map.values());
		return ret;
	}
	public static Group getGroup(String name) {
		Group ret = map.get(name);
		if (ret == null) {
			ret = new Group(name);
			map.put(name, ret);
		}
		return ret;
	}
	private static void addElement(Element e) {
		Group group = getGroup(e.name.group);
		Element old = group.nameMap.put(e.name.name, e);
		if (old != null) {
			if (old.equals(e)) {
				String message = String.format("Skip duplicate element  %-40s  %4s", e.name, Tags.toTagString(e.tag));
				logger.warn(message);
			} else {
				String message = String.format("Duplicate element %s  old tag = %02X  new tag = %02X", e.name, old.tag, e.tag);
				logger.error(message);
				throw new UnexpectedException(message);
			}
		}
	}
	private static void loadElement(String resourcePath) {
		logger.info("Start load Element File  path = {}", resourcePath);
		final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Matcher matcherLine = Pattern.compile(LINE).matcher("");
		Matcher matcherComplexTag  = Pattern.compile(Tags.COMPLEX_OR_EMPTY).matcher("");
		
		try (
			BufferedReader r = new BufferedReader(new InputStreamReader(classLoader.getResourceAsStream(resourcePath)));
		) {
			//
			int lineNo = 0;
			for(;;) {
				final String line = r.readLine();
				lineNo++;
				if (line == null) break;
				if (line.length() == 0 || line.charAt(0) == '#') continue;
				
				matcherLine.reset(line);
				if (matcherLine.matches()) {
					final String groupName= matcherLine.group("name");
					final String tag      = matcherLine.group("tag");
					final String format   = matcherLine.group("format");
					final String template = matcherLine.group("template");
					final String comment  = matcherLine.group("comment");
					
					final Name name = new Name(groupName);
					
					int tagValue = Integer.parseUnsignedInt(tag, 16) & 0xFFFF;
					List<Tags> templateList = new ArrayList<>();
					matcherComplexTag.reset(template);
					while(matcherComplexTag.find()) {
						final String compelexTag = matcherComplexTag.group();
						templateList.add(Tags.getInstance(compelexTag));
					}
					
					addElement(new Element(lineNo, name, tagValue, format, templateList, comment));
				} else {
					logger.error("UNKNOWN {}", line);
				}
			}
		} catch (Exception e) {
			logger.error("Exception", e);
		}
		
		logger.info("Stop  load Element File");
	}
	public static void buildNameMapAll() {
		List<String> resourcePathList = Util.findResource(Util.ROOT_PACAGE_NAME, Pattern.quote(FILE_NAME));
		// load as many as possible
		for(String resourcePath: resourcePathList) {
			loadElement(resourcePath);
		}
	}
	public static void buildTagMapAll() {
		// First  create tagMap of ISO
		Group isoGroup = map.get(GROUP_NAME_ISO);
		isoGroup.buildTagMap();

		// Second create tagMap of others
		for(Group g: map.values()) {
			if (g.equals(isoGroup)) continue;
			g.buildTagMap();
		}
		
		// Show duplicate element with ISO
		for(Group myGroup: map.values()) {
			if (myGroup.equals(isoGroup)) continue;
			for(Tags tag: isoGroup.tagMap.keySet()) {
				Element myElement  = myGroup.tagMap.get(tag);
				if (myElement == null) continue;
				Element isoElement = isoGroup.tagMap.get(tag);
//				if (!myElement.template.equals(isoElement.template)) continue;
				String message = String.format("Duplicate tag %-10s between %s %s and %s %s", tag.toCompactString(), isoElement.name, isoElement.template, myElement.name, myElement.template);
				logger.warn(message);
			}
		}
	}
	
	
	public final String               name;
	public final Map<String, Element> nameMap = new TreeMap<>();
	public final Map<Tags,   Element> tagMap  = new TreeMap<>();
	private Group(String name) {
		this.name = name;
	}
	
	@Override
	public boolean equals(Object o) {
		if (o instanceof Group) {
			Group that = (Group)o;
			return name.equals(that.name);
		}
		return false;
	}
	
	public final Element findElement(Tags tag) {
		Element ret = tagMap.get(tag);
		if (ret != null) return ret;
		
		for(;;) {
			ret = tagMap.get(tag);
			if (ret != null) return ret;
			if (tag.valueList.size() == 1) break;
			tag = tag.removeFirst();
		}
		
		return null;
	}
	private List<Element> findTemplateInternal(int tag) {
		List<Element> ret = new ArrayList<>();
		for(Element e: nameMap.values()) {
			if (e.tag == tag && e.isTemplate()) ret.add(e);
		}
		return ret;
	}
	private Element findTemplate(int tag) {
		List<Element> ret = findTemplateInternal(tag);
		if (ret.size() == 0 && !name.equals("ISO")) ret = getGroup("ISO").findTemplateInternal(tag);

		final int size = ret.size();
		if (size == 1) {
			// Found one
			Element e = ret.get(0);
			return e;
		} else if (size == 0) {
			// Found zero
			String message = String.format("Found nothing tag = %02X  in group %s", tag, name);
			logger.error(message);
			throw new UnexpectedException(message);
		} else {
			// Found more than one
			List<String> nameList = new ArrayList<>();
			for(Element e: ret) nameList.add(e.name.toString());
			String message = String.format("Found more than one  tag = %s  found %s", Tags.toTagString(tag), Util.join(nameList));
			logger.error(message);
			throw new UnexpectedException(message);
		}
	}
	private void buildTagMap(Tags tags, Element element) {
		Element old = tagMap.get(tags);
		if (old == null) {
			tagMap.put(tags, element);
		} else {
			String message = String.format("buildTagMap  already exists  tag = %s  old = %s  new = %s", tags, old.name, element.name);
			logger.error(message);
			throw new UnexpectedException(message);
		}
	}
	private void buildTagMap(List<Integer> tagList, Element templateElelement, Element targetElement) {
		tagList.add(0, templateElelement.tag);
		// If templateElelement is top most level, add to tagMap
		if (templateElelement.template.isEmpty()) {
			buildTagMap(Tags.getInstance(tagList), targetElement);
		} else {
			for(Tags template: templateElelement.template) {
				if (template.isEmpty()) {
					buildTagMap(Tags.getInstance(tagList), targetElement);
				} else if (template.isComplex()) {
					Element firstElement = Element.findElement(Tags.getInstance(template.getFirst()));
					if (firstElement.isTopMost()) {
						logger.info("tagList {}", tagList);
						List<Integer> targetTagList = new ArrayList<>(template.valueList);
						targetTagList.addAll(tagList);
						buildTagMap(Tags.getInstance(targetTagList), targetElement);
					} else {
						logger.error("targetElement     {}", targetElement);
						logger.error("templateElelement {}", templateElelement);
						String message = String.format("First element of complex tag must be top level  %s", templateElelement.toString());
						throw new UnexpectedException(message);
					}
				} else {
					buildTagMap(tagList, findTemplate(template.getFirst()), targetElement);
				}
			}
		}
	}
	public void buildTagMap() {
		for(Element e: nameMap.values()) {
			logger.debug("{}", e.toString());
			for(Tags template: e.template) {
				if (template.isEmpty()) {
					// This element is top most level
					buildTagMap(Tags.getInstance(e.tag), e);
				} else if (template.isComplex()) {
					// Complex
					// Check every tag in template is template
					for(int value: template.valueList) {
						findTemplate(value);
					}
					// first template element must be global element
					for(int value: template.valueList) {
						Element top = findTemplate(value);
						if (top.name.toString().equals("GP::CARD-CHIP-DETAIL")) break; // Special case of GP::CARD-CHIP-DETAIL
						if (!top.isTopMost())	{
							String message = String.format("First element (%s) of template is not global.  element = %s", e.name, top.name);
							logger.error(message);
							throw new UnexpectedException(message);
						}
						// check only first element of template
						break;
					}
					buildTagMap(Tags.getInstance(template, e.tag), e);
				} else {
					// Simple
					List<Integer> tagList = new ArrayList<>();
					tagList.add(0, e.tag);
					buildTagMap(tagList, findTemplate(template.getFirst()), e);
				}
			}
		}
	}
}
