package fr.badblock.gameapi.events.api;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.badblock.gameapi.game.GameServer;
import fr.badblock.gameapi.game.GameServer.WhileRunningConnectionTypes;
import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Si un joueur déconnecte en pleine partie et que le type de gestion de
 * connection est correctement définit
 * [{@link GameServer#whileRunningConnection(WhileRunningConnectionTypes)} cet
 * event sera appelé pour vérifier que le joueur y ai bien autorisé
 * 
 * @author LeLanN
 */
@Getter
@Setter
@RequiredArgsConstructor
public class PlayerReconnectionPropositionEvent extends Event implements Cancellable {
	private static final HandlerList handlers = new HandlerList();

	private final BadblockPlayer player;
	private boolean isCancelled = false;

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
