package fr.badblock.gameapi.packets.out.play;

import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import fr.badblock.gameapi.packets.BadblockOutPacket;

/**
 * Packet envoyé lorsque une entité reçoit un effet de potion
 * 
 * @author LeLanN
 */
public interface PlayEntityEffect extends BadblockOutPacket {
	/**
	 * Récupère le niveau
	 * 
	 * @return Le niveau
	 */
	public byte getAmplifier();

	/**
	 * Récupère la durée en secondes
	 * 
	 * @return La durée
	 */
	public int getDurationInSeconds();

	/**
	 * Récupère l'ID de l'entité
	 * 
	 * @return L'ID
	 */
	public int getEntityId();

	/**
	 * Récupère le type d'effet
	 * 
	 * @return Le type
	 */
	public PotionEffectType getPotionEffect();

	/**
	 * Récupère si il faut cacher les particules
	 * 
	 * @return Si il faut cacher les particules
	 */
	public boolean isHideParticles();

	/**
	 * Load le packet depuis un effet de potion Bukkit
	 * 
	 * @param effect
	 *            L'effet bukkit
	 * @return Le packet
	 */
	public PlayEntityEffect load(PotionEffect effect);

	/**
	 * Définit le niveau
	 * 
	 * @param amplifier
	 *            Le niveau
	 * @return Le packet
	 */
	public PlayEntityEffect setAmplifier(byte amplifier);

	/**
	 * Définit la duréee en secondes
	 * 
	 * @param duration
	 *            La durée
	 * @return Le packet
	 */
	public PlayEntityEffect setDurationInSeconds(int duration);

	/**
	 * Définit l'ID de l'entité
	 * 
	 * @param id
	 *            L'ID
	 * @return Le packet
	 */
	public PlayEntityEffect setEntityId(int id);

	/**
	 * Définit si il faut cacher les particules
	 * 
	 * @param hide
	 *            Si il faut les cacher
	 * @return Le packet
	 */
	public PlayEntityEffect setHideParticles(boolean hide);

	/**
	 * Définit le type d'effet
	 * 
	 * @param type
	 *            Le type
	 * @return Le packet
	 */
	public PlayEntityEffect setPotionEffect(PotionEffectType type);
}
