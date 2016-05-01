package fr.badblock.gameapi.packets.out.play;

import org.bukkit.Location;

import fr.badblock.gameapi.packets.BadblockOutPacket;

public interface PlayEntityTeleport extends BadblockOutPacket {
	public int getEntityId();
	
	public PlayEntityTeleport setEntityId(int entityId);

	public Location getTo();
	
	public PlayEntityTeleport setTo(Location location);
	
	public boolean isOnGround();
	
	public PlayEntityTeleport setOnGround(boolean onGround);
}
