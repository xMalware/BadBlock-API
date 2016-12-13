package fr.badblock.gameapi.game;

import java.util.Collection;

import fr.badblock.gameapi.events.api.PlayerReconnectionPropositionEvent;
import fr.badblock.gameapi.players.BadblockPlayerData;
import fr.badblock.gameapi.players.BadblockTeam;

/**
 * Classe permettant de gérer le statut des jeux
 * 
 * @authors xMalware & LeLanN
 */
public interface GameServer {
	/**
	 * Représente les différents types de reconnection
	 * 
	 * @author LeLanN
	 */
	public static enum WhileRunningConnectionTypes {
		/**
		 * Met le joueur en spectateur
		 */
		SPECTATOR,
		/**
		 * Récupère les données joueurs stockées si le joueur avait déco<br>
		 * Si cette politique n'est pas utilisable à chaque fois, vous pouvez
		 * cancel l'event {@link PlayerReconnectionPropositionEvent}
		 */
		BACKUP;
	}

	/**
	 * Si la phase du jeu change (entrée en Deathmatch par exemple), utiliser
	 * ceci pour cancel les propositions pour rejoindre le serveur<br>
	 * Autrement, utilisé automatiquement à la fin de la partie
	 */
	public void cancelReconnectionInvitations();

	/**
	 * Si une team perd, utiliser ceci pour cancel les propositions pour
	 * rejoindre le serveur
	 * 
	 * @param team
	 *            La team
	 */
	public void cancelReconnectionInvitations(BadblockTeam team);

	/**
	 * Récupère la date/heure de début du jeu (GameState = RUNNING)
	 * 
	 * @return La date/heure
	 */
	public String getGameBegin();

	/**
	 * Récupérer le statut de la partie
	 * 
	 * @return gameState Le statut
	 */
	public GameState getGameState();

	/**
	 * Récupére le nombre max de joueurs
	 * 
	 * @return Le nombre max de joueurs
	 */
	public int getMaxPlayers();

	/**
	 * Récupère les joueurs sauvegardés avec
	 * {@link #saveTeamsAndPlayersForResult()}
	 * 
	 * @return Une liste des tous les joueurs
	 */
	public Collection<BadblockPlayerData> getSavedPlayers();

	/**
	 * Récupère les teams sauvegardés avec
	 * {@link #saveTeamsAndPlayersForResult()}
	 * 
	 * @return Une liste des toutes les teams
	 */
	public Collection<BadblockTeam> getSavedTeams();

	/**
	 * Sauvegarde les teams/joueurs pour pouvoir les récupérer pour le résultat
	 * final
	 */
	public void saveTeamsAndPlayersForResult();

	/**
	 * Définir le statut de la partie
	 * 
	 * @param gameState
	 *            Le statut
	 */
	public void setGameState(GameState gameState);

	/**
	 * Définit le nombre max de joueurs
	 * 
	 * @param maxPlayers
	 *            Le nombre max de joueurs
	 */
	public void setMaxPlayers(int maxPlayers);

	/**
	 * Définit le traitement des joueurs si il se reconnecte après le début de
	 * la partie
	 * 
	 * @param type
	 *            Le type de traitement
	 */
	public void whileRunningConnection(WhileRunningConnectionTypes type);
	
	/**
	 * Récupère le TPS du serveur
	 * @return
	 */
	public double getPassmarkTps();
	
	/**
	 * Détermine si le serveur est joignable en pleine partie par Docker
	 * @return
	 */
	public boolean isJoinableWhenRunning();
	
	public void keepAlive();
	
}
