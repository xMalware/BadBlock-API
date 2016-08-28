package fr.badblock.gameapi.packets.in;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;

/**
 * Packet envoyé pour demandé le statut (joueurs, nombre de joueurs, motd,
 * version du protocol). N'a aucun argument.
 * 
 * @author LeLanN
 */
public interface StatusRequest extends BadblockInPacket {
	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.STATUS_REQUEST;
	}
}
