package fr.badblock.gameapi.events.api;

import org.bukkit.event.Cancellable;
import org.bukkit.event.HandlerList;

import fr.badblock.gameapi.events.abstracts.BadblockPlayerEvent;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.players.BadblockTeam;
import fr.badblock.gameapi.utils.i18n.TranslatableString;
import lombok.Getter;
import lombok.Setter;

/**
 * Event appelé lorsque un joueur entre dans une team
 * 
 * @author LeLanN
 */
public class PlayerJoinTeamEvent extends BadblockPlayerEvent implements Cancellable {
	/**
	 * Raison de l'entrée dans la team
	 * 
	 * @author LeLanN
	 */
	public static enum JoinReason {
		/**
		 * Choix fait par le joueur lors de la période d'attente
		 */
		WHILE_WAITING,
		/**
		 * Changement de la répartition ou assignation forcée de team lors du
		 * démarrage
		 */
		REBALANCING;
	}

	private static final HandlerList handlers = new HandlerList();
	public static HandlerList getHandlerList() {
		return handlers;
	}
	@Getter
	private final BadblockTeam previousTeam;

	@Getter
	private final BadblockTeam newTeam;
	@Getter
	private final JoinReason reason;

	@Getter
	@Setter
	private boolean isCancelled = false;

	@Getter
	@Setter
	private TranslatableString cancelReason = null;

	public PlayerJoinTeamEvent(BadblockPlayer player, BadblockTeam previousTeam, BadblockTeam newTeam, JoinReason reason) {
		this(player, previousTeam, newTeam, reason, false, null);
	}

	public PlayerJoinTeamEvent(BadblockPlayer player, BadblockTeam previousTeam, BadblockTeam newTeam, JoinReason reason, boolean isCancelled) {
		this(player, previousTeam, newTeam, reason, isCancelled, null);
	}
	
	public PlayerJoinTeamEvent(BadblockPlayer player, BadblockTeam previousTeam, BadblockTeam newTeam, JoinReason reason, boolean isCancelled, TranslatableString cancelReason) {
		super(player);
		this.previousTeam = previousTeam;
		this.newTeam = newTeam;
		this.reason = reason;
		this.isCancelled = isCancelled;
		this.cancelReason = cancelReason;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}
