package fr.badblock.gameapi.configuration.values;

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
 * @author LeLanN
 */
@Data@AllArgsConstructor@NoArgsConstructor
public class MapItemStack implements MapValue<ItemStack> {
	private int						  amount       = 0;
	private Material				  type		   = Material.STONE;
	private short					  durability   = 0;
	private boolean 				  unbreakable  = false;
	private Map<Enchantment, Integer> enchants 	   = Maps.newConcurrentMap();
	private String[]				  lore         = new String[0];
	private String					  displayName  = null;

	public MapItemStack(ItemStack item){
		this.amount 	 = item.getAmount();
		this.type		 = item.getType();
		this.durability  = item.getDurability();
		this.unbreakable = item.getItemMeta().spigot().isUnbreakable();
		this.enchants	 = item.getEnchantments();
		this.lore		 = item.getItemMeta().getLore().toArray(new String[0]);
		this.displayName = item.getItemMeta().getDisplayName();
	}

	public ItemStack getHandle(){
		ItemStackFactory factory = GameAPI.getAPI().createItemStackFactory()
				.unbreakable(unbreakable)
				.lore(lore)
				.displayName(displayName)
				.durability(durability)
				.type(type);

		for(Entry<Enchantment, Integer> entry : enchants.entrySet()){
			factory.enchant(entry.getKey(), entry.getValue());
		}

		return factory.create(amount);
	}
}
