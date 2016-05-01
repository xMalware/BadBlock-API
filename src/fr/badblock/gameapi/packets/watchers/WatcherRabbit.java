package fr.badblock.gameapi.packets.watchers;

import org.bukkit.entity.Rabbit;

public interface WatcherRabbit extends WatcherAgeable {
	public WatcherRabbit setType(Rabbit.Type type);
}
