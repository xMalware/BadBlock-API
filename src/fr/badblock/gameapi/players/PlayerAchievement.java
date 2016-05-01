package fr.badblock.gameapi.players;

import fr.badblock.gameapi.utils.i18n.TranslatableString;

/**
 * Représente un achievment (succès) qu'un joueur pourra débloquer en jouant sur BadBlock
 * @author LeLanN
 */
public interface PlayerAchievement {
	/**
	 * Récupère le nom 'interne' (qui ne sera jamais affiché) de l'achievement. Par exemple rush.kill1<br>
	 * Pour obtenir la version affichage, passer par le système d'i18n.
	 * @return Le nom 'interne'
	 */
	public String getAchievementName();
	
	/**
	 * Retourne le nom d'affichage de l'achievement (la clé)
	 * @return Le nom d'affichage
	 */
	public TranslatableString getDisplayName();
	
	/**
	 * Retourne la description de l'achievement (quelques mots) (la clé)
	 * @return La description de l'achievement
	 */
	public TranslatableString getDescription();
	
	/**
	 * Vérifie si l'achievement se réinitialise à chaque partie ou si il est un achievement cumulatif.<br>
	 * Par exemple, pour les achievements 'faire 10/100/100 kills', retourne false.<br>
	 * @return Si l'achievement est 'tout seul'
	 */
	public boolean isOnegameAchievement();
	
	/**
	 * Récupère la valeur demandée pour que l'achievement soit débloquer. Peut être des kills, des lits cassés, des morts, ...
	 * @return La valeur demandée
	 */
	public int getAchievementNeededValue();
	
	/**
	 * Récupère le gain en points boutiques
	 * @return Le gain en points boutiques
	 */
	public int getMegaCoinsReward();
	
	/**
	 * Récupère le gain en XP
	 * @return Le gain en XP
	 */
	public int getXpReward();
	
	/**
	 * Récupère le gain en BadCoins
	 * @return Le gain en BadCoins
	 */
	public int getBadcoinsReward();
}
