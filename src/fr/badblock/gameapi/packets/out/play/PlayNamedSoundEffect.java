package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import fr.badblock.gameapi.utils.selections.Vector3f;

/**
 * Packet envoyé pour jouer un son
 * @author LeLanN
 */
public interface PlayNamedSoundEffect extends BadblockOutPacket {
	public String getSoundName();
	
	public PlayNamedSoundEffect setSoundName(String name);
	
	public Vector3f getPosition();
	
	public PlayNamedSoundEffect setPosition(Vector3f position);
	
	public float getVolume();
	
	public PlayNamedSoundEffect setVolume(float volume);
	
	public byte getPitch();
	
	public PlayNamedSoundEffect setPitch(byte volume);
}
