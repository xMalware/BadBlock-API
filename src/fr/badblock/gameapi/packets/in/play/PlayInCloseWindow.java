package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;

/**
 * Packet envoyé par le joueur quand il ferme un inventaire
 * @author LeLanN
 */
public interface PlayInCloseWindow extends BadblockInPacket {
	/**
	 * Récupère l'ID de l'inventaire (0 = inventaire du joueur)
	 * @return L'ID
	 */
	public int getWindowId();
	
	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_CLOSE_WINDOW;
	}
}
