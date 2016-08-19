package fr.badblock.gameapi.players.data;

/**
 * R�presente les donn�es du booster actif d'un joueur
 * @author LeLanN
 */
public interface BoosterData {
	
	/**
	 * R�cup�re le bonus en coins (achetable ou durant un �v�nement) du joueur.
	 * @return Le bonus (� multiplier par le nombre normal)
	 */
	public int getCoinsMultiplier();

	/**
	 * R�cup�re le bonus en XP (en fonction du niveau) du joueur.
	 * @return Le bonus (� multiplier par le nombre normal)
	 */
	public int getXPMultiplier();
	
	/**
	 * R�cup�re le timestamp d'expiration du booster.
	 * @return Le timestamp d'expiration du booster
	 */
	public long getExpireTime();

	/**
	 * R�cup�re le fait que le booster est encore valide ou non
	 * @return Un bool�en
	 */
	public boolean isValid();
	
	/**
	 * R�cup�re le fait que le booster est expir� ou non
	 * @return Un bool�en
	 */
	public boolean isExpired();
	
}
