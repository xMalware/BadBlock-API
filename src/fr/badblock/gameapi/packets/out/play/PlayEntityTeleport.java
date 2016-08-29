package fr.badblock.gameapi.packets.out.play;

import org.bukkit.Location;

import fr.badblock.gameapi.packets.BadblockOutPacket;

public interface PlayEntityTeleport extends BadblockOutPacket {
	public int getEntityId();

	public Location getTo();

	public boolean isOnGround();

	public PlayEntityTeleport setEntityId(int entityId);

	public PlayEntityTeleport setOnGround(boolean onGround);

	public PlayEntityTeleport setTo(Location location);
}
