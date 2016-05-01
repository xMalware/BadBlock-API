package fr.badblock.gameapi.utils.itemstack;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import fr.badblock.gameapi.GameAPI;

/**
 * Classe utilitaires permettant diverses aide concernant les items.
 * @author LeLanN
 */
public class ItemStackUtils {
	/**
	 * Compare plusieurs items et vérifie si ils ont le même type/data
	 * @param items Les différents items à comparés
	 * @return Si les items ont le même type/id
	 */
	public static boolean areSimilar(ItemStack... items){
		Material material = null;
		short	 data	  = -1;
		
		for(ItemStack item : items){
			if(material == null){
				material = item.getType();
				data	 = item.getDurability();
			} else if(material != item.getType() || data != item.getDurability()){
				return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Compare plusieurs items et vérifie si ils sont exactement pareils (type/data/enchantements/lore/displayname).<br>
	 * Attention ! Deux livres avec un texte différent ne seront pas différenciés; deux fireworks; ...
	 * @param items Les différents items à comparés
	 * @return Si les items sont exactement pareils
	 */
	public static boolean areSame(ItemStack... items){
		Material 				  material    = null;
		short	 				  data	      = -1;
		Map<Enchantment, Integer> enchants    = null;
		List<String>			  lore		  = null;
		String					  displayName = null;
		
		for(ItemStack item : items){
			if(material == null){
				material    = item.getType();
				data	    = item.getDurability();
				enchants    = item.getEnchantments();
				
				if(item.getItemMeta() == null) continue;
				
				lore	    = item.getItemMeta().getLore();
				displayName = item.getItemMeta().getDisplayName();
			} else {
				if(material != item.getType()) return false;
				if(data != item.getDurability()) return false;
				if(enchants != null && !enchants.equals(item.getEnchantments())) return false;
				
				if(item.getItemMeta() != null){
					if(lore != null && !lore.equals(item.getItemMeta().getLore())) return false;
					if(displayName != null && !displayName.equals(item.getItemMeta().getDisplayName())) return false;
				} else if(lore != null || displayName != null) return false;
			}
		}
		
		return true;
	}
	
	/**
	 * Change le nom d'affichage et le lore d'un item
	 * @param item L'item à modifier
	 * @param displayName Le nom d'affichage à mettre
	 * @param lore Le lore à mettre
	 */
	public static ItemStack changeDisplay(ItemStack item, String displayName, String... lore){
		if(isValid(item)){
			ItemMeta meta = item.getItemMeta();
		
			meta.setDisplayName(GameAPI.i18n().replaceColors(displayName));
			meta.setLore(GameAPI.i18n().replaceColors(Arrays.asList(lore)));
		
			item.setItemMeta(meta);
		}
		
		return item;
	}
	
	/**
	 * Permet de réparer un ou plusieurs ItemStack
	 * @param items Les items.
	 */
	public static void repair(ItemStack... items){
		for(ItemStack item : items){
			if(!item.getType().isBlock() && item.getType().getMaxDurability() >= 1 && item.getDurability() != 0){
				item.setDurability((short)0);
			}
		}
	}
	
	/**
	 * Vérifie si l'item est valide, c'est à dire si il n'est pas null ou vide.
	 * @param item L'item
	 * @return
	 */
	public static boolean isValid(ItemStack item){
		return item != null && item.getType() != Material.AIR && item.getItemMeta() != null;
	}
	
	/**
	 * Véririfie si l'item est valide et si il a un nom d'affichage
	 * @param item
	 * @return
	 */
	public static boolean hasDisplayname(ItemStack item){
		return isValid(item) && item.getItemMeta().getDisplayName() != null;
	}
}
