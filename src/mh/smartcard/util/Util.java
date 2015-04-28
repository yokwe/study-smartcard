package mh.smartcard.util;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.TerminalFactory;

import mh.smartcard.UnexpectedException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Util {
	private static final Logger logger = LoggerFactory.getLogger(Util.class);

	public static byte[] fromHexString(String s) {
		// case XX XX XX
		if (3 <= s.length() && ((s.length() + 1) % 3) == 0 && s.charAt(2) == ' ') {
			int length = (s.length() + 1) / 3;
			byte[] ret = new byte[length];
			for(int i = 0; i < length; i++) {
				int j = i * 3;
				ret[i] = (byte) ((Character.digit(s.charAt(j), 16) << 4) + Character.digit(s.charAt(j + 1), 16));
			}
			return ret;
		}
		
		// case XXXXXX
		if ((s.length() % 2) == 0) {
			int length = s.length() / 2;
			byte[] ret = new byte[length];
			for(int i = 0; i < length; i++) {
				int j = i * 2;
				ret[i] = (byte) ((Character.digit(s.charAt(j), 16) << 4) + Character.digit(s.charAt(j + 1), 16));
			}
			return ret;
		}
		
		// None of above
		throw new UnexpectedException(s);
	}

	public static int toInteger(byte[] data) {
		ByteBuffer bb = new ByteBuffer(data);
		final int firstByte = bb.get();
		int ret = firstByte & 0x7F;
		while(bb.hasRemaining()) {
			ret <<= 8;
			ret += bb.get();
		}
		// If first byte of bit 8 is not zero, make 2's complement.
		if ((firstByte & 0b1000_000) != 0){
			ret = ~ret + 1;
		}
		return ret;
	}
	
	public static String join(List<String> elements) {
		if (elements.isEmpty()) return "[]";
		StringBuilder ret = new StringBuilder();
		for(String element: elements) ret.append(", ").append(element);
		return "[" + ret.substring(2) + "]";
	}
	
	
    private static final char DOT = '.';
    private static final char SLASH = '/';
    public static final String CLASS_SUFFIX = ".class";
    public static final String ROOT_PACAGE_NAME = "mh.smartcard";
    public static final List<String> findResource(final String packageName, final String patternString) {
    	final Matcher matcher = Pattern.compile(patternString).matcher("");
        final ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            final List<String> ret = new LinkedList<>();
        	final Enumeration<URL> resources = classLoader.getResources(packageName.replace(DOT, SLASH));
            while (resources.hasMoreElements()) {
                final File file = new File(resources.nextElement().getFile());
                ret.addAll(findResourceInternal(file, packageName, matcher));
            }
            return ret;
        } catch (IOException e) {
            throw new UnexpectedException("IOException", e);
        }
    }
    private static final List<String> findResourceInternal(final File file, final String scannedPackage, final Matcher matcher) {
        final List<String> ret = new LinkedList<>();
        if (file.isDirectory()) {
            for (File nestedFile : file.listFiles()) {
                if (nestedFile.isDirectory()) {
                    ret.addAll(findResourceInternal(nestedFile, scannedPackage + DOT + nestedFile.getName(), matcher));
                } else {
                    ret.addAll(findResourceInternal(nestedFile, scannedPackage, matcher));
                }
            }
        } else if (matcher.reset(file.getName()).matches()) {
            ret.add(scannedPackage.replace(DOT, SLASH) + SLASH + file.getName());
        }
         return ret;
    }

    public static final List<Class<?>> findClass(final List<String> resourcePathList) {
    	List<Class<?>> ret = new LinkedList<>();
    	for(String resourcePath: resourcePathList) {
    		if (resourcePath.endsWith(CLASS_SUFFIX)) {
                final int beginIndex = 0;
                final int endIndex = resourcePath.length() - CLASS_SUFFIX.length();
                final String className = resourcePath.substring(beginIndex, endIndex).replace(SLASH, DOT);
                try {
                    ret.add(Class.forName(className));
                } catch (ClassNotFoundException e) {
                    logger.warn("e = {}", e);
                }
            }
    	}
    	
    	return ret;
    }
    
    
	public static String getResourcePath(Class<?> clazz, String suffix) {
		return clazz.getName().replaceAll("\\.", "/") + suffix;
	}
	
	public static Map<String, String> loadProperties(Class<?> clazz) {
		final String path = getResourcePath(clazz, ".properties");
		return loadProperties(path);
	}
	public static Map<String, String> loadProperties(String resourcePath) {
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		Map<String, String> ret = new TreeMap<>();

		try {
			Properties props = new Properties();
			logger.info("resourcePath = {}", resourcePath);
			props.load(classLoader.getResourceAsStream(resourcePath));
			for(String key: props.stringPropertyNames()) {
				ret.put(key, props.getProperty(key));
			}
		} catch (IOException e) {
			logger.error("Exception", e);
			throw new UnexpectedException("IOException", e);
		}
		
		return ret;
	}
	

	// RegExp
	public static final String SPACE = "[ \\t]";
	public static final String SPACE_STAR = groupStar(SPACE);
	public static final String SPACE_PLUS = groupPlus(SPACE);

	public static String group(String string) {
		return "(" + string + ")";
	}
	public static String groupStar(String string) {
		return group(string) + "*";
	}
	public static String groupPlus(String string) {
		return group(string) + "+";
	}
	public static String groupOpt(String string) {
		return group(string) + "?";
	}
	public static String groupOr(String a, String b) {
		return group(group(a) + "|" + group(b));
	}
	
	
	// CardTermianl
	public static List<CardTerminal> getCardPresentTerminalList() {
		TerminalFactory factory = TerminalFactory.getDefault();
		logger.info("Provider {}", factory.getProvider().getInfo());
		
		try {
			List<CardTerminal> ret = new ArrayList<>();
			
			// We have interest of terminal that has card.
			for(CardTerminal terminal: factory.terminals().list()) {
				if (terminal.isCardPresent()) ret.add(terminal);
			}
			
			return ret;
		} catch (CardException e) {
			throw new UnexpectedException("CardException", e);
		}
	}
}
