package fr.badblock.gameapi.utils.i18n;

/**
 * Représente la version traduite d'un mot, tel que trouvable dans la configuration.
 * La classe n'est uniquement que la représentation brute de la configuration, et ne permet pas de formatter le message.
 * Passer par {@link fr.badblock.gameapi.utils.i18n.Language} pour cela.
 * 
 * @author LelanN
 */
public interface Word {
	/**
	 * Récupère le mot
	 * @param plural Si le mot est au pluriel
	 * @param determinant Le déterminant du mot
	 * @return Le mot
	 */
	public String get(boolean plural, WordDeterminant determinant);
	
	/**
	 * Représente les différents déterminants possible
	 * @author LeLanN
	 */
	public enum WordDeterminant {
		/**
		 * En français un / une / du / de la / des
		 */
		UNDEFINED,
		/**
		 * En français l'/ le / la / les
		 */
		DEFINED,
		/**
		 * Le mot, tout simplement :o
		 */
		SIMPLE;
	}
}
