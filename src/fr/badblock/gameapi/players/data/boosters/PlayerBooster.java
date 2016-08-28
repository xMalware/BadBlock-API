package fr.badblock.gameapi.players.data.boosters;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerBooster {

	private long timeElapsed;
	private long maxTime;
	private boolean enabled;
	private Booster booster;

	/**
	 * Récupère le fait que le booster est encore valide ou non
	 * 
	 * @return Un booléen
	 */
	public boolean isValid() {
		return maxTime > timeElapsed;
	}

	/**
	 * Récupère le fait que le booster est expiré ou non
	 * 
	 * @return Un booléen
	 */
	public boolean isExpired() {
		return !isValid();
	}

}
