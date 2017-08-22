package fr.badblock.gameapi.events.fakedeaths;

import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Event appel� lorsque le joueur meurt seul (ni PVP ni PVE).
 * 
 * @author LeLanN
 */
@RequiredArgsConstructor
public class NormalDeathEvent extends FakeDeathEvent {
	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlers;
	}
	@Getter
	private final BadblockPlayer player;

	@Getter
	private final DamageCause lastDamageCause;
	
	@Getter
	private final Object recognizer;

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}
