package fr.badblock.gameapi.packets.out.play;

import org.bukkit.util.Vector;

import fr.badblock.gameapi.packets.BadblockOutPacket;

public interface PlayEntityRelativeMove extends BadblockOutPacket {
	public int getEntityId();

	public PlayEntityRelativeMove setEntityId(int entityId);

	public Vector getMove();

	public PlayEntityRelativeMove setMove(Vector location);

	public boolean isOnGround();

	public PlayEntityRelativeMove setOnGround(boolean onGround);
}
