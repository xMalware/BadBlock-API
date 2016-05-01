package fr.badblock.gameapi.packets.watchers;

import org.bukkit.entity.Entity;

public interface WatcherEntity {
	public WatcherEntity setOnFire(boolean onFire);
	
	public WatcherEntity setInvisibile(boolean invisible);
	
	public WatcherEntity setCustomName(String customName);
	
	public WatcherEntity setCustomNameVisible(boolean customNameVisible);
	
	public void applyToEntity(Entity entity);
}
