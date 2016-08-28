package fr.badblock.gameapi.game.result;

import java.util.Map;

import com.google.common.collect.Maps;

import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

/**
 * Représente un résultat de partie en fonction du joueur<br>
 * Ce résultat peut contenir :
 * <ul>
 * <li>Les statistiques des joueurs dans la partie</li>
 * <li>Un classement des teams/scores et</li>
 * <li>Des informations comme la durée de la partie</li>
 * </ul>
 * Le résultat doit en théorie être traduit pour le joueur directemment (la
 * traduction n'est pas sur le web) et être spécifique à chaque
 * joueur/langue<br>
 * Utiliser {@link BadblockPlayer#postResult(Result)}<br>
 * Pour les noms, possibilité d'utiliser [avatar:pseudo] et [img:example.png]
 * pour afficher des images.
 * 
 * @author LeLanN
 */
@RequiredArgsConstructor
public class Result {
	@Getter
	private final String displayName;
	private final Map<String, ResultCategory> categories = Maps.newHashMap();

	/**
	 * Register une nouvelle catégorie au résultat
	 * 
	 * @param name
	 *            Le nom (interne) de la catégorie
	 * @param category
	 *            La catégorie
	 * @return La catégorie
	 */
	public <T extends ResultCategory> T registerCategory(@NonNull String name, @NonNull T category) {
		name = name.toLowerCase();
		categories.put(name, category);

		return category;
	}

	/**
	 * Récupère une catégorie
	 * 
	 * @param name
	 *            Le nom (interne) de la catégorie
	 * @return La catégorie trouvée
	 */
	public ResultCategory getCategory(String name) {
		return categories.get(name.toLowerCase());
	}

	/**
	 * Enlève une catégorie
	 * 
	 * @param name
	 *            Le nom (interne) de la catégorie
	 */
	public void removeCatagory(String name) {
		categories.remove(name.toLowerCase());
	}
}
