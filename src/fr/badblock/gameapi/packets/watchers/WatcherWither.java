package fr.badblock.gameapi.packets.watchers;

public interface WatcherWither extends WatcherLivingEntity {
	public WatcherWither setTargets(int e1, int e2, int e3);

	public WatcherWither setInvulnerableTime(int time);
}
