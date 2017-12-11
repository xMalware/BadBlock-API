package fr.badblock.gameapi.game.rankeds;

import fr.badblock.gameapi.players.BadblockPlayer;

public class FakeRankedManager extends RankedManager
{

	@Override
	public void initialize(String gameName, String... fields) {
		System.out.println("[FakeRankedManager] (" + gameName + ") Initialize with " + fields.length + " data.");
	}

	@Override
	public void fill(String gameName) {
		System.out.println("[FakeRankedManager] (" + gameName + ") Fill");
	}

	@Override
	public void fill(String gameName, String playerName) {
		System.out.println("[FakeRankedManager] (" + gameName + ") Fill for " + playerName);
	}

	@Override
	public String getCurrentRankedGameName() {
		return "unknown";
	}

	@Override
	public long getData(String gameName, BadblockPlayer playerName, String fieldName) {
		return 0;
	}

	@Override
	public void calcPoints(String gameName, BadblockPlayer player, RankedCalc calc) {
		long points = calc.done();
		System.out.println("[FakeRankedManager] (" + gameName + ") Calculated points for " + player.getName() + " : " + points);
	}
	
}
