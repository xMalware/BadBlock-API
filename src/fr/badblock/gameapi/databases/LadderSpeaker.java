package fr.badblock.gameapi.databases;

import com.google.gson.JsonObject;

import fr.badblock.gameapi.game.GameState;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.utils.general.Callback;

/**
 * Classe permettant de communiquer de mani�re simple avec Ladder.
 * @author LeLanN
 */
public interface LadderSpeaker {
	/**
	 * R�cup�re les donn�es d'un joueur
	 * @param player Le joueur
	 * @param callback Appeler lorsque les donn�es sont re�ues
	 */
	public void getPlayerData(BadblockPlayer player, Callback<JsonObject> callback);
	
	/**
	 * R�cup�re les donn�es d'un joueur
	 * @param player Le joueur
	 * @param callback Appeler lorsque les donn�es sont re�ues
	 */
	public void getPlayerData(String player, Callback<JsonObject> callback);
	
	/**
	 * Change les donn�es d'un joueur
	 * @param player Le joueur
	 * @param toUpdate Les donn�es � update (ne doit pas n�cessairement �tre complet)
	 */
	public void updatePlayerData(BadblockPlayer player, JsonObject toUpdate);

	/**
	 * Change les donn�es d'un joueur
	 * @param player Le joueur
	 * @param toUpdate Les donn�es � update (ne doit pas n�cessairement �tre complet)
	 */
	public void updatePlayerData(String player, JsonObject toUpdate);
	
	/**
	 * R�cup�re les donn�es associ�es � l'IP d'un joueur
	 * @param player Le joueur
	 * @param callback Appeler lorsque les donn�es sont re�ues
	 */
	public void getIpPlayerData(BadblockPlayer player, Callback<JsonObject> callback);

	/**
	 * Change les donn�es associ�es � l'IP d'un joueur
	 * @param player Le joueur
	 * @param toUpdate Les donn�es � update (ne doit pas n�cessairement �tre complet)
	 */
	public void updateIpPlayerData(BadblockPlayer player, JsonObject toUpdate);
	
	/**
	 * Envoit un packet de keepalive � ladder
	 * @param state La state actuelle
	 * @param current Le nombre de joueurs actuel
	 * @param max Le nombre maximum de joueurs
	 */
	public void keepAlive(GameState state, int current, int max);
	
	/**
	 * Demande les permissions � Ladder.
	 */
	public void askForPermissions();
}
