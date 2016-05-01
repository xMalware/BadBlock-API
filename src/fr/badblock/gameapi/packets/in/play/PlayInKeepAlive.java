package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;

/**
 * Packet envoyé pour vérifier que la connection entre le joueur et le client est toujours active.
 * @author LeLanN
 */
public interface PlayInKeepAlive extends BadblockInPacket {
	/**
	 * Une clé aléatoire envoyé précedemment par le serveur.
	 * @return La clé aléatoire
	 */
	public int getKeepAliveId();
	
	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_KEEPALIVE;
	}
}
