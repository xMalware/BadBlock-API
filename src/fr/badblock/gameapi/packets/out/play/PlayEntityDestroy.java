package fr.badblock.gameapi.packets.out.play;

import org.bukkit.entity.Entity;

import fr.badblock.gameapi.packets.BadblockOutPacket;

/**
 * Packet envoyé au joueur quand des entités sont détruites (c'est à dire, {@link Entity#remove()})
 * @author LeLanN
 */
public interface PlayEntityDestroy extends BadblockOutPacket {
	/**
	 * Récupère entités détruites
	 * @return Les entités
	 */
	public int[] getEntities();
	
	/**
	 * Définit les entités détruites
	 * @param entities Les entités
	 * @return Le packet
	 */
	public PlayEntityDestroy setEntities(int[] entities);
}
