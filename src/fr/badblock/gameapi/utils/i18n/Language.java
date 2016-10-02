package fr.badblock.gameapi.utils.i18n;

import fr.badblock.gameapi.utils.i18n.Word.WordDeterminant;

/**
 * Implémentation d'une langue, va gérer la configuration et le formattage des
 * messages.
 * 
 * @author LeLanN
 */
public interface Language {
	/**
	 * 
	 * Formatte le message envoyé dans cette langue (pour ce qui est du
	 * header/footer, la langue dépend de celle du Message).
	 * 
	 * @param message
	 *            Le message à formatter.
	 * @param args
	 *            Les arguments à remplacer dans le message (%0 le premier, %1
	 *            le deuxième, ..., %n le énième)
	 * 
	 * @return Le message formatté et traduit.
	 */
	public String[] formatMessage(Message message, Object... args);

	/**
	 * 
	 * Récupère un message dans cette langue.
	 * 
	 * @param key
	 *            La clé du message dans le fichier configuration
	 * @param args
	 *            Les arguments à remplacer dans le message (%0 le premier, %1
	 *            le deuxième, ..., %n le énième)
	 * 
	 * @return Le message formatté et traduit
	 */
	public String[] get(String key, Object... args);

	/**
	 * Le footer (c'est à dire partie de message succédant la principale). Il
	 * s'agit ici d'une ligne. Utilisé lors du formattage du message.
	 * 
	 * @return Le footer trouvé, n'est pas censé être null.
	 */
	public String getFooter();

	/**
	 * La version longue du header (c'est à dire partie de message précédent la
	 * principale). Dans le cas du header long, il s'agit d'une ligne. Utilisé
	 * lors du formattage du message.
	 * 
	 * @return Le header trouvé, n'est pas censé être null.
	 */
	public String[] getHeader();

	/**
	 * Renvoit la langue gérée par la classe.
	 * 
	 * @return La langue.
	 */
	public Locale getLocale();

	/**
	 * Récupère le message de manière non formatté, tel qu'il a été récupéré
	 * dans la configuration.
	 * 
	 * @param key
	 *            La clé du message dans le fichier configuration
	 * @return Le message non formatté. Si il n'existe pas, retourne null.
	 */
	public Message getMessage(String key);

	/**
	 * La version courte du header (c'est à dire partie de message précédent la
	 * principale). Dans le cas du header long, il s'agit d'un simple préfixe.
	 * Utilisé lors du formattage du message.
	 * 
	 * Ex : [BadBlock]
	 * 
	 * @return Le header trouvé, n'est pas censé être null.
	 */
	public String getShortHeader();

	/**
	 * Récupère le mot de manière non formatté, tel qu'il a été récupéré dans la
	 * configuration.
	 * 
	 * @param key
	 *            La clé du mot dans le fichier configuration
	 * @return Le mot non formatté. Si il n'existe pas, retourne null.
	 */
	public Word getWord(String key);

	/**
	 * Récupère un message d'une seule ligne dans cette langue
	 * 
	 * @param key
	 *            La clé du message dans le fichier configuration
	 * @param plural
	 *            Si le mot doit être au pluriel
	 * @param determinant
	 *            Le type de déterminant avant le mot
	 * 
	 * @return Le message formatté et traduit
	 */
	public String getWord(String key, boolean plural, WordDeterminant determinant);
}
