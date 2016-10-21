package fr.badblock.gameapi.events.api;

import org.bukkit.event.HandlerList;

import fr.badblock.gameapi.events.abstracts.BadblockPlayerEvent;
import fr.badblock.gameapi.players.BadblockPlayer;

/**
 * Event appelé lorsque les permissions du joueur sont mises à jour
 * 
 * @author LeLanN
 */
public class PlayerPermissionLoadedEvent extends BadblockPlayerEvent {
	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlers;
	}

	public PlayerPermissionLoadedEvent(BadblockPlayer player) {
		super(player);
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}