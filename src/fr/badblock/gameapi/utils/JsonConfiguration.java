package fr.badblock.gameapi.utils;

import org.bukkit.Location;
import org.bukkit.inventory.ItemStack;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

/**
 * Une série d'utilitaires pour la configuration en JSON
 * @author LeLanN
 */
public interface JsonConfiguration {
	/**
	 * Sauvegarde un item en JSON
	 * @param item L'item
	 * @return Le JSON
	 */
	public JsonObject saveItemStack(ItemStack item);
	
	/**
	 * Charge un item d'un objet JSON
	 * @param object Le JSON
	 * @return L'item
	 */
	public ItemStack loadItemStack(JsonObject object);
	
	/**
	 * Charge un array d'itemstack
	 * @param array Le JSON
	 * @return Les items
	 */
	public ItemStack[] loadItemArray(JsonArray array);
	
	/**
	 * Sauvegarde d'une location en JSON
	 * @param location La location
	 * @return Le JSON
	 */
	public JsonObject saveLocation(Location location);

	/**
	 * Charge une location d'un objet JSON
	 * @param object Le JSON
	 * @return La location
	 */
	public Location loadLocation(JsonObject object);
}
