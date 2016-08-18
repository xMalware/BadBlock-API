package fr.badblock.gameapi.players.data;

import com.google.gson.JsonObject;

import fr.badblock.gameapi.achievements.PlayerAchievement;
import fr.badblock.gameapi.players.kits.PlayerKit;
import fr.badblock.gameapi.utils.i18n.Locale;

/**
 * Répresente les données du booster actif d'un joueur
 * @author LeLanN
 */
public interface BoosterData {
	
	/**
	 * Récupère le bonus en coins (achetable ou durant un évènement) du joueur.
	 * @return Le bonus (à multiplier par le nombre normal)
	 */
	public int getCoinsMultiplier();

	/**
	 * Récupère le bonus en XP (en fonction du niveau) du joueur.
	 * @return Le bonus (à multiplier par le nombre normal)
	 */
	public int getXPMultiplier();
	
	/**
	 * Récupère le timestamp d'expiration du booster.
	 * @return Le timestamp d'expiration du booster
	 */
	public long getExpireTime();

	/**
	 * Récupère le fait que le booster est encore valide ou non
	 * @return Un booléen
	 */
	public boolean isValid();
	
	/**
	 * Récupère le fait que le booster est expiré ou non
	 * @return Un booléen
	 */
	public boolean isExpired();
	
	/**
	 * Récupère des données joueurs spécialisées.<br>
	 * Attentin, si la clé à déjà été chargée avec une autre classe, peut provoquer une erreur.
	 * @param key La clé
	 * @param clazz La classe
	 * @return Le PlayerData
	 */
	public <T extends GameData> T gameData(String key, Class<T> clazz);
	
	/**
	 * Renvoit les informations modifiées à Ladder pour qu'elles soient sauvegardées.
	 * @return L'objet
	 */
	public JsonObject saveData();
}
