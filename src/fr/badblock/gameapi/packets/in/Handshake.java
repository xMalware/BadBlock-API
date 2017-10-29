package fr.badblock.gameapi.packets.in;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;
import lombok.Getter;

/**
 * Premier packet envoyé par le joueur. Il n'est pas modifiable.
 * 
 * @author LeLanN
 */
public interface Handshake extends BadblockInPacket {
	/**
	 * Représente les différents protocoles pouvant ętre demandé par le joueur
	 * lors de l'handhsake
	 * 
	 * @author LeLanN
	 */
	public enum NextState {
		/**
		 * Le client veut ping le serveur
		 */
		STATUS(1),
		/**
		 * Le client veut jouer
		 */
		LOGIN(2);

		public static NextState getById(int id) {
			for (NextState state : values())
				if (state.getId() == id)
					return state;
			return null;
		}

		@Getter
		private int id;

		NextState(int id) {
			this.id = id;
		}
	}

	/**
	 * Récupčre le protocol que le joueur souhaite pour la prochaine étape
	 * 
	 * @return La prochaine étape
	 */
	public NextState getNextState();

	/**
	 * Récupčre la version du protocol souhaité par le joueur
	 * 
	 * @return La version du protocol
	 */
	public int getProtocolVersion();

	/**
	 * Récupčre l'addresse du serveur selon le joueur (contient aussi, avec
	 * Bungee, des informations sur le joueur)
	 * 
	 * @return L'addresse du serveur
	 */
	public String getServerAddress();

	/**
	 * Récupčre le port du serveur selon le joueur
	 * 
	 * @return Le port du serveur
	 */
	public int getServerPort();

	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.HANDSHAKE;
	}
}
