package fr.badblock.gameapi.players.data;

import java.util.List;

import com.google.gson.JsonObject;

import fr.badblock.gameapi.achievements.PlayerAchievement;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.players.data.boosters.PlayerBooster;
import fr.badblock.gameapi.players.kits.PlayerKit;
import fr.badblock.gameapi.utils.i18n.Locale;

/**
 * RÃ©presente les diffÃ©rentes donnÃ©es d'un joueur (coins/xp, kits, achievements,
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
	 * @return Le nombre de badcoins donnÃ©s
	 */
	public int addBadcoins(int badcoins, boolean applyBonus);

	/**
	 * Ajoute des XP dans une valeur temporaire qui seront ajoutÃ© Å• l'ajout de nouveaux XP dÃ©finitif (addXp())
	 * Cela permet de mettre les achievements dans le gain final de la partie
	 * 
	 * @param xp
	 *            L'XP Å• ajouter
	 * @param applyBonus
	 *            Si il faut appliquer les bonus joueur/serveur
	 * @return Le nombre d'xp donnÃ©s
	 */
	public void addTempXp(long xp, boolean applyBonus);

	/**
	 * Ajoute des BadCoins dans une valeur temporaire qui seront ajoutÃ© Å• l'ajout de nouveaux BadCoins dÃ©finitif (addXp())
	 * Cela permet de mettre les achievements dans le gain final de la partie
	 * 
	 * @param badcoins
	 *            Une valeur POSITIVE
	 * @param applyBonus
	 *            Si il faut appliquer les bonus joueur/serveur
	 * @return Le nombre de badcoins donnÃ©s
	 */
	public void addTempBadcoins(long badcoins, boolean applyBonus);

	/**
	 * Ajoute de l'XP au joueur
	 * 
	 * @param xp
	 *            L'XP Å• ajouter
	 * @param applyBonus
	 *            Si il faut appliquer les bonus joueur/serveur
	 * @return Le nombre d'xp donnÃ©
	 */
	public long addXp(long xp, boolean applyBonus);
	
	/**
	 * Ajouter des donnÃ©es temporaires au joueur
	 * (bien mettre le nombre de donnÃ©es initialisÃ©es!)
	 * @param data
	 */
	public void incrementTempRankedData(String gameName, String field, long data);
	
	/**
	 * Mettre des donnÃ©es temporaires au joueur
	 * (bien mettre le nombre de donnÃ©es initialisÃ©es!)
	 * @param data
	 */
	public void setTempRankedData(String gameName, String field, long data);

	/**
	 * VÃ©rifie si le joueur peut obtenir le niveau suivant du kit (achievements
	 * et badcoins)
	 * 
	 * @param kit
	 *            Le kit
	 * @return Si il peut l'obtenir (si il est au niveau maximal, false)
	 */
	public boolean canUnlockNextLevel(PlayerKit kit);

	/**
	 * RÃ©cupÄ�re des donnÃ©es joueurs spÃ©cialisÃ©es.<br>
	 * Attentin, si la clÃ© Å• dÃ©jÅ• Ã©tÃ© chargÃ©e avec une autre classe, peut
	 * provoquer une erreur.
	 * 
	 * @param key
	 *            La clÃ©
	 * @param clazz
	 *            La classe
	 * @return Le PlayerData
	 */
	public <T extends GameData> T gameData(String key, Class<T> clazz);

	/**
	 * RÃ©cupÄ�re l'avancement du joueur dans un achievement
	 * 
	 * @param achievement
	 *            L'achievement en question
	 * @return L'avancement du joueur (si pas d'avancement, un nouveau sera
	 *         crÃ©Ã©)
	 */
	public PlayerAchievementState getAchievementState(PlayerAchievement achievement);

	/**
	 * RÃ©cupÄ�re le nombre de BadCoins du joueur
	 * 
	 * @return Le nombre de BadCoins
	 */
	public int getBadcoins();

	public int addRankedPoints(int rankedPoints);
	
	public int removeRankedPoints(int rankedPoints);

	public double getBadcoinsMultiplier();
	public double getXpMultiplier();

	/**
	 * RÃ©cupÄ�re les boosters du joueur
	 * 
	 * @return les boosters du joueur dans une liste
	 */
	public List<PlayerBooster> getBoosters();

	/**
	 * Récupère la liste des ignorés
	 * 
	 * @return ignore list
	 */
	public List<String> getIgnoreList();
	
	/**
	 * Insère une liste des ignorés
	 * 
	 * @return ignore list
	 */
	public void setIgnoreList(List<String> list);
	
	/**
	 * RÃ©cupÄ�re le nom interne du dernier kit utilisÃ© dans un jeu
	 * 
	 * @param game
	 *            Le nom (interne) du jeu
	 * @return Le nom interne du kit ou null si aucun
	 */
	public String getLastUsedKit(String game);

	/**
	 * RÃ©cupÄ�re le niveau du joueur
	 * 
	 * @return Le niveau du joueur
	 */
	public int getLevel();

	/**
	 * RÃ©cupÄ�re le langage choisit par le joueur.
	 * 
	 * @return Le langage.
	 */
	public Locale getLocale();

	/**
	 * RÃ©cupÄ�re une statistique du joueur
	 * 
	 * @param gameName
	 *            Le jeu
	 * @param stat
	 *            La statistique
	 * @return La valeur
	 */
	public double getStatistics(String gameName, String stat);

	/**
	 * RÃ©cupÄ�re le niveau que le joueur a pour un kit.
	 * 
	 * @param kit
	 *            Le kit
	 * @return Le niveau (0 = pas le Kit)
	 */
	public int getUnlockedKitLevel(PlayerKit kit);

	/**
	 * RÃ©cupÄ�re l'XP obtenue dans le niveau en cours (autrement dit,
	 * rÃ©initialisÃ©e Å• chaque niveau)
	 * 
	 * @return L'XP
	 */
	public long getXp();

	/**
	 * RÃ©cupÄ�re l'XP obtenue pour passer au niveau suivant (rÃ©initialisÃ©e Å•
	 * chaque niveau)
	 * 
	 * @return L'Xp nÃ©cessaire
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
	 *            La valeur Å• ajouter
	 */
	public void increaseStatistic(String gameName, String stat, double value);

	/**
	 * Augmente toutes les valeurs des achievements donnÃ©s de 1
	 * 
	 * @param player
	 *            Le joueur pour tenter de valider les achievements
	 * @param achievements
	 *            Les achievements
	 */
	public void incrementAchievements(BadblockPlayer player, PlayerAchievement... achievements);

	/**
	 * IncrÃ©mente (1) la statistique du joueur
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
	 * Renvoit les informations modifiÃ©es Å• Ladder pour qu'elles soient
	 * sauvegardÃ©es.
	 * 
	 * @return L'objet
	 */
	public JsonObject saveData();

	/**
	 * DÃ©finit le nom interne du dernier kit utilisÃ© dans un jeu
	 * 
	 * @param game
	 *            Le nom (interne) du jeu
	 * @param kit
	 *            Le nom (interne) du kit
	 */
	public void setLastUsedKit(String game, String kit);

	/**
	 * DÃ©bloque le niveau suivant du kit. Ne fonctionne pas si
	 * {@link #canUnlockNextLevel(PlayerKit)} retourne false.
	 * 
	 * @param kit
	 *            Le kit
	 */
	public void unlockNextLevel(PlayerKit kit);
	
}
