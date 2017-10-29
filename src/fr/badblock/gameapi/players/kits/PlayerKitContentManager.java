package fr.badblock.gameapi.players.kits;

import org.bukkit.Material;

import com.google.gson.JsonObject;

import fr.badblock.gameapi.players.BadblockPlayer;

/**
 * Une interface représentant simplement un manager de contenu du Kit
 * (uniquement les items, en théorie).<br>
 * Doit ętre fournit par le jeu (onEnable) via
 * {@link fr.badblock.gameapi.GameAPI#setKitContentManager(PlayerKitContentManager)}.
 * 
 * @author LeLanN
 */
public interface PlayerKitContentManager {
	/**
	 * Permet de récupčre des données depuis l'inventaire d'un joueur
	 * 
	 * @param player
	 *            Le joueur
	 * @return Les données créées
	 */
	public JsonObject createFromInventory(BadblockPlayer player);

	/**
	 * Permet de donner ŕ un joueur un kit
	 * 
	 * @param content
	 *            Le contenu
	 * @param player
	 *            Le joueur
	 */
	public void give(JsonObject content, BadblockPlayer player);

	/**
	 * Permet de donner ŕ un joueur un kit
	 * 
	 * @param content
	 *            Le contenu
	 * @param player
	 *            Le joueur
	 */
	public void give(JsonObject content, BadblockPlayer player, Material... withoutMaterials);
	
}
