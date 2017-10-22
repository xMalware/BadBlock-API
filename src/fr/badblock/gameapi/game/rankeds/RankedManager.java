package fr.badblock.gameapi.game.rankeds;

import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.run.BadblockGame;

public abstract class RankedManager {

	public static RankedManager instance = new FakeRankedManager();

	public abstract void initialize(BadblockGame badblockGame, String... fields);
	public abstract void fill(BadblockGame badblockGame, BadblockPlayer badblockPlayer, long... data);

}
