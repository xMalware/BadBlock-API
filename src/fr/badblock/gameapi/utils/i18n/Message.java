package fr.badblock.gameapi.utils.i18n;

/**
 * ReprÃ©sente la version traduite d'un message, tel que trouvable dans la
 * configuration. La classe n'est uniquement que la reprÃ©sentation brute de la
 * configuration, et ne permet pas de formatter le message. Passer par
 * {@link fr.badblock.gameapi.utils.i18n.Language} pour cela.
 * 
 * @author LelanN
 */
public interface Message {
	/**
	 * Renvoit tous les versions possibles du messages. Si le Message n'est pas
	 * alÃ©toire, un seul sera renvoyÃ©.
	 * 
	 * @return La totalitÃ© des messages.
	 */
	public String[][] getAllMessages();
	
	/**
	 * Renvoit le message 'brute', sans remplacement des paramÄ�tres ou des
	 * couleurs. NÃ©anmoins, si le message est alÃ©atoire, en renvoit un au
	 * hasard.
	 * 
	 * @return Le message non formattÃ©.
	 */
	public String[] getUnformattedMessage();

	/**
	 * VÃ©rifie si le message est alÃ©atoire, autrement dit si plusieurs messages
	 * ont Ã©tÃ© configurÃ©s et que un sera choisit au hasard.
	 */
	public boolean isRandomMessage();

	/**
	 * VÃ©rifie si le message utilise le footer (voir
	 * {@link fr.badblock.gameapi.utils.i18n.Language})
	 */
	public boolean useFooter();

	/**
	 * VÃ©rifie si le message utilise le header "long" (voir
	 * {@link fr.badblock.gameapi.utils.i18n.Language})
	 */
	public boolean useHeader();

	/**
	 * VÃ©rifie si le message utilise le header "court" (voir
	 * {@link fr.badblock.gameapi.utils.i18n.Language})
	 */
	public boolean useShortHeader();
}
