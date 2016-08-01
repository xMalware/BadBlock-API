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

	/**
	 * Définit si l'entité est invisible
	 * @param invisible Si elle est invisible
	 * @deprecated Utiliser {@link #addCreatureFlag(CreatureFlag)} et {@link #removeCreatureFlag(CreatureFlag)} à la place
	 */
	default void setInvisible(boolean invisible){
		if(invisible)
			addCreatureFlag(CreatureFlag.INVISIBLE);
		else removeCreatureFlag(CreatureFlag.INVISIBLE);
	}

	/**
	 * Vérifie si l'entité est invisible
	 * @deprecated Utiliser {@link #hasCreatureFlag(CreatureFlag)} à la place
	 */
	default boolean isInvisible(){
		return hasCreatureFlag(CreatureFlag.INVISIBLE);
	}

	/**
	 * Vérifie si la créature est résistante au feu
	 * @deprecated Utiliser {@link #hasCreatureFlag(CreatureFlag)} à la place
	 */
	default boolean isFireProof(){
		return hasCreatureFlag(CreatureFlag.FIREPROOF);
	}

	/**
	 * Défini si la créature est résistante au feu
	 * @param fireProof Sa résistance (ou non)
	 * @deprecated Utiliser {@link #addCreatureFlag(CreatureFlag)} et {@link #removeCreatureFlag(CreatureFlag)} à la place
	 */
	default void setFireProof(boolean fireProof){
		if(fireProof)
			addCreatureFlag(CreatureFlag.FIREPROOF);
		else removeCreatureFlag(CreatureFlag.FIREPROOF);
	}

	/**
	 * Vérifie si l'entité est invincible (ne peut prendre aucun dégat naturels, seul setHealth lui fera perdre de la vie)
	 * @deprecated Utiliser {@link #hasCreatureFlag(CreatureFlag)} à la place
	 */
	default boolean isInvincible(){
		return hasCreatureFlag(CreatureFlag.INVINCIBLE);
	}

	/**
	 * Défini si la créature est invincible (ne peut prendre aucun dégats naturels, seul setHealth lui fera perdre de la vie) 
	 * @param invincible Si la créature est invincible
	 * @deprecated Utiliser {@link #addCreatureFlag(CreatureFlag)} et {@link #removeCreatureFlag(CreatureFlag)} à la place
	 */
	default void setInvincible(boolean invincible){
		if(invincible)
			addCreatureFlag(CreatureFlag.INVINCIBLE);
		else removeCreatureFlag(CreatureFlag.INVINCIBLE);
	}

	/**
	 * Vérifie si l'entité est agressive (va essayer de frapper un joueur si elle en voit un)
	 * @deprecated Utiliser {@link #hasCreatureFlag(CreatureFlag)} à la place
	 */
	default boolean isAgressive(){
		return hasCreatureFlag(CreatureFlag.AGRESSIVE);
	}

	/**
	 * Défini si l'entité est agressive (va essayer de frapper un joueur si elle en voit un).
	 * @param agressive Si l'entité est agressive.
	 * @deprecated Utiliser {@link #addCreatureFlag(CreatureFlag)} et {@link #removeCreatureFlag(CreatureFlag)} à la place
	 */
	default void setAgressive(boolean agressive){
		if(agressive)
			addCreatureFlag(CreatureFlag.AGRESSIVE);
		else removeCreatureFlag(CreatureFlag.AGRESSIVE);
	}

	/**
	 * Vérifie si l'entité peut se faire monter (clique droit sur l'entité) par n'importe quel joueur.
	 * Pour le cochon, il ne nécessite plus de selle et de canne.
	 * @deprecated Utiliser {@link #hasCreatureFlag(CreatureFlag)} à la place
	 */
	default boolean isRideable(){
		return hasCreatureFlag(CreatureFlag.RIDEABLE);
	}

	/**
	 * Défini si l'entité peut se faire monter (clique droit sur l'entité) par n'importe quel joueur.
	 * @param rideable Si l'entité peut se faire monter
	 * @deprecated Utiliser {@link #addCreatureFlag(CreatureFlag)} et {@link #removeCreatureFlag(CreatureFlag)} à la place
	 */
	default void setRideable(boolean rideable){
		if(rideable)
			addCreatureFlag(CreatureFlag.RIDEABLE);
		else removeCreatureFlag(CreatureFlag.RIDEABLE);
	}

	/**
	 * Change la vitesse de l'entité
	 * @param speed La nouvelle vitesse
	 * @deprecated Utiliser {@link #setCreatureGenericAttribute(CreatureGenericAttribute, double)}
	 */
	default void setSpeed(double speed){
		setCreatureGenericAttribute(CreatureGenericAttribute.SPEED, speed);
	}

	/**
	 * Récupère la vitesse de l'entité
	 * @deprecated Utiliser {@link #getCreatureGenericAttribute(CreatureGenericAttribute)}
	 */
	default double getSpeed(){
		return getCreatureGenericAttribute(CreatureGenericAttribute.SPEED);
	}

	/**
	 * Change les dégats fait par l'entité
	 * @param damage Les nouveaux dégats
	 * @deprecated Utiliser {@link #setCreatureGenericAttribute(CreatureGenericAttribute, double)}
	 */
	default void setDamage(double damage){
		setCreatureGenericAttribute(CreatureGenericAttribute.DAMAGE, damage);
	}

	/**
	 * Récupère les dégats fait par l'entité
	 * @deprecated Utiliser {@link #getCreatureGenericAttribute(CreatureGenericAttribute)}
	 */
	default double getDamage(){
		return getCreatureGenericAttribute(CreatureGenericAttribute.DAMAGE);
	}

	/**
	 * Vérifie si l'entité peut se déplacer. Si non, elle n'est plus soumise à la gravité, n'a pas de knockback en combat, ...
	 * @deprecated Utiliser {@link #getCreatureBehaviour(CreatureBehaviour)}
	 */
	default boolean isMovable() {
		return getCreatureBehaviour() == CreatureBehaviour.MOTIONLESS;
	}

	/**
	 * Autorise ou non l'entité à se déplacer
	 * @param movable Si l'entité peut se déplacer
	 * @deprecated Utiliser {@link #setCreatureBehaviour(CreatureBehaviour)}
	 */
	default void setMovable(boolean movable) {
		setCreatureBehaviour(movable ? CreatureBehaviour.NORMAL : CreatureBehaviour.MOTIONLESS);
	}

	/**
	 * Vérifie si l'entité peut voler. Une entité non montée qui vole ne va pas attaquer les joueurs et va se contenter de se déplacer rapidement (à la manière d'une chauve-souris).
	 * @deprecated Utiliser {@link #getCreatureBehaviour(CreatureBehaviour)}
	 */
	default boolean isAllowedToFly(){
		return getCreatureBehaviour() == CreatureBehaviour.FLYING;
	}

	/**
	 * Défini si l'entité peut voler. Une entité non montée qui vole ne va pas attaquer les joueurs et va se contenter de se déplacer rapidement (à la manière d'une chauve-souris).
	 * @param allowed Si l'entité peut voler.
	 * @deprecated Utiliser {@link #setCreatureBehaviour(CreatureBehaviour)}
	 */
	default void setAllowedToFly(boolean allowed) {
		setCreatureBehaviour(allowed ? CreatureBehaviour.NORMAL : CreatureBehaviour.FLYING);
	}

	public void setCreatureBehaviour(CreatureBehaviour behaviour);

	public CreatureBehaviour getCreatureBehaviour();

	default void addCreatureFlag(CreatureFlag flag){
		if(!getFlags().contains(flag)){
			getFlags().add(flag);
			regenerateAttributes();
		}
	}

	default void removeCreatureFlag(CreatureFlag flag){
		getFlags().remove(flag);
		regenerateAttributes();
	}

	default boolean hasCreatureFlag(CreatureFlag flag){
		return getFlags().contains(flag);
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
