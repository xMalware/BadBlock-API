package fr.badblock.gameapi.packets.watchers;

import org.bukkit.entity.Entity;

import fr.badblock.gameapi.utils.i18n.TranslatableString;

public interface WatcherEntity {
	public void applyToEntity(Entity entity);

	public WatcherEntity setCustomName(String name);

	public WatcherEntity setCustomName(TranslatableString name);

	public WatcherEntity setCustomNameVisible(boolean customNameVisible);

	public WatcherEntity setInvisibile(boolean invisible);

	public WatcherEntity setOnFire(boolean onFire);
}
