package fr.badblock.gameapi.utils.i18n;

import lombok.Getter;

/**
 * Représente les différents langages supportés par BadBlock.
 * 
 * @author LelanN
 */
public enum Locale {
	/**
	 * Représente la langue française telle qu'elle est parlée en France
	 */
	FRENCH_FRANCE("fr_FR"),
	/**
	 * Représente la langue anglaise telle qu'elle est parlée aux Etats-Unis
	 */
	ENGLISH_US("en_US");

	public static Locale getLocale(String localeId) {
		for (Locale locale : values())
			if (locale.getLocaleId().equalsIgnoreCase(localeId))
				return locale;

		return null;
	}

	@Getter
	private String localeId;

	private Locale(String localeId) {
		this.localeId = localeId;
	}
}
