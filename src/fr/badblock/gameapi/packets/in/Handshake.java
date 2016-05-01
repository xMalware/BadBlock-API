package fr.badblock.gameapi.packets.in;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;
import lombok.Getter;

/**
 * Premier packet envoyé par le joueur. Il n'est pas modifiable.
 * @author LeLanN
 */
public interface Handshake extends BadblockInPacket {
	/**
	 * Récupère la version du protocol souhaité par le joueur
	 * @return La version du protocol
	 */
	public int getProtocolVersion();
	
	/**
	 * Récupère l'addresse du serveur selon le joueur (contient aussi, avec Bungee, des informations sur le joueur)
	 * @return L'addresse du serveur
	 */
	public String getServerAddress();
	
	/**
	 * Récupère le port du serveur selon le joueur
	 * @return Le port du serveur
	 */
	public int getServerPort();

	/**
	 * Récupère le protocol que le joueur souhaite pour la prochaine étape
	 * @return La prochaine étape
	 */
	public NextState getNextState();
	
	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.HANDSHAKE;
	}
	
	/**
	 * Représente les différents protocoles pouvant être demandé par le joueur lors de l'handhsake
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
		
		@Getter private int id;
		
		NextState(int id){
			this.id = id;
		}
		
		public static NextState getById(int id){
			for(NextState state : values())
				if(state.getId() == id)
					return state;
			return null;
		}
	}
}
