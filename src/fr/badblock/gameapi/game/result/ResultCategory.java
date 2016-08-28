package fr.badblock.gameapi.game.result;

/**
 * Représente une catégorie pour {@link Result}
 * 
 * @author LeLanN
 */
public interface ResultCategory {
	/**
	 * Représente les différents types de catégories
	 * 
	 * @author LeLanN
	 */
	public enum CategoryType {
		/**
		 * Une catégorie à entrée simple
		 */
		LINED_DATA,
		/**
		 * Une catégorie à double entrée (utile pour un classement par exemple)
		 */
		ARRAY_DATA;
	}

	/**
	 * Récupère le nom d'affichage de la catégorie
	 * 
	 * @return Le nom d'affichage
	 */
	public String getCategoryName();

	/**
	 * Récupère le type de catégorie
	 * 
	 * @return Le type de catégorie
	 */
	public CategoryType getType();
}
