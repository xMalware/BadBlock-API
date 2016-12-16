package fr.badblock.gameapi.sentry;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter public class SEntry {

	private String		playerName;
	private String		serverName;
	private boolean		newGame;

	public SEntry (String playerName, String serverName, boolean newGame) {
		this.setPlayerName(playerName);
		this.setServerName(serverName);
		this.setNewGame(newGame);
	}

}
