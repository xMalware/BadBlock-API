package fr.badblock.gameapi.events.abstracts;

import org.bukkit.event.Event;

import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public abstract class BadblockPlayerEvent extends Event {
	
	@Getter
	protected BadblockPlayer player;

	
}
