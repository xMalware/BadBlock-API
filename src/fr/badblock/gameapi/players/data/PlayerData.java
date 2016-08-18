package fr.badblock.gameapi.players.data;

import com.google.gson.JsonObject;

import fr.badblock.gameapi.achievements.PlayerAchievement;
import fr.badblock.gameapi.players.kits.PlayerKit;
import fr.badblock.gameapi.utils.i18n.Locale;

/**
 * Répresente les différentes données d'un joueur (coins/xp, kits, achievements, stats, ...)
 * @author LeLanN
 */
public interface PlayerData {
	/**
	 * Récupère le nombre de BadCoins du joueur
	 * @return Le nombre de BadCoins
	 */
	public int getBadcoins();
	
	/**
	 * Ajoute des BadCoins au joueur
	 * @param badcoins Une valeur POSITIVE
	 * @param applyBonus Si il faut appliquer les bonus joueur/serveur
	 * @return Le nombre de badcoins donnés
	 */
	public int addBadcoins(int badcoins, boolean applyBonus);
	
	/**
	 * Supprime des BadCoins au joueur
	 * @param badcoins Une valeur POSITIVE
	 */
	public void removeBadcoins(int badcoins);
	
	/**
	 * Récupère le niveau du joueur
	 * @return Le niveau du joueur
	 */
	public int getLevel();
	
	/**
	 * Récupère l'XP obtenue dans le niveau en cours (autrement dit, réinitialisée à chaque niveau)
	 * @return L'XP
	 */
	public long getXp();
	
	/**
	 * Récupère l'XP obtenue pour passer au niveau suivant (réinitialisée à chaque niveau)
	 * @return L'Xp nécessaire
	 */
	public long getXpUntilNextLevel();
	
	/**
	 * Ajoute de l'XP au joueur
	 * @param xp L'XP à ajouter
	 * @param applyBonus Si il faut appliquer les bonus joueur/serveur
	 * @return Le nombre d'xp donné
	 */
	public long addXp(long xp, boolean applyBonus);
	
	/**
	 * Récupère les données du booster du joueur
	 * @return {@link BoosterData}
	 */
	public BoosterData getBoosterData();
	
	/**
	 * Récupère l'avancement du joueur dans un achievement
	 * @param achievement L'achievement en question
	 * @return L'avancement du joueur (si pas d'avancement, un nouveau sera créé)
	 */
	public PlayerAchievementState getAchievementState(PlayerAchievement achievement);

	/**
	 * Récupère le niveau que le joueur a pour un kit.
	 * @param kit Le kit
	 * @return Le niveau (0 = pas le Kit)
	 */
	public int getUnlockedKitLevel(PlayerKit kit);
	
	/**
	 * Vérifie si le joueur peut obtenir le niveau suivant du kit (achievements et badcoins)
	 * @param kit Le kit
	 * @return Si il peut l'obtenir (si il est au niveau maximal, false)
	 */
	public boolean canUnlockNextLevel(PlayerKit kit);

	/**
	 * Débloque le niveau suivant du kit. Ne fonctionne pas si {@link #canUnlockNextLevel(PlayerKit)} retourne false.
	 * @param kit Le kit
	 */
	public void unlockNextLevel(PlayerKit kit);
	
	/**
	 * Récupère le nom interne du dernier kit utilisé dans un jeu
	 * @param game Le nom (interne) du jeu
	 * @return Le nom interne du kit ou null si aucun
	 */
	public String getLastUsedKit(String game);
	
	/**
	 * Définit le nom interne du dernier kit utilisé dans un jeu
	 * @param game Le nom (interne) du jeu
	 * @param kit Le nom (interne) du kit
	 */
	public void setLastUsedKit(String game, String kit);
	
	/**
	 * Récupère le langage choisit par le joueur.
	 * @return Le langage.
	 */
	public Locale getLocale();
	
	/**
	 * Récupère une statistique du joueur
	 * @param gameName Le jeu
	 * @param stat La statistique
	 * @return La valeur
	 */
	public double getStatistics(String gameName, String stat);
	
	/**
	 * Incrémente (1) la statistique du joueur
	 * @param gameName Le jeu
	 * @param stat La statistique
	 */
	public void incrementStatistic(String gameName, String stat);
	
	/**
	 * Augment la statistique du joueur
	 * @param gameName Le jeu
	 * @param stat La statistique
	 * @param value La valeur à ajouter
	 */
	public void increaseStatistic(String gameName, String stat, double value);
	
	/**
	 * Récupère des données joueurs spécialisées.<br>
	 * Attentin, si la clé à déjà été chargée avec une autre classe, peut provoquer une erreur.
	 * @param key La clé
	 * @param clazz La classe
	 * @return Le PlayerData
	 */
	public <T extends GameData> T gameData(String key, Class<T> clazz);
	
	/**
	 * Renvoit les informations modifiées à Ladder pour qu'elles soient sauvegardées.
	 * @return L'objet
	 */
	public JsonObject saveData();
}
