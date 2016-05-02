package fr.badblock.gameapi.players;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;

import fr.badblock.gameapi.game.GameServer;
import fr.badblock.gameapi.game.GameServer.WhileRunningConnectionTypes;
import fr.badblock.gameapi.players.data.InGameData;
import fr.badblock.gameapi.players.data.PlayerData;
import fr.badblock.gameapi.utils.CustomObjective;

/**
 * Donne les anciennes données d'un joueur ayant déconnecté après le début de la partie.<br>
 * Si le mini-jeu l'autorise, ces informations seront réutilisées (voir {@link GameServer#whileRunningConnection(WhileRunningConnectionTypes)})
 * @author LeLanN
 */
public interface BadblockOfflinePlayer {
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
	 * La dernière position du joueur
	 * @return La dernière position
	 */
	public Location getLastLocation();
	
	/**
	 * Récupère la fausse dimension dans laquelle le joueur était (afin de lui recharger au retour :o)
	 * @return La fausse dimension
	 */
	public World.Environment getFalseDimension();
	
	/**
	 * Récupère le CustomObjective vu par le joueur avant la déconnection
	 * @return Le CustomObjective (null si non définit)
	 */
	public CustomObjective getCustomObjective();
	
	/**
	 * Récupère les données du joueur. Elles ne seront pas redemandées à Ladder (elles n'ont théoriquement pas changées)
	 * @return Les données
	 */
	public PlayerData getPlayerData();
	
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
}
