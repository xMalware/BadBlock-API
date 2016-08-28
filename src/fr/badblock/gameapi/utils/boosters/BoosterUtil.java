package fr.badblock.gameapi.utils.boosters;

import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.players.data.PlayerData;
import fr.badblock.gameapi.players.data.boosters.Booster;
import fr.badblock.gameapi.players.data.boosters.PlayerBooster;

/**
 * Classe contenant des utilitaires pour les boosters
 * 
 * @author xMalware
 */
public class BoosterUtil {

	public static double getBoosted(PlayerData playerData, BoostedValue boostedValue) {
		if (playerData.getBoosters() == null || playerData.getBoosters().isEmpty())
			return 1;
		double boosted = 1D;
		for (PlayerBooster playerBooster : playerData.getBoosters()) {
			Booster booster = playerBooster.getBooster();
			if (booster == null)
				continue;
			double value = 0D;
			switch (boostedValue) {
			case XP:
				value = booster.getXpMultiplier();
				break;
			case COINS:
				value = booster.getCoinsMultiplier();
				break;
			}
			boosted = value > boosted ? value : boosted;
		}
		return boosted;
	}

	public static long getBoosted(PlayerData playerData, BoostedValue boostedValue, long value) {
		double boosted = getBoosted(playerData, boostedValue);
		return value *= boosted;
	}

	public static int getBoosted(PlayerData playerData, BoostedValue boostedValue, int value) {
		double boosted = getBoosted(playerData, boostedValue);
		return value *= boosted;
	}

	public static double getBoosted(BadblockPlayer player, BoostedValue boostedValue) {
		return getBoosted(player.getPlayerData(), boostedValue);
	}

	public static long getBoosted(BadblockPlayer player, BoostedValue boostedValue, long value) {
		return getBoosted(player.getPlayerData(), boostedValue, value);
	}

	public static int getBoosted(BadblockPlayer player, BoostedValue boostedValue, int value) {
		return getBoosted(player.getPlayerData(), boostedValue, value);
	}

}
