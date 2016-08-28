package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;

public interface PlayEntityLook extends BadblockOutPacket {
	public int getEntityId();

	public float getPitch();

	public float getYaw();

	public boolean isGround();

	public PlayEntityLook setEntityId(int entityId);

	public PlayEntityLook setGround(boolean ground);

	public PlayEntityLook setPitch(float pitch);

	public PlayEntityLook setYaw(float yaw);
}
