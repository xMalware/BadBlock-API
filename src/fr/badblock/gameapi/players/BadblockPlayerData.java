package fr.badblock.gameapi.players;

import java.util.UUID;

import fr.badblock.gameapi.players.data.InGameData;
import fr.badblock.gameapi.players.data.PlayerData;
import fr.badblock.gameapi.utils.i18n.TranslatableString;

/**
 * Représente les données d'un joueur
 * @author LeLanN
 */
public interface BadblockPlayerData {
	/**
	 * Récupère l'UUID du joueur
	 * @return L'UUID
	 */
	public UUID getUniqueId();
	
	/**
	 * Récupère le pseudo du joueur
	 * @return Le pseudo
	 */
	public String getName();
	
	/**
	 * Récupère les données du joueur. Elles ne seront pas redemandées à Ladder (elles n'ont théoriquement pas changées)
	 * @return Les données
	 */
	public PlayerData getPlayerData();
	
	/**
	 * Récupère le préfixe (par exemple [Admin]) pour afficher le nom du group du joueur
	 * @return Le préfixe
	 */
	public TranslatableString getGroupPrefix();
	
	/**
	 * Récupère le préfixe (par exemple [Admin]) pour afficher le nom du group du joueur en tablist
	 * @return Le préfixe
	 */
	public TranslatableString getTabGroupPrefix();
	
	/**
	 * Récupère les données ingame du joueur, avant sa déconnection. Attention, la classe fournie doit avoir un constructeur sans arguments.
	 * 
	 * @param clazz La classe implémentant InGameData
	 * @return Les données joueurs (ou null si la classe donnée n'est pas correcte)
	 */
	public <T extends InGameData> T inGameData(Class<T> clazz);
	
	/**
	 * Récupère la team du joueur avant sa déconnection
	 * @return La team
	 */
	public BadblockTeam getTeam();
	
	/**
	 * Définit la team du joueur. A ne pas utiliser pour la changer réellement, simplement une valeur de stockage.
	 * @param team La team
	 */
	public void setTeam(BadblockTeam team);
}
