package fr.badblock.gameapi.signs;

import org.bukkit.block.Block;

public interface SignManager {
	/**
	 * Rend un panneau traductible
	 * @param block Le panneau
	 * @param key La cl� i18n
	 * @param args Les arguments
	 */
	public void setSignTranslatable(Block block, String key, Object... args);

	/**
	 * Rendre un panneau non traductible
	 * @param block Le panneau
	 */
	public void setSignNotTranslatable(Block block);
	
	/**
	 * Renvois les donn�es du panneau aux joueurs
	 * @param block Le panneau
	 */
	public void updateSign(Block block);
	
	/**
	 * V�rifie si un panneau est traductible
	 * @param block Le panneau
	 * @return Si il est traductible
	 */
	public boolean isSignTranslatable(Block block);
	
	/**
	 * Enl�ve les traductions de tous les panneaux
	 */
	public void clearAllSigns();
}