package fr.badblock.gameapi.packets.watchers;

import org.bukkit.DyeColor;

public interface WatcherSheep extends WatcherAgeable {
	public WatcherSheep setColor(DyeColor color);

	public WatcherSheep setSheared(boolean sheared);
}
