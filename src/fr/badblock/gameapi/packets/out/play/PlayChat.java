package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import lombok.Getter;
import net.md_5.bungee.api.chat.BaseComponent;

/**
<<<<<<< HEAD
 * Packet envoy� lorsque l'on veut envoyer un message chat/actionbar au joueur
=======
 * Packet envoyé lorsque l'on veut envoyer un message chat/actionbar au joueur
>>>>>>> branch 'master' of http://gitlab.badblock-network.fr/API0/BadblockAPI.git
 * 
 * @author LeLanN
 */
public interface PlayChat extends BadblockOutPacket {
	/**
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
	 * @return Les messages
	 */
	public String getContent();
	
	public BaseComponent[] getContentAsComponent();

	/**
	 * 
	 * @return Le type
	 */
	public ChatType getType();

	/**
	 * 
	 * @param content
	 *            Le messages
	 * @return Le packet
	 */
	public PlayChat setContent(String content);
	
	public PlayChat setContent(BaseComponent... components);

	/**
	 * Définit le type de message
	 * 
	 * @param type
	 *            Le type
	 * @return Le packet
	 */
	public PlayChat setType(ChatType type);
}
