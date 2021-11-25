package helio.framework.velocity;

import org.apache.commons.lang3.StringUtils;

public class VelocityFunctions {

	public static String quote() {
		return "\"";
	}
	
	public static String lower(String str) {
		return StringUtils.lowerCase(str);
	}

	public static String trim(String str) {
		return str.trim();
	}

	public static boolean notBlank(String str) {
		return !str.trim().isEmpty();
	}

	public static String regex_replace(String str, String regex, String replacement) {
		return str.replaceAll(regex, replacement);
	}

}
