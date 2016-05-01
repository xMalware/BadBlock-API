package fr.badblock.gameapi.packets.watchers;

import org.bukkit.entity.Ocelot;

public interface WatcherOcelot extends WatcherTameableAnimal {
	public WatcherOcelot setType(Ocelot.Type type);
}
