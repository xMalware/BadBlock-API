package fr.badblock.gameapi.events.fakedeaths;

import org.bukkit.Location;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Appelé lorsqu'un joueur vient d'ętre téléporté aprčs une fausse mort.
 * 
 * @author LelanN
 */
@RequiredArgsConstructor
public class PlayerFakeRespawnEvent extends Event {
	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlers;
	}
	@Getter
	private final BadblockPlayer player;

	@Getter
	private final Location location;

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}