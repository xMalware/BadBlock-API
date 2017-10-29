package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;
import lombok.Getter;

/**
 * Packet envoyé dans certains cas particuliers (voir {@link ClientCommands} par
 * le client.
 * 
 * @author LeLanN
 */
public interface PlayInClientCommand extends BadblockInPacket {
	/**
	 * Représente les différentes actions pour {@link PlayInClientCommand}.
	 * 
	 * @author LeLanN
	 */
	public enum ClientCommands {
		PERFORM_RESPAWN(0), REQUEST_STATS(1), OPEN_INVENTORY_ACHIEVEMENT(2);

		public static ClientCommands getById(int id) {
			for (ClientCommands state : values())
				if (state.getId() == id)
					return state;
			return null;
		}

		@Getter
		private int id;

		ClientCommands(int id) {
			this.id = id;
		}
	}

	/**
	 * Récupčre l'action demandée par le packet
	 * 
	 * @return L'action
	 */
	public ClientCommands getAction();

	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_CLIENT_COMMAND;
	}
}
