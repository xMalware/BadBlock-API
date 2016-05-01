package fr.badblock.gameapi;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

public abstract class BadListener implements Listener {
	public BadListener(){
		Bukkit.getPluginManager().registerEvents(this, GameAPI.getAPI());
	}
}
