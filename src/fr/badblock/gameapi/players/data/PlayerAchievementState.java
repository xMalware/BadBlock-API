package fr.badblock.gameapi.players.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import fr.badblock.gameapi.achievements.PlayerAchievement;
import fr.badblock.gameapi.players.BadblockPlayer;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Représente l'avancement d'un joueur dans un achievement (si il l'a déjà
 * réussi (et quand), sa progression).<br>
 * Peut être obtenu via
 * {@link fr.badblock.gameapi.players.data.PlayerData#getAchievementState(fr.badblock.gameapi.players.PlayerAchievement)}
 * 
 * @author LeLanN
 */
@Data
@AllArgsConstructor
public class PlayerAchievementState {
	private boolean succeeds;
	private String succeedsDate;
	private double progress;

	public PlayerAchievementState() {
		this(false, "", 0);
	}

	/**
	 * Change l'achievement en 'réussi'. Sauvegarde la date de réussite et
	 * réinitialisera l'avancée.
	 */
	public void succeed() {
		if (succeeds)
			return;

		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

		this.succeeds = true;
		this.succeedsDate = dateFormat.format(new Date());
		this.progress = 0d;
	}

	/**
	 * Change la progression du joueur dans son achievement
	 * 
	 * @param progression
	 *            Les 'points' de progression a ajouter.
	 */
	public void progress(double progression) {
		if (!succeeds)
			this.progress += progression;
	}

	/**
	 * Essaye de terminer l'achievement (si il est complet)
	 * 
	 * @param achievement
	 *            L'achievement
	 */
	public void trySucceed(BadblockPlayer player, PlayerAchievement achievement) {
		if (!succeeds && progress >= achievement.getNeededValue()) {
			succeed();
			achievement.reward(player);
		}
	}
}
