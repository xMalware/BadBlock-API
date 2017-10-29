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
 * <li>Mise ŕ jour de balise
 * </ul>
 * <br>
 * 
 * @see "http://wiki.vg/Block_Actions"
 * @author LeLanN
 */
public interface PlayBlockAction extends BadblockOutPacket {
	/**
	 * Récupčre les coordonnées du bloc
	 * 
	 * @return Les coordonnées
	 */
	public Vector3f getBlockPosition();

	/**
	 * Récupčre le type de block
	 * 
	 * @return Le type de block
	 */
	public Material getBlockType();

	/**
	 * Récupčre la premičre donnée.
	 * 
	 * @see "http://wiki.vg/Block_Actions"
	 * @return La premičre donnée
	 */
	public byte getByte1();

	/**
	 * Récupčre la deuxičme donnée.
	 * 
	 * @see "http://wiki.vg/Block_Actions"
	 * @return La deuxičme donnée
	 */
	public byte getByte2();

	/**
	 * Définit les coordonnées du bloc
	 * 
	 * @param position
	 *            Les coordonnées
	 * @return Le packet
	 */
	public PlayBlockAction setBlockPosition(Vector3f position);

	/**
	 * Définit le type de block
	 * 
	 * @param type
	 *            Le type de block
	 * @return Le packet
	 */
	public PlayBlockAction setBlockType(Material type);

	/**
	 * Définit la premičre donnée.
	 * 
	 * @see "http://wiki.vg/Block_Actions"
	 * @param value
	 *            La premičre donnée
	 * @return Le packet
	 */
	public PlayBlockAction setByte1(byte value);

	/**
	 * Définit la deuxičme donnée.
	 * 
	 * @see "http://wiki.vg/Block_Actions"
	 * @param value
	 *            La deuxičme donnée
	 * @return Le packet
	 */
	public PlayBlockAction setByte2(byte value);
}
