package fr.badblock.gameapi.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.plugin.Plugin;

import fr.badblock.gameapi.utils.selections.CuboidSelection;

public class ConfigUtils {

	/**
	 * Converti une location block en chaine
	 * 
	 * @param location
	 * @return
	 */
	public static String convertLocationBlockToString(Location location) {
		String world = location.getWorld().getName();
		int x = location.getBlockX();
		int y = location.getBlockY();
		int z = location.getBlockZ();
		return world + "," + x + "," + y + "," + z;
	}

	/**
	 * Convertir une location en chaine
	 * 
	 * @param location
	 * @return
	 */
	public static String convertLocationToString(Location location) {
		String world = location.getWorld().getName();
		double x = location.getX();
		double y = location.getY();
		double z = location.getZ();
		float pitch = location.getPitch();
		float yaw = location.getYaw();
		return world + "," + x + "," + y + "," + z + "," + pitch + "," + yaw;
	}

	/**
	 * Location Block ďż˝ partir d'une chaine
	 * 
	 * @param string
	 * @return
	 */
	public static Location convertStringToBlockLocation(String string) {
		if (string != null) {
			String[] wxyz = string.split(",");
			World w = Bukkit.getWorld(wxyz[0]);
			int x = Integer.parseInt(wxyz[1]);
			int y = Integer.parseInt(wxyz[2]);
			int z = Integer.parseInt(wxyz[3]);
			return new Location(w, x, y, z);
		}
		return null;
	}

	/**
	 * Location ďż˝ partir d'une chaine
	 * 
	 * @param string
	 * @return
	 */
	public static Location convertStringToLocation(String string) {
		if (string == null)
			return null;
		String[] wxyzPitchYaw = string.split(",");
		World w = Bukkit.getWorld(wxyzPitchYaw[0]);
		double x = Double.parseDouble(wxyzPitchYaw[1]);
		double y = Double.parseDouble(wxyzPitchYaw[2]);
		double z = Double.parseDouble(wxyzPitchYaw[3]);
		float pitch = Float.parseFloat(wxyzPitchYaw[4]);
		float yaw = Float.parseFloat(wxyzPitchYaw[5]);
		Location location = new Location(w, x, y, z);
		location.setPitch(pitch);
		location.setYaw(yaw);
		return location;
	}

	/**
	 * Rďż˝cupďż˝rer un objet quelconque ďż˝ l'aide d'une clďż˝ & crďż˝er cette clďż˝ avec
	 * une valeur par dďż˝faut si cette clďż˝ n'est pas prďż˝sente dans la
	 * configuration
	 * 
	 * @param plugin
	 * @param key
	 * @param defaultValue
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T get(Plugin plugin, String key, T defaultValue) {
		if (!plugin.getConfig().contains(key)) {
			plugin.getConfig().set(key, defaultValue);
			plugin.saveConfig();
		}
		return (T) plugin.getConfig().get(key);
	}

	/**
	 * Recupďż˝rer une location de type Block dans la config
	 * 
	 * @param plugin
	 * @param node
	 * @return
	 */
	public static Location getBlockLocationFromFile(Plugin plugin, String node) {
		return convertStringToBlockLocation(plugin.getConfig().getString(node));
	}

	/**
	 * RĂŠcupĂŠrer un cuboid depuis une configuration
	 * @param plugin
	 * @param node
	 * @return
	 */
	public static CuboidSelection getCuboid(Plugin plugin, String node) {
		return new CuboidSelection(convertStringToBlockLocation(plugin.getConfig().getString(node + ".location1")), convertStringToBlockLocation(plugin.getConfig().getString(node + ".location2")));
	}

	/**
	 * Rďż˝cupďż˝rer un boolďż˝en & le set si il n'y est pas prďż˝sent dans la
	 * configuration
	 * 
	 * @param plugin
	 * @param key
	 * @return
	 */
	public static boolean getBoolean(Plugin plugin, String key) {
		if (!plugin.getConfig().contains(key)) {
			plugin.getConfig().set(key, false);
			plugin.saveConfig();
			return false;
		}
		return plugin.getConfig().getBoolean(key, false);
	}

	/**
	 * Rďż˝pďż˝rer un entier & le set si il n'y est pas prďż˝sent dans la
	 * configuration
	 * 
	 * @param plugin
	 * @param key
	 * @return
	 */
	public static int getInt(Plugin plugin, String key) {
		if (!plugin.getConfig().contains(key)) {
			plugin.getConfig().set(key, 0);
			plugin.saveConfig();
			return 0;
		}
		return plugin.getConfig().getInt(key, 0);
	}

	/**
	 * Recupďż˝re le point nommďż˝
	 * 
	 * @param plugin
	 * @return le point nommďż˝ ou le spawn de base du world
	 */
	public static Location getLocation(Plugin plugin, String name) {
		return getLocationFromFile(plugin, name) != null ? getLocationFromFile(plugin, name) : null;
	}

