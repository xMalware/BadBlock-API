package fr.badblock.gameapi.events.api;

import org.bukkit.event.HandlerList;

import fr.badblock.gameapi.events.abstracts.BadblockPlayerEvent;
import fr.badblock.gameapi.players.BadblockPlayer;

/**
 * Event appelé lorsque un joueur rentre dans le serveur pendant une partie
 * 
 * @author LeLanN
 */
public class SpectatorJoinEvent extends BadblockPlayerEvent {
	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public SpectatorJoinEvent(BadblockPlayer player) {
		super(player);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}
