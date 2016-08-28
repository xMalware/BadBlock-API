package fr.badblock.gameapi.packets.watchers;

import org.bukkit.inventory.ItemStack;

public interface WatcherEnderman extends WatcherLivingEntity {
	public WatcherEnderman setCarriedBlock(ItemStack block);

	public WatcherEnderman setScreaming(boolean screaming);
}
