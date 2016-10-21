package fr.badblock.gameapi.utils.general;

import lombok.Getter;
import lombok.Setter;

/**
 * Util for statistics
 * 
 * @author xMalware
 */
public class CalcUtil {

	@Getter
	@Setter
	private static CalcUtil instance = new CalcUtil();

	public String convertInt(double x) {
		if (x % 1.0 != 0)
			return String.format("%s", x);
		else
			return String.format("%.0f", x);
	}

	/**
	 * Get a ratio without any error
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public String getRatio(double x, double y) {
		if (x == 0)
			return "0";
		if (y == 0)
			y = 1;
		return convertInt(MathsUtils.round(x / y, 2));
	}

}
