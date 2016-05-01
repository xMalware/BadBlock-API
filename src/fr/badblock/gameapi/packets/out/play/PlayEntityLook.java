package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;

public interface PlayEntityLook extends BadblockOutPacket {
	public int getEntityId();
	
	public PlayEntityLook setEntityId(int entityId);
	
	public float getYaw();
	
	public PlayEntityLook setYaw(float yaw);
	
	public float getPitch();
	
	public PlayEntityLook setPitch(float pitch);
	
	public boolean isGround();
	
	public PlayEntityLook setGround(boolean ground);
}
