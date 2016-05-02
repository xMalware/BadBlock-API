package fr.badblock.gameapi.events.api;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.badblock.gameapi.game.GameServer;
import fr.badblock.gameapi.game.GameServer.WhileRunningConnectionTypes;
import fr.badblock.gameapi.players.BadblockOfflinePlayer;
import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Si un joueur déconnecte en pleine partie et que le type de gestion de connection est correctement définit [{@link GameServer#whileRunningConnection(WhileRunningConnectionTypes)}
 * cet event sera appelé pour vérifier que le joueur y ai bien autorisé
 * 
 * @author LeLanN
 */
@Getter@Setter@AllArgsConstructor
public class PlayerReconnectionPropositionEvent extends Event implements Cancellable{
	private static final HandlerList	handlers	= new HandlerList();

	private final BadblockPlayer		player;
	private final BadblockOfflinePlayer playerData;
	
	private boolean 				    isCancelled;
	
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
