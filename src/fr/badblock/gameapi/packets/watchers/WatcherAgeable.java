package fr.badblock.gameapi.packets.watchers;

/**
 * Représente les watchers d'une entité pouvant se reproduire.
 * 
 * @author LeLanN
 */
public interface WatcherAgeable extends WatcherLivingEntity {
	/**
	 * Définit si l'entité est un bébé ou non.
	 * 
	 * @param baby
	 *            Si l'entité est un bébé
	 * @return Le watcher
	 */
	public WatcherAgeable setBaby(boolean baby);
}
