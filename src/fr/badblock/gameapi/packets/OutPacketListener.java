package fr.badblock.gameapi.packets;

import java.lang.reflect.ParameterizedType;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Permet d'écouter de maničre simple les packets allant au client
 * 
 * @author LeLanN
 *
 * @param <T>
 *            Le type de packet ŕ écouter
 * @see fr.badblock.gameapi.GameAPI#listenAtPacket(OutPacketListener)
 */
@Data
@EqualsAndHashCode(callSuper = false)
public abstract class OutPacketListener<T extends BadblockOutPacket> extends PacketListener<T> {
	/**
	 * Récupčre la classe du packet listen. Utile uniquement en interne.
	 * 
	 * @return La classe
	 */
	@SuppressWarnings("unchecked")
	public Class<T> getGenericPacketClass() {
		return (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	// TODO la classe BadBlockOutPackets
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
