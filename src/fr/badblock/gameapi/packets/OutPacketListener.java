package fr.badblock.gameapi.packets;

import java.lang.reflect.ParameterizedType;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Permet d'écouter de manière simple les packets allant au client
 * 
 * @author LeLanN
 *
 * @param <T>
 *            Le type de packet à écouter
 * @see fr.badblock.gameapi.GameAPI#listenAtPacket(OutPacketListener)
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class OutPacketListener<T extends BadblockOutPacket> extends PacketListener<T> {
	// TODO la classe BadBlockOutPackets
	/**
	 * Appel la classe avant le packet reçu
	 * 
	 * @param packet
	 *            La classe
	 */
	public abstract void listen(BadblockPlayer player, T packet);

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
	 * Permet de register le listener plus simplement
	 */
	public void register() {
		GameAPI.getAPI().listenAtPacket(this);
	}
}
