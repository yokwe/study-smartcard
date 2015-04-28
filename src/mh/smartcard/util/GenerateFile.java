package mh.smartcard.util;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

import mh.smartcard.iso.Element;
import mh.smartcard.iso.Group;
import mh.smartcard.iso.Tags;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GenerateFile {
	private static final Logger logger = LoggerFactory.getLogger(GenerateFile.class);
	
	private static void generateAIDList() {
		Map<String, String>aidMap = Util.loadProperties(AIDList.class);
		
		String outPath = "src/mh/smartcard/util/AIDList.java";
		logger.info("outPath = {}", outPath);
		
		try (PrintWriter out = new PrintWriter(outPath)) {
			out.println("package mh.smartcard.util;");
			out.println();
			out.println("import java.util.ArrayList;");
			out.println("import java.util.List;");
			out.println("import java.util.Map;");
			out.println("import java.util.TreeMap;");
			out.println();
			out.println("import mh.smartcard.data.Hex;");
			out.println();
			out.println("public final class AIDList {");

			for(String number: aidMap.keySet()) {
				String name = aidMap.get(number);
				String javaName = name.replaceAll("-", "_");
				
				out.format("\t\tpublic static final String NAME_%-40s   = \"%s\";", javaName, name).println();
				out.format("\t\tpublic static final String NUMBER_%-40s = \"%s\";", javaName, number).println();
				out.println();
			}

			out.println("\tprivate static final Map<String, String> map = new TreeMap<>();");
			out.println("\tstatic {");
			for(String number: aidMap.keySet()) {
				String name = aidMap.get(number).replaceAll("-", "_");
				out.format("\t\tmap.put(NUMBER_%-40s NAME_%s);", name + ",", name).println();
			}
			out.println("\t}");
			out.println();
			
			for(String number: aidMap.keySet()) {
				String name = aidMap.get(number).replaceAll("-", "_");;
				
				out.format("\tpublic static final byte[] %-40s = Util.fromHexString(NUMBER_%s);", name, name).println();
			}

			//				out.format("\t\tmap.put(%-40s %s);", "\"" + number + "\",", "\"" + name + "\"").println();

			out.println();
			out.println("\tpublic static String getName(byte[] nuberData) {");
			out.println("\t\tString number = Hex.toString(nuberData);");
			out.println("\t\tString ret = map.get(number);");
			out.println("\t\treturn (ret != null) ? ret : (\"UNKNOWN-\" + number);");
			out.println("\t}");

			out.println();
			out.println("\tpublic static List<String> getNumberList() {");
			out.println("\t\tList<String> ret = new ArrayList<>();");
			out.println("\t\tfor(String number: map.keySet()) ret.add(number);");
			out.println("\t\treturn ret;");
			out.println("\t}");

			out.println("}");
		} catch (Exception e) {
			logger.error("Exception", e);
		}
	}
	
	private static void generateTagList() {
		String className = "TagList";
		String outPath = String.format("src/mh/smartcard/util/%s.java", className);
		logger.info("outPath = {}", outPath);
		
		// Generate TagMap.java
		try (PrintWriter out = new PrintWriter(outPath)) {
			out.println("package mh.smartcard.util;");
			out.println();
			out.println("import mh.smartcard.iso.Tags;");
			out.println();
			out.format("public final class %s {", className).println();
			
			for(Group group: Group.getGroupList()) {
				if (group.nameMap.isEmpty()) continue;
				
				out.format("\tpublic static final class %s {", group.name).println();
				for(Element e: group.nameMap.values()) {
					out.format("\t\tpublic static final Tags %-40s  = Tags.getInstance(0x%s);", e.name.name.replaceAll("-", "_").replaceAll(":", "_"), Tags.toTagString(e.tag)).println();
				}
				out.println("\t}");
			}
			out.println();
			
			{
				List<String> lineList = new ArrayList<>();
				for(Group group: Group.getGroupList()) {
					if (group.nameMap.isEmpty()) continue;
					
					out.println();
					for(Map.Entry<Tags, Element>entry: group.tagMap.entrySet()) {
						Tags    key   = entry.getKey();
						Element value = entry.getValue();
						lineList.add(String.format("\t// %-20s  %s", key, value.name.toString()));
					}
					
					logger.info("{} nameMap = {}", group.name, group.nameMap.size());
					logger.info("{} tagMap  = {}", group.name, group.tagMap.size());
				}
				
				{
					Comparator<String> stringComparator = new Comparator<String>() {
						@Override
						public int compare(String o1, String o2) {
							return o1.compareTo(o2);
						}
					};
					lineList.sort(stringComparator);
				}
				
				for(String line: lineList) {
					out.println(line);
				}
			}
			
			out.println("}");
		} catch (FileNotFoundException e) {
			logger.error("FileNotFoundException", e);
		}

		// Show stats of nameMap and tagMap
		logger.info("dataMap = {}", Element.dataMap.size());
	}

	public static void main(String[] args) {
		Element.init();
		
		generateTagList();
		generateAIDList();
	}
}