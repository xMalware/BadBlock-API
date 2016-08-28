package fr.badblock.gameapi.packets.in;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;

/**
 * Packet envoyé pour connaître le ping du joueur avec le serveur (temps de
 * réponse).
 * 
 * @author LeLanN
 */
public interface StatusPing extends BadblockInPacket {
	/**
	 * Un nombre qui n'a pas de réelle valeur, simplement utilisé pour être sûr
	 * que la réponse au ping est la bonne.<br>
	 * 
	 * @return Le nombre
	 */
	public long getLongValue();

	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.STATUS_PING;
	}
}
