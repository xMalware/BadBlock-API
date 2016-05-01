package fr.badblock.gameapi.players.data;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Représente l'avancement d'un joueur dans un achievement (si il l'a déjà réussi (et quand), sa progression).<br>
 * Peut être obtenu via {@link fr.badblock.gameapi.players.data.PlayerData#getAchievementState(fr.badblock.gameapi.players.PlayerAchievement)}
 * @author LeLanN
 */
@Data@AllArgsConstructor
public class PlayerAchievementState {
	private boolean succeeds;
	private String	succeedsDate;
	private double  progress;

	public PlayerAchievementState(){
		this(false, "", 0);
	}
	
	/**
	 * Change l'achievement en 'réussi'. Sauvegarde la date de réussite et réinitialisera l'avancée.
	 */
	public void succeed(){
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		
		this.succeeds 	  = true;
		this.succeedsDate = dateFormat.format(new Date());
		this.progress	  = 0d;
	}
	
	/**
	 * Change la progression du joueur dans son achievement
	 * @param progression Les 'points' de progression a ajouter.
	 */
	public void progress(double progression){
		this.progress += progression;
	}
}
