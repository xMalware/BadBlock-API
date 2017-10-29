package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import lombok.Getter;
import net.md_5.bungee.api.chat.BaseComponent;

/**
 * Packet envoy� lorsque l'on veut envoyer un message chat/actionbar au joueur
 * 
 * @author LeLanN
 */
public interface PlayChat extends BadblockOutPacket {
	/**
	 * Repr�sente les diff�rents types de messages envoyables gr�ce �
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
		 * Message (syst�me) dans le chat
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
	 * R�cup�re le message � envoyer
	 * 
	 * @return Les messages
	 */
	public String getContent();
	
	public BaseComponent[] getContentAsComponent();

	/**
	 * R�cup�re le type de message
	 * 
	 * @return Le type
	 */
	public ChatType getType();

	/**
	 * D�finit le message � envoyer
	 * 
	 * @param content
	 *            Le messages
	 * @return Le packet
	 */
	public PlayChat setContent(String content);
	
	public PlayChat setContent(BaseComponent... components);

	/**
	 * D�finit le type de message
	 * 
	 * @param type
	 *            Le type
	 * @return Le packet
	 */
	public PlayChat setType(ChatType type);
}
