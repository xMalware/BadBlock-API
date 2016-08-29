package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;

/**
 * Packet envoyé par un joueur pour controller un véhicule
 * 
 * @author LelanN
 */
public interface PlayInSteerVehicle extends BadblockInPacket {
	/**
	 * Récupère la valeur du mouvement haut/bas demandé (positif = vers l'avant)
	 * 
	 * @return La valeur
	 */
	public float getForward();

	/**
	 * Récupère la valeur du mouvement latéral demandé (positif = gauche)
	 * 
	 * @return La valeur
	 */
	public float getSideways();

	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_STEER_VEHICLE;
	}

	/**
	 * Si le joueur veut que le véhicule saute
	 * 
	 * @return Un boolean
	 */
	public boolean isJump();

	/**
	 * Si le joueur veut descendre
	 * 
	 * @return Un boolean
	 */
	public boolean isUnmount();
}
