package fr.badblock.gameapi.game.rankeds;

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
	public String getCurrentRankedGameName() {
		return "unknown";
	}
	
}
