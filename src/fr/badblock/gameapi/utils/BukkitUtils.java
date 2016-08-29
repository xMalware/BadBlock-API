package fr.badblock.gameapi.utils;

import java.util.List;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.players.BadblockPlayer.BadblockMode;

/**
 * Une série de méthode permettant de simplifier certaines utilisations de l'API
 * Bukkit.
 * 
 * @author LelanN
 */
public class BukkitUtils {
	/**
	 * Execute une action pour chaque joueur
	 * 
	 * @param action
	 *            L'action
	 */
	public static void forEachPlayers(Consumer<BadblockPlayer> action) {
		for (Player p : Bukkit.getOnlinePlayers()) {
			BadblockPlayer player = (BadblockPlayer) p;

			action.accept(player);
		}
	}

	/**
	 * Récupère un environement de monde (nether, end, overworld) à partir de
	 * son id
	 * 
	 * @param id
	 *            L'id
	 * @return L'environment (si inexistant, overworld)
	 */
	@SuppressWarnings("deprecation")
	public static Environment getEnvironment(int id) {
		Environment env = Environment.getEnvironment(id);

		if (env == null)
			return Environment.NORMAL;
		return env;
	}

	/**
	 * Récupère les joueurs étant entrain de joueur (hors spectateurs)
	 * 
	 * @return Les joueurs
	 */
	public static Set<BadblockPlayer> getPlayers() {
		return Bukkit.getOnlinePlayers().stream().map(player -> {
			return (BadblockPlayer) player;
		}).filter(player -> {
			return player.getBadblockMode() != BadblockMode.SPECTATOR;
		}).collect(Collectors.toSet());
	}

	/**
	 * Vérifie si la location peut acceuillir un joueur
	 * 
	 * @param location
	 *            La location
	 * @return Un boolean
	 */
	public static boolean isSafeLocation(Location location) {
		Block block = location.getBlock();
		return !block.getType().isSolid() || !block.getRelative(0, 1, 0).getType().isSolid();
	}

	/**
	 * Téléporte tous les joueurs vers une liste de locations
	 * 
	 * @param location
	 *            Les locatioins
	 * @param whenNoTp
	 *            Là ou tp quand le joueur ne doit pas être tp avec les autres
	 * @param doTp
	 *            Vérifie si le joueur doit êtret p
	 */
	public static void teleportPlayersToLocations(List<Location> location, Location whenNoTp,
			Predicate<BadblockPlayer> doTp) {
		int i = 0;

		for (Player p : Bukkit.getOnlinePlayers()) {
			BadblockPlayer player = (BadblockPlayer) p;

			if (doTp.test(player)) {
				if (i >= location.size())
					i = 0;

				location.get(i).getChunk().load();
				player.teleport(location.get(i));
				i++;
			} else
				player.teleport(whenNoTp);
		}
	}

	/**
	 * Permet de changer un block de manière temporaire
	 * 
	 * @param block
	 *            Le block
	 * @param newType
	 *            Le type à mettre temporairement
	 * @param newData
	 *            La data à mettre temporairement
	 * @param ticks
	 *            Le nombre de ticks pendant lesquels le block va rester
	 */
	public static void temporaryChangeBlock(Block block, Material newType, byte newData, int ticks) {

	}
}
