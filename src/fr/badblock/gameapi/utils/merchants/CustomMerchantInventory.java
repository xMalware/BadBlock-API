package fr.badblock.gameapi.utils.merchants;

import org.bukkit.entity.Villager;

/**
 * Représente les offres d'un marchant
 * @author LeLanN
 */
public interface CustomMerchantInventory {
	/**
	 * Récupère les objets vendables par le marchant
	 * @return Les objets vendables
	 */
	public CustomMerchantRecipe[] getRecipes();
	
	/**
	 * Enlève un objet vendable
	 * @param recipe L'objet
	 */
	public void removeRecipe(CustomMerchantRecipe recipe);
	
	/**
	 * Ajoute un objet vendable
	 * @param recipe L'objet
	 */
	public void addRecipe(CustomMerchantRecipe recipe);
	
	/**
	 * Applique les offres choisies à un villageois
	 * @param villager Le villageois
	 */
	public void applyTo(Villager villager);
}
