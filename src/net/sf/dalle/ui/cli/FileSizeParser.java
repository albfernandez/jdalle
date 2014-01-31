package net.sf.dalle.ui.cli;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSizeParser {
	public static long toBytes(String filesize) {
		long returnValue = -1;
		Pattern patt = Pattern.compile("([\\d.]+)([TGMK]B)",
				Pattern.CASE_INSENSITIVE);
		Matcher matcher = patt.matcher(filesize);
		Map<String, Integer> powerMap = new HashMap<>();
		powerMap.put("TB", 4);
		powerMap.put("GB", 3);
		powerMap.put("MB", 2);
		powerMap.put("KB", 1);
		if (matcher.find()) {
			String number = matcher.group(1);
			int pow = powerMap.get(matcher.group(2).toUpperCase());
			BigDecimal bytes = new BigDecimal(number);
			bytes = bytes.multiply(BigDecimal.valueOf(1024).pow(pow));
			returnValue = bytes.longValue();
		}
		return returnValue;
	}
	public static String toHumanReadable (long fileSize) {
		return toHumanReadable(fileSize, Locale.getDefault());
	}
	public static String toHumanReadable(long fileSize, Locale locale) {
		return "";
	}
}
