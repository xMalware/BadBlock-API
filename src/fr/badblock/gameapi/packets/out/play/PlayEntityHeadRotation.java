package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;

public interface PlayEntityHeadRotation extends BadblockOutPacket {
	public int getEntityId();

	public float getRotation();

	public PlayEntityHeadRotation setEntityId(int entityId);

	public PlayEntityHeadRotation setRotation(float rotation);
}
