package fr.badblock.gameapi.utils.entities;

import java.util.List;

import org.bukkit.entity.Entity;

/**
 * Représentante une créature vivante (monstre, animal ou ambient).<br>
 * Ajoute des méthodes non présente par défaut dans Bukkit (vitesse ou invincibilité par exemple) évitant de passer par NMS.<br>
 * Ajoute des méthodes non présente par défaut dans Minecraft, principalement le comportement de la créature (pouvoir voler, agressivité, ...)<br>
 * <br>
 * Les CustomCreatures sont register lorsque le plugin API s'enable.<br>
 * Pour récupérer, utiliser {@link fr.badblock.gameapi.utils.entities.CreatureUtils#getAsCustom(org.bukkit.entity.Entity)}
 * @author LeLanN
 */
public interface CustomCreature {
	/**
	 * Récupère le type de la créature.
	 * @return Le type de la créature.
	 */
	public CreatureType getEntityType();

	public void setCreatureBehaviour(CreatureBehaviour behaviour);

	public CreatureBehaviour getCreatureBehaviour();

	default void addCreatureFlag(CreatureFlag flag){
		if(!getFlags().contains(flag)){
			getFlags().add(flag);
			regenerateAttributes();
		}
	}
	
	default void addCreatureFlags(CreatureFlag... flags){
		for(CreatureFlag flag : flags){
			addCreatureFlag(flag);
		}
	}

	default void removeCreatureFlag(CreatureFlag flag){
		getFlags().remove(flag);
		regenerateAttributes();
	}
	
	default void removeCreatureFlags(CreatureFlag... flags){
		for(CreatureFlag flag : flags){
			getFlags().remove(flag);
		}
		
		regenerateAttributes();
	}

	default boolean hasCreatureFlag(CreatureFlag flag){
		return getFlags().contains(flag);
	}
	
	default boolean hasCreatureFlags(CreatureFlag... flags){
		for(CreatureFlag flag : flags){
			if(!hasCreatureFlag(flag))
				return false;
		}
		
		return true;
	}

	public List<CreatureFlag> getFlags();

	public void setCreatureGenericAttribute(CreatureGenericAttribute attribute, double value);
	
	public double getCreatureGenericAttribute(CreatureGenericAttribute attribute);
	
	public void regenerateAttributes();

	/**
	 * Récupère l'entité Bukkit
	 * @return L'entité Bukkit
	 */
	public Entity  getBukkit();

	public enum CreatureFlag {
		RIDEABLE,
		AGRESSIVE,
		INVINCIBLE,
		FIREPROOF,
		INVISIBLE;
	}

	public enum CreatureBehaviour {
		MOTIONLESS,
		FLYING,
		NORMAL;
	}
	
	public enum CreatureGenericAttribute {
		SPEED,
		DAMAGE;
	}
}
