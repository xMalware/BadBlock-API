package fr.badblock.gameapi.utils;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldBorder;

import lombok.Getter;

public class BorderUtils {
	@Getter
	private static final World managedWorld = Bukkit.getWorlds().get(0);
	
	public static void setBorder(int size, long time){
		setBorder(size, time, getManagedWorld());
	}
	
	public static void setBorder(int size, long time, World world){
		WorldBorder wb = world.getWorldBorder();
		
		wb.setCenter(0d, 0d);
		wb.setWarningTime(3);
		wb.setWarningDistance(2);
		wb.setDamageAmount(0.5d);
		wb.setSize(size * 2, time);
	}
	
	public static void setBorder(int size){
		setBorder(size, 0);
	}
	
	public static void setBorder(World world, int size){
		setBorder(size, 0, world);
	}
	
	public static int getBorderSize(){
		return getBorderSize(getManagedWorld());
	}
	
	public static int getBorderSize(World world){
		WorldBorder wb = world.getWorldBorder();
		
		return (int) wb.getSize();
	}
}
