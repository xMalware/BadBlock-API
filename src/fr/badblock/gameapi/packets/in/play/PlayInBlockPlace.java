package fr.badblock.gameapi.packets.in.play;

import org.bukkit.inventory.ItemStack;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;
import fr.badblock.gameapi.packets.in.play.PlayInBlockDig.BlockFace;
import fr.badblock.gameapi.utils.selections.Vector3f;

/**
 * Packet envoyé par le joueur quand il pose un block
 * 
 * @author LeLanN
 */
public interface PlayInBlockPlace extends BadblockInPacket {
	/**
	 * Récupčre la face du block cliqué
	 * 
	 * @return La face
	 */
	public BlockFace getBlockFace();

	/**
	 * Récupčre la position du block
	 * 
	 * @return La position
	 */
	public Vector3f getBlockPosition();

	/**
	 * Récupčre la position du block cliqué
	 * 
	 * @return La position du curseur
	 */
	public Vector3f getCursorPosition();

	/**
	 * Récupčre l'item
	 * 
	 * @return L'item
	 */
	public ItemStack getItemStack();

	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_BLOCK_PLACE;
	}
}
