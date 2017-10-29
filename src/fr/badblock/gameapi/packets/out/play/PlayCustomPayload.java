package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;

/**
 * Packet de 'plugin message'
 * 
 * @author LeLanN
 */
public interface PlayCustomPayload extends BadblockOutPacket {
	/**
	 * Récupčre le channel du plugin message
	 * 
	 * @return Le channel
	 */
	public String getChannel();

	/**
	 * Récupčre les données du plugin message
	 * 
	 * @return Les données
	 */
	public byte[] getData();
}
