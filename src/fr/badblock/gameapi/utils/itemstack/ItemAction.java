package fr.badblock.gameapi.utils.itemstack;

import java.util.Arrays;
import java.util.List;

import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryAction;

import lombok.Getter;

/**
 * Représente les différentes intéractions possible avec un item
 * @author LeLanN
 * @author xMalware
 */
@Getter public enum ItemAction {
	/**
	 * Lorsque le joueur clique gauche avec l'item
	 */
	LEFT_CLICK_AIR(Action.LEFT_CLICK_AIR),
	/**
	 * Lorsque le joueur clique gauche un bloc avec l'item
	 */
	LEFT_CLICK_BLOCK(Action.LEFT_CLICK_BLOCK),
	/**
	 * Lorsque le joueur clique droit avec l'item
	 */
	RIGHT_CLICK_AIR(Action.RIGHT_CLICK_AIR),
	/**
	 * Lorsque le joueur clique droit un bloc avec l'item
	 */
	RIGHT_CLICK_BLOCK(Action.RIGHT_CLICK_BLOCK),
	/**
	 * Lorsque le joueur drop l'item sans l'inventaire
	 */
	DROP(),
	/**
	 * Lorsque le joueur clique gauche dans l'inventaire
	 */
	INVENTORY_LEFT_CLICK(InventoryAction.PICKUP_ALL, InventoryAction.COLLECT_TO_CURSOR, InventoryAction.HOTBAR_MOVE_AND_READD, InventoryAction.HOTBAR_SWAP, InventoryAction.MOVE_TO_OTHER_INVENTORY),
	/**
	 * Lorsque le joueur clique droit dans l'inventaire
	 */
	INVENTORY_RIGHT_CLICK(InventoryAction.PICKUP_HALF, InventoryAction.PICKUP_ONE, InventoryAction.PICKUP_SOME),
	/**
	 * Lorsque le joueur midclick dans l'inventaire
	 */
	INVENTORY_WHEEL_CLICK(InventoryAction.NOTHING, InventoryAction.CLONE_STACK),
	/**
	 * Lorsque le joueur drop l'item dans l'inventaire
	 */
	INVENTORY_DROP(InventoryAction.DROP_ONE_SLOT, InventoryAction.DROP_ALL_SLOT, InventoryAction.DROP_ONE_CURSOR, InventoryAction.DROP_ONE_CURSOR);
	
	private List<InventoryAction> assignedInventoryAction;
	private Action				  action;
	
	ItemAction(){}
	
	ItemAction(Action action) {
		this.action = action;
	}
	
	ItemAction(InventoryAction... assignedInventoryAction) {
		this.assignedInventoryAction = Arrays.asList(assignedInventoryAction);
	}
	
	public static ItemAction get(InventoryAction inventoryAction) {
		for(ItemAction itemAction : values())
			if(itemAction.getAssignedInventoryAction() != null && itemAction.getAssignedInventoryAction().contains(inventoryAction))
				return itemAction;
		return null;
	}
	
	public static ItemAction get(Action action) {
		for(ItemAction itemAction : values())
			if(itemAction.getAction() == action)
				return itemAction;
		return null;
	}
	
}
