package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;

/**
 * Packet envoyé par un joueur quand il écrit un message dans le chat (ou
 * commande).
 * 
 * @author LeLanN
 */
public interface PlayInChat extends BadblockInPacket {
	/**
	 * Le message envoyé par le joueur
	 * 
	 * @return Le message
	 */
	public String getMessage();

	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_CHAT;
	}
}
