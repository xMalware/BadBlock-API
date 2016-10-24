package fr.badblock.gameapi.utils.itemstack;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.players.BadblockPlayer;
import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import net.minecraft.server.v1_8_R3.NBTTagList;

/**
 * Classe utilitaires permettant diverses aide concernant les items.
 * 
 * @author LeLanN
 */
public class ItemStackUtils {
	/**
	 * Compare plusieurs items et vérifie si ils sont exactement pareils
	 * (type/data/enchantements/lore/displayname).<br>
	 * Attention ! Deux livres avec un texte différent ne seront pas
	 * différenciés; deux fireworks; ...
	 * 
	 * @param items
	 *            Les différents items à comparés
	 * @return Si les items sont exactement pareils
	 */
	public static boolean areSame(ItemStack... items) {
		Material material = null;
		short data = -1;
		Map<Enchantment, Integer> enchants = null;
		List<String> lore = null;
		String displayName = null;

		for (ItemStack item : items) {
			if (material == null) {
				material = item.getType();
				data = item.getDurability();
				enchants = item.getEnchantments();

				if (item.getItemMeta() == null)
					continue;

				lore = item.getItemMeta().getLore();
				displayName = item.getItemMeta().getDisplayName();
			} else {
				if (material != item.getType())
					return false;
				if (data != item.getDurability())
					return false;
				if (enchants != null && !enchants.equals(item.getEnchantments()))
					return false;

				if (item.getItemMeta() != null) {
					if (lore != null && !lore.equals(item.getItemMeta().getLore()))
						return false;
					if (displayName != null && !displayName.equals(item.getItemMeta().getDisplayName()))
						return false;
				} else if (lore != null || displayName != null)
					return false;
			}
		}

		return true;
	}

	/**
	 * Compare plusieurs items et vérifie si ils ont le même type/data
	 * 
	 * @param items
	 *            Les différents items à comparés
	 * @return Si les items ont le même type/id
	 */
	public static boolean areSimilar(ItemStack... items) {
		Material material = null;
		short data = -1;

		for (ItemStack item : items) {
			if (material == null) {
				material = item.getType();
				data = item.getDurability();
			} else if (material != item.getType() || data != item.getDurability()) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Change le nom d'affichage et le lore d'un item
	 * 
	 * @param item
	 *            L'item à modifier
	 * @param displayName
	 *            Le nom d'affichage à mettre
	 * @param lore
	 *            Le lore à mettre
	 */
	public static ItemStack changeDisplay(ItemStack item, String displayName, String... lore) {
		if (isValid(item)) {
			ItemMeta meta = item.getItemMeta();

			meta.setDisplayName(GameAPI.i18n().replaceColors(displayName));
			meta.setLore(GameAPI.i18n().replaceColors(Arrays.asList(lore)));

			item.setItemMeta(meta);
		}

		return item;
	}

	/**
	 * Change le lore d'un item
	 * 
	 * @param item
	 *            L'item à modifier
	 * @param lore
	 *            Le lore à mettre
	 */
	public static ItemStack changeLore(ItemStack item, List<String> lore) {
		if (isValid(item)) {
			ItemMeta meta = item.getItemMeta();

			meta.setLore(lore == null ? null : GameAPI.i18n().replaceColors(lore));

			item.setItemMeta(meta);
		}

		return item;
	}

	/**
	 * Change le lore d'un item
	 * 
	 * @param item
	 *            L'item à modifier
	 * @param lore
	 *            Le lore à mettre
	 */
	public static ItemStack changeLore(ItemStack item, String... lore) {
		return changeLore(item, Arrays.asList(lore));
	}

	/**
	 * Véririfie si l'item est valide et si il a un nom d'affichage
	 * 
	 * @param item
	 * @return
	 */
	public static boolean hasDisplayname(ItemStack item) {
		return isValid(item) && item.getItemMeta().getDisplayName() != null;
	}

	/**
	 * Vérifie si l'item est valide, c'est à dire si il n'est pas null ou vide.
	 * 
	 * @param item
	 *            L'item
	 * @return Un boolean
	 */
	public static boolean isValid(ItemStack item) {
		return item != null && item.getType() != Material.AIR && item.getItemMeta() != null;
	}

	/**
	 * Maximise le nombre d'items dans le stack
	 * 
	 * @param item
	 *            L'item
	 */
	public static void maxStack(ItemStack item) {
		int max = item.getType().getMaxStackSize();

		if (item.getAmount() >= max)
			return;

		item.setAmount(max);
	}

	/**
	 * Permet de réparer un ou plusieurs ItemStack
	 * 
	 * @param items
	 *            Les items.
	 * @return Le nombre d'item réparés
	 */
	public static int repair(ItemStack... items) {
		int result = 0;

		for (ItemStack item : items) {
			if (isValid(item) && !item.getType().isBlock() && item.getType().getMaxDurability() >= 1
					&& item.getDurability() != 0) {
				item.setDurability((short) 0);
				result++;
			}
		}

		return result;
	}
	
	public static ItemStack encodeId(ItemStack itemStack, int itemID){
		if(!hasDisplayname(itemStack)){
			throw new IllegalArgumentException("Invalid item");
		}
		
		String name = encodeIDInName(itemStack.getItemMeta().getDisplayName(), itemID);
		
		ItemMeta meta = itemStack.getItemMeta();
		meta.setDisplayName(name);
		itemStack.setItemMeta(meta);
		
		return itemStack;
	}
	
	public static String encodeIDInName(String itemName, int itemID) {
		String 		  id 	  = Integer.toString(itemID);
		StringBuilder builder = new StringBuilder();
		
		for(int i = 0; i < id.length(); i++) {
			builder.append(ChatColor.COLOR_CHAR).append(id.charAt(i));
		}
		
		return builder.toString() + ChatColor.COLOR_CHAR + "S" + itemName;
	}

	public static int decodeItemId(ItemStack itemStack) {
		if(!hasDisplayname(itemStack))
			return -1;
		
		return decodeItemFromName(itemStack.getItemMeta().getDisplayName());
	}
	
	public static int decodeItemFromName(String itemName) {
		int intId = -1;
		
		if(itemName.contains(ChatColor.COLOR_CHAR + "S")) {
			String[] stringID = itemName.split(ChatColor.COLOR_CHAR + "S");
			if (stringID.length > 0) {
				itemName = stringID[0].replaceAll(ChatColor.COLOR_CHAR + "", "");
				try {
					intId = Integer.parseInt(itemName);
				} catch (NumberFormatException unused){}
			}
		}
		
		return intId;
	}
	
	public static void removeInHand(BadblockPlayer player, int count){
		if(isValid(player.getItemInHand())){
			int newCount = count == -1 ? 0 : player.getItemInHand().getAmount() - count;
			
			if(newCount <= 0)
				player.setItemInHand(null);
			else player.getItemInHand().setAmount(newCount);
			
			player.updateInventory();
		}
	}
	
	public static ItemStack fakeEnchant(ItemStack itemStack) {
		net.minecraft.server.v1_8_R3.ItemStack nmsStack = CraftItemStack.asNMSCopy(itemStack);
		NBTTagCompound tag = null;
		if (!nmsStack.hasTag()){
			tag = new NBTTagCompound();
			nmsStack.setTag(tag);
		}
		if (tag == null)
			tag = nmsStack.getTag();
		NBTTagList ench = new NBTTagList();
		tag.set("ench", ench);
		nmsStack.setTag(tag);
		return itemStack = CraftItemStack.asBukkitCopy(nmsStack);
	}
	
}
