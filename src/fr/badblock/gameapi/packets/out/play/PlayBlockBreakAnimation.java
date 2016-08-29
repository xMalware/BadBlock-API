package fr.badblock.gameapi.packets.out.play;

import org.bukkit.block.Block;

import fr.badblock.gameapi.packets.BadblockOutPacket;

/**
 * Packet envoyé lorsqu'une entité casse un bloc afin de faire l'animation du
 * block qui se casse;
 * 
 * @author LeLanN
 */
public interface PlayBlockBreakAnimation extends BadblockOutPacket {
	/**
	 * Récupère le block sur lequel jouer l'animation
	 * 
	 * @return Le block
	 */
	public Block getBlock();

	/**
	 * Récupère l'id de l'entité
	 * 
	 * @return L'id
	 */
	public int getEntityId();

	/**
	 * Récupère la taille de la 'fissure'
	 * 
	 * @return La taille (voir {@link #setState(int)})
	 */
	public int getState();

	/**
	 * Définit le block sur lequel jouer l'animation
	 * 
	 * @param block
	 *            Le block
	 * @return Le packet
	 */
	public PlayBlockBreakAnimation setBlock(Block block);

	/**
	 * Définit l'id de l'entité
	 * 
	 * @param entityId
	 *            L'id
	 * @return Le packet
	 */
	public PlayBlockBreakAnimation setEntityId(int entityId);

	/**
	 * Définit la taille de la 'fissure'
	 * 
	 * @param state
	 *            La taille, de 0 à 9. Si c'est une autre valeur, plus
	 *            d'animation.
	 * @return Le packet
	 */
	public PlayBlockBreakAnimation setState(int state);
}
