package fr.badblock.gameapi.utils;

import org.bukkit.Bukkit;
import org.bukkit.WorldBorder;

public class BorderUtils {
	public static void setBorder(int size, long time){
		WorldBorder wb = Bukkit.getWorlds().get(0).getWorldBorder();
		
		wb.setCenter(0d, 0d);
		wb.setWarningTime(3);
		wb.setWarningDistance(2);
		wb.setDamageAmount(0.5d);
		wb.setSize(size * 2, time);
	}
	
	public static void setBorder(int size){
		setBorder(size, 0);
	}
	
	public static int getBorderSize(){
		WorldBorder wb = Bukkit.getWorlds().get(0).getWorldBorder();
		
		return (int) wb.getSize();
	}
}
