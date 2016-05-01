package fr.badblock.gameapi.packets.out.play;

import org.bukkit.potion.PotionEffectType;

import fr.badblock.gameapi.packets.BadblockOutPacket;

/**
 * Packet envoyé pour enlevé un effet de potion d'une entité
 * @author LeLanN
 */
public interface PlayRemoveEntityEffect extends BadblockOutPacket {
	/**
	 * Récupère l'entité (id)
	 * @return L'id
	 */
	public int getEntityId();
	
	/**
	 * Définit l'entité (id)
	 * @param entityId L'id
	 * @return Le packet
	 */
	public PlayRemoveEntityEffect setEntityId(int entityId);
	
	/**
	 * Récupère l'effect à enlever
	 * @return L'effect
	 */
	public PotionEffectType getEffect();
	
	/**
	 * Définit l'effect à enlever
	 * @param effect L'effect
	 * @return Le packet
	 */
	public PlayRemoveEntityEffect setEffect(PotionEffectType effect);
}
