package fr.badblock.gameapi.packets.out.play;

import org.bukkit.util.Vector;

import fr.badblock.gameapi.packets.BadblockOutPacket;

public interface PlayEntityRelativeMove extends BadblockOutPacket {
	public int getEntityId();

	public Vector getMove();

	public boolean isOnGround();

	public PlayEntityRelativeMove setEntityId(int entityId);

	public PlayEntityRelativeMove setMove(Vector location);

	public PlayEntityRelativeMove setOnGround(boolean onGround);
}
