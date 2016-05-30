package fr.badblock.gameapi.databases;

import java.util.UUID;

import com.google.gson.JsonObject;

import fr.badblock.gameapi.game.GameState;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.utils.general.Callback;

/**
 * Classe permettant de communiquer de manière simple avec Ladder.
 * @author LeLanN
 */
public interface LadderSpeaker {
	/**
	 * Récupère les données d'un joueur
	 * @param player Le joueur
	 * @param callback Appeler lorsque les données sont reçues
	 */
	public void getPlayerData(BadblockPlayer player, Callback<JsonObject> callback);
	
	/**
	 * Récupère les données d'un joueur
	 * @param player Le joueur
	 * @param callback Appeler lorsque les données sont reçues
	 */
	public void getPlayerData(String player, Callback<JsonObject> callback);
	
	/**
	 * Change les données d'un joueur
	 * @param player Le joueur
	 * @param toUpdate Les données à update (ne doit pas nécessairement être complet)
	 */
	public void updatePlayerData(BadblockPlayer player, JsonObject toUpdate);

	/**
	 * Change les données d'un joueur
	 * @param player Le joueur
	 * @param toUpdate Les données à update (ne doit pas nécessairement être complet)
	 */
	public void updatePlayerData(String player, JsonObject toUpdate);
	
	/**
	 * Récupère les données associées à l'IP d'un joueur
	 * @param player Le joueur
	 * @param callback Appeler lorsque les données sont reçues
	 */
	public void getIpPlayerData(BadblockPlayer player, Callback<JsonObject> callback);

	/**
	 * Change les données associées à l'IP d'un joueur
	 * @param player Le joueur
	 * @param toUpdate Les données à update (ne doit pas nécessairement être complet)
	 */
	public void updateIpPlayerData(BadblockPlayer player, JsonObject toUpdate);
	
	/**
	 * Envoit un packet de keepalive à ladder
	 * @param state La state actuelle
	 * @param current Le nombre de joueurs actuel
	 * @param max Le nombre maximum de joueurs
	 */
	public void keepAlive(GameState state, int current, int max);
	
	/**
	 * Envoit une demande de nombre de joueurs
	 * @param servers Les serveurs (* = tous)
	 * @param count Les joueurs
	 */
	public void sendPing(String[] servers, Callback<Integer> count);
	
	/***
	 * Envoit une proposition (ou annulation) pour revenir à la partie
	 * @param uniqueId Le joueur
	 * @param invited Si le joueur peut encore venir
	 */
	public void sendReconnectionInvitation(UUID uniqueId, boolean invited);
	
	/**
	 * Demande les permissions à Ladder.
	 */
	public void askForPermissions();
}
