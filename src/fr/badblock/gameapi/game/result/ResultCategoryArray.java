package fr.badblock.gameapi.game.result;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Représente une catégorie ayant une construction ŕ double entrée
 * 
 * @author LeLanN
 */
@Getter
@RequiredArgsConstructor
public class ResultCategoryArray implements ResultCategory {
	/**
	 * Représente une ligne pour {@link ResultCategoryLined}
	 * 
	 * @author LeLanN
	 */
	@Getter
	@AllArgsConstructor
	public static class ResultCategoryEntry {
		@Setter
		private String description;
		private String[] contents;
	}
	private final String categoryName;

	private final String[] fields;

	private final CategoryType type = CategoryType.ARRAY_DATA;

	private final List<ResultCategoryEntry> lines = new ArrayList<>();

	/**
	 * Ajoute une ligne ŕ la catégorie
	 * 
	 * @param description
	 *            La description de la ligne
	 * @param content
	 *            Le contenu de la ligne
	 * @return L'ID de la nouvelle ligne
	 */
	public int addLine(@NonNull String description, @NonNull String... content) {
		if (content.length != fields.length) {
			throw new IllegalArgumentException("Content must be of the same size than field (" + fields.length + ")");
		}

		lines.add(new ResultCategoryEntry(description, content));
		return lines.size() - 1;
	}

	/**
	 * Récupčre une ligne afin de la modifier
	 * 
	 * @param at
	 *            L'ID
	 * @return L'ID
	 */
	public ResultCategoryEntry getLine(int at) {
		if (at < lines.size())
			return lines.get(at);
		else
			return null;
	}

	/**
	 * Enlčve une ligne ŕ la catégorie
	 * 
	 * @param id
	 *            La ligne
	 */
	public void removeLine(int id) {
		if (id < lines.size()) {
			lines.remove(id);
		}
	}
}
