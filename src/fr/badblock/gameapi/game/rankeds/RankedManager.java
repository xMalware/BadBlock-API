package fr.badblock.gameapi.game.rankeds;

public abstract class RankedManager {

	public static RankedManager instance = new FakeRankedManager();

	public abstract String getCurrentRankedGameName();
	public abstract void initialize(String gameName, String... fields);
	public abstract void fill(String gameName);

}
