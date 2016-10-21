package fr.badblock.gameapi.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import fr.badblock.gameapi.events.abstracts.BadblockPlayerEvent;
import fr.badblock.gameapi.fakeentities.FakeEntity;
import fr.badblock.gameapi.packets.in.play.PlayInUseEntity.UseEntityAction;
import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.Getter;
import lombok.Setter;

/**
 * Appelée lorsque un joueur clique sur un fausse entité.
 * 
 * @author LeLanN
 */
public class PlayerFakeEntityInteractEvent extends BadblockPlayerEvent implements Cancellable {
	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlers;
	}
	@Getter
	private FakeEntity<?> entity;
	@Getter
	private UseEntityAction action;

	@Getter
	@Setter
	private boolean cancelled;

	public PlayerFakeEntityInteractEvent(BadblockPlayer player, FakeEntity<?> entity, UseEntityAction action, boolean cancelled) {
		super(player);
		this.entity = entity;
		this.action = action;
		this.cancelled = cancelled;
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
}
