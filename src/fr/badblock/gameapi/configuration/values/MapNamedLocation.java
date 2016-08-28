package fr.badblock.gameapi.configuration.values;

import org.bukkit.Location;

import fr.badblock.gameapi.configuration.values.MapLocation;
import fr.badblock.gameapi.configuration.values.MapValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapNamedLocation implements MapValue<Location> {
	private MapLocation loc;
	private String name;

	public MapNamedLocation(Location loc, String name) {
		this(new MapLocation(loc), name);
	}

	@Override
	public Location getHandle() {
		return loc.getHandle();
	}
}