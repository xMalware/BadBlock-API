package fr.badblock.gameapi.configuration.values;

import java.util.List;

import fr.badblock.gameapi.utils.merchants.CustomMerchantRecipe;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapRecipe implements MapValue<CustomMerchantRecipe> {
	public static MapList<MapRecipe, CustomMerchantRecipe> toMapList(List<CustomMerchantRecipe> objs) {
		MapList<MapRecipe, CustomMerchantRecipe> result = new MapList<>();

		for (CustomMerchantRecipe is : objs) {
			result.add(new MapRecipe(is));
		}

		return result;
	}
	private MapItemStack item1;
	private MapItemStack item2;

	private MapItemStack result;

	public MapRecipe(CustomMerchantRecipe recipe) {
		this(new MapItemStack(recipe.getFirstItem()), new MapItemStack(recipe.getSecondItem()),
				new MapItemStack(recipe.getResult()));
	}

	@Override
	public CustomMerchantRecipe getHandle() {
		return new CustomMerchantRecipe(item1.getHandle(), item2.getHandle(), result.getHandle());
	}
}