package fr.badblock.gameapi.configuration;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;

import com.google.gson.JsonObject;

import fr.badblock.gameapi.configuration.values.MapList;
import fr.badblock.gameapi.configuration.values.MapValue;

/**
 * ReprÃ©sente une configuration simplifiÃ©e
 * 
 * @author LeLanN
 */
public interface BadConfiguration {
	/**
	 * RÃ©cupÄ�re une sous-section Å• la configuration
	 * 
	 * @param key
	 *            La clÃ© de la sous-section
	 * @return La sous-section
	 */
	public BadConfiguration getSection(String key);

	/**
	 * RÃ©cupÄ�re une valeur
	 * 
	 * @param key
	 *            La clÃ© de la valeur
	 * @param clazzValue
	 *            Le type de la valeur
	 * @return La valeur (les modifications ne s'appliquent pas Å• la
	 *         configuration)
	 */
	public <T extends MapValue<?>> T getValue(String key, Class<T> clazzValue);

	/**
	 * RÃ©cupÄ�re une valeur
	 * 
	 * @param key
	 *            La clÃ© de la valeur
	 * @param clazzValue
	 *            Le type de la valeur
	 * @param def
	 *            La valeur par dÃ©faut (sera set si non trouvÃ©ee)
	 * @return La valeur (les modifications ne s'appliquent pas Å• la
	 *         configuration)
	 */
	public <T extends MapValue<?>> T getValue(String key, Class<T> clazzValue, T def);
	
	public <T> T getSimpleValueList(String key, Type clazzValue);

	/**
	 * RÃ©cupÄ�re une liste de valeurs
	 * 
	 * @param key
	 *            La clÃ© de la liste
	 * @param clazzValue
	 *            Le type de valeur
	 * @return La liste de valeurs (les modifications ne s'appliquent pas Å• la
	 *         configuration)
	 */
	public <T extends MapValue<K>, K> MapList<T, K> getValueList(String key, Class<T> clazzValue);

	/**
	 * RÃ©cupÄ�re une liste de valeurs
	 * 
	 * @param key
	 *            La clÃ© de la liste
	 * @param clazzValue
	 *            Le type de valeur
	 * @param def
	 *            La valeur par dÃ©faut (sera set si non trouvÃ©e)
	 * @return La liste de valeurs (les modifications ne s'appliquent pas Å• la
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
	 * DÃ©finit une valeur
	 * 
	 * @param key
	 *            La clÃ© de la valeur
	 * @param value
	 *            La valeur
	 */
	public <T extends MapValue<?>> void setValue(String key, T value);

	/**
	 * DÃ©finit une liste de valeurs
	 * 
	 * @param key
	 *            La clÃ© de la liste
	 * @param value
	 *            La liste de valeurs
	 */
	public <T extends MapValue<?>> void setValueList(String key, Collection<T> value);
	
	public <T> void setValueSimpleList(String key, Collection<T> value);
}
