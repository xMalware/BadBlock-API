package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;

public interface PlayEntityHeadRotation extends BadblockOutPacket {
	public int getEntityId();

	public PlayEntityHeadRotation setEntityId(int entityId);

	public float getRotation();

	public PlayEntityHeadRotation setRotation(float rotation);
}
