package fr.badblock.gameapi.utils.entities;

import java.util.List;
import java.util.Random;
import java.util.function.Function;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.ItemStack;

/**
 * Représentante une créature vivante (monstre, animal ou ambient).<br>
 * Ajoute des méthodes non présente par défaut dans Bukkit (vitesse ou
 * invincibilité par exemple) évitant de passer par NMS.<br>
 * Ajoute des méthodes non présente par défaut dans Minecraft, principalement le
 * comportement de la créature (pouvoir voler, agressivité, ...)<br>
 * <br>
 * Les CustomCreatures sont register lorsque le plugin API s'enable.<br>
 * Pour récupérer, utiliser
 * {@link fr.badblock.gameapi.utils.entities.CreatureUtils#getAsCustom(org.bukkit.entity.Entity)}
 * 
 * @author LeLanN
 */
public interface CustomCreature {
	/**
	 * Liste les comportements appliquables aux entités
	 * 
	 * @author LeLanN
	 */
	public enum CreatureBehaviour {
		/**
		 * L'entité ne peut pas se déplacer ou tourner la tête. Elle n'est pas
		 * non plus sensible à la gravité.
		 */
		MOTIONLESS,
		/**
		 * L'entité peut voler (IA de chauve-souris)
		 */
		FLYING,
		/**
		 * L'entité est normale (comportement par défaut)
		 */
		NORMAL;
	}

	/**
	 * Liste des flags applicables aux entités
	 * 
	 * @author LeLanN
	 */
	public enum CreatureFlag {
		/**
		 * Si l'entité est rideable (autrement dit si l'on peut monter dessus en
		 * cliquant.<br>
		 * Néanmoins, que l'entité ai se flag ou nous, si un joueur est passager
		 * de l'entité, il pourra la contrôler.
		 */
		RIDEABLE,
		/**
		 * Si l'entité est agressive (attaque les joueurs proches)
		 */
		AGRESSIVE,
		/**
		 * Si l'entité est invincible (ne peut pas prendre de dégat)
		 */
		INVINCIBLE,
		/**
		 * Si l'entité est invincible au feu (ne peut pas prendre de dégat de
		 * feu)
		 */
		FIREPROOF,
		/**
		 * Si l'entité est invisible
		 */
		INVISIBLE;
	}

	/**
	 * List les attributs appliquable à l'entité
	 * 
	 * @author LeLanN
	 */
	public enum CreatureGenericAttribute {
		/**
		 * La vitesse de l'entité
		 */
		SPEED,
		/**
		 * Les dégats fait par l'entité
		 */
		DAMAGE;
	}
	
	public enum TargetType {
		NEAREST,
		HURTED_BY;
	}

	/**
	 * Ajoute un flag à l'entité
	 * 
	 * @param flag
	 *            Le flag
	 */
	default void addCreatureFlag(CreatureFlag flag) {
		if (!getFlags().contains(flag)) {
			getFlags().add(flag);
			regenerateAttributes();
		}
	}

	/**
	 * Ajoute des flags à l'entité
	 * 
	 * @param flags
	 *            Les flags
	 */
	default void addCreatureFlags(CreatureFlag... flags) {
		for (CreatureFlag flag : flags) {
			addCreatureFlag(flag);
		}
	}
	
	public void targetAllHurtingCreatures();
	
	public void addTargetable(EntityType entityType, TargetType targetType);

	public void removeTargetable(EntityType entityType);
	
	public void clearTargetables();
	
	public TargetType getTargetType(EntityType entityType);
	
	/**
	 * Récupère l'entité Bukkit
	 * 
	 * @return L'entité Bukkit
	 */
	public Entity getBukkit();

	/**
	 * Récupère le comportement de l'entité
	 * 
	 * @return Le comportement
	 */
	public CreatureBehaviour getCreatureBehaviour();

	/**
	 * Récupère un attribut générique de l'entité
	 * 
	 * @param attribute
	 *            L'attribut
	 * @return La valeur
	 */
	public double getCreatureGenericAttribute(CreatureGenericAttribute attribute);

	/**
	 * Récupère le type de la créature.
	 * 
	 * @return Le type de la créature.
	 */
	public CreatureType getEntityType();

	/**
	 * Récupère une liste des flags de l'entité
	 * 
	 * @return Les flags
	 */
	public List<CreatureFlag> getFlags();

	/**
	 * Vérifie si l'entité à un flag
	 * 
	 * @param flag
	 *            Le flag
	 * @return Si l'entité l'a
	 */
	default boolean hasCreatureFlag(CreatureFlag flag) {
		return getFlags().contains(flag);
	}

	/**
	 * Vérifie si l'entité à des flags
	 * 
	 * @param flag
	 *            Les flags
	 * @return Si l'entité les a
	 */
	default boolean hasCreatureFlags(CreatureFlag... flags) {
		for (CreatureFlag flag : flags) {
			if (!hasCreatureFlag(flag))
				return false;
		}

		return true;
	}

	/**
	 * Met à jour le comportement de l'entité avec les flags actuels (appelé
	 * automatiquement)
	 */
	public void regenerateAttributes();

	/**
	 * Enlève un flag à l'entité
	 * 
	 * @param flag
	 *            Le flag
	 */
	default void removeCreatureFlag(CreatureFlag flag) {
		getFlags().remove(flag);
		regenerateAttributes();
	}

	/**
	 * Enlève des flags à l'entité
	 * 
	 * @param flags
	 *            Les flags
	 */
	default void removeCreatureFlags(CreatureFlag... flags) {
		for (CreatureFlag flag : flags) {
			getFlags().remove(flag);
		}

		regenerateAttributes();
	}

	/**
	 * Définit le comportement de l'entité (au niveau du mouvement)
	 * 
	 * @param behaviour
	 *            Le comportement
	 */
	public void setCreatureBehaviour(CreatureBehaviour behaviour);

	/**
	 * Définit un attribut générique de l'entité
	 * 
	 * @param attribute
	 *            L'attribut
	 * @param value
	 *            La valeur
	 */
	public void setCreatureGenericAttribute(CreatureGenericAttribute attribute, double value);
	
	public void setCustomLoots(Function<Random, List<ItemStack>> function);
	
	public void setSpeed(double speed);
	
	public double getSpeed();
	
	public Function<Random, List<ItemStack>> getCustomLoots();
}
