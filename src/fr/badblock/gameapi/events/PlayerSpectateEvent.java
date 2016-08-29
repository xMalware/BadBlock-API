package fr.badblock.gameapi.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@RequiredArgsConstructor
public class PlayerSpectateEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlers;
	}
	@Getter
	private final BadblockPlayer player;
	@Getter
	private final Entity entity;

	@Getter
	@Setter
	private boolean isCancelled = false;

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}