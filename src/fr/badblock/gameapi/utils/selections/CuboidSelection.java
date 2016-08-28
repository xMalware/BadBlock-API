package fr.badblock.gameapi.utils.selections;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import lombok.Getter;

/**
 * Représente une séléction en forme de cube, définie par deux vecteurs et un
 * monde
 * 
 * @author LeLanN
 */
public class CuboidSelection extends AbstractSelection {
	@Getter
	private Vector3f firstBound, secondBound;

	/**
	 * Crée une nouvelle séléction à partir du nom du monde et deux de points
	 * 
	 * @param worldName
	 *            Le monde
	 * @param firstBound
	 *            Le premier vecteur
	 * @param secondBound
	 *            Le seconde vecteur
	 */
	public CuboidSelection(String worldName, Vector3f firstBound, Vector3f secondBound) {
		super(worldName);
		this.firstBound = firstBound;
		this.secondBound = secondBound;
	}

	public CuboidSelection(Location firstBound, Location secondBound) {
		super(firstBound.getWorld().getName());
		this.firstBound = new Vector3f(firstBound);
		this.secondBound = new Vector3f(secondBound);
	}

	@Override
	public boolean isInSelection(Vector3f loc) {
		return loc.getX() >= getMinX() && loc.getX() <= getMaxX() && loc.getY() >= getMinY() && loc.getY() <= getMaxY()
				&& loc.getZ() >= getMinZ() && loc.getZ() <= getMaxZ();
	}

	@Override
	public Location getCenter() {
		double x = Math.abs(firstBound.getX() - secondBound.getX()) / 2 + getMinX();
		double y = Math.abs(firstBound.getY() - secondBound.getY()) / 2 + getMinY();
		double z = Math.abs(firstBound.getZ() - secondBound.getZ()) / 2 + getMinZ();

		return new Location(Bukkit.getWorld(getWorldName()), x, y, z);
	}

	@Override
	public Location getRandomLocation() {
		double x = new Random().nextDouble() * (getMaxX() - getMinX()) + getMinX();
		double y = new Random().nextDouble() * (getMaxY() - getMinY()) + getMinY();
		double z = new Random().nextDouble() * (getMaxZ() - getMinZ()) + getMinZ();

		return new Location(Bukkit.getWorld(getWorldName()), x, y, z);
	}

	protected double getMaxZ() {
		return Math.max(firstBound.getZ(), secondBound.getZ());
	}

	protected double getMinZ() {
		return Math.min(firstBound.getZ(), secondBound.getZ());
	}

	protected double getMaxX() {
		return Math.max(firstBound.getX(), secondBound.getX());
	}

	protected double getMinX() {
		return Math.min(firstBound.getX(), secondBound.getX());
	}

	protected double getMaxY() {
		return Math.max(firstBound.getY(), secondBound.getY());
	}

	protected double getMinY() {
		return Math.min(firstBound.getY(), secondBound.getY());
	}
}
