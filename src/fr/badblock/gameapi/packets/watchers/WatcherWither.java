package fr.badblock.gameapi.packets.watchers;

public interface WatcherWither extends WatcherLivingEntity {
	public WatcherWither setInvulnerableTime(int time);

	public WatcherWither setTargets(int e1, int e2, int e3);
}
