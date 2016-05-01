package fr.badblock.gameapi.packets.out.play;

import org.bukkit.Material;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import fr.badblock.gameapi.utils.selections.Vector3f;

/**
 * Packet envoyé par le serveur lors de :
 * <ul>
 * <li>Ouverture et fermeture de coffre
 * <li>Activation/Désactivation de pistons
 * <li>Noteblock jouant une note
 * <li>Mise à jour de balise
 * </ul>
 * <br>
 * @see "http://wiki.vg/Block_Actions"
 * @author LeLanN
 */
public interface PlayBlockAction extends BadblockOutPacket {
	/**
	 * Récupère les coordonnées du bloc
	 * @return Les coordonnées
	 */
	public Vector3f getBlockPosition();
	
	/**
	 * Définit les coordonnées du bloc
	 * @param position Les coordonnées
	 * @return Le packet
	 */
	public PlayBlockAction setBlockPosition(Vector3f position);
	
	/**
	 * Récupère la première donnée.
	 * @see "http://wiki.vg/Block_Actions"
	 * @return La première donnée
	 */
	public byte getByte1();
	
	/**
	 * Définit la première donnée.
	 * @see "http://wiki.vg/Block_Actions"
	 * @param value La première donnée
	 * @return Le packet
	 */
	public PlayBlockAction setByte1(byte value);
	
	/**
	 * Récupère la deuxième donnée.
	 * @see "http://wiki.vg/Block_Actions"
	 * @return La deuxième donnée
	 */
	public byte getByte2();
	
	/**
	 * Définit la deuxième donnée.
	 * @see "http://wiki.vg/Block_Actions"
	 * @param value La deuxième donnée
	 * @return Le packet
	 */
	public PlayBlockAction setByte2(byte value);
	
	/**
	 * Récupère le type de block
	 * @return Le type de block
	 */
	public Material getBlockType();
	
	/**
	 * Définit le type de block
	 * @param type Le type de block
	 * @return Le packet
	 */
	public PlayBlockAction setBlockType(Material type);
}
