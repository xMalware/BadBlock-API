package fr.badblock.gameapi.players.data;

import java.util.List;
import java.util.Optional;

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
	 * Ajoute de l'XP au joueur
	 * 
	 * @param xp
	 *            L'XP à ajouter
	 * @param applyBonus
	 *            Si il faut appliquer les bonus joueur/serveur
	 * @return Le nombre d'xp donné
	 */
	public long addXp(long xp, boolean applyBonus);

	/**
	 * Ajoute des "shop points" au joueur
	 * 
	 * @param "shop points"
	 *            Le nombre de "shop points" à ajouter
	 * @return le nombre de "shop points" que le joueur aura alors :p
	 */
	public long addShopPoints(long shopPoints);

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
	 * Récupère des données joueurs spécialisées.<br>
	 * Attentin, si la clé à déjà été chargée avec une autre classe, peut
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
	 * Récupère l'avancement du joueur dans un achievement
	 * 
	 * @param achievement
	 *            L'achievement en question
	 * @return L'avancement du joueur (si pas d'avancement, un nouveau sera
	 *         créé)
	 */
	public PlayerAchievementState getAchievementState(PlayerAchievement achievement);

	/**
	 * Récupère le nombre de BadCoins du joueur
	 * 
	 * @return Le nombre de BadCoins
	 */
	public int getBadcoins();
	
	/**
	 * Récupère le nombre de ShopPoints du joueur
	 * 
	 * @return Le nombre de ShopPoints
	 */
	public int getShopPoints();

	/**
	 * Récupère les boosters du joueur
	 * 
	 * @return les boosters du joueur dans une liste
	 */
	public List<PlayerBooster> getBoosters();

	/**
	 * Récupère un booster actif du joueur
	 * @return le booster (ou null, si aucun n'est activé)
	 */
	public default PlayerBooster getActiveBooster(){
		Optional<PlayerBooster> res = getBoosters().stream().filter(booster -> booster.isEnabled() && !booster.isExpired()).findAny();
		
		return res.isPresent() ? res.get() : null;
	}
	
	/**
	 * Récupère le nom interne du dernier kit utilisé dans un jeu
	 * 
	 * @param game
	 *            Le nom (interne) du jeu
	 * @return Le nom interne du kit ou null si aucun
	 */
	public String getLastUsedKit(String game);

	/**
	 * Récupère le niveau du joueur
	 * 
	 * @return Le niveau du joueur
	 */
	public int getLevel();

	/**
	 * Récupère le langage choisit par le joueur.
	 * 
	 * @return Le langage.
	 */
	public Locale getLocale();

	/**
	 * Récupère une statistique du joueur
	 * 
	 * @param gameName
	 *            Le jeu
	 * @param stat
	 *            La statistique
	 * @return La valeur
	 */
	public double getStatistics(String gameName, String stat);

	/**
	 * Récupère le niveau que le joueur a pour un kit.
	 * 
	 * @param kit
	 *            Le kit
	 * @return Le niveau (0 = pas le Kit)
	 */
	public int getUnlockedKitLevel(PlayerKit kit);

	/**
	 * Récupère l'XP obtenue dans le niveau en cours (autrement dit,
	 * réinitialisée à chaque niveau)
	 * 
	 * @return L'XP
	 */
	public long getXp();

	/**
	 * Récupère l'XP obtenue pour passer au niveau suivant (réinitialisée à
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
	 *            La valeur à ajouter
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
	 * Renvoit les informations modifiées à Ladder pour qu'elles soient
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
