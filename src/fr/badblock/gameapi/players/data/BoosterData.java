package fr.badblock.gameapi.players.data;

/**
 * Répresente les données du booster actif d'un joueur
 * @author xMalware
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
	 * Vérifie si le booster est encore valide ou non
	 * @return Un booléen
	 */
	public boolean isValid();
	
	/**
	 * Vérifie si le booster est expiré ou non
	 * @return Un booléen
	 */
	public boolean isExpired();
	
}