	/**
	 * Recupďż˝re une location dans la config
	 * 
	 * @param plugin
	 * @param node
	 * @return
	 */
	public static Location getLocationFromFile(Plugin plugin, String node) {
		return convertStringToLocation(plugin.getConfig().getString(node));
	}

	/*
	 * Location file convertion
	 */

	/**
	 * Recupďż˝re une liste de locations
	 */
	public static List<Location> getLocationList(Plugin plugin, String name) {
		List<Location> spawns = new ArrayList<Location>();
		List<String> configSpawns = plugin.getConfig().getStringList(name);
		for (String loc : configSpawns) {
			String[] wxyz = loc.split(",");
			World w = Bukkit.getWorld(wxyz[0]);
			double x = Double.parseDouble(wxyz[1]);
			double y = Double.parseDouble(wxyz[2]);
			double z = Double.parseDouble(wxyz[3]);
			float pitch = Float.parseFloat(wxyz[4]);
			float yaw = Float.parseFloat(wxyz[5]);
			Location location = new Location(w, x, y, z);
			location.setPitch(pitch);
			location.setYaw(yaw);
			spawns.add(location);
		}
		return spawns;
	}

	/**
	 * Recupďż˝rer le nom de la map
	 * 
	 * @param plugin
	 * @return le nom ou "NoName" si la configuration n'en a pas
	 */
	public static String getMapName(Plugin plugin) {
		return (plugin.getConfig().getString("mapName") != null ? plugin.getConfig().getString("mapName") : "NoName");
	}

	/**
	 * Rďż˝cupďż˝rer un matďż˝riel & le set si il n'y est pas prďż˝sent dans la
	 * configuration
	 * 
	 * @param plugin
	 * @param key
	 * @return
	 */
	public static Material getMaterial(Plugin plugin, String key) {
		if (!plugin.getConfig().contains(key)) {
			plugin.getConfig().set(key, Material.STONE.name());
			plugin.saveConfig();
			return Material.AIR;
		}
		return Material.getMaterial(plugin.getConfig().getString(key, Material.STONE.name()));
	}

	/**
	 * Rďż˝cupďż˝rer une liste de chaďż˝ne de caractďż˝res & la crďż˝er si elle n'est pas
	 * prďż˝sente dans la configuration
	 * 
	 * @param plugin
	 * @param key
	 * @return
	 */
	public static List<String> getStringList(Plugin plugin, String key) {
		if (!plugin.getConfig().contains(key)) {
			List<String> list = new ArrayList<>();
			plugin.getConfig().set(key, list);
			plugin.saveConfig();
			return list;
		}
		return plugin.getConfig().getStringList(key);
	}

	/**
	 * Rďż˝cupďż˝rer une liste de chaďż˝ne de caractďż˝res & la crďż˝er si elle n'est pas
	 * prďż˝sente dans la configuration
	 * 
	 * @param plugin
	 * @param key
	 * @return
	 */
	public static List<Integer> getIntList(Plugin plugin, String key) {
		if (!plugin.getConfig().contains(key)) {
			List<Integer> list = new ArrayList<>();
			plugin.getConfig().set(key, list);
			plugin.saveConfig();
			return list;
		}
		return plugin.getConfig().getIntegerList(key);
	}

	/*
	 * Convertions
	 */

	/**
	 * Sauvegarde la location en type Block
	 * 
	 * @param plugin
	 * @param node
	 * @param location
	 */
	public static void saveBlockLocation(Plugin plugin, String node, Location location) {
		plugin.getConfig().set(node, convertLocationBlockToString(location));
		plugin.saveConfig();
	}

	/**
	 * Sauvegarde la location
	 * 
	 * @param plugin
	 * @param node
	 * @param location
	 */
	public static void saveLocation(Plugin plugin, String node, Location location) {
		plugin.getConfig().set(node, convertLocationToString(location));
		plugin.saveConfig();
	}

	/**
	 * Sauvegarde une liste de locations
	 * 
	 * @param plugin
	 * @param locations
	 * @param name
	 */
	public static void saveLocationList(Plugin plugin, List<Location> locations, String name) {
		List<String> locsToString = new ArrayList<String>();
		for (Location location : locations) {
			locsToString.add(ConfigUtils.convertLocationToString(location));
		}
		plugin.getConfig().set(name, locsToString);
		plugin.saveConfig();
	}

	/**
	 * Dďż˝finit la location nommďż˝e
	 * 
	 * @param plugin
	 * @param location
	 */
	public static void setLocation(Plugin plugin, Location location, String name) {
		saveLocation(plugin, name, location);
		plugin.saveConfig();
	}
}
