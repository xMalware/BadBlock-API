package fr.badblock.gameapi.packets.watchers;

import org.bukkit.DyeColor;

public interface WatcherWolf extends WatcherTameableAnimal {
	public WatcherWolf setAngry(boolean angry);

	public WatcherWolf setBegging(boolean begging);

	public WatcherWolf setCollarColor(DyeColor color);
}
