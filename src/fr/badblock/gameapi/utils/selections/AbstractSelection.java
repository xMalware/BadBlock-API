package fr.badblock.gameapi.utils.selections;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;

import lombok.Getter;

/**
 * Représente une zone dans l'espace définie par un nombre inconnu de vecteurs
 * et un monde
 * 
 * @author LeLanN
 */
public abstract class AbstractSelection {
	@Getter
	protected String worldName;

	public AbstractSelection(String worldName) {
		this.worldName = worldName;
	}

	public abstract Location getCenter();

	public abstract Location getRandomLocation();

	/**
	 * Vérifie si un bloc est dans la séléction (monde et coordonnées)
	 * 
	 * @param b
	 *            Le block
	 * @return Un boolean
	 */
	public boolean isInSelection(Block b) {
		return isInSelection(b.getLocation());
	}

	/**
	 * Vérifie si trois points sont dans la séléction
	 * 
	 * @param x
	 *            X
	 * @param y
	 *            Y
	 * @param z
	 *            Z
	 * @return Un boolean
	 */
	public boolean isInSelection(double x, double y, double z) {
		return isInSelection(new Vector3f(x, y, z));
	}

	/**
	 * Vérifie si une entité est dans la séléction (monde et coordonnées)
	 * 
	 * @param e
	 *            L'entité
	 * @return Un boolean
	 */
	public boolean isInSelection(Entity e) {
		return isInSelection(e.getLocation());
	}

	/**
	 * Vérifie si une location est dans la séléction (monde et coordonnées)
	 * 
	 * @param loc
	 *            La location
	 * @return Un boolean
	 */
	public boolean isInSelection(Location loc) {
		return loc.getWorld().getName().equalsIgnoreCase(worldName)
				&& isInSelection(new Vector3f(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
	}

	/**
	 * Vérifie si le vecteur est contenu dans la séléction
	 * 
	 * @param loc
	 *            Le vecteur
	 * @return Un boolean
	 */
	public abstract boolean isInSelection(Vector3f loc);
}
