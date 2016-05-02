package fr.badblock.gameapi.game.result;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Représente une catégorie ayant une construction à entrée unique
 * @author LeLanN
 */
@Getter@RequiredArgsConstructor
public class ResultCategoryLined implements ResultCategory {
	private final String 				   categoryName;
	private final CategoryType 			   type			= CategoryType.LINED_DATA;
	
	private final List<ResultCategoryLine> lines 	    = new ArrayList<>();
	
	/**
	 * Récupère une ligne afin de la modifier
	 * @param at L'ID
	 * @return L'ID
	 */
	public ResultCategoryLine getLine(int at){
		if(at < lines.size())
			return lines.get(at);
		else return null;
	}

	/**
	 * Ajoute une ligne à la catégorie
	 * @param description La description de la ligne
	 * @param content Le contenu de la ligne
	 * @return L'ID de la nouvelle ligne
	 */
	public int addLine(@NonNull String description, @NonNull String content){
		lines.add(new ResultCategoryLine(description, content));
		return lines.size() - 1;
	}
	
	/**
	 * Enlève une ligne à la catégorie
	 * @param id La ligne
	 */
	public void removeLine(int id){
		if(id < lines.size()){
			lines.remove(id);
		}
	}
	
	/**
	 * Représente une ligne pour {@link ResultCategoryLined}
	 * @author LeLanN
	 */
	@Data@AllArgsConstructor
	public static class ResultCategoryLine {
		private String lineDescription;
		private String lineContent;
	}
}
