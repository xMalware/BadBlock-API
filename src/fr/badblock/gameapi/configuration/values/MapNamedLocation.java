package fr.badblock.gameapi.configuration.values;

import org.bukkit.Location;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
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