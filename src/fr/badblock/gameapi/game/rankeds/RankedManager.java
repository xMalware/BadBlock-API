package fr.badblock.gameapi.game.rankeds;

import fr.badblock.gameapi.players.BadblockPlayer;

public abstract class RankedManager {

	public static RankedManager instance = new FakeRankedManager();

	public abstract void initialize(String gameName, String... fields);
	public abstract void fill(String gameName, BadblockPlayer badblockPlayer, long... data);

}
