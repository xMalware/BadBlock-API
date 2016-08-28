package fr.badblock.gameapi.configuration.values;

import java.util.ArrayList;
import java.util.List;

public class MapList<T extends MapValue<V>, V> extends ArrayList<T> {
	private static final long serialVersionUID = -7246147602943049275L;

	public List<V> getHandle() {
		List<V> result = new ArrayList<>();

		for (MapValue<V> value : this) {
			result.add(value.getHandle());
		}

		return result;
	}
}
