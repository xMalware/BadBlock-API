package fr.badblock.gameapi.utils.i18n;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.utils.i18n.Word.WordDeterminant;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Représente un mot traductible. Utiliser ŕ plusieurs endroit dans l'API pour
 * simplifier, ou pour intégrer un mot traduit en<br>
 * paramčtre d'un message (ex : block ou entité).
 * 
 * @author LeLanN
 */
@Data
@AllArgsConstructor
public class TranslatableWord {
	private String key;
	private boolean plural;
	private WordDeterminant determinant;

	/**
	 * Récupčre le mot traduit dans une langue
	 * 
	 * @param locale
	 *            La langue
	 * @return Le mot
	 */
	public String getWord(Locale locale) {
		return GameAPI.i18n().getWord(locale, key, plural, determinant);
	}
}
