package fr.badblock.gameapi.players.scoreboard;

import org.bukkit.scoreboard.Scoreboard;

import com.google.gson.JsonArray;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.utils.i18n.TranslatableString;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Représente le ScoreBoard commun à tous les joueurs, avec des méthodes
 * précrées.
 * 
 * @author LeLanN
 */
public interface BadblockScoreboard {
	/**
	 * Représente un élément du vote
	 * 
	 * @author LeLanN
	 */
	@Data
	@AllArgsConstructor
	public static class VoteElement {
		private String internalName;
		private String displayName;
	}

	/**
	 * Commence un vote à partir d'une liste de valeur
	 * 
	 * @param maps
	 *            Un array JSON avec toutes les maps sous forme de VoteElement.
	 */
	public void beginVote(JsonArray maps);

	/**
	 * Si la méthode est appelée, l'API va register un objectif (scoreboard)
	 * pour gérer l'affichage de la vie sous les pseudos joueurs.
	 */
	public void doBelowNameHealth();

	/**
	 * Si la méthode est appelée, l'API va register des teams (scoreboard) pour
	 * gérer l'affichage des groups des joueurs dans la tablist et au dessus des
	 * noms du joueur<br>
	 */
	public void doGroupsPrefix();
	
	/**
	 * Si la méthode est appelée l'API va afficher un holograme au dessus des joueurs prenant des dégats
	 */
	public void doOnDamageHologram();

	/**
	 * Si la méthode est appelée, l'API va register un objectif (scoreboard)
	 * pour gérer l'affichage de la vie dans la tablist.
	 */
	public void doTabListHealth();

	/**
	 * Si la méthode est appelée, l'API va register des teams (scoreboard) pour
	 * gérer l'affichage des teams des joueurs dans la tablist et au dessus des
	 * noms du joueur<br>
	 * A appelé après avoir register les teams via
	 * {@link GameAPI#registerTeams(int, org.bukkit.configuration.ConfigurationSection)}
	 * !
	 */
	public void doTeamsPrefix();

	/**
	 * Le vote s'arrête (les joueurs ne peuvent plus voter).
	 */
	public void endVote();

	/**
	 * Récupère le Scoreboard Bukkit
	 * 
	 * @return Le scoreboard
	 */
	public Scoreboard getHandler();
	
	/**
	 * Savoir si il y a ou non les prefix des groupes en affichage dans le scoreboard
	 * @return
	 */
	public boolean hasShownGroupPrefix();
	
	/**
	 * Récupère le nom d'affichage utilisé pour le joueur
	 * 
	 * @param player
	 *            Le joueur
	 * @return Le nom, ou null si aucun
	 */
	public TranslatableString getUsedName(BadblockPlayer player);

	/**
	 * Récupère le nombre de votes pour le gagnant
	 * 
	 * @return Le nombre de votes
	 */
	public int getVotesForWinner();

	/**
	 * Récupère la map gagnante
	 * 
	 * @return La map gagnante
	 */
	public VoteElement getWinner();

	/**
	 * Ouvre l'inventaire de vote à un joueur
	 * 
	 * @param player
	 *            Le joueur
	 * @return L'inventaire custom
	 */
	public void openVoteInventory(BadblockPlayer locale);
}
