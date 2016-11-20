package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPackets;
import fr.badblock.gameapi.utils.selections.Vector3f;

/**
 * Packet envoy� pour d�finir la position et la direction regard�e
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

	/**
	 * La nouvelle position
	 * 
	 * @return La position
	 */
	public Vector3f getPosition();

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
