package fr.badblock.gameapi.events.fakedeaths;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Event appelé lorsque le joueur se fait tué en PVP ou en PVE.<br>
 * L'entité fournie peut être :
 * <ul>
 * <li>Un joueur</li>
 * <li>Une entité agressive</li>
 * <li>Un projectile si la source du projectile n'est pas une entité vivante
 * (par exemple avec un dispenser).</li> Cet event est aussi appelé si l'entité
 * a été en combat dans les quelques dernières secondes et est morte de chute,
 * feu, ... Utiliser le {@link #getLastDamageCause()} pour ça.<br>
 * {@link #getFightType()} donne le type du dernier combat (CàC, TàD, potion,
 * custom)
 * 
 * @author LeLanN
 */
@RequiredArgsConstructor
public class FightingDeathEvent extends FakeDeathEvent {
	private static final HandlerList handlers = new HandlerList();

	@Getter
	private final BadblockPlayer player;
	@Getter
	private final Entity killer;
	@Getter
	private final FightingDeaths fightType;
	@Getter
	private final DamageCause lastDamageCause;

	/**
	 * Les différents modes de PvP pour savoir comment est mort le joueur.
	 * 
	 * @author LeLanN
	 */
	public enum FightingDeaths {
		/**
		 * Au corps à corps
		 */
		INFIGHTING,
		/**
		 * A l'arc ou à distance
		 */
		BOW,
		/**
		 * Avec des potions
		 */
		POTION,
		/**
		 * Via les dégats d'un plugins
		 */
		CUSTOM;
	}

	public HandlerList getHandlers() {
		return handlers;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}
}
