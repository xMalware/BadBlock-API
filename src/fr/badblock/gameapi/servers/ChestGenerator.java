package fr.badblock.gameapi.servers;

import java.io.File;

import org.bukkit.inventory.ItemStack;

/**
 * Permet de générer des coffres aléatoires avec une configuration
 * @author LeLanN
 */
public interface ChestGenerator {
	/**
	 * Permet de configurer les coffres
	 * @param file Le fichier de configuration
	 */
	public void setConfigurationFile(File file);

	/**
	 * Vérifie si le générateur à été configuré
	 * @return Si il a été configuré
	 */
	public boolean isConfigurated();
	
	/**
	 * Permet de générer un inventaire d'un certains nombre de lignes
	 * avec la configuration chargée
	 * @param lines Le nombre de lignes
	 * @return Le contenu de l'inventaire
	 */
	public ItemStack[] generateChest(int lines);
	
	/**
	 * Après que cette méthode soit appelée, les coffres ouverts par les joueurs seront automatiquement générés
	 */
	public void beginJob();

	/**
	 * Vérifie si le générateur a été démarré (remplissage automatique des coffres)
	 * @return Si il a été démarré
	 */
	public boolean isWorking();
	
	/**
	 * Réinitialise le contenu des coffres
	 */
	public void resetChests();
	
	/**
	 * Définit si le coffre se supprime lorsqu'un joueur le ferme
	 * @param removeOnOpen Si le coffre se supprime
	 */
	public void setRemoveOnOpen(boolean removeOnOpen);
	
	/**
	 * Ajoute un item dans la configuration chargée
	 * @param item L'item
	 * @param probability La probabilité d'avoir cet item
	 * @param save Si la configuration doit être sauvegardée avec cet item
	 */
	public void addItemInConfiguration(ItemStack item, int probability, boolean save);
}
