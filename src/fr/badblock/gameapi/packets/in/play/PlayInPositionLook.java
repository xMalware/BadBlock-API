package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPackets;

/**
 * Packet envoyé pour définir la position et la direction regardée
 * 
 * @author LeLanN
 */
public interface PlayInPositionLook extends PlayInFlying {
	/**
	 * Le nouveau pitch
	 * 
	 * @return Le pitch
	 */
	public float getPitch();

	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_POSITION_LOOK;
	}

	/**
	 * Le nouveau yaw
	 * 
	 * @return Le yaw
	 */
	public float getYaw();
}
