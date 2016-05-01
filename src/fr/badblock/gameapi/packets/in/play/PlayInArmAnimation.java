package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;

/**
 * Packet envoyé par le joueur lorsque il bouge les bras. Aucun paramètre.
 * @author LeLanN
 */
public interface PlayInArmAnimation extends BadblockInPacket {
	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_ARM_ANIMATION;
	}
}
