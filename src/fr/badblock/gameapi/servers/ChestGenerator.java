package fr.badblock.gameapi.servers;

import java.io.File;

import org.bukkit.inventory.ItemStack;

public interface ChestGenerator {
	public void setConfigurationFile(File file);
	
	public ItemStack[] generateChest(int lines);
	
	public boolean isConfigurated();
	
	public boolean isWorking();
	
	public void beginJob();
	
	public void resetChests();
	
	public void addItemInConfiguration(ItemStack item, int probability);
}
