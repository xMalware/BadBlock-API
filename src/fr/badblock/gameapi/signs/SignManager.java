package fr.badblock.gameapi.signs;

import org.bukkit.block.Block;

import fr.badblock.gameapi.utils.i18n.TranslatableString;

public interface SignManager {
	/**
	 * Enlève les traductions de tous les panneaux
	 */
	public void clearAllSigns();

	/**
	 * Récupère la traduction d'un panneau
	 * 
	 * @param block
	 *            Le panneau
	 * @return La traduction (ou null si inexistante)
	 */
	public TranslatableString getTraduction(Block block);

	/**
	 * Vérifie si un panneau est traductible
	 * 
	 * @param block
	 *            Le panneau
	 * @return Si il est traductible
	 */
	public boolean isSignTranslatable(Block block);

	/**
	 * Rendre un panneau non traductible
	 * 
	 * @param block
	 *            Le panneau
	 */
	public void setSignNotTranslatable(Block block);

	/**
	 * Rend un panneau traductible
	 * 
	 * @param block
	 *            Le panneau
	 * @param key
	 *            La clé i18n
	 * @param args
	 *            Les arguments
	 */
	public void setSignTranslatable(Block block, String key, Object... args);

	/**
	 * Renvois les données du panneau aux joueurs
	 * 
	 * @param block
	 *            Le panneau
	 */
	public void updateSign(Block block);
}
