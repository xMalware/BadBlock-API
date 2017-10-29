package fr.badblock.gameapi.packets;

import fr.badblock.gameapi.players.BadblockPlayer;

/**
 * Permet d'écouter de maničre simple les packets venant du client
 * 
 * @author LeLanN
 *
 * @param <T>
 *            Le type de packet ŕ écouter
 * @see fr.badblock.gameapi.GameAPI#listenAtPacket(GlobalPacketListener)
 */
public abstract class GlobalPacketListener {

	/**
	 * Appel la classe avant le packet reçu
	 * 
	 * @param packet
	 *            La classe
	 */
	public abstract void listen(BadblockPlayer player, BadblockInPacket packet);
	
	/**
	 * Appel la classe avant le packet reçu
	 * 
	 * @param packet
	 *            La classe
	 */
	public abstract void listen(BadblockPlayer player, BadblockOutPacket packet);

}
