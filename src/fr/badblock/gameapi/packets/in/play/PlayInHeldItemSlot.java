package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;

/**
 * Packet envoyé par le joueur lorsque l'item séléctionné change.
 * @author LelanN
 */
public interface PlayInHeldItemSlot extends BadblockInPacket {
	/**
	 * Le nouveau slot (entre 0 et 8)
	 * @return Le slot
	 */
	public int getSlot();
	
	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_HELD_ITEM_SLOT;
	}
}
