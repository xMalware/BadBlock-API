package fr.badblock.gameapi.packets.in;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;

/**
 * Packet envoyé après le Handhsake par un joueur voulant se connecter.
 * @author LeLanN
 */
public interface LoginStart extends BadblockInPacket {
	public String getUserName();
	
	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.LOGIN_START;
	}
}
