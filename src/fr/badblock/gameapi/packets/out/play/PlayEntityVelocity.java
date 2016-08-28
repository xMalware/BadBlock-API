package fr.badblock.gameapi.packets.out.play;

import org.bukkit.util.Vector;

import fr.badblock.gameapi.packets.BadblockOutPacket;

public interface PlayEntityVelocity extends BadblockOutPacket {
	public int getEntityId();

	public PlayEntityVelocity setEntityId(int entityId);

	public Vector getVelocity();

	public PlayEntityVelocity setVelocity(Vector vector);
}
