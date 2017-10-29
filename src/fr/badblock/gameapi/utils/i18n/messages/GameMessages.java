package fr.badblock.gameapi.utils.i18n.messages;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import fr.badblock.gameapi.events.fakedeaths.FightingDeathEvent;
import fr.badblock.gameapi.events.fakedeaths.NormalDeathEvent;
import fr.badblock.gameapi.utils.i18n.TranslatableString;
import fr.badblock.gameapi.utils.i18n.TranslatableWord;
import fr.badblock.gameapi.utils.i18n.Word.WordDeterminant;

/**
 * Liste de messages utilisés par l'API ou ŕ déstination des plugins annexes
 * pour unifier le systčme de traduction (pas de doublon) et simplifier le
 * développement.<br>
 * Messages jeux.
 * 
 * @author LeLanN
 */
public class GameMessages {
	public static TranslatableString deathEventMessage(FightingDeathEvent e) {
		if (e.getKiller().getType() == EntityType.PLAYER) {
			return new TranslatableString("deathmessages.pvp." + e.getLastDamageCause().name().toLowerCase(),
					e.getPlayer().getName(), e.getKiller().getName());
		} else {
			return new TranslatableString("deathmessages.pve." + e.getLastDamageCause().name().toLowerCase(),
					new TranslatableWord("entities." + e.getKiller().getType().name().toLowerCase(), false,
							WordDeterminant.UNDEFINED));
		}
	}

	public static TranslatableString deathEventMessage(NormalDeathEvent e) {
		return new TranslatableString("deathmessages.normal." + e.getLastDamageCause().name().toLowerCase(),
				e.getPlayer().getName());
	}

	/**
	 * Lorsqu'un spectateur s'éloigne, le message qui lui est affiché. Utile
	 * uniquement pour l'API.
	 * 
	 * @return Le message
	 */
	public static TranslatableString doNotGoTooFarWhenSpectator() {
		return new TranslatableString("game.spectator.toofar");
	}

	/**
	 * Lorsqu'un un joueur join, récupčre le message de join
	 * 
	 * @param name
	 *            Le nom du jeu ou serveur.
	 * @param player
	 *            Le nom du joueur
	 * @param current
	 *            Le nombre de joueurs connectés
	 * @param max
	 *            Le nombre maximum de joueurs connectés.
	 * @return Le message
	 */
	public static TranslatableString joinMessage(String name, String player, int current, int max) {
		return new TranslatableString("game.join", name, player, current, max);
	}

	public static TranslatableWord material(Material material, boolean plural, WordDeterminant determinant) {
		return new TranslatableWord("materials." + material.name().toLowerCase(), plural, determinant);
	}

	public static TranslatableString missingPlayersActionBar(int players) {
		return new TranslatableString("game.missingplayers", players);
	}

	/**
	 * Le temps avant que le joueur respawn (title)
	 * 
	 * @return La key du title
	 */
	public static String respawnTitleKey() {
		return "game.respawnIn-title";
	}

	public static TranslatableString startIn(int time, ChatColor color) {
		return new TranslatableString("game.startin.title", time, color.getChar());
	}

	public static TranslatableString startInActionBar(int time, ChatColor color) {
		return new TranslatableString("game.startin.actionbar", time, color.getChar());
	}
}
