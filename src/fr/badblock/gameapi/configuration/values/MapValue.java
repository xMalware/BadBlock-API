package fr.badblock.gameapi.configuration.values;

/**
 * Représente une valeur pour la configuration des maps
 * @author LelanN
 *
 * @param <T> Le type de valeur
 */
public interface MapValue<T> {
	/**
	 * Récupère la véritable valeur
	 * @return La véritable valeur
	 */
	public T getHandle();
}
