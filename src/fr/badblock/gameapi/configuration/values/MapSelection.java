package fr.badblock.gameapi.configuration.values;

import fr.badblock.gameapi.utils.selections.CuboidSelection;
import fr.badblock.gameapi.utils.selections.Vector3f;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapSelection implements MapValue<CuboidSelection> {
	private String world = "world";
	private Vector3f first = new Vector3f();
	private Vector3f second = new Vector3f();

	public MapSelection(CuboidSelection selection) {
		this.world = selection.getWorldName();
		this.first = selection.getFirstBound();
		this.second = selection.getSecondBound();
	}

	@Override
	public CuboidSelection getHandle() {
		return new CuboidSelection(world, first, second);
	}
}
