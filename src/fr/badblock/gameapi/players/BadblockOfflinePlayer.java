package fr.badblock.gameapi.players;

import org.bukkit.World;

import fr.badblock.gameapi.game.GameServer;
import fr.badblock.gameapi.game.GameServer.WhileRunningConnectionTypes;
import fr.badblock.gameapi.players.scoreboard.CustomObjective;

/**
 * Donne les anciennes données d'un joueur ayant déconnecté après le début de la
 * partie.<br>
 * Si le mini-jeu l'autorise, ces informations seront réutilisées (voir
 * {@link GameServer#whileRunningConnection(WhileRunningConnectionTypes)})
 * 
 * @author LeLanN
 */
public interface BadblockOfflinePlayer extends BadblockPlayerData {
	/**
	 * Récupère le CustomObjective vu par le joueur avant la déconnection
	 * 
	 * @return Le CustomObjective (null si non définit)
	 */
	public CustomObjective getCustomObjective();

	/**
	 * Récupère la fausse dimension dans laquelle le joueur était (afin de lui
	 * recharger au retour :o)
	 * 
	 * @return La fausse dimension
	 */
	public World.Environment getFalseDimension();
}
