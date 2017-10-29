package fr.badblock.gameapi.utils.general;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Random;

/**
 * Classe contenant plusieurs méthodes utiles pour l'utilisation des nombres
 * 
 * @author LeLanN
 */
public class MathsUtils {
	/**
	 * Ajoute un certains pourcentage ŕ une valeur de base
	 * 
	 * @param base
	 *            La valeur de base
	 * @param percent
	 *            Le pourcentage ŕ ajouter
	 * @return La nouvelle valeur
	 */
	public static double addPercentage(double base, double percent) {
		return (1d + percent / 100d) * base;
	}

	/**
	 * Ajoute un certains pourcentage, un certains nombre de fois, ŕ une valeur
	 * de base
	 * 
	 * @param base
	 *            La valeur de base
	 * @param percent
	 *            Le pourcentage ŕ ajouter
	 * @param n
	 *            Le nombre de fois oů il faut ajouter ce pourcentage
	 * @return La nouvelle valeur
	 */
	public static double addPercentage(double base, double percent, int n) {
		return Math.pow(1d + percent / 100d, n) * base;
	}

	/**
	 * Renvoit un nombre aléatoire
	 *
	 * @param max
	 *            Le maximum
	 * @param min
	 *            Le minimum
	 * @return Le nombre
	 */
	public static double doubleRandomInclusive(double max, double min) {
		if (max < min) {
			double tmp = min;
			min = max;
			max = tmp;
		}

		double r = Math.random();
		if (r < 0.5) {
			return ((1 - Math.random()) * (max - min) + min);
		}
		return (Math.random() * (max - min) + min);
	}

	/**
	 * Renvoit un nombre aléatoire
	 *
	 * @param max
	 *            Le maximum
	 * @param min
	 *            Le minimum
	 * @return Le nombre
	 */
	public static int integerRandomInclusive(int max, int min) {
		if (max < min) {
			int tmp = min;
			min = max;
			max = tmp;
		}

		return new Random().nextInt(max) + min;
	}

	/**
	 * Arrondit un nombre ŕ un certains nombre de décimales
	 * 
	 * @param number
	 *            La valeur
	 * @param dec
	 *            Le nombre de décimales
	 * @return La nouvelle valeur
	 */
	public static double round(double number, int dec) {
		if (dec < 0)
			throw new IllegalArgumentException();

		BigDecimal bd = new BigDecimal(number);
		bd = bd.setScale(dec, RoundingMode.HALF_UP);

		return bd.doubleValue();
		// int div = (int) Math.pow(10, dec);
		// return (double) ((int)(number * div)) / (double) div;
	}
}
