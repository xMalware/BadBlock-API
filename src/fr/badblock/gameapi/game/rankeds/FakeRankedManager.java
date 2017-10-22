package fr.badblock.gameapi.game.rankeds;

import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.run.BadblockGame;

public class FakeRankedManager extends RankedManager
{

	@Override
	public void initialize(BadblockGame badblockGame, String... fields) {
		System.out.println("[FakeRankedManager] (" +badblockGame.getDisplayGameName() + ") Initialize with " + fields.length + " data.");
	}

	@Override
	public void fill(BadblockGame badblockGame, BadblockPlayer badblockPlayer, long... data) {
		System.out.println("[FakeRankedManager] (" +badblockGame.getDisplayGameName() + ") Fill " + badblockPlayer.getName() + " with " + data.length + " data.");
	}
	
}
