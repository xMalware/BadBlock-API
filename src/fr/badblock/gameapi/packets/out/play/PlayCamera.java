package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;

/**
 * Lorsque envoyé au joueur il 'devient' une autre entité (gamemode 3)
 * 
 * @author LeLanN
 */
public interface PlayCamera extends BadblockOutPacket {
	/**
	 * Récupère l'entité
	 * 
	 * @return L'ID
	 */
	public int getEntityId();

	/**
	 * Définit l'entité
	 * 
	 * @param entityId
	 *            L'ID
	 * @return Le packet
	 */
	public PlayCamera setEntityId(int entityId);
}
