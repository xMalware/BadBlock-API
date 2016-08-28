package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;

/**
 * Packet utiliser par le client pour update les abilities du joueur (ce qu'il
 * est autorisé à faire)
 * 
 * @author LeLanN
 */
public interface PlayAbilities extends BadblockOutPacket {
	/**
	 * Récupère la vitesse de vole du joueur
	 * 
	 * @return La vitesse
	 */
	public float getFlyingSpeed();

	/**
	 * Récupère la vitesse de marche du joueur
	 * 
	 * @return La vitesse
	 */
	public float getWalkingSpeed();

	/**
	 * Si le joueur est autorisé à voler
	 * 
	 * @return Un boolean
	 */
	public boolean isAllowedToFly();

	/**
	 * Si le joueur est en créatif
	 * 
	 * @return Un boolean
	 */
	public boolean isCreative();

	/**
	 * Si le joueur vole (seul paramètre utilisé)
	 * 
	 * @return Un boolean
	 */
	public boolean isFlying();

	/**
	 * Si le joueur est en godmode
	 * 
	 * @return Un boolean
	 */
	public boolean isGodmoded();
}
