package fr.badblock.gameapi.players.scoreboard;

import fr.badblock.gameapi.players.BadblockPlayer;

/**
 * Représente un objectif vu par un seul joueur, qui peut être mis à jour de
 * manière très rapide et sans clignements.<br>
 * Obtenir avec {@link fr.badblock.gameapi.GameAPI#buildCustomObjective(String)}
 * 
 * @author LeLanN
 */
public interface CustomObjective {
	/**
	 * Permet de changer une ligne du scoreboard.<br>
	 * Attention, la ligne 1 correspond à la ligne du bas du scoreboard et le
	 * scoreboard ne peut en afficher que 15.<br>
	 * 
	 * @param line
	 *            La ligne du texte (entre 1 et 15)
	 * @param text
	 *            Le texte à afficher, maximum 32 caractères (code couleur = 2
	 *            caractères)
	 */
	public void changeLine(int line, String text);

	/**
	 * Actuallise le scoreboard
	 */
	public void generate();

	/**
	 * Récupère le joueur regardant cet objective
	 * 
	 * @return Le BadblockPlayer (si il n'a pas encore été assigné ou que le
	 *         joueur n'est plus là, null)
	 */
	public BadblockPlayer getAssignedPlayer();

	/**
	 * Supprime une ligne du scoreboard.
	 * 
	 * @param line
	 *            La ligne à supprimer (entre 1 et 15)
	 */
	public void removeLine(int line);

	/**
	 * Réinitialise l'Objective.
	 */
	public void reset();

	/**
	 * Change le nom de l'objective
	 * 
	 * @param displayName
	 *            Le nom (maximum 32 caractères).
	 */
	public void setDisplayName(String displayName);

	/**
	 * Définit le générateur
	 * 
	 * @param generator
	 *            Le générateur
	 */
	public void setGenerator(BadblockScoreboardGenerator generator);

	/**
	 * Affiche l'objectif à un joueur (un seul par CustomObjective !).
	 * 
	 * @param player
	 *            Le joueur
	 */
	public void showObjective(BadblockPlayer player);
}
