package fr.badblock.gameapi.servers;

import java.io.File;
import java.util.List;
import java.util.function.Function;

import org.bukkit.inventory.ItemStack;

import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.AllArgsConstructor;

/**
 * Permet de générer des coffres aléatoires avec une configuration
 * 
 * @author LeLanN
 */
public interface ChestGenerator {
	/**
	 * Ajoute un item dans la configuration chargée
	 * 
	 * @param item
	 *            L'item
	 * @param probability
	 *            La probabilité d'avoir cet item
	 * @param save
	 *            Si la configuration doit être sauvegardée avec cet item
	 */
	public void addItemInConfiguration(ItemStack item, int probability, boolean save);

	/**
	 * Après que cette méthode soit appelée, les coffres ouverts par les joueurs
	 * seront automatiquement générés
	 */
	public void beginJob();

	/**
	 * Permet de générer un inventaire d'un certains nombre de lignes avec la
	 * configuration chargée
	 * 
	 * @param lines
	 *            Le nombre de lignes
	 * @return Le contenu de l'inventaire
	 */
	public ItemStack[] generateChest(BadblockPlayer player, int lines);

	/**
	 * Vérifie si le générateur à été configuré
	 * 
	 * @return Si il a été configuré
	 */
	public boolean isConfigurated();

	/**
	 * Vérifie si le générateur a été démarré (remplissage automatique des
	 * coffres)
	 * 
	 * @return Si il a été démarré
	 */
	public boolean isWorking();

	/**
	 * Réinitialise le contenu des coffres
	 */
	public void resetChests();

	/**
	 * Permet de configurer les coffres
	 * 
	 * @param file
	 *            Le fichier de configuration
	 */
	public void setConfigurationFile(File file);

	/**
	 * Définit si le coffre se supprime lorsqu'un joueur le ferme
	 * 
	 * @param removeOnOpen
	 *            Si le coffre se supprime
	 */
	public void setRemoveOnOpen(boolean removeOnOpen);
	
	/**
	 * Définit si les coffres sont individuels (faux coffres)
	 * @param individualChest Si les coffres sont individuels
	 */
	public void setIndividualChest(boolean individualChest);
	
	public void setAlternateItemsProvider(Function<BadblockPlayer, List<ISProb>> provider);
	
	@AllArgsConstructor
	public static class ISProb {
		public int prob;
		public ItemStack stack;
	}
}
