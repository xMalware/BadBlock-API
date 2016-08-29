package fr.badblock.gameapi.packets.watchers;

public interface WatcherZombie extends WatcherLivingEntity {
	public WatcherZombie setBaby(boolean baby);

	public WatcherZombie setConverting(boolean converting);

	public WatcherZombie setVillager(boolean villager);
}
