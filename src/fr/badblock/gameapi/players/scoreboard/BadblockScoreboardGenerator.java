package fr.badblock.gameapi.players.scoreboard;

import fr.badblock.gameapi.GameAPI;

/**
 * Repr�sente la classe permettant de g�n�rer le scoreboard.
 * 
 * @author LeLanN
 */
public abstract class BadblockScoreboardGenerator {

	/**
	 * Permet de g�n�rer une derni�re ligne anim�e
	 * 
	 * @param objective
	 *            L'objectif g�r�
	 */
	public void doBadblockFooter(CustomObjective objective) {
		objective.changeLine(1, "&bplay.badblock.fr / " + GameAPI.getServerName());
		//bukkitTask = Bukkit.getScheduler().runTaskTimer(GameAPI.getAPI(), this::doBadblockFooter0, 0, 1L);
	}

	/*private void doBadblockFooter0() {
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
	}*/

	/**
	 * G�n�re le scoreboard
	 */
	public abstract void generate();
}
