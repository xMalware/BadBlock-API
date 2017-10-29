package fr.badblock.gameapi.packets;

import fr.badblock.gameapi.players.BadblockPlayer;

/**
 * Représente un packet MineCraft, allant du serveur au client, tel qu'il est
 * géré par l'API.<br>
 * Ces packets ne sont pas intercéptés et sont simplement envoyé sans passer par
 * un traitement via le serveur.<br>
 * Ils peuvent ętre trčs utiles, par exemple pour les fausses entités ou les
 * titles non géré par l'API Bukkit/Spigot en 1.8.<br>
 * <br>
 * De ce fait tous les packets ne sont pas représentés. En effet :
 * <ul>
 * <li>Pour certains, l'API Bukkit/Spigot est largement suffisante (par exemple
 * [Player].spigot().respawn() pour le packet de respawn)</li>
 * <li>Pour d'autres, il peut ętre 'dangeureux' ou simplement inefficace
 * d'envoyé le packet sans que le serveur en soit informé</li>
 * </ul>
 * <br>
 * Si un packet n'est pas assez documenté, penser ŕ se documenter grâce ŕ
 * http://wiki.vg/Protocol<br>
 * Pour instancier un packet, voir
 * {@link fr.badblock.gameapi.GameAPI#createPacket(Class)}<br>
 * La classe n'est pas ŕ confondre avec {@link BadblockInPacket}
 * 
 * @author LeLanN
 */
public interface BadblockOutPacket extends BadblockPacket {
	/**
	 * Envoit le packet ŕ un joueur.
	 * 
	 * @param player
	 *            Le joueur
	 */
	public void send(BadblockPlayer player);
}
