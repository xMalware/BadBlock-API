package fr.badblock.gameapi.utils.i18n;

/**
 * Représente la version traduite d'un message, tel que trouvable dans la
 * configuration. La classe n'est uniquement que la représentation brute de la
 * configuration, et ne permet pas de formatter le message. Passer par
 * {@link fr.badblock.gameapi.utils.i18n.Language} pour cela.
 * 
 * @author LelanN
 */
public interface Message {
	/**
	 * Renvoit tous les versions possibles du messages. Si le Message n'est pas
	 * alétoire, un seul sera renvoyé.
	 * 
	 * @return La totalité des messages.
	 */
	public String[][] getAllMessages();

	/**
	 * Renvoit le message 'brute', sans remplacement des paramčtres ou des
	 * couleurs. Néanmoins, si le message est aléatoire, en renvoit un au
	 * hasard.
	 * 
	 * @return Le message non formatté.
	 */
	public String[] getUnformattedMessage();

	/**
	 * Vérifie si le message est aléatoire, autrement dit si plusieurs messages
	 * ont été configurés et que un sera choisit au hasard.
	 */
	public boolean isRandomMessage();

	/**
	 * Vérifie si le message utilise le footer (voir
	 * {@link fr.badblock.gameapi.utils.i18n.Language})
	 */
	public boolean useFooter();

	/**
	 * Vérifie si le message utilise le header "long" (voir
	 * {@link fr.badblock.gameapi.utils.i18n.Language})
	 */
	public boolean useHeader();

	/**
	 * Vérifie si le message utilise le header "court" (voir
	 * {@link fr.badblock.gameapi.utils.i18n.Language})
	 */
	public boolean useShortHeader();
}
