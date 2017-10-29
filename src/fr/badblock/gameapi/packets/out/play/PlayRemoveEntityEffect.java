package fr.badblock.gameapi.packets.out.play;

import org.bukkit.potion.PotionEffectType;

import fr.badblock.gameapi.packets.BadblockOutPacket;

/**
 * Packet envoyé pour enlevé un effet de potion d'une entité
 * 
 * @author LeLanN
 */
public interface PlayRemoveEntityEffect extends BadblockOutPacket {
	/**
	 * Récupčre l'effect ŕ enlever
	 * 
	 * @return L'effect
	 */
	public PotionEffectType getEffect();

	/**
	 * Récupčre l'entité (id)
	 * 
	 * @return L'id
	 */
	public int getEntityId();

	/**
	 * Définit l'effect ŕ enlever
	 * 
	 * @param effect
	 *            L'effect
	 * @return Le packet
	 */
	public PlayRemoveEntityEffect setEffect(PotionEffectType effect);

	/**
	 * Définit l'entité (id)
	 * 
	 * @param entityId
	 *            L'id
	 * @return Le packet
	 */
	public PlayRemoveEntityEffect setEntityId(int entityId);
}
