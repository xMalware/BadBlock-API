package fr.badblock.gameapi.utils.i18n.messages;

import fr.badblock.gameapi.utils.i18n.TranslatableString;

/**
 * Liste de messages utilisés par l'API ou ŕ déstination des plugins annexes
 * pour unifier le systčme de traduction (pas de doublon) et simplifier le
 * développement.<br>
 * Messages commandes.
 * 
 * @author LeLanN
 */
public class CommandMessages {
	/**
	 * Lorsqu'un joueur n'a pas la permission d'utilisé une commande.
	 * 
	 * @return Le message
	 */
	public static TranslatableString noPermission() {
		return new TranslatableString("commands.nopermission");
	}
}
