package fr.badblock.gameapi.utils.general;

import java.util.Map.Entry;
import java.text.DecimalFormat;
import java.util.NavigableMap;
import java.util.TreeMap;

public class NumberFormat {

	private static final NavigableMap<Long, String> suffixes = new TreeMap<> ();
	static {
	  suffixes.put(1_000L, "K");
	  suffixes.put(1_000_000L, "M");
	}

	public static String format(long value) {
	  if (value == Long.MIN_VALUE) return format(Long.MIN_VALUE + 1);
	  if (value < 0) return "-" + format(-value);
	  if (value < 1000) return Long.toString(value);
	  
	  Entry<Long, String> e = suffixes.floorEntry(value);
	  Long divideBy = e.getKey();
	  String suffix = e.getValue();

	  double truncated = ((double) value) / ((double) (((double) divideBy) / 10.0D));
	  truncated /= 10d;
	  DecimalFormat format = new DecimalFormat("0.##");
	  String result = format.format(truncated) + suffix;
	  return result;
	}
	
}
