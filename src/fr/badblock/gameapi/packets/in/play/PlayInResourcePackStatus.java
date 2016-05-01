package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;
import lombok.Getter;

/**
 * Packet envoyé par le joueur pour donner le statut du téléchargement du ressource pack.
 * @author LeLanN
 */
public interface PlayInResourcePackStatus extends BadblockInPacket {
	/**
	 * Une clé envoyé par le packet pour envoyé le resource pack
	 * @return La clé
	 */
	public String getHash();

	/**
	 * Récupère le statut actuel du téléchargement
	 * @return Le statut
	 */
	public ResourcePackStatus getStatus();
	
	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_RESOURCEPACK_STATUS;
	}
	
	/**
	 * Représente les différents statuts possible pour le ressource pack
	 * @author LeLanN
	 */
	public enum ResourcePackStatus {
		SUCCESSFULLY_LOADED(0),
		DECLINED(1),
		FAILED_DOWNLOAD(2),
		ACCEPTED(3);
		
		@Getter private int id;
		
		ResourcePackStatus(int id){
			this.id = id;
		}
		
		public static ResourcePackStatus getById(int id){
			for(ResourcePackStatus state : values())
				if(state.getId() == id)
					return state;
			return null;
		}
	}

}
