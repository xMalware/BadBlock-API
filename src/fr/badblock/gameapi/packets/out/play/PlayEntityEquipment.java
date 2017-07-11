package fr.badblock.gameapi.packets.out.play;

import org.bukkit.inventory.ItemStack;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import fr.badblock.gameapi.packets.EnumItemSlot;

public interface PlayEntityEquipment extends BadblockOutPacket {
	public int getEntityId();

	public ItemStack getItemStack();

	public int getSlot();

	public PlayEntityEquipment setEntityId(int entityId);

	public PlayEntityEquipment setItemStack(ItemStack item);

	public PlayEntityEquipment setSlot(int slot);
}
