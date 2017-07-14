package fr.badblock.gameapi.packets;

import fr.badblock.gameapi.players.BadblockPlayer;

/**
 * Permet d'écouter de manière simple les packets venant du client
 * 
 * @author LeLanN
 *
 * @param <T>
 *            Le type de packet à écouter
 * @see fr.badblock.gameapi.GameAPI#listenAtPacket(GlobalPacketListener)
 */
public abstract class GlobalPacketListener {

	/**
	 * Appel la classe avant le packet reçu
	 * 
	 * @param packet
	 *            La classe
	 */
	public abstract void listen(BadblockPlayer player, Object packet);

}
