package fr.badblock.gameapi.events;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.badblock.gameapi.fakeentities.FakeEntity;
import fr.badblock.gameapi.packets.in.play.PlayInUseEntity.UseEntityAction;
import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Appelée lorsque un joueur clique sur un fausse entité.
 * 
 * @author LeLanN
 */
@AllArgsConstructor
public class PlayerFakeEntityInteractEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();

	@Getter
	private BadblockPlayer player;
	@Getter
	private FakeEntity<?> entity;
	@Getter
	private UseEntityAction action;
	@Getter
	@Setter
	private boolean cancelled;

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
