package fr.badblock.gameapi.packets.watchers;

import org.bukkit.entity.Entity;

import fr.badblock.gameapi.utils.i18n.TranslatableString;

public interface WatcherEntity {
	public WatcherEntity setOnFire(boolean onFire);
	
	public WatcherEntity setInvisibile(boolean invisible);
	
	public WatcherEntity setCustomName(TranslatableString name);
	
	public WatcherEntity setCustomName(String name);
	
	public WatcherEntity setCustomNameVisible(boolean customNameVisible);
	
	public void applyToEntity(Entity entity);
}
