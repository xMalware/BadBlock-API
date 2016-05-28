package fr.badblock.gameapi.configuration.values;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Représente un Material Bukkit et sa data (ex : Stone:1)
 * @author LeLanN
 */
@Data@AllArgsConstructor@NoArgsConstructor
public class MapMaterial implements MapValue<Material> {
	private String material		 = "AIR";
	private short  materialData  = 0;
	
	public MapMaterial(ItemStack from){
		this.material     = from.getType().name();
		this.materialData = from.getDurability();
	}
	
	public MapMaterial(Material material, short data){
		this.material     = material.name();
		this.materialData = data;
	}
	
	/**
	 * Vérifie si l'item correspond au Material
	 * @param item L'item
	 * @return Si il correspond
	 */
	public boolean match(ItemStack item){
		return item.getType() == getHandle() && item.getDurability() == materialData;
	}
	
	/**
	 * Vérifie si l'item correspond au Block
	 * @param block Le block
	 * @return Si il correspond
	 */
	@SuppressWarnings("deprecation")
	public boolean match(Block item){
		return item.getType() == getHandle() && item.getData() == materialData;
	}
	
	public Material getHandle(){
		return Material.matchMaterial(material);
	}
}
