package fr.badblock.gameapi.packets;

import java.lang.reflect.ParameterizedType;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.players.BadblockPlayer;

/**
 * Permet d'écouter de manière simple les packets venant du client
 * 
 * @author LeLanN
 *
 * @param <T>
 *            Le type de packet à écouter
 * @see fr.badblock.gameapi.GameAPI#listenAtPacket(InPacketListener)
 */
public abstract class InPacketListener<T extends BadblockInPacket> extends PacketListener<T> {

	/**
	 * Récupère la classe du packet listen. Utile uniquement en interne.
	 * 
	 * @return La classe
	 */
	@SuppressWarnings("unchecked")
	public Class<T> getGenericPacketClass() {
		return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * Appel la classe avant le packet reçu
	 * 
	 * @param packet
	 *            La classe
	 */
	public abstract void listen(BadblockPlayer player, T packet);

	/**
	 * Permet de register le listener plus simplement
	 */
	public void register() {
		GameAPI.getAPI().listenAtPacket(this);
	}
}
