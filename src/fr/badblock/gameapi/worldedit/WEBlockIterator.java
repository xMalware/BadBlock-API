package fr.badblock.gameapi.worldedit;

import org.bukkit.World;

public interface WEBlockIterator {
	/**
	 * Récupère le monde dans lequel l'itérateur doit être traité
	 * @return Un monde Bukkit
	 */
	public World getWorld();
	
	/**
	 * Renvoi le nombre total de block de l'itérateur, possiblement une estimation
	 * @return Un entier
	 */
	public long getCount();
	
	/**
	 * Renvoi vrai si le prochain block est dans un chunk différent
	 * @return Un booléen
	 */
	public boolean hasNextChunk();
	
	/**
	 * Récupère le prochain chunk
	 * @return Un tableau de 2 entiers
	 */
	public int[] getNextChunk();
	
	/**
	 * Vérifie si il reste au moins un block à traiter
	 * @return Un booléen
	 */
	public boolean hasNext();
	
	/**
	 * Récupère la prochaine position
	 * @return Un tableau de 3 entiers
	 */
	public int[] getNextPosition();
}
