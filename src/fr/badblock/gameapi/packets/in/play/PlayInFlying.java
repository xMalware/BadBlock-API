package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;
import fr.badblock.gameapi.utils.selections.Vector3f;

/**
 * Packet envoyé pour définir la position
 * 
 * @author LeLanN
 */
public interface PlayInFlying extends BadblockInPacket {
	/**
	 * La nouvelle position
	 * 
	 * @return La position
	 */
	public Vector3f getPosition();
	
	public boolean hasPosition();
	
	public boolean hasLook();
	
	public boolean isOnGround();

	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_FLYING;
	}
}
