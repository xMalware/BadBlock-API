package fr.badblock.gameapi.worldedit;

import org.bukkit.command.CommandSender;

public interface WEAction {
	/**
	 * Renvoi le nombre total d'itération ŕ faire
	 * Le résultat peut ętre une estimation et non un chiffre exacte
	 * @return Un entier
	 */
	public long getTotalIterationCount();
	
	/**
	 * Renvoi le nombre d'appel fait ŕ l'action
	 * @return Un entier
	 */
	public long getIterationCount();

	/**
	 * Renvoi l'utilisateur ayant demandé 
	 * @return
	 */
	public CommandSender getApplicant();
	
	/**
	 * Vérifie si une ou plusieurs autre action doit ętre faite
	 * @return Un booléen
	 */
	public boolean hasNext();
	
	/**
	 * Fait la prochaine action
	 */
	public void next();
	
	/**
	 * Appelé lorsque l'action commence ŕ ętre traitée
	 */
	public void notifyStart();
	
	/**
	 * Applé lorsque l'action n'est plus traitée (possiblment en cours de traitement)
	 */
	public void notifyEnd();
}
