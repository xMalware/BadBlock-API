package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;

/**
 * Packet envoyé par le client lorsque un click dans un inventaire n'a pas été
 * accepté
 * 
 * @author LeLanN
 */
public interface PlayInTransaction extends BadblockInPacket {
	/**
	 * Le nombre unique de la transaction
	 * 
	 * @return Le nombre
	 */
	public short getActionNumber();

	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_TRANSACTION;
	}

	/**
	 * L'id de l'inventaire
	 * 
	 * @return L'ID
	 */
	public int getWindowId();

	/**
	 * Si c'est accepté
	 * 
	 * @return Un boolean
	 */
	public boolean isAccepted();
}
