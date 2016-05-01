package fr.badblock.gameapi.packets.out.play;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.badblock.gameapi.packets.BadblockOutPacket;

/**
 * Packet envoy� lorsque une entit� re�oit un effet de potion
 * @author LeLanN
 */
public interface PlayEntityEffect extends BadblockOutPacket {
	/**
	 * R�cup�re l'ID de l'entit�
	 * @return L'ID
	 */
	public int getEntityId();
	
	/**
	 * D�finit l'ID de l'entit�
	 * @param id L'ID
	 * @return Le packet
	 */
	public PlayEntityEffect setEntityId(int id);
	
	/**
	 * Load le packet depuis un effet de potion Bukkit
	 * @param effect L'effet bukkit
	 * @return Le packet
	 */
	public PlayEntityEffect load(PotionEffect effect);
	
	/**
	 * R�cup�re le type d'effet
	 * @return Le type
	 */
	public PotionEffectType getPotionEffect();
	
	/**
	 * D�finit le type d'effet
	 * @param type Le type
	 * @return Le packet
	 */
	public PlayEntityEffect setPotionEffect(PotionEffectType type);
	
	/**
	 * R�cup�re le niveau
	 * @return Le niveau
	 */
	public byte getAmplifier();
	
	/**
	 * D�finit le niveau
	 * @param amplifier Le niveau
	 * @return Le packet
	 */
	public PlayEntityEffect setAmplifier(byte amplifier);
	
	/**
	 * R�cup�re la dur�e en secondes
	 * @return La dur�e
	 */
	public int getDurationInSeconds();
	
	/**
	 * D�finit la dur�ee en secondes
	 * @param duration La dur�e
	 * @return Le packet
	 */
	public PlayEntityEffect setDurationInSeconds(int duration);
	
	/**
	 * R�cup�re si il faut cacher les particules
	 * @return Si il faut cacher les particules
	 */
	public boolean isHideParticles();
	
	/**
	 * D�finit si il faut cacher les particules
	 * @param hide Si il faut les cacher
	 * @return Le packet
	 */
	public PlayEntityEffect setHideParticles(boolean hide);
}
