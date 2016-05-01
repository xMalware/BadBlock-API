package fr.badblock.gameapi.packets.watchers;

public interface WatcherTameableAnimal extends WatcherAgeable {
	public WatcherTameableAnimal setSitting(boolean sitting);
	
	public WatcherTameableAnimal setTamed(boolean tamed);
}
