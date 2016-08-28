package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import fr.badblock.gameapi.utils.selections.Vector3f;

/**
 * Packet envoyé pour faire spawn des orbes d'expériences.
 * 
 * @author LeLanN
 */
public interface PlaySpawnEntityExperienceOrb extends BadblockOutPacket {
	public int getEntityId();

	public PlaySpawnEntityExperienceOrb setEntityId(int entityId);

	public Vector3f getPosition();

	public PlaySpawnEntityExperienceOrb setPosition(Vector3f vector);

	public short getOrbCount();

	public PlaySpawnEntityExperienceOrb setOrbCount(short orbCount);
}
