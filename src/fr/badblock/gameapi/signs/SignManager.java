package fr.badblock.gameapi.signs;

import org.bukkit.block.Block;

public interface SignManager {
	/**
	 * Rend un panneau traductible
	 * @param block Le panneau
	 * @param key La clé i18n
	 * @param args Les arguments
	 */
	public void setSignTranslatable(Block block, String key, Object... args);

	/**
	 * Rendre un panneau non traductible
	 * @param block Le panneau
	 */
	public void setSignNotTranslatable(Block block);
	
	/**
	 * Renvois les données du panneau aux joueurs
	 * @param block Le panneau
	 */
	public void updateSign(Block block);
	
	/**
	 * Vérifie si un panneau est traductible
	 * @param block Le panneau
	 * @return Si il est traductible
	 */
	public boolean isSignTranslatable(Block block);
	
	/**
	 * Enlève les traductions de tous les panneaux
	 */
	public void clearAllSigns();
}
