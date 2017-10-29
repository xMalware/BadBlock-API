package fr.badblock.gameapi.events;

import java.util.List;
import java.util.UUID;

import org.bukkit.event.HandlerList;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.events.abstracts.BadblockPlayerEvent;
import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.Getter;

/**
 * Appelée lorsque q'un groupe arrive en jeu
 * 
 * @author LeLanN
 */
public class PartyJoinEvent extends BadblockPlayerEvent {
	private static final HandlerList handlers = new HandlerList();

	public static HandlerList getHandlerList() {
		return handlers;
	}
	@Getter
	private List<UUID> playersWithHim;
	
	public PartyJoinEvent(BadblockPlayer player, List<UUID> playersWithHim) {
		super(player);
		this.playersWithHim = playersWithHim; 
	}
	
	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
	
	public void balancePlayersInTeam() {
		GameAPI.getAPI().balancePlayers(player, this.getPlayersWithHim());
	}
	
}
