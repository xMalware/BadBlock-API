package fr.badblock.gameapi.packets.in.play;

import java.util.UUID;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;

/**
 * Packet envoyé par le client lorsqu'il veut se téléporté à un joueur en tant
 * que spectateur
 * 
 * @author LelanN
 */
public interface PlayInSpectate extends BadblockInPacket {
	/**
	 * Récupère l'UUID du joueur visé
	 * 
	 * @return L'UUID
	 */
	public UUID getPlayerUID();

	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_SPECCTATE;
	}
}
