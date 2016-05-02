package fr.badblock.gameapi.game;

import fr.badblock.gameapi.events.api.PlayerReconnectionPropositionEvent;

/**
 * Classe permettant de gérer le statut des jeux
 * @author LeLanN
 * @author xMalware
 */
public interface GameServer {
	/**
	 * Définir le statut de la partie
	 * @param gameState Le statut
	 */
	public void setGameState(GameState gameState);

	/**
	 * Récupérer le statut de la partie
	 * @return gameState Le statut
	 */
	public GameState getGameState();
	
	/**
	 * Définit le nombre max de joueurs
	 * @param maxPlayers Le nombre max de joueurs
	 */
	public void setMaxPlayers(int maxPlayers);

	/**
	 * Récupére le nombre max de joueurs
	 * @return Le nombre max de joueurs
	 */
	public int getMaxPlayers();
	
	/**
	 * Définit le traitement des joueurs si il se reconnecte après le début de la partie
	 * @param type Le type de traitement
	 */
	public void whileRunningConnection(WhileRunningConnectionTypes type);
	
	/**
	 * Si la phase du jeu change (entrée en Deathmatch par exemple), utiliser ceci pour cancel les propositions pour rejoindre le serveur<br>
	 * Autrement, utilisé automatiquement à la fin de la partie
	 */
	public void cancelReconnectionInvatations();
	
	/**
	 * Représente les différents types de reconnection
	 * @author LeLanN
	 */
	public static enum WhileRunningConnectionTypes {
		/**
		 * Met le joueur en spectateur
		 */
		SPECTATOR,
		/**
		 * Récupère les données joueurs stockées si le joueur avait déco<br>
		 * Si cette politique n'est pas utilisable à chaque fois, vous pouvez cancel l'event {@link PlayerReconnectionPropositionEvent}
		 */
		BACKUP;
	}
}
