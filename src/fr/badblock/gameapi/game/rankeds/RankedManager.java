package fr.badblock.gameapi.game.rankeds;

import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.utils.general.Callback;

public abstract class RankedManager {

	public static RankedManager instance = new FakeRankedManager();

	public abstract String getCurrentRankedGameName();
	public abstract void initialize(String gameName, String... fields);
	public abstract void fill(String gameName);
	public abstract void fill(String gameName, String playerName);
	public abstract void getTotalRank(String gameName, BadblockPlayer player, Callback<Integer> callback);
	public abstract void getMonthRank(String gameName, BadblockPlayer player, Callback<Integer> callback);
	public abstract long getData(String gameName, BadblockPlayer player, String fieldName);
	public abstract void calcPoints(String gameName, BadblockPlayer player, RankedCalc calc);
	
}
