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
	
}
