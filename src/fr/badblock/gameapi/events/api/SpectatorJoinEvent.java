package fr.badblock.gameapi.events.api;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Event appelé lorsque un joueur rentre dans le serveur pendant une partie
 * 
 * @author LeLanN
 */
@RequiredArgsConstructor
public class SpectatorJoinEvent extends Event {
	private static final HandlerList handlers = new HandlerList();

	@Getter
	private final BadblockPlayer player;

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
