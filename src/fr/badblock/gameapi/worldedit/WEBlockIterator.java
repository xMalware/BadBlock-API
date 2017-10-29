package fr.badblock.gameapi.worldedit;

import org.bukkit.World;

public interface WEBlockIterator {
	/**
	 * Récupčre le monde dans lequel l'itérateur doit ętre traité
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
	 * Récupčre le prochain chunk
	 * @return Un tableau de 2 entiers
	 */
	public int[] getNextChunk();
	
	/**
	 * Vérifie si il reste au moins un block ŕ traiter
	 * @return Un booléen
	 */
	public boolean hasNext();
	
	/**
	 * Récupčre la prochaine position
	 * @return Un tableau de 3 entiers
	 */
	public int[] getNextPosition();
}
