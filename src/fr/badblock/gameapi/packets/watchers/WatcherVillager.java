package fr.badblock.gameapi.packets.watchers;

import org.bukkit.entity.Villager;

public interface WatcherVillager extends WatcherAgeable {
	public WatcherVillager setProfession(Villager.Profession profession);
}
