package fr.badblock.gameapi.events.fakedeaths;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Event appelďż˝ lorsque le joueur se fait tuďż˝ en PVP ou en PVE.<br>
 * L'entitďż˝ fournie peut ďż˝tre :
 * <ul>
 * <li>Un joueur</li>
 * <li>Une entitďż˝ agressive</li>
 * <li>Un projectile si la source du projectile n'est pas une entitďż˝ vivante
 * (par exemple avec un dispenser).</li> Cet event est aussi appelďż˝ si l'entitďż˝
 * a ďż˝tďż˝ en combat dans les quelques derniďż˝res secondes et est morte de chute,
 * feu, ... Utiliser le {@link #getLastDamageCause()} pour ďż˝a.<br>
 * {@link #getFightType()} donne le type du dernier combat (Cďż˝C, Tďż˝D, potion,
 * custom)
 * 
 * @author LeLanN
 */
@RequiredArgsConstructor
public class FightingDeathEvent extends FakeDeathEvent {
	/**
	 * Les diffďż˝rents modes de PvP pour savoir comment est mort le joueur.
	 * 
	 * @author LeLanN
	 */
	public enum FightingDeaths {
		/**
		 * Au corps ďż˝ corps
		 */
		INFIGHTING,
		/**
		 * A l'arc ou ďż˝ distance
		 */
		BOW,
		/**
		 * Avec des potions
		 */
		POTION,
		/**
		 * Via les dďż˝gats d'un plugins
		 */
		CUSTOM;
	}

	private static final HandlerList handlers = new HandlerList();
	public static HandlerList getHandlerList() {
		return handlers;
	}
	@Getter
	private final BadblockPlayer player;
	@Getter
	private final Entity killer;

	@Getter
	private final FightingDeaths fightType;

	@Getter
	private final DamageCause lastDamageCause;
	
	@Getter
	private final Object recognizer;

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}
}
