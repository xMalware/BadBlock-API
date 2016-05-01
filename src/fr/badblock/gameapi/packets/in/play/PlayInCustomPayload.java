package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;

/**
 * Packet de 'plugin message'
 * @author LeLanN
 */
public interface PlayInCustomPayload extends BadblockInPacket {
	/**
	 * Récupère le channel du plugin message
	 * @return Le channel
	 */
	public String getChannel();
	
	/**
	 * Récupère les données du plugin message
	 * @return Les données
	 */
	public byte[] getData();
	
	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_CUSTOM_PAYLOAD;
	}
}
