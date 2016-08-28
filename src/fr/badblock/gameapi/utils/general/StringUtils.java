package fr.badblock.gameapi.utils.general;

import java.util.Collection;
import java.util.Map;

public class StringUtils {
	public static <T> String join(Collection<T> toJoin, String joiner) {
		boolean first = true;
		;
		String result = "";

		for (T o : toJoin) {
			if (o == null)
				continue;
			if (!first)
				result += joiner;
			else
				first = false;

			result += o.toString();
		}

		return result;
	}

	public static <K, V> String join(Map<K, V> toJoin, String joiner, String subJoiner) {
		boolean first = true;
		;
		String result = "";

		for (K key : toJoin.keySet()) {
			if (!first)
				result += joiner;
			else
				first = false;

			result += key + subJoiner + toJoin.get(key);
		}

		return result;
	}

	public static <T> String join(T[] toJoin, String joiner) {
		return join(toJoin, joiner, 0);
	}

	public static <T> String join(T[] toJoin, String joiner, int deb) {
		boolean first = true;
		;
		String result = "";

		for (int i = deb; i < toJoin.length; i++) {
			T o = toJoin[i];
			if (!first)
				result += joiner;
			else
				first = false;

			result += o.toString();
		}

		return result;
	}

	public static String getUpperFirstLetter(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1, string.length());
	}
}
