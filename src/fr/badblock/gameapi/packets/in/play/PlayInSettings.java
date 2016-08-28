package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;
import lombok.Getter;

/**
 * Packet envoyé par le client lorsqu'il change ses paramètres
 * 
 * @author LeLanN
 */
public interface PlayInSettings extends BadblockInPacket {
	/**
	 * Représente les différents modes de chats possibles
	 * 
	 * @author LeLanN
	 */
	public enum ChatMode {
		ENABLED(0), COMMANDS_ONLY(1), HIDDEN(2);

		public static ChatMode getById(int id) {
			for (ChatMode state : values())
				if (state.getId() == id)
					return state;
			return null;
		}

		@Getter
		private int id;

		ChatMode(int id) {
			this.id = id;
		}
	}

	/**
	 * Récupère m'activation du chat du joueur
	 * 
	 * @return L'activation
	 */
	public ChatMode getChatMode();

	/**
	 * Les parties du skin que le joueur affiche :
	 * <ul>
	 * <li>0x01 : Cape activée
	 * <li>0x02 : Veste activée
	 * <li>0x04 : Manche gauche activée
	 * <li>0x08 : Manche droite activée
	 * <li>0x10 : Jambière de gauche activée
	 * <li>0x20 : Jambière de droite activée
	 * <li>0x40 : Chapeau activé
	 * </ul>
	 * 
	 * @return
	 */
	public int getDisplayedSkinParts();

	/**
	 * La langue du joueur, sous la forme langue_PAYS (par exemple : fr_FR)
	 * 
	 * @return La langue
	 */
	public String getLocale();

	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_SETTINGS;
	}

	/**
	 * Récupère la view distance du joueur en chunk
	 * 
	 * @return La view distance
	 */
	public int getViewDistance();

	/**
	 * Si la couleur est activée dans le chat du joueur
	 * 
	 * @return Si la couleur est activée
	 */
	public boolean isColorEnabled();
}
