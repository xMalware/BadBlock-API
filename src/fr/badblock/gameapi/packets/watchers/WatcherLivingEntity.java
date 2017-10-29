package fr.badblock.gameapi.packets.watchers;

/**
 * Représente les DataWatchers d'une entité vivante.<br>
 * Tous ceux existants ne sont pas représentes car parfois peu utile dans notre
 * utilisation.
 * 
 * @author LeLanN
 */
public interface WatcherLivingEntity extends WatcherEntity {
	public WatcherLivingEntity setArrowsInEntity(int arrows);
}
