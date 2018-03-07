package fr.badblock.gameapi.utils.general;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Classe contenant plusieurs mÃ©thodes utiles pour l'utilisation des tableaux et
 * listes
 * 
 * @author LeLanN
 */
public class ArraysUtils {
	/**
	 * Permet de filtrer une liste de chaÃ®ne de caractÄ�re en fonction du dÃ©but
	 * 
	 * @param list
	 *            La liste Å• filtrer
	 * @param prefix
	 *            Le prÃ©fixe
	 * @return La nouvelle liste
	 */
	public static List<String> filter(Collection<String> list, String prefix) {
		Set<String> filtered = new LinkedHashSet<>();
		if (list != null) {
			String lowerPrefix = prefix.toLowerCase();
			for (String test : list) {
				if (test.toLowerCase().startsWith(lowerPrefix)) {
					filtered.add(test);
				}
			}
		}

		if (filtered.size() > 20) {
			return new ArrayList<>(filtered).subList(0, 20);
		}
		return new ArrayList<>(filtered);
	}

	@SuppressWarnings("unchecked")
	/**
	 * Permet de concactÃ©nÃ© une liste avec des tableaux
	 * 
	 * @param base
	 *            La liste de base
	 * @param others
	 *            Les tableaux
	 */
	public static <T> void join(List<T> base, T[]... others) {
		for (T[] other : others)
			join(base, other);
	}

	/**
	 * Permet de concactÃ©nÃ© une liste avec d'autres
	 * 
	 * @param base
	 *            La liste de base
	 * @param other
	 *            Les autres
	 */
	public static <T> void join(List<T> base, T[] other) {
		for (T obj : other)
			base.add(obj);
	}

	@SuppressWarnings("unchecked")
	/**
	 * Permet de concactÃ©nÃ© un tableau avec d'autres
	 * 
	 * @param base
	 *            Le tableau de base
	 * @param others
	 *            Les autres
	 * @return Le nouveau tableau
	 */
	public static <T> T[] join(T[] base, T[]... others) {
		List<T> result = new ArrayList<T>();
		join(result, base);

		for (T[] other : others)
			join(result, other);

		return (T[]) result.toArray();
	}

	/**
	 * Permet de remplacer une valeur par une autre dans toutes les chaÃ®nes d'un
	 * tableau
	 * 
	 * @param base
	 *            Le tableau
	 * @param key
	 *            La valeur de base
	 * @param value
	 *            La valeur de remplacement
	 * @return Le nouveau tablau
	 */
	public static String[] replace(String[] base, String key, String value) {
		for (int i = 0; i < base.length; i++) {
			base[i] = base[i].replace(key, value);
		}

		return base;
	}
	
	/**
	 * Ajouter une valeur à un tableau déjà existant plus facilement.
	 * @param arr
	 * @param element
	 * @return
	 */
	public static <T> T[] append(T[] arr, T element)
	{
	    final int N = arr.length;
	    arr = Arrays.copyOf(arr, N + 1);
	    arr[N] = element;
	    return arr;
	}
	
}
