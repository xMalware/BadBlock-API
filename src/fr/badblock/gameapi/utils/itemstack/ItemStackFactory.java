package fr.badblock.gameapi.utils.itemstack;

import org.bukkit.Color;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import fr.badblock.gameapi.utils.i18n.Locale;
import fr.badblock.gameapi.utils.i18n.TranslatableString;

/**
 * Classe permettant de créer un item stack complexe de manière beaucoup plus simple.<br>
 * Utiliser {@link fr.badblock.gameapi.GameAPI} pour avoir une nouvelle factory.
 * @author LeLanN
 */
public interface ItemStackFactory {
	/**
	 * Définit si le futur item est cassable ou non.
	 * @param unbreakable Si le futur item est cassable.
	 * @return La factory
	 */
	public ItemStackFactory unbreakable(boolean unbreakable);
	
	/**
	 * Permet d'enchanter le futur item
	 * @param enchantment L'enchantement à appliquer
	 * @param level Le niveau de l'enchantement
	 * @return La factory
	 */
	public ItemStackFactory enchant(Enchantment enchantment, int level);
	
	/**
	 * Permet de changer la description du futur item. Les codes couleurs seront remplacée automatiquement.
	 * @param lore La liste de description
	 * @return La factory
	 */
	public ItemStackFactory lore(String... lore);
	
	/**
	 * Permet de changer la description du futur item. Les codes couleurs seront remplacée automatiquement.
	 * @param lore La liste de description
	 * @return La factory
	 */
	public ItemStackFactory lore(TranslatableString lore);

	
	/**
	 * Permet de changer le displayname du futur item. Les codes couleurs seront remplacée automatiquement.
	 * @param displayName Le displayName à mettre à l'item
	 * @return La factory
	 */
	public ItemStackFactory displayName(String displayName);
	
	/**
	 * Permet de changer le displayname du futur item. Les codes couleurs seront remplacée automatiquement.
	 * @param displayName Le displayName à mettre à l'item
	 * @return La factory
	 */
	public ItemStackFactory displayName(TranslatableString displayName);
	
	/**
	 * Change la durabilité (ou le data, c'est équivalent) du futur item.
	 * @param data Le nouveau data
	 * @return La factory
	 */
	public ItemStackFactory durability(short data);
	
	/**
	 * Change le type du futur item
	 * @param material Le nouveau type
	 * @return La factory
	 */
	public ItemStackFactory type(Material material);
	
	/**
	 * Crée l'ItemStack en lui ajoutant une couleur de laine. type() et durability() automatique.
	 * @param amount Le nombre d'item à créer
	 * @param color La couleur de laine
	 * @return L'item créé
	 */
	public ItemStack asWool(int amount, DyeColor color);
	
	/**
	 * Crée l'ItemStack en lui ajoutant une couleur d'armure. Attention, utiliser le bon type(material) avant.
	 * @param amount Le nombre d'item à créer
	 * @param color La couleur d'armure
	 * @return L'item créé
	 */
	public ItemStack asLeatheredArmor(int amount, Color color);
	
	/**
	 * Crée l'ItemStack en lui ajoutant une couleur d'armure. type() et durability() automatique.
	 * @param amount Le nombre d'item à créer
	 * @param owner Le personnage du crâne créé
	 * @return L'item créé
	 */
	public ItemStack asSkull(int amount, String owner);
	
	/**
	 * Crée un ItemStack normal
	 * @param amount
	 * @return L'item créé
	 */
	public ItemStack create(int amount);
	
	/**
	 * Transforme la factory en itemstackextra
	 * @param amount Le nom d'item à créer
	 * @return L'extra
	 */
	public ItemStackExtra asExtra(int amount);
	
	/**
	 * Permet de 'dire' à la factory d'utiliser l'I18n pour displayName et lore
	 * @param locale La langue utilisée
	 */
	public ItemStackFactory doWithI18n(Locale locale);
	
	/**
	 * Retourne une nouvelle factory avec les mêmes paramètres
	 * @return Une nouvelle factory
	 */
	public ItemStackFactory clone();
}
