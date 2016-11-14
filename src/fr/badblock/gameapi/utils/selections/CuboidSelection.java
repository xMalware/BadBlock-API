package fr.badblock.gameapi.utils.selections;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;

import lombok.Getter;

/**
 * Repr�sente une s�l�ction en forme de cube, d�finie par deux vecteurs et un
 * monde
 * 
 * @author LeLanN
 */
public class CuboidSelection extends AbstractSelection {
	@Getter
	private Vector3f firstBound, secondBound;

	public CuboidSelection(Location firstBound, Location secondBound) {
		super(firstBound.getWorld().getName());
		this.firstBound = new Vector3f(firstBound);
		this.secondBound = new Vector3f(secondBound);
	}

	/**
	 * Cr�e une nouvelle s�l�ction � partir du nom du monde et deux de points
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

	@Override
	public Location getCenter() {
		double x = Math.abs(firstBound.getX() - secondBound.getX()) / 2 + getMinX();
		double y = Math.abs(firstBound.getY() - secondBound.getY()) / 2 + getMinY();
		double z = Math.abs(firstBound.getZ() - secondBound.getZ()) / 2 + getMinZ();

		return new Location(Bukkit.getWorld(getWorldName()), x, y, z);
	}

	public double getMaxX() {
		return Math.max(firstBound.getX(), secondBound.getX());
	}

	public double getMaxY() {
		return Math.max(firstBound.getY(), secondBound.getY());
	}

	public double getMaxZ() {
		return Math.max(firstBound.getZ(), secondBound.getZ());
	}

	public double getMinX() {
		return Math.min(firstBound.getX(), secondBound.getX());
	}

	public double getMinY() {
		return Math.min(firstBound.getY(), secondBound.getY());
	}

	public double getMinZ() {
		return Math.min(firstBound.getZ(), secondBound.getZ());
	}
	
	public List<Block> getBlocks() {
		List<Block> list = new ArrayList<>();
		World world = Bukkit.getWorld(getWorldName());
		for (int x = Double.valueOf(getMinX()).intValue(); x <= Double.valueOf(getMaxX()).intValue(); x++) {
            for (int y = Double.valueOf(getMinY()).intValue(); y <= Double.valueOf(getMaxY()).intValue(); y++) {
                for (int z = Double.valueOf(getMinZ()).intValue(); z <= Double.valueOf(getMaxZ()).intValue(); z++) {
                	list.add(world.getBlockAt(x, y, z));
                }
            }
        }
		return list;
	}

	@Override
	public Location getRandomLocation() {
		double x = new Random().nextDouble() * (getMaxX() - getMinX()) + getMinX();
		double y = new Random().nextDouble() * (getMaxY() - getMinY()) + getMinY();
		double z = new Random().nextDouble() * (getMaxZ() - getMinZ()) + getMinZ();

		return new Location(Bukkit.getWorld(getWorldName()), x, y, z);
	}

	@Override
	public boolean isInSelection(Vector3f loc) {
		return loc.getX() >= getMinX() && loc.getX() <= getMaxX() && loc.getY() >= getMinY() && loc.getY() <= getMaxY()
				&& loc.getZ() >= getMinZ() && loc.getZ() <= getMaxZ();
	}
	
	public CuboidSelection reduce(int reduce){
		double minX = getMinX() < 0 ? getMinX() + reduce : getMinX() - reduce;
		double maxX = getMaxX() < 0 ? getMaxX() + reduce : getMaxX() - reduce;
		
		double minY = getMinY() < 0 ? getMinY() + reduce : getMinY() - reduce;
		double maxY = getMaxY() < 0 ? getMaxY() + reduce : getMaxY() - reduce;
		
		double minZ = getMinZ() < 0 ? getMinZ() + reduce : getMinZ() - reduce;
		double maxZ = getMaxZ() < 0 ? getMaxZ() + reduce : getMaxZ() - reduce;
		
		return new CuboidSelection(worldName, new Vector3f(minX, minY, minZ), new Vector3f(maxX, maxY, maxZ));
	}
}
