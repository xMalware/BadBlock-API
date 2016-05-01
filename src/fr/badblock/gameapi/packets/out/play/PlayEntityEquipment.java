package fr.badblock.gameapi.packets.out.play;

import org.bukkit.inventory.ItemStack;

import fr.badblock.gameapi.packets.BadblockOutPacket;

public interface PlayEntityEquipment extends BadblockOutPacket {
	public int getEntityId();
	
	public PlayEntityEquipment setEntityId(int entityId);
	
	public int getSlot();
	
	public PlayEntityEquipment setSlot(int slot);
	
	public ItemStack getItemStack();
	
	public PlayEntityEquipment setItemStack(ItemStack item);
}
