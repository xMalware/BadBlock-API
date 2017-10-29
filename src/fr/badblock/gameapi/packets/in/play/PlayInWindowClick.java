package fr.badblock.gameapi.packets.in.play;

import org.bukkit.inventory.ItemStack;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;

/**
 * Packet envoyé par le client lorsqu'il clique sur un item dans un inventaire
 * <ul>
 * <li>Mode 0 : Clique avec un bouton de souris seul
 * <ul>
 * <li>Bouton 0 : clique gauche</li>
 * <li>Bouton 1 : clique droit</li>
 * </ul>
 * </li>
 * <li>Mode 1 : Clique avec un bouton de souris seul + shift
 * <ul>
 * <li>Bouton 0 : clique gauche + shift</li>
 * <li>Bouton 1 : clique droit + shift</li>
 * </ul>
 * </li>
 * <li>Mode 2 : Clique avec une touche (nombre)
 * <ul>
 * <li>Bouton 0 : Touche 1</li>
 * <li>Bouton 1 : Touche 2</li>
 * <li>...</li>
 * <li>Bouton 8 : Touche 9</li>
 * </ul>
 * </li>
 * <li>Mode 3 : Clique avec la molette (bouton 2)</li>
 * <li>Mode 4 : Actions de drops
 * <ul>
 * <li>Bouton 0 : clique sur la touche de drop (clavier AZERTY : a)</li>
 * <li>Bouton 1 : clique sur la touche de drop (clavier AZERTY : a) + ctrl</li>
 * <li>Bouton 0 + slot -999 : Clique gauche en dehors de l'inventaire</li>
 * <li>Bouton 1 + slot -999 : Clique droit en dehors de l'inventaire</li>
 * </ul>
 * </li>
 * <li>Mode 5 : Actions de drag and drop
 * <ul>
 * <li>Bouton 0 + slot -999 : Drag avec bouton gauche</li>
 * <li>Bouton 4 + slot -999 : Drag avec bouton droit</li>
 * <li>Bouton 1 : Ajoute un slot avec drag gauche</li>
 * <li>Bouton 5 : Ajoute un slot avec drag droit</li>
 * <li>Bouton 2 + slot -999 : Ajoute un slot avec drag gauche</li>
 * <li>Bouton 6 + slot -999 : Termine le drag gauche</li>
 * <li>Bouton 6 + slot -999 : Termine le drag droit</li>
 * </ul>
 * </li>
 * <li>Mode 6 : Double clique</li>
 * </ul>
 * 
 * @author LeLanN
 */
public interface PlayInWindowClick extends BadblockInPacket {
	/**
	 * Un ID unique pour la transaction, utilisé pour la réponse du serveur
	 * 
	 * @return L'ID
	 */
	public int getActionNumber();

	/**
	 * Le bouton utilisé
	 * 
	 * @return Le bouton
	 */
	public int getButton();

	/**
	 * Récupčre l'item cliqué
	 * 
	 * @return L'item
	 */
	public ItemStack getItem();

	/**
	 * Le mode utilisé
	 * 
	 * @return Le mode
	 */
	public int getMode();

	/**
	 * Le slot cliqué
	 * 
	 * @return Le slot
	 */
	public int getSlot();

	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_WINDOW_CLICK;
	}

	/**
	 * L'ID de l'inventaire
	 * 
	 * @return L'ID
	 */
	public int getWindowId();
}
