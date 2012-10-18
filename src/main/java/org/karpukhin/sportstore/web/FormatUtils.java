package org.karpukhin.sportstore.web;

/**
 * @author Pavel Karpukhin
 */
public abstract class FormatUtils {

	public static final String DEFAULT_TEXT = "&mdash;";

	public static String encode(Object object) {
		return object != null ? object.toString() : "";
	}

	public static String encodeValue(String string) {
		if (string == null || string.isEmpty())
			return DEFAULT_TEXT;
		return string;
	}

	public static String formatString(String string) {
		if (string == null || string.isEmpty())
			return DEFAULT_TEXT;
		if (string.length() > 25)
			return string.substring(0, 25);
		return string;
	}
}
