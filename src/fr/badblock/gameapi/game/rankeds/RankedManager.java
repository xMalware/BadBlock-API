package fr.badblock.gameapi.game.rankeds;

import fr.badblock.gameapi.players.BadblockPlayer;

public abstract class RankedManager {

	public static RankedManager instance = new FakeRankedManager();

	public abstract String getCurrentRankedGameName();
	public abstract void initialize(String gameName, String... fields);
	public abstract void fill(String gameName);
	public abstract long getData(String gameName, String playerName, String fieldName);
	public abstract void calcPoints(String gameName, BadblockPlayer player, RankedCalc calc);
	
}
