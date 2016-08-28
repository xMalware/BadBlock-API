package fr.badblock.gameapi.configuration.values;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import com.google.common.collect.Maps;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.utils.itemstack.ItemStackFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente un ItemStack Bukkit
 * 
 * @author LeLanN
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MapItemStack implements MapValue<ItemStack> {
	public static MapList<MapItemStack, ItemStack> toMapList(List<ItemStack> items) {
		MapList<MapItemStack, ItemStack> result = new MapList<>();

		for (ItemStack is : items) {
			if (is != null)
				result.add(new MapItemStack(is));
			else {
				result.add(new MapItemStack(new ItemStack(Material.AIR)));
			}
		}

		return result;
	}
	private int amount = 0;
	private Material type = Material.STONE;
	private short durability = 0;
	private boolean unbreakable = false;
	private Map<String, Integer> enchants = Maps.newConcurrentMap();
	private String[] lore = new String[0];

	private String displayName = null;

	public MapItemStack(ItemStack item) {
		if (item == null) {
			amount = -1;
			return;
		}

		this.amount = item.getAmount();
		this.type = item.getType();
		this.durability = item.getDurability();

		if (item.getItemMeta() != null) {
			this.unbreakable = item.getItemMeta().spigot().isUnbreakable();

			if (item.getItemMeta().getLore() != null) {
				this.lore = item.getItemMeta().getLore().toArray(new String[0]);
			} else {
				this.lore = new String[] {};
			}
			this.displayName = item.getItemMeta().getDisplayName();
		}

		this.enchants = Maps.newConcurrentMap();

		item.getEnchantments().entrySet().forEach(entry -> {
			enchants.put(entry.getKey().getName(), entry.getValue());
		});
	}

	@Override
	public ItemStack getHandle() {
		if (amount == -1)
			return null;

		ItemStackFactory factory = GameAPI.getAPI().createItemStackFactory().unbreakable(unbreakable).lore(lore)
				.displayName(displayName).durability(durability).type(type);

		for (Entry<String, Integer> entry : enchants.entrySet()) {
			factory.enchant(Enchantment.getByName(entry.getKey()), entry.getValue());
		}

		return factory.create(amount);
	}
}
