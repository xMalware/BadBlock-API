package fr.badblock.gameapi.utils.entities;

import java.util.List;

import org.bukkit.entity.Entity;

/**
 * Repr�sentante une cr�ature vivante (monstre, animal ou ambient).<br>
 * Ajoute des m�thodes non pr�sente par d�faut dans Bukkit (vitesse ou
 * invincibilit� par exemple) �vitant de passer par NMS.<br>
 * Ajoute des m�thodes non pr�sente par d�faut dans Minecraft, principalement le
 * comportement de la cr�ature (pouvoir voler, agressivit�, ...)<br>
 * <br>
 * Les CustomCreatures sont register lorsque le plugin API s'enable.<br>
 * Pour r�cup�rer, utiliser
 * {@link fr.badblock.gameapi.utils.entities.CreatureUtils#getAsCustom(org.bukkit.entity.Entity)}
 * 
 * @author LeLanN
 */
public interface CustomCreature {
	/**
	 * R�cup�re le type de la cr�ature.
	 * 
	 * @return Le type de la cr�ature.
	 */
	public CreatureType getEntityType();

	/**
	 * D�finit le comportement de l'entit� (au niveau du mouvement)
	 * 
	 * @param behaviour
	 *            Le comportement
	 */
	public void setCreatureBehaviour(CreatureBehaviour behaviour);

	/**
	 * R�cup�re le comportement de l'entit�
	 * 
	 * @return Le comportement
	 */
	public CreatureBehaviour getCreatureBehaviour();

	/**
	 * Ajoute un flag � l'entit�
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
	 * Ajoute des flags � l'entit�
	 * 
	 * @param flags
	 *            Les flags
	 */
	default void addCreatureFlags(CreatureFlag... flags) {
		for (CreatureFlag flag : flags) {
			addCreatureFlag(flag);
		}
	}

	/**
	 * Enl�ve un flag � l'entit�
	 * 
	 * @param flag
	 *            Le flag
	 */
	default void removeCreatureFlag(CreatureFlag flag) {
		getFlags().remove(flag);
		regenerateAttributes();
	}

	/**
	 * Enl�ve des flags � l'entit�
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
	 * V�rifie si l'entit� � un flag
	 * 
	 * @param flag
	 *            Le flag
	 * @return Si l'entit� l'a
	 */
	default boolean hasCreatureFlag(CreatureFlag flag) {
		return getFlags().contains(flag);
	}

	/**
	 * V�rifie si l'entit� � des flags
	 * 
	 * @param flag
	 *            Les flags
	 * @return Si l'entit� les a
	 */
	default boolean hasCreatureFlags(CreatureFlag... flags) {
		for (CreatureFlag flag : flags) {
			if (!hasCreatureFlag(flag))
				return false;
		}

		return true;
	}

	/**
	 * R�cup�re une liste des flags de l'entit�
	 * 
	 * @return Les flags
	 */
	public List<CreatureFlag> getFlags();

	/**
	 * D�finit un attribut g�n�rique de l'entit�
	 * 
	 * @param attribute
	 *            L'attribut
	 * @param value
	 *            La valeur
	 */
	public void setCreatureGenericAttribute(CreatureGenericAttribute attribute, double value);

	/**
	 * R�cup�re un attribut g�n�rique de l'entit�
	 * 
	 * @param attribute
	 *            L'attribut
	 * @return La valeur
	 */
	public double getCreatureGenericAttribute(CreatureGenericAttribute attribute);

	/**
	 * Met � jour le comportement de l'entit� avec les flags actuels (appel�
	 * automatiquement)
	 */
	public void regenerateAttributes();

	/**
	 * R�cup�re l'entit� Bukkit
	 * 
	 * @return L'entit� Bukkit
	 */
	public Entity getBukkit();

	/**
	 * Liste des flags applicables aux entit�s
	 * 
	 * @author LeLanN
	 */
	public enum CreatureFlag {
		/**
		 * Si l'entit� est rideable (autrement dit si l'on peut monter dessus en
		 * cliquant.<br>
		 * N�anmoins, que l'entit� ai se flag ou nous, si un joueur est passager
		 * de l'entit�, il pourra la contr�ler.
		 */
		RIDEABLE,
		/**
		 * Si l'entit� est agressive (attaque les joueurs proches)
		 */
		AGRESSIVE,
		/**
		 * Si l'entit� est invincible (ne peut pas prendre de d�gat)
		 */
		INVINCIBLE,
		/**
		 * Si l'entit� est invincible au feu (ne peut pas prendre de d�gat de
		 * feu)
		 */
		FIREPROOF,
		/**
		 * Si l'entit� est invisible
		 */
		INVISIBLE;
	}

	/**
	 * Liste les comportements appliquables aux entit�s
	 * 
	 * @author LeLanN
	 */
	public enum CreatureBehaviour {
		/**
		 * L'entit� ne peut pas se d�placer ou tourner la t�te. Elle n'est pas
		 * non plus sensible � la gravit�.
		 */
		MOTIONLESS,
		/**
		 * L'entit� peut voler (IA de chauve-souris)
		 */
		FLYING,
		/**
		 * L'entit� est normale (comportement par d�faut)
		 */
		NORMAL;
	}

	/**
	 * List les attributs appliquable � l'entit�
	 * 
	 * @author LeLanN
	 */
	public enum CreatureGenericAttribute {
		/**
		 * La vitesse de l'entit�
		 */
		SPEED,
		/**
		 * Les d�gats fait par l'entit�
		 */
		DAMAGE;
	}
}
