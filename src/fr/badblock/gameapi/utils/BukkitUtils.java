package fr.badblock.gameapi.utils;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

/**
 * Une série de méthode permettant de simplifier certaines utilisations de l'API Bukkit.
 * @author LelanN
 */
public class BukkitUtils {
	/**
	 * Permet de changer un block de manière temporaire
	 * @param block Le block
	 * @param newType Le type à mettre temporairement
	 * @param newData La data à mettre temporairement
	 * @param ticks Le nombre de ticks pendant lesquels le block va rester
	 */
	public static void temporaryChangeBlock(Block block, Material newType, byte newData, int ticks){
		
	}
	
	/**
	 * Vérifie si la location peut acceuillir un joueur
	 * @param location La location
	 * @return Un boolean
	 */
	public static boolean isSafeLocation(Location location) {
		Block block = location.getBlock();
		return !block.getType().isSolid() || !block.getRelative(0, 1, 0).getType().isSolid();
	}
}
