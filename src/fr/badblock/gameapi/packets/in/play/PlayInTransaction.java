package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;

/**
 * Packet envoyé par le client lorsque un click dans un inventaire n'a pas été accepté
 * @author LeLanN
 */
public interface PlayInTransaction extends BadblockInPacket {
	/**
	 * L'id de l'inventaire
	 * @return L'ID
	 */
	public int getWindowId();
	
	/**
	 * Le nombre unique de la transaction
	 * @return Le nombre
	 */
	public short getActionNumber();
	
	/**
	 * Si c'est accepté
	 * @return Un boolean
	 */
	public boolean isAccepted();
	
	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_TRANSACTION;
	}
}
