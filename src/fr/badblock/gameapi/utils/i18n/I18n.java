package fr.badblock.gameapi.utils.i18n;

import java.util.Collection;
import java.util.List;

import org.bukkit.command.CommandSender;

import fr.badblock.gameapi.utils.i18n.Word.WordDeterminant;

/**
 * Représente le systčme de traduction et de messages configurables BadBlock.
 * Les différentes langues pouvant ętre supportées sont representées par
 * l'énumération {@link fr.badblock.gameapi.utils.i18n.Locale}.
 * 
 * @author LeLanN
 */
public interface I18n {
	/**
	 * Broadcast un message ŕ tous les joueurs. Le systčme va rechercher la
	 * langue la plus adaptée pour tous les joueurs.
	 * 
	 * @param key
	 *            La clé du message dans le ficher configuration
	 * @param args
	 *            Les arguments ŕ remplacer dans le message (%0 le premier, %1
	 *            le deuxičme, ..., %n le éničme)
	 */
	public void broadcast(String key, Object... args);

	/**
	 * 
	 * Récupčre un message via la langue du CommandSender.
	 * 
	 * @param sender
	 *            Le CommandSender (Bukkit)
	 * @param key
	 *            La clé du message dans le fichier configuration
	 * @param args
	 *            Les arguments ŕ remplacer dans le message (%0 le premier, %1
	 *            le deuxičme, ..., %n le éničme)
	 * 
	 * @return Le message formatté et traduit
	 */
	public String[] get(CommandSender sender, String key, Object... args);

	/**
	 * 
	 * Récupčre un message via une langue choisie.
	 * 
	 * @param locale
	 *            La langue choisie
	 * @param key
	 *            La clé du message dans le fichier configuration
	 * @param args
	 *            Les arguments ŕ remplacer dans le message (%0 le premier, %1
	 *            le deuxičme, ..., %n le éničme)
	 * 
	 * @return Le message formatté et traduit
	 */
	public String[] get(Locale locale, String key, Object... args);

	/**
	 * Récupčre un message via la langue par défaut.
	 * 
	 * @param key
	 *            La clé du message dans le fichier configuration
	 * @param args
	 *            Les arguments ŕ remplacés dans le message (%0 le premier, %1
	 *            le deuxičme, ..., %n le éničme)
	 * 
	 * @return Le message formatté
	 */
	public String[] get(String key, Object... args);

	/**
	 * Tous les langages de l'énumération Locale ne sont pas forcémment traduit.
	 * Cette méthode permet de récupérer les quels le sont.
	 * 
	 * @return La liste des langages traduits
	 */
	public Collection<Locale> getConfiguratedLocales();

	/**
	 * Récupčre une langue ŕ partir de son nom
	 * 
	 * @param locale
	 *            La langue ŕ trouver
	 * @return La langue trouvée (si elle n'existe pas, la langue par défaut est
	 *         envoyée)
	 */
	public Language getLanguage(Locale locale);

	/**
	 * Récupčre un message d'une seule ligne via une langue choisie.
	 * 
	 * @param locale
	 *            La langue choisie
	 * @param key
	 *            La clé du message dans le fichier configuration
	 * @param plural
	 *            Si le mot doit ętre au pluriel
	 * @param determinant
	 *            Le type de déterminant avant le mot
	 * 
	 * @return Le message formatté et traduit
	 */
	public String getWord(Locale locale, String key, boolean plural, WordDeterminant determinant);

	/**
	 * Récupčre un message d'une seule ligne via la langue par défaut.
	 * 
	 * @param key
	 *            La clé du message dans le fichier configuration
	 * @param plural
	 *            Si le mot doit ętre au pluriel
	 * @param determinant
	 *            Le type de déterminant avant le mot
	 * 
	 * @return Le message formatté et traduit
	 */
	public String getWord(String key, boolean plural, WordDeterminant determinant);

	/**
	 * Permet de remplacer de maničre plus rapide les couleurs, pour plusieurs
	 * messages. Appel en réalité ChatColor.translateAlternateColorCodes.
	 * 
	 * @param base
	 *            Les messages ŕ formatter
	 * @return Les messages remplacés
	 */
	public List<String> replaceColors(List<String> base);

	/**
	 * Permet de remplacer de maničre plus rapide les couleurs. Appel en réalité
	 * ChatColor.translateAlternateColorCodes.
	 * 
	 * @param base
	 *            Le message ŕ formatter
	 * @return Le message remplacé
	 */
	public String replaceColors(String base);

	/**
	 * Permet de remplacer de maničre plus rapide les couleurs, pour plusieurs
	 * messages. Appel en réalité ChatColor.translateAlternateColorCodes.
	 * 
	 * @param base
	 *            Les messages ŕ formatter
	 * @return Les messages remplacés
	 */
	public String[] replaceColors(String... base);

	/**
	 * Envoit un message ŕ un CommandSender. Le systčme va rechercher la langue
	 * la plus adaptée.
	 * 
	 * @param sender
	 *            Le CommandSender (Bukkit)
	 * @param key
	 *            La clé du message dans le ficher configuration
	 * @param args
	 *            Les arguments ŕ remplacer dans le message (%0 le premier, %1
	 *            le deuxičme, ..., %n le éničme)
	 */
	public void sendMessage(CommandSender sender, String key, Object... args);
}
