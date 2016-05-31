package fr.badblock.gameapi.utils.itemstack;

import org.bukkit.inventory.ItemStack;

/**
 * Classe permettant de gérer des fonctionnalités par l'API Bukkit facilement.<br>
 * Utiliser {@link fr.badblock.gameapi.GameAPI} pour initialiser.
 * 
 * @author LeLanN
 */
public interface ItemStackExtra {
	/**
	 * Autorise une action sur l'item sans l'écouter
	 * @param action Les actions
	 * @return L'item extra
	 */
	public ItemStackExtra allow(ItemAction... action);
	
	/**
	 * Refuse une action sur l'item sans l'écouter
	 * @param action Les actions
	 * @return L'item extra
	 */
	public ItemStackExtra disallow(ItemAction... action);
	
	/**
	 * Si l'item peut drop lorsque le joueur mort
	 * @param can Si il peut
	 * @return L'item extra
	 */
	public ItemStackExtra allowDropOnDeath(boolean can);

	
	/**
	 * Ecoute une action sur l'item
	 * @param event Pour écouter les actions
	 * @param actions Les actions
	 * @return L'item extra
	 */
	public ItemStackExtra listen(ItemEvent event, ItemAction... actions);

	/**
	 * Définit les listeners en fonction d'un type prééxistant d'item
	 * @param optionalEvent Un event à renseigner si le type choisit est cliquable
	 * @param place Le type d'item
	 * @return L'item extra
	 */
	public ItemStackExtra listenAs(ItemEvent optionalEvent, ItemPlaces place);
	
	
	/**
	 * Récupère l'item géré par l'extra
	 */
	public ItemStack getHandler();
	
	/**
	 * Abandonne les fonctionnalitées de l'extra
	 */
	public void stopListeningAt();
	
	/**
	 * Change le displayName de l'item
	 * @param name Le nouveau display name
	 */
	public ItemStackExtra setDisplayName(String name);
	
	/**
	 * Les différentes places d'un item
	 * @author LeLanN
	 */
	public static enum ItemPlaces {
		/**
		 * Un item d'inventaire sur lequel on peut cliquer
		 */
		INVENTORY_CLICKABLE,
		/**
		 * Un item d'inventaire sur lequel on ne peut pas cliquer
		 */
		INVENTORY_UNCLICKABLE,
		/**
		 * Un item de la barre du joueur en jeu sur lequel on peut cllquer
		 */
		HOTBAR_CLICKABLE,
		/**
		 * Un item de la barere du joueur en jeu sur lequel on ne peut pas cliquer
		 */
		HOTBAR_UNCLICKABLE,
		/**
		 * Un item normal (reset)
		 */
		NORMAL;
	}
}
