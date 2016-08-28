package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;

/**
 * Packet envoyé par le client quand il se met à voler. La plupart des
 * informations du packets sont inutiles.
 * 
 * @author LeLanN
 */
public interface PlayInAbilities extends BadblockInPacket {
	/**
	 * Récupère la vitesse de vole du joueur
	 * 
	 * @return La vitesse
	 */
	public float getFlyingSpeed();

	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_ABILITIES;
	}

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
