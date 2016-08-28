package fr.badblock.gameapi.packets.out.play;

import org.bukkit.block.Block;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import fr.badblock.gameapi.utils.i18n.TranslatableString;

/**
 * Packet envoyé par le serveur pour changer le contenu d'un panneau
 * 
 * @author LeLanN
 */
public interface PlayUpdateSign extends BadblockOutPacket {
	/**
	 * Récupère les lignes du tableau en i18n
	 * 
	 * @return Les lignes
	 */
	public TranslatableString getLinesI18n();

	/**
	 * Définit les lignes du tableau en i18n
	 * 
	 * @param string
	 *            Les lignes
	 * @return Le packet
	 */
	public PlayUpdateSign setLinesI18n(TranslatableString string);

	/**
	 * Récupère les lignes du tableau
	 * 
	 * @return Les lignes
	 */
	public String[] getLines();

	/**
	 * Définit les lignes du tableau
	 * 
	 * @param lines
	 *            Les lignes
	 * @return Le packet
	 */
	public PlayUpdateSign setLines(String[] lines);

	/**
	 * Récupère le block concerné
	 * 
	 * @return Le block
	 */
	public Block getBlock();

	/**
	 * Définit le block concerné
	 * 
	 * @param block
	 *            Le block
	 * @return Le packet
	 */
	public PlayUpdateSign setBlock(Block block);
}
