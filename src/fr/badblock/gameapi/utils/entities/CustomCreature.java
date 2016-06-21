package fr.badblock.gameapi.utils.entities;

import org.bukkit.Location;
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
	 * Vérifie si la créature est sur le sol
	 */
	public boolean isOnGround();
	
	/**
	 * Vérifie si la créature est dans la lave
	 */
	public boolean isInLava();
	
	/**
	 * Vérifie si la créature est dans l'eau
	 */
	public boolean isInWater();

	/**
	 * Vérifie si la créature est résistante au feu
	 */
	public boolean isFireProof();
	
	public void   setInvisible(boolean invisible);
	
	/**
	 * Défini si la créature est résistante au feu
	 * @param fireProof Sa résistance (ou non)
	 */
	public void    setFireProof(boolean fireProof);
	
	/**
	 * Vérifie si l'entité est invincible (ne peut prendre aucun dégat naturels, seul setHealth lui fera perdre de la vie)
	 */
	public boolean isInvincible();

	/**
	 * Défini si la créature est invincible (ne peut prendre aucun dégats naturels, seul setHealth lui fera perdre de la vie) 
	 * @param invincible Si la créature est invincible
	 */
	public void    setInvincible(boolean invincible);
	
	/**
	 * Change la vitesse de l'entité
	 * @param speed La nouvelle vitesse
	 */
	public void    setSpeed(double speed);
	
	/**
	 * Récupère la vitesse de l'entité
	 */
	public double  getSpeed();
	
	/**
	 * Change les dégats fait par l'entité
	 * @param damage Les nouveaux dégats
	 */
	public void    setDamage(double damage);
	
	/**
	 * Récupère les dégats fait par l'entité
	 */
	public double  getDamage();
	
	/**
	 * Vérifie si l'entité peut se déplacer. Si non, elle n'est plus soumise à la gravité, n'a pas de knockback en combat, ...
	 */
	public boolean isMovable();
	
	/**
	 * Autorise ou non l'entité à se déplacer
	 * @param movable Si l'entité peut se déplacer
	 */
	public void    setMovable(boolean movable);
	
	/**
	 * Vérifie si l'entité est agressive (va essayer de frapper un joueur si elle en voit un)
	 */
	public boolean isAgressive();

	/**
	 * Défini si l'entité est agressive (va essayer de frapper un joueur si elle en voit un).
	 * @param agressive Si l'entité est agressive.
	 */
	public void    setAgressive(boolean agressive);
	
	/**
	 * Vérifie si l'entité peut se faire monter (clique droit sur l'entité) par n'importe quel joueur.
	 * Pour le cochon, il ne nécessite plus de selle et de canne.
	 */
	public boolean isRideable();

	/**
	 * Défini si l'entité peut se faire monter (clique droit sur l'entité) par n'importe quel joueur.
	 * @param rideable Si l'entité peut se faire monter
	 */
	public void    setRideable(boolean rideable);
	
	/**
	 * Vérifie si l'entité peut voler. Une entité non montée qui vole ne va pas attaquer les joueurs et va se contenter de se déplacer rapidement (à la manière d'une chauve-souris).
	 */
	public boolean isAllowedToFly();

	/**
	 * Défini si l'entité peut voler. Une entité non montée qui vole ne va pas attaquer les joueurs et va se contenter de se déplacer rapidement (à la manière d'une chauve-souris).
	 * @param allowed Si l'entité peut voler.
	 */
	public void    setAllowedToFly(boolean allowed);
	
	/**
	 * Fait exploser l'entité
	 * @param power La puissance de l'explosion
	 * @param flaming Si l'explosion provoque des flammes
	 * @param smoking Si l'explosion provoque de la fumée
	 */
	public void    explode(Location location, float power, boolean flaming, boolean smoking);
	
	/**
	 * Récupère l'entité Bukkit
	 * @return L'entité Bukkit
	 */
	public Entity  getBukkit();
}
