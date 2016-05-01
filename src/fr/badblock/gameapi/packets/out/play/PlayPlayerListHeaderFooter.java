package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;

/**
 * Packet envoyé pour changer les lignes au-dessus et en-dessous des joueurs dans la tablist
 * @author LelanN
 */
public interface PlayPlayerListHeaderFooter extends BadblockOutPacket {
	/**
	 * Récupère les message du header
	 * @return Le messages
	 */
	public String getHeader();
	
	/**
	 * Définit le message du header
	 * @param header Le message
	 * @return Le packet
	 */
	public PlayPlayerListHeaderFooter setHeader(String header);
	
	/**
	 * Récupère les message du footer
	 * @return Le message
	 */
	public String getFooter();
	
	/**
	 * Définit le message du footer
	 * @param footer Le message
	 * @return Le packet
	 */
	public PlayPlayerListHeaderFooter setFooter(String footer);
}
