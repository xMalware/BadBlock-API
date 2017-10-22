package fr.badblock.gameapi.game.rankeds;

import fr.badblock.gameapi.players.BadblockPlayer;

public class FakeRankedManager extends RankedManager
{

	@Override
	public void initialize(String gameName, String... fields) {
		System.out.println("[FakeRankedManager] (" + gameName + ") Initialize with " + fields.length + " data.");
	}

	@Override
	public void fill(String gameName, BadblockPlayer badblockPlayer, long... data) {
		System.out.println("[FakeRankedManager] (" + gameName + ") Fill " + badblockPlayer.getName() + " with " + data.length + " data.");
	}
	
}
