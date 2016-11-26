package fr.badblock.gameapi.utils;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.apache.commons.lang3.ArrayUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World.Environment;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import fr.badblock.gameapi.BadListener;
import fr.badblock.gameapi.command.AbstractCommand;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.players.BadblockPlayer.BadblockMode;
import fr.badblock.gameapi.utils.reflection.ReflectionUtils;
import fr.badblock.gameapi.utils.reflection.Reflector;

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
	 * Récupère le joueur demandé
	 * 
	 * @return Le pseudo
	 */
	public static BadblockPlayer getPlayer(String playerName) {
		Player player = Bukkit.getPlayer(playerName);
		if (player == null) return null;
		return (BadblockPlayer) player;
	}

	/**
	 * Récupère le joueur demandé
	 * 
	 * @return Le pseudo
	 */
	public static BadblockPlayer getPlayer(UUID playerUniqueId) {
		Player player = Bukkit.getPlayer(playerUniqueId);
		if (player == null) return null;
		return (BadblockPlayer) player;
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

	public static void setMaxPlayers(int maxPlayers) throws Exception {
		new Reflector(ReflectionUtils.getHandle( Bukkit.getServer() )).setFieldValue("maxPlayers", maxPlayers);
	}

	/**
	 * Instancie les listeners (BadListener et Listener) et commands (AbstractCommand) présent dans un package
	 * @param plugin Le plugin
	 * @param paths Les packages
	 * @throws IOException En cas de problème avec la lecture du JAR
	 */
	public static void instanciateListenersAndCommandsFrom(Plugin plugin, String... paths) throws IOException {
		URL url = plugin.getClass().getProtectionDomain().getCodeSource().getLocation();

		ZipInputStream zip = new ZipInputStream(url.openStream());
		ZipEntry entry = null;

		while((entry = zip.getNextEntry()) != null)
		{
			String finded = null;
			
			for(String path : paths){
				if(entry.getName().startsWith( path.replace(".", "/") ))
				{
					finded = path;
					break;
				}}

			if(finded != null && entry.getName().endsWith(".class"))
			{
				try {
					String[] splitted = entry.getName().split("/");
					splitted = splitted[splitted.length - 1].split("\\.");

					String className = finded + "." + splitted[0];

					Class<?> clazz = plugin.getClass().getClassLoader().loadClass(className);

					if( inheritFrom(clazz, BadListener.class) )
					{
						instanciate(clazz);
					}
					else if( inheritNormalListener(clazz) )
					{
						Listener listener = (Listener) instanciate(clazz);

						if(listener != null)
							plugin.getServer().getPluginManager().registerEvents(listener, plugin);
					}
					else if( inheritFrom(clazz, AbstractCommand.class) )
					{
						instanciate(clazz);
					}
				} catch(Exception e){
					e.printStackTrace();
				}
			}

		}
	}

	private static boolean inheritFrom(Class<?> clazz, Class<?> from){
		while(clazz != Object.class)
		{
			if(clazz == from)
				return true;

			clazz = clazz.getSuperclass();
		}

		return false;
	}

	private static boolean inheritNormalListener(Class<?> clazz){

		while(clazz != Object.class)
		{
			if(ArrayUtils.contains(clazz.getInterfaces(), Listener.class))
				return true;

			clazz = clazz.getSuperclass();
		}

		return false;

	}

	private static Object instanciate(Class<?> clazz) throws Exception {
		if(clazz.getConstructor() == null)
		{
			System.out.println("Warning: can't load " + clazz.getCanonicalName() + " (no empty constructor)");
			return null;
		}

		return clazz.getConstructor().newInstance();
	}

}
