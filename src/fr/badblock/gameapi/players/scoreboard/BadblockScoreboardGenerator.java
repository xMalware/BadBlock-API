package fr.badblock.gameapi.players.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitTask;

import fr.badblock.gameapi.GameAPI;

/**
 * Représente la classe permettant de générer le scoreboard.
 * 
 * @author LeLanN
 */
public abstract class BadblockScoreboardGenerator {

	private BukkitTask		bukkitTask;
	private CustomObjective	objective;
	private int				current;
	
	/**
	 * Permet de générer une derničre ligne animée
	 * 
	 * @param objective
	 *            L'objectif géré
	 */
	public void doBadblockFooter(CustomObjective objective) {
		objective.changeLine(1, "&7badblock.fr / " + GameAPI.getServerName());
		bukkitTask = Bukkit.getScheduler().runTaskTimer(GameAPI.getAPI(), this::doBadblockFooter0, 0, 5L);
	}

	private void doBadblockFooter0() {
		if (objective.getAssignedPlayer() == null || !objective.getAssignedPlayer().isOnline()) {
			bukkitTask.cancel();
			return;
		}

		String base = "badblock.fr / " + GameAPI.getServerName();

		if (current < 0) {
			objective.changeLine(1, "&b" + base);
		} else {
			String result = "&b" + base.substring(0, current) + ChatColor.BLUE + base.substring(current, current + 1)
			+ "&b" + base.substring(current + 1);
			objective.changeLine(1, result);
		}

		current++;

		if (current == base.length()) {
			current = -10;
		}
	}

	/**
	 * Génčre le scoreboard
	 */
	public abstract void generate();
}
