package fr.badblock.gameapi.utils.selections;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;

import lombok.Getter;

/**
 * Reprďż˝sente une sďż˝lďż˝ction en forme de cube, dďż˝finie par deux vecteurs et un
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
	 * Crďż˝e une nouvelle sďż˝lďż˝ction ďż˝ partir du nom du monde et deux de points
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
		return augment(reduce, reduce, reduce, -reduce, -reduce, -reduce);
	}
	
	public CuboidSelection move(int val)
	{
		return augment(val, val, val, val, val, val);
	}
	
	public CuboidSelection augment(int val)
	{
		return augment(-val, -val, -val, val, val, val);
	}
	
	public CuboidSelection augment(int dx1, int dy1, int dz1, int dx2, int dy2, int dz2)
	{
		return new CuboidSelection(worldName, new Vector3f(getMinX() + dx1, getMinY() + dy1, getMinZ() + dz1), new Vector3f(getMaxX() + dx2, getMaxY() + dy2, getMaxZ() + dz2));
	}
	
	public CuboidSelection augment(int val, BlockFace face)
	{
		switch(face)
		{
			case DOWN:
				return augment(0, -val, 0, 0, 0, 0);
			case EAST:
				return augment(-val, 0, 0, 0, 0, 0);
			case NORTH:
				return augment(0, 0, -val, 0, 0, 0);
			case SOUTH:
				return augment(0, 0, 0, 0, 0, val);
			case UP:
				return augment(0, 0, 0, 0, val, 0);
			case WEST:
				return augment(0, 0, 0, val, 0, 0);
			default:
				return this;
		}
	}
}
