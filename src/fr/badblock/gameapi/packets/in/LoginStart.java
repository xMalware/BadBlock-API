package fr.badblock.gameapi.packets.in;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;

/**
 * Packet envoyé aprčs le Handhsake par un joueur voulant se connecter.
 * 
 * @author LeLanN
 */
public interface LoginStart extends BadblockInPacket {
	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.LOGIN_START;
	}

	public String getUserName();
}
