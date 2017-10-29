package fr.badblock.gameapi.utils.itemstack;

import org.bukkit.inventory.ItemStack;

import fr.badblock.gameapi.players.BadblockPlayer;

/**
 * Représente un inventaire agissant en tant que GUI.<br>
 * Utiliser
 * {@link fr.badblock.gameapi.GameAPI#createCustomInventory(int, String)} pour
 * l'obtenir.
 * 
 * @author LeLanN
 */
public interface CustomInventory {
	/**
	 * Permet d'ajouter un item cliquable dans l'inventaire. Si déjŕ placé, il
	 * sera simplement remplacé. Le callback sera appelé si un joueur clique sur
	 * l'item.
	 * 
	 * @param line
	 *            La ligne de l'item (commence ŕ 0 !)
	 * @param slot
	 *            Le slot ŕ utilisé (commence ŕ 0 !)
	 * @param event
	 *            Le callback qui sera appelé
	 */
	public CustomInventory addClickableItem(int slot, ItemStack item, ItemEvent event);

	/**
	 * Permet d'ajouter un item cliquable dans l'inventaire. Si déjŕ placé, il
	 * sera simplement remplacé. Le callback sera appelé si un joueur clique sur
	 * l'item.
	 * 
	 * @param slot
	 *            Le slot ŕ utilisé (commence ŕ 0 !)
	 * @param item
	 *            L'item ŕ placer. Si null, l'item sera supprimé [comme avec
	 *            removeItem()]. Ici, on attend un itemExtra déjŕ 'configuré'.
	 */
	public CustomInventory addClickableItem(int slot, ItemStackExtra item);

	/**
	 * Ajouter un item décoratif (aucune action lorsque l'on clique)
	 * 
	 * @param slot
	 *            Le slot ŕ utilisé (commence ŕ 0 !)
	 * @param item
	 *            L'item décoratif ŕ ajouter
	 */
	public CustomInventory addDecorationItem(int slot, ItemStack item);

	/**
	 * Récupčre le nombre de lignes de l'inventaire
	 * 
	 * @return Le nombre de lignes
	 */
	public int getLineCount();

	/**
	 * Permet d'afficher l'inventaire au joueur.
	 * 
	 * @param player
	 *            Le joueur auquel afficher l'inventaire
	 */
	public void openInventory(BadblockPlayer player);

	/**
	 * Permet de supprimer un item de l'inventaire.
	 * 
	 * @param slot
	 *            Le slot ŕ utilisé (commence ŕ 0 !)
	 */
	public CustomInventory removeItem(int slot);

	/**
	 * Récupčre la taille de l'inventaire
	 * 
	 * @return La taille
	 */
	public int size();
}
