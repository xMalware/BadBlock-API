package fr.badblock.gameapi.packets;

/**
 * Représente un packet BadBlock
 * @author audra
 *
 */
public interface BadblockPacket {
	/**
	 * Vérifie si le packet sera traité par le serveur
	 * @return Si il sera traité
	 */
	public boolean isCancelled();
	
	/**
	 * Définit si le packet sera traité par le serveur
	 * @param cancelled Si il sera traité
	 */
	public void setCancelled(boolean cancelled);
}
