package fr.badblock.gameapi.utils.itemstack;

import fr.badblock.gameapi.players.BadblockPlayer;

/**
 * Appelé lorsqu'un ItemStackExtra est utilisé
 * @author LeLanN
 */
public abstract class ItemEvent {
	/**
	 * Appelée quand l'item est utilisé
	 * @param action L'action
	 * @param player Le joueur
	 * @return Si l'action doit être annulée
	 */
	public abstract boolean call(ItemAction action, BadblockPlayer player);
}
