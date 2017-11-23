package fr.badblock.gameapi.players.data;

import java.util.List;

import com.google.gson.JsonObject;

import fr.badblock.gameapi.achievements.PlayerAchievement;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.players.data.boosters.PlayerBooster;
import fr.badblock.gameapi.players.kits.PlayerKit;
import fr.badblock.gameapi.utils.i18n.Locale;

/**
 * Répresente les différentes données d'un joueur (coins/xp, kits, achievements,
 * stats, ...)
 * 
 * @author LeLanN
 */
public interface PlayerData {

	/**
	 * Get replay
	 */
	public List<String> getReplay();

	/**
	 * Set replay
	 */
	public void setReplay(List<String> replay);
	
	/**
	 * Ajoute des BadCoins au joueur
	 * 
	 * @param badcoins
	 *            Une valeur POSITIVE
	 * @param applyBonus
	 *            Si il faut appliquer les bonus joueur/serveur
	 * @return Le nombre de badcoins donnés
	 */
	public int addBadcoins(int badcoins, boolean applyBonus);

	/**
	 * Ajoute des XP dans une valeur temporaire qui seront ajouté ŕ l'ajout de nouveaux XP définitif (addXp())
	 * Cela permet de mettre les achievements dans le gain final de la partie
	 * 
	 * @param xp
	 *            L'XP ŕ ajouter
	 * @param applyBonus
	 *            Si il faut appliquer les bonus joueur/serveur
	 * @return Le nombre d'xp donnés
	 */
	public void addTempXp(long xp, boolean applyBonus);

	/**
	 * Ajoute des BadCoins dans une valeur temporaire qui seront ajouté ŕ l'ajout de nouveaux BadCoins définitif (addXp())
	 * Cela permet de mettre les achievements dans le gain final de la partie
	 * 
	 * @param badcoins
	 *            Une valeur POSITIVE
	 * @param applyBonus
	 *            Si il faut appliquer les bonus joueur/serveur
	 * @return Le nombre de badcoins donnés
	 */
	public void addTempBadcoins(long badcoins, boolean applyBonus);

	/**
	 * Ajoute de l'XP au joueur
	 * 
	 * @param xp
	 *            L'XP ŕ ajouter
	 * @param applyBonus
	 *            Si il faut appliquer les bonus joueur/serveur
	 * @return Le nombre d'xp donné
	 */
	public long addXp(long xp, boolean applyBonus);
	
	/**
	 * Ajouter des données temporaires au joueur
	 * (bien mettre le nombre de données initialisées!)
	 * @param data
	 */
	public void incrementTempRankedData(String gameName, String field, long data);
	
	/**
	 * Mettre des données temporaires au joueur
	 * (bien mettre le nombre de données initialisées!)
	 * @param data
	 */
	public void setTempRankedData(String gameName, String field, long data);

	/**
	 * Vérifie si le joueur peut obtenir le niveau suivant du kit (achievements
	 * et badcoins)
	 * 
	 * @param kit
	 *            Le kit
	 * @return Si il peut l'obtenir (si il est au niveau maximal, false)
	 */
	public boolean canUnlockNextLevel(PlayerKit kit);

	/**
	 * Récupčre des données joueurs spécialisées.<br>
	 * Attentin, si la clé ŕ déjŕ été chargée avec une autre classe, peut
	 * provoquer une erreur.
	 * 
	 * @param key
	 *            La clé
	 * @param clazz
	 *            La classe
	 * @return Le PlayerData
	 */
	public <T extends GameData> T gameData(String key, Class<T> clazz);

	/**
	 * Récupčre l'avancement du joueur dans un achievement
	 * 
	 * @param achievement
	 *            L'achievement en question
	 * @return L'avancement du joueur (si pas d'avancement, un nouveau sera
	 *         créé)
	 */
	public PlayerAchievementState getAchievementState(PlayerAchievement achievement);

	/**
	 * Récupčre le nombre de BadCoins du joueur
	 * 
	 * @return Le nombre de BadCoins
	 */
	public int getBadcoins();

	public int addRankedPoints(int rankedPoints);
	
	public int removeRankedPoints(int rankedPoints);

	public double getBadcoinsMultiplier();
	public double getXpMultiplier();

	/**
	 * Récupčre les boosters du joueur
	 * 
	 * @return les boosters du joueur dans une liste
	 */
	public List<PlayerBooster> getBoosters();
	
	/**
	 * Récupčre le nom interne du dernier kit utilisé dans un jeu
	 * 
	 * @param game
	 *            Le nom (interne) du jeu
	 * @return Le nom interne du kit ou null si aucun
	 */
	public String getLastUsedKit(String game);

	/**
	 * Récupčre le niveau du joueur
	 * 
	 * @return Le niveau du joueur
	 */
	public int getLevel();

	/**
	 * Récupčre le langage choisit par le joueur.
	 * 
	 * @return Le langage.
	 */
	public Locale getLocale();

	/**
	 * Récupčre une statistique du joueur
	 * 
	 * @param gameName
	 *            Le jeu
	 * @param stat
	 *            La statistique
	 * @return La valeur
	 */
	public double getStatistics(String gameName, String stat);

	/**
	 * Récupčre le niveau que le joueur a pour un kit.
	 * 
	 * @param kit
	 *            Le kit
	 * @return Le niveau (0 = pas le Kit)
	 */
	public int getUnlockedKitLevel(PlayerKit kit);

	/**
	 * Récupčre l'XP obtenue dans le niveau en cours (autrement dit,
	 * réinitialisée ŕ chaque niveau)
	 * 
	 * @return L'XP
	 */
	public long getXp();

	/**
	 * Récupčre l'XP obtenue pour passer au niveau suivant (réinitialisée ŕ
	 * chaque niveau)
	 * 
	 * @return L'Xp nécessaire
	 */
	public long getXpUntilNextLevel();

	/**
	 * Augment la statistique du joueur
	 * 
	 * @param gameName
	 *            Le jeu
	 * @param stat
	 *            La statistique
	 * @param value
	 *            La valeur ŕ ajouter
	 */
	public void increaseStatistic(String gameName, String stat, double value);

	/**
	 * Augmente toutes les valeurs des achievements donnés de 1
	 * 
	 * @param player
	 *            Le joueur pour tenter de valider les achievements
	 * @param achievements
	 *            Les achievements
	 */
	public void incrementAchievements(BadblockPlayer player, PlayerAchievement... achievements);

	/**
	 * Incrémente (1) la statistique du joueur
	 * 
	 * @param gameName
	 *            Le jeu
	 * @param stat
	 *            La statistique
	 */
	public void incrementStatistic(String gameName, String stat);

	/**
	 * Supprime des BadCoins au joueur
	 * 
	 * @param badcoins
	 *            Une valeur POSITIVE
	 */
	public void removeBadcoins(int badcoins);

	/**
	 * Renvoit les informations modifiées ŕ Ladder pour qu'elles soient
	 * sauvegardées.
	 * 
	 * @return L'objet
	 */
	public JsonObject saveData();

	/**
	 * Définit le nom interne du dernier kit utilisé dans un jeu
	 * 
	 * @param game
	 *            Le nom (interne) du jeu
	 * @param kit
	 *            Le nom (interne) du kit
	 */
	public void setLastUsedKit(String game, String kit);

	/**
	 * Débloque le niveau suivant du kit. Ne fonctionne pas si
	 * {@link #canUnlockNextLevel(PlayerKit)} retourne false.
	 * 
	 * @param kit
	 *            Le kit
	 */
	public void unlockNextLevel(PlayerKit kit);
	
}
