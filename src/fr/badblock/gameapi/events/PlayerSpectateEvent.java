package fr.badblock.gameapi.events;

import org.bukkit.entity.Entity;
import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import fr.badblock.gameapi.events.abstracts.BadblockPlayerEvent;
import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.Getter;
import lombok.Setter;

public class PlayerSpectateEvent extends BadblockPlayerEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlers;
	}
	@Getter
	private final Entity entity;

	@Getter
	@Setter
	private boolean isCancelled = false;

	public PlayerSpectateEvent(BadblockPlayer player, Entity entity) {
		this(player, entity, false);
	}
	
	public PlayerSpectateEvent(BadblockPlayer player, Entity entity, boolean isCancelled) {
		super(player);
		this.entity = entity;
		this.isCancelled = isCancelled;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}