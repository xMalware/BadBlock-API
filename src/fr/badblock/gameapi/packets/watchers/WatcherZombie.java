package fr.badblock.gameapi.packets.watchers;

public interface WatcherZombie extends WatcherLivingEntity {
	public WatcherZombie setBaby(boolean baby);
	
	public WatcherZombie setVillager(boolean villager);
	
	public WatcherZombie setConverting(boolean converting);
}
