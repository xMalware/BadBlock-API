package fr.badblock.gameapi.utils.general;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;

public class Flags {

	private static Map<UUID, Map<String, Long>>	flags;
	
	static
	{
		flags = new HashMap<>();
	}
	
	public static boolean isValid(Player player, String flag)
	{
		UUID uuid = player.getUniqueId();
		if (!hasFlag(player, flag))
		{
			return false;
		}
		Map<String, Long> map = !flags.containsKey(uuid) ? new HashMap<>() : flags.get(uuid);
		if (!map.containsKey(flag))
		{
			return false;
		}
		return map.get(flag) > System.currentTimeMillis();
	}
	
	public static boolean hasFlag(Player player, String flag)
	{
		UUID uuid = player.getUniqueId();
		if (!flags.containsKey(uuid))
		{
			return false;
		}
		Map<String, Long> map = !flags.containsKey(uuid) ? new HashMap<>() : flags.get(uuid);
		return map.containsKey(flag);
	}
	
	public static void setTemporaryFlag(Player player, String flag, long time)
	{
		UUID uuid = player.getUniqueId();
		Map<String, Long> map = !flags.containsKey(uuid) ? new HashMap<>() : flags.get(uuid);
		map.put(flag, System.currentTimeMillis() + time);
		flags.put(uuid, map);
	}
	
	public static void removeFlag(Player player, String flag)
	{
		UUID uuid = player.getUniqueId();
		if (!hasFlag(player, flag))
		{
			return;
		}
		Map<String, Long> map = !flags.containsKey(uuid) ? new HashMap<>() : flags.get(uuid);
		map.remove(flag);
		flags.put(uuid, map);
	}
	
}
