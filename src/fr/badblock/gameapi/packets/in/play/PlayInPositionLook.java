package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;
import fr.badblock.gameapi.utils.selections.Vector3f;

/**
 * Packet envoyé pour définir la position et la direction regardée
 * @author LeLanN
 */
public interface PlayInPositionLook extends BadblockInPacket {
	/**
	 * La nouvelle position
	 * @return La position
	 */
	public Vector3f getPosition();
	
	/**
	 * Le nouveau yaw
	 * @return Le yaw
	 */
	public float getYaw();
	
	/**
	 * Le nouveau pitch
	 * @return Le pitch
	 */
	public float getPitch();

	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_POSITION_LOOK;
	}
}
