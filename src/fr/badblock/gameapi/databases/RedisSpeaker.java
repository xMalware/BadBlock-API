package fr.badblock.gameapi.databases;

import com.google.gson.JsonObject;

import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.utils.general.Callback;

/**
 * Classe permettant de communiquer de maničre simple avec Redis.
 * 
 * @author xMalware
 */
public interface RedisSpeaker {
	
	/**
	 * Demande les permissions ŕ Redis.
	 */
	public void askForPermissions();

	/**
	 * Récupčre les données associées ŕ l'IP d'un joueur
	 * 
	 * @param player
	 *            Le joueur
	 * @param callback
	 *            Appeler lorsque les données sont reçues
	 */
	public void getIpPlayerData(BadblockPlayer player, Callback<JsonObject> callback);

	/**
	 * Récupčre les données d'un joueur
	 * 
	 * @param player
	 *            Le joueur
	 * @param callback
	 *            Appeler lorsque les données sont reçues
	 */
	public void getPlayerData(BadblockPlayer player, Callback<JsonObject> callback);

	/**
	 * Envoit une demande de nombre de joueurs
	 * 
	 * @param servers
	 *            Les serveurs (* = tous)
	 * @param count
	 *            Les joueurs
	 */
	public void sendPing(String[] servers, Callback<Integer> count);

	/***
	 * Envoit une proposition (ou annulation) pour revenir ŕ la partie
	 * 
	 * @param name
	 *            Le joueur
	 * @param invited
	 *            Si le joueur peut encore venir
	 */
	public void sendReconnectionInvitation(String name, boolean invited);

	/**
	 * Change les données associées ŕ l'IP d'un joueur
	 * 
	 * @param player
	 *            Le joueur
	 * @param toUpdate
	 *            Les données ŕ update (ne doit pas nécessairement ętre complet)
	 */
	public void updateIpPlayerData(BadblockPlayer player, JsonObject toUpdate);

	/**
	 * Change les données d'un joueur
	 * 
	 * @param player
	 *            Le joueur
	 * @param toUpdate
	 *            Les données ŕ update (ne doit pas nécessairement ętre complet)
	 */
	public void updatePlayerData(BadblockPlayer player, JsonObject toUpdate);

}
