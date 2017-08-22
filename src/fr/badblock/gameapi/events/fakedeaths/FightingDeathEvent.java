package fr.badblock.gameapi.events.fakedeaths;

import org.bukkit.entity.Entity;
import org.bukkit.event.HandlerList;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Event appel� lorsque le joueur se fait tu� en PVP ou en PVE.<br>
 * L'entit� fournie peut �tre :
 * <ul>
 * <li>Un joueur</li>
 * <li>Une entit� agressive</li>
 * <li>Un projectile si la source du projectile n'est pas une entit� vivante
 * (par exemple avec un dispenser).</li> Cet event est aussi appel� si l'entit�
 * a �t� en combat dans les quelques derni�res secondes et est morte de chute,
 * feu, ... Utiliser le {@link #getLastDamageCause()} pour �a.<br>
 * {@link #getFightType()} donne le type du dernier combat (C�C, T�D, potion,
 * custom)
 * 
 * @author LeLanN
 */
@RequiredArgsConstructor
public class FightingDeathEvent extends FakeDeathEvent {
	/**
	 * Les diff�rents modes de PvP pour savoir comment est mort le joueur.
	 * 
	 * @author LeLanN
	 */
	public enum FightingDeaths {
		/**
		 * Au corps � corps
		 */
		INFIGHTING,
		/**
		 * A l'arc ou � distance
		 */
		BOW,
		/**
		 * Avec des potions
		 */
		POTION,
		/**
		 * Via les d�gats d'un plugins
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
