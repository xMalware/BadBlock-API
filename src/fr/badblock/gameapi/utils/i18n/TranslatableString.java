package fr.badblock.gameapi.utils.i18n;

import org.bukkit.command.CommandSender;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.Data;

/**
 * Représente une chaîne de caractčre traductible. Utiliser ŕ plusieurs endroit
 * dans l'API pour simplifier.
 * 
 * @author LeLanN
 */
@Data
public class TranslatableString {
	private String key;
	private Object[] objects;

	/**
	 * Crée une nouvelle chaîne traduisible
	 * 
	 * @param key
	 *            La key
	 * @param objects
	 *            Les arguments
	 */
	public TranslatableString(String key, Object... objects) {
		this.key = key;
		this.objects = objects;
	}

	/**
	 * Envoit le message ŕ tous les joueurs
	 */
	public void broadcast() {
		GameAPI.i18n().broadcast(key, objects);
	}

	/**
	 * Récupčre le message sur plusieurs lignes
	 * 
	 * @param player
	 *            Le joueur (pour avoir la langue)
	 * @return Le message
	 */
	public String[] get(BadblockPlayer player) {
		return get(player.getPlayerData().getLocale());
	}

	/**
	 * Récupčre le message sur plusieurs lignes
	 * 
	 * @param locale
	 *            La langue
	 * @return Le message
	 */
	public String[] get(Locale locale) {
		return GameAPI.i18n().get(locale, key, objects);
	}

	/**
	 * Récupčre la premičre ligne du message
	 * 
	 * @param player
	 *            Le joueur (pour la langue)
	 * @return La ligne
	 */
	public String getAsLine(BadblockPlayer player) {
		return getAsLine(player.getPlayerData().getLocale());
	}

	/**
	 * Récupčre la premičre ligne du message
	 * 
	 * @param player
	 *            Le joueur (pour la langue)
	 * @return La ligne
	 */
	public String getAsLine(CommandSender sender) {
		if (sender instanceof BadblockPlayer)
			return getAsLine((BadblockPlayer) sender);

		return getAsLine(Locale.ENGLISH_US);
	}

	/**
	 * Récupčre la premičre ligne du message
	 * 
	 * @param locale
	 *            La langue
	 * @return La ligne
	 */
	public String getAsLine(Locale locale) {
		return GameAPI.i18n().get(locale, key, objects)[0];
	}

	/**
	 * Envoit le message ŕ un command sender
	 * 
	 * @param sender
	 *            Le sender
	 */
	public void send(CommandSender sender) {
		GameAPI.i18n().sendMessage(sender, key, objects);
	}
}
