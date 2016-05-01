package fr.badblock.gameapi.events.api;

import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.players.BadblockTeam;
import fr.badblock.gameapi.utils.i18n.TranslatableString;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Event appelé lorsque un joueur entre dans une team
 * @author LeLanN
 */
@RequiredArgsConstructor
public class PlayerJoinTeamEvent extends Event implements Cancellable {
	private static final HandlerList	handlers	= new HandlerList();

	@Getter private final BadblockPlayer player;
	@Getter private final BadblockTeam   previousTeam;
	@Getter private final BadblockTeam   newTeam;
	@Getter private final JoinReason	 reason;
	
	@Getter@Setter
	private boolean 		   isCancelled  = false;
	@Getter@Setter
	private TranslatableString cancelReason = null;
	
	
	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
	
	/**
	 * Raison de l'entrée dans la team
	 * @author LeLanN
	 */
	public static enum JoinReason {
		/**
		 * Choix fait par le joueur lors de la période d'attente
		 */
		WHILE_WAITING,
		/**
		 * Changement de la répartition ou assignation forcée de team lors du démarrage
		 */
		REBALANCING;
	}
}
