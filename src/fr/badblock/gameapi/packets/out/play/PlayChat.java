package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import lombok.Getter;

/**
 * Packet envoyé lorsque l'on veut envoyer un message chat/actionbar au joueur
 * 
 * @author LeLanN
 */
public interface PlayChat extends BadblockOutPacket {
	/**
	 * Représente les différents types de messages envoyables grâce ŕ
	 * {@link PlayChat}
	 * 
	 * @author LeLanN
	 */
	public enum ChatType {
		/**
		 * Message dans le chat
		 */
		CHAT(0),
		/**
		 * Message (systčme) dans le chat
		 */
		SYSTEM(1),
		/**
		 * ActionBar
		 */
		ACTION(2);

		public static ChatType getByValue(byte value) {
			for (ChatType c : values())
				if (c.getValue() == value)
					return c;
			return null;
		}

		@Getter
		private byte value;

		ChatType(int value) {
			this.value = (byte) value;
		}
	}

	/**
	 * Récupčre le message ŕ envoyer
	 * 
	 * @return Les messages
	 */
	public String getContent();

	/**
	 * Récupčre le type de message
	 * 
	 * @return Le type
	 */
	public ChatType getType();

	/**
	 * Définit le message ŕ envoyer
	 * 
	 * @param content
	 *            Le messages
	 * @return Le packet
	 */
	public PlayChat setContent(String content);

	/**
	 * Définit le type de message
	 * 
	 * @param type
	 *            Le type
	 * @return Le packet
	 */
	public PlayChat setType(ChatType type);
}
