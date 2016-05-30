package fr.badblock.gameapi.players.kits;

import fr.badblock.gameapi.achievements.PlayerAchievement;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.utils.itemstack.ItemStackExtra;

/**
 * Représente un Kit obtenable par un joueur.
 * @author LeLanN
 */
public interface PlayerKit {
	/**
	 * Récupère le nom 'interne' (qui ne sera jamais affiché) du kit. Par exemple rush.archer<br>
	 * Pour obtenir la version affichage, passer par le système d'i18n.
	 * @return Le nom 'interne'
	 */
	public String getKitName();
	
	/**
	 * Retourne l'itemstack devant représenter l'item dans un inventaire avec
	 * les informations propre au joueur (le niveau, si il est séléctionné)
	 * @param player Le joueur pour lequel l'item est fait
	 * @return L'itemstack
	 */
	public ItemStackExtra getKitItem(BadblockPlayer player);
	
	/**
	 * Récupère les achievements que doit avoir terminé le joueur pour obtenir le Kit au niveau donné
	 * @param level Le niveau du Kit
	 * @return Les achievements
	 */
	public PlayerAchievement[] getNeededAchievements(int level);
	
	/**
	 * Récupère le nombre de BadCoins nécessaires pour obtenir le kit au niveau donné
	 * @param level Le niveau du Kit
	 * @return
	 */
	public int getBadcoinsCost(int level);
	
	/**
	 * Récupère le niveau maximal du Kit
	 * @return
	 */
	public int getMaxLevel();
	
	/**
	 * Vérifie si le Kit est VIP. Si oui, aucun achievements / badcoins n'est nécessaire
	 * @return
	 */
	public boolean isVIP();
	
	/**
	 * Donne le Kit à un joueur (le niveau auquel le joueur à le droit sera recherché automatiquement).
	 * @param player Le joueur
	 */
	public void giveKit(BadblockPlayer player);
}
