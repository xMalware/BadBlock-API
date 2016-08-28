package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;

/**
 * Packet envoyé par le joueur lorsqu'il enchant un item
 * 
 * @author LeLanN
 */
public interface PlayInEnchantItem extends BadblockInPacket {
	/**
	 * Récupère l'inventaire de la table d'enchantement
	 * 
	 * @return L'inventaire
	 */
	public int getWindowId();

	/**
	 * Récupère l'enchantement choisit (0 = celui du haut)
	 * 
	 * @return L'enchantement
	 */
	public int getEnchant();

	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_ENCHANT_ITEM;
	}
}
