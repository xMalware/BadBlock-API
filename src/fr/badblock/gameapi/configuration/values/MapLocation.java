package fr.badblock.gameapi.configuration.values;

import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/***
 * Représente une Location Bukkit
 * @author LeLanN
 */
@Data@AllArgsConstructor@NoArgsConstructor
public class MapLocation implements MapValue<Location> {
	private String world	= "world";
	private double x		= 0.0d;
	private double y		= 0.0d;
	private double z		= 0.0d;
	private float  yaw		= 0.0f;
	private float  pitch	= 0.0f;
	
	/**
	 * Créé une MapLocation depuis une location Bukkit
	 * @param location La location
	 */
	public MapLocation(Location location){
		this(location.getWorld().getName(), location.getX(), location.getY(), location.getZ(), location.getYaw(), location.getPitch());
	
		round();
	}
	
	@Override
	public Location getHandle() {
		return new Location(Bukkit.getWorld(world), x, y, z, yaw, pitch);
	}
	
	@Override
	public void postLoad(){
		round();
	}
	
	protected void round(){
		x = (int) x + (x / Math.abs(x)) * 0.5d;
		y = (int) y;
		z = (int) z + (z / Math.abs(z)) * 0.5d;
		
		int nearYaw = (int) (yaw / 90);
		
		yaw(nearYaw - 1);
		yaw(nearYaw);
		yaw(nearYaw + 1);
		
		yaw = Math.round(yaw);
		
		int nearPitch = (int) (pitch / 90);
		
		pitch(nearPitch - 1);
		pitch(nearPitch);
		pitch(nearPitch + 1);
		
		pitch = Math.round(pitch);
	}
	
	private void yaw(int i){
		if( Math.abs(yaw - i * 90) < 5.0 )
			yaw = i * 90;
	}
	
	private void pitch(int i){
		if( Math.abs(pitch - i * 90) < 5.0 )
			pitch = i * 90;
	}
	
	public static MapList<MapLocation, Location> toMapList(List<Location> objs){
		MapList<MapLocation, Location> result = new MapList<>();
		
		for(Location is : objs){
			result.add(new MapLocation(is));
		}
		
		return result;
	}
}
