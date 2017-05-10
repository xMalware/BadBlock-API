package fr.badblock.gameapi.worldedit;

import org.bukkit.Material;

public interface WESimpleEditor {
	public void setData(Material material, byte data);

	public void setDataReplace(Material material, byte data);
	
	public void setCurrentChunk(int x, int z);
	
	public void replaceBlockAt(int x, int y, int z);
	
	public char getBlockAt(int x, int y, int z);
	
	public void setBlockAt(int x, int y, int z);
	
	public boolean hasSameData(int x, int y, int z);

	public void finalizeWorkOnChunk();
}
