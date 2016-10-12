package fr.badblock.gameapi.events.api;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * Event appelé lorsque des données du joueur ont été modifiées
 * 
 * @author LeLanN
 */
@AllArgsConstructor
public class PlayerDataChangedEvent extends Event {
	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Getter
	private BadblockPlayer player;

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}