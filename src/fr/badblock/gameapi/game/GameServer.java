package fr.badblock.gameapi.game;

/**
 * Classe permettant de gérer le statut des jeux
 * @author xMalware
 * @author LeLanN
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
}
