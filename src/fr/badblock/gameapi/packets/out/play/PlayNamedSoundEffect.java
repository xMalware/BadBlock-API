package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import fr.badblock.gameapi.utils.selections.Vector3f;

/**
 * Packet envoyé pour jouer un son
 * 
 * @author LeLanN
 */
public interface PlayNamedSoundEffect extends BadblockOutPacket {
	public byte getPitch();

	public Vector3f getPosition();

	public String getSoundName();

	public float getVolume();

	public PlayNamedSoundEffect setPitch(byte volume);

	public PlayNamedSoundEffect setPosition(Vector3f position);

	public PlayNamedSoundEffect setSoundName(String name);

	public PlayNamedSoundEffect setVolume(float volume);
}
