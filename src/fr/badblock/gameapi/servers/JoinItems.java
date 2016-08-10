package fr.badblock.gameapi.servers;

import java.io.File;
import java.util.Map;

import fr.badblock.gameapi.players.kits.PlayerKit;
import fr.badblock.gameapi.run.BadblockGame;

/**
 * Classe permettant de gérer les items donnés au joueur leur du join. A utiliser dans le onEnable() :o
 * @author LeLanN
 */
public interface JoinItems {
	/**
	 * Demande à l'API de gérer l'item de kit
	 * @param slot Le slot dans lequel mettre l'item à la connection
	 * @param kits Les kits chargés par le plgin
	 * @param kitListConfig Le fichier de configuration pour savoir l'ordre des kits dans l'inventaire
	 */
	public void registerKitItem(int slot, Map<String, PlayerKit> kits, File kitListConfig);

	/**
	 * Demande à l'API de gérer l'item de team (à appeler après avoir charger les teams)
	 * @param slot Le slot
	 * @param teamListConfig Le fichier de configuration pour savoir l'ordre des teams dans l'inventaire
	 */
	public void registerTeamItem(int slot, File teamListConfig);
	
	/**
	 * Demande à l'API de gérer l'item de vote (choix de la map)
	 * @param slot Le slot
	 */
	public void registerVoteItem(int slot);
	
	/**
	 * Demande à l'API de gérer l'item permettant de voir les achievements
	 * @param slot Le slot
	 * @param game Le jeu
	 */
	public void registerAchievementsItem(int slot, BadblockGame game);
	
	/**
	 * Demande à l'API de gérer l'item pour quitter la partie
	 * @param slot Le slot
	 * @param fallbackServer Le serveur sur lequel téléporter le joueur
	 */
	public void registerLeaveItem(int slot, String fallbackServer);
	
	/**
	 * Clear l'inventaire du  joueur à la connection (par défaut activé)
	 */
	public void doClearInventory(boolean clear);
	
	/**
	 * A appeler lorsqu'il faut arrêter les actions demandées (par exemple, lorsque la partie commence)
	 */
	public void end();
}
