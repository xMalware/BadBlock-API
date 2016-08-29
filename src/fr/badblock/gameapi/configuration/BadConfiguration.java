package fr.badblock.gameapi.configuration;

import java.io.File;
import java.util.Collection;
import java.util.List;

import com.google.gson.JsonObject;

import fr.badblock.gameapi.configuration.values.MapList;
import fr.badblock.gameapi.configuration.values.MapValue;

/**
 * Représente une configuration simplifiée
 * 
 * @author LeLanN
 */
public interface BadConfiguration {
	/**
	 * Récupère une sous-section à la configuration
	 * 
	 * @param key
	 *            La clé de la sous-section
	 * @return La sous-section
	 */
	public BadConfiguration getSection(String key);

	/**
	 * Récupère une valeur
	 * 
	 * @param key
	 *            La clé de la valeur
	 * @param clazzValue
	 *            Le type de la valeur
	 * @return La valeur (les modifications ne s'appliquent pas à la
	 *         configuration)
	 */
	public <T extends MapValue<?>> T getValue(String key, Class<T> clazzValue);

	/**
	 * Récupère une valeur
	 * 
	 * @param key
	 *            La clé de la valeur
	 * @param clazzValue
	 *            Le type de la valeur
	 * @param def
	 *            La valeur par défaut (sera set si non trouvéee)
	 * @return La valeur (les modifications ne s'appliquent pas à la
	 *         configuration)
	 */
	public <T extends MapValue<?>> T getValue(String key, Class<T> clazzValue, T def);

	/**
	 * Récupère une liste de valeurs
	 * 
	 * @param key
	 *            La clé de la liste
	 * @param clazzValue
	 *            Le type de valeur
	 * @return La liste de valeurs (les modifications ne s'appliquent pas à la
	 *         configuration)
	 */
	public <T extends MapValue<K>, K> MapList<T, K> getValueList(String key, Class<T> clazzValue);

	/**
	 * Récupère une liste de valeurs
	 * 
	 * @param key
	 *            La clé de la liste
	 * @param clazzValue
	 *            Le type de valeur
	 * @param def
	 *            La valeur par défaut (sera set si non trouvée)
	 * @return La liste de valeurs (les modifications ne s'appliquent pas à la
	 *         configuration)
	 */
	public <T extends MapValue<K>, K> MapList<T, K> getValueList(String key, Class<T> clazzValue, List<T> def);

	/**
	 * Sauvegarde la configuration sous forme de JsonObject
	 * 
	 * @return Le JsonObject
	 */
	public JsonObject save();

	/**
	 * Sauvegarde la ocnfiguration dans un fichhier
	 * 
	 * @param file
	 *            Le fichier
	 */
	public void save(File file);

	/**
	 * Définit une valeur
	 * 
	 * @param key
	 *            La clé de la valeur
	 * @param value
	 *            La valeur
	 */
	public <T extends MapValue<?>> void setValue(String key, T value);

	/**
	 * Définit une liste de valeurs
	 * 
	 * @param key
	 *            La clé de la liste
	 * @param value
	 *            La liste de valeurs
	 */
	public <T extends MapValue<?>> void setValueList(String key, Collection<T> value);
}
