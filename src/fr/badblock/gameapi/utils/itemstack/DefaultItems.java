package fr.badblock.gameapi.utils.itemstack;

import org.bukkit.inventory.ItemStack;

import fr.badblock.gameapi.utils.i18n.Locale;

/**
 * Représente quelques items par défaut (pour les inventaires ect) BadBlock
 * 
 * @author LeLanN
 */
public interface DefaultItems {
	public ItemStack getHelpItemstack(Locale locale, String helpKey);

}
