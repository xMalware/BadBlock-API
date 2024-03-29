package fr.badblock.gameapi.sentry;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter public class FullSEntry {

	private int 		ingamePLayers;
	private String		prefix;
	private int			waitinglinePlayers;

	public FullSEntry (String prefix, int ingamePlayers, int waitinglinePlayers) {
		this.setPrefix(prefix);
		this.setIngamePLayers(ingamePlayers);
		this.setWaitinglinePlayers(waitinglinePlayers);
	}

}
