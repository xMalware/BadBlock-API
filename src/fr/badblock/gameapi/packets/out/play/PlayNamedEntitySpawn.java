package fr.badblock.gameapi.packets.out.play;

import org.bukkit.entity.Player;

import fr.badblock.gameapi.packets.BadblockOutPacket;

/**
 * Packet envoyé au joueur quand un autre joueur entre dans son champs de vision
 * @author LeLanN
 */
public interface PlayNamedEntitySpawn extends BadblockOutPacket {
	/**
	 * Récupère le joueur qui doit être affiché
	 * @return Le joueur
	 */
	public Player getPlayer();
	
	/**
	 * Définit le joueur qui doit être affiché
	 * @param player Le joueur
	 * @return Le packet
	 */
	public PlayNamedEntitySpawn setPlayer(Player player);
}
