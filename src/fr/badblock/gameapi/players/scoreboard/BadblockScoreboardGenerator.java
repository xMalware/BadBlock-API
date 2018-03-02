package fr.badblock.gameapi.players.scoreboard;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.scheduler.BukkitTask;

import fr.badblock.gameapi.GameAPI;

/**
 * ReprÃ©sente la classe permettant de gÃ©nÃ©rer le scoreboard.
 * 
 * @author LeLanN
 */
public abstract class BadblockScoreboardGenerator {

	private BukkitTask		bukkitTask;
	private CustomObjective	objective;
	private int				current;
	
	/**
	 * Permet de gÃ©nÃ©rer une derniÄ�re ligne animÃ©e
	 * 
	 * @param objective
	 *            L'objectif gÃ©rÃ©
	 */
	public void doBadblockFooter(CustomObjective objective) {
		this.objective = objective;
		objective.changeLine(1, "&7play.badblock.fr");
		bukkitTask = Bukkit.getScheduler().runTaskTimer(GameAPI.getAPI(), this::doBadblockFooter0, 0, 1L);
	}

	private void doBadblockFooter0() {
		if (objective.getAssignedPlayer() == null || !objective.getAssignedPlayer().isOnline()) {
			bukkitTask.cancel();
			return;
		}

		String base = "play.badblock.fr";

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
	 * GÃ©nÄ�re le scoreboard
	 */
	public abstract void generate();
}
