package fr.badblock.gameapi.configuration.values;

import com.google.gson.JsonElement;

/**
 * Représente une valeur primitive
 * 
 * @author LelanN
 *
 * @param <T>
 *            Le type de valeur
 */
public interface MapValuePrimitive<T> extends MapValue<T> {
	/**
	 * Charge la valeur depuis un élément JSON
	 * 
	 * @param json
	 *            L'élément
	 */
	public void from(JsonElement json);

	/**
	 * Récupère la valeur sous forme d'élément JSON
	 * 
	 * @return L'élément
	 */
	public JsonElement to();
}
