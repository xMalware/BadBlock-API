package fr.badblock.gameapi.utils.selections;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;

/**
 * Représente une séléction en forme de cercle (la hauteur n'est pas prise en compte)
 * @author LeLanN
 *
 */
public class CircleSelection extends AbstractSelection {
	private Vector3f center;
	private double	 radius;
	
	/**
	 * Crée une nouvelle séléction à partir du nom du monde et deux de points
	 * @param worldName Le monde
	 * @param center Le centre
	 */
	public CircleSelection(String worldName, Vector3f center, double radius){
		super(worldName);
		this.center = center;
		this.radius = radius;
	}
	
	@Override
	public boolean isInSelection(Vector3f loc) {
		return Math.sqrt(square(loc.getX() - center.getX()) + square(loc.getZ() - center.getZ())) <= radius;
	}
	
	private double square(double base){
		return base * base;
	}

	@Override
	public Location getCenter() {
		return center.bukkit(Bukkit.getWorld(getWorldName()));
	}

	@Override
	public Location getRandomLocation() {
		Location clone = getCenter().clone();
		clone.add(clone.getDirection().multiply(new Random().nextDouble() * radius));

		return clone;
	}
}
