package fr.badblock.gameapi.players.data.boosters;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PlayerBooster {

	private String  username;
	private long	expire;
	private boolean enabled;
	private long 	addedXp;
	private long 	addedBadcoins;
	private String  gameName;
	private Booster booster;

	/**
	 * Récupčre le fait que le booster est expiré ou non
	 * 
	 * @return Un booléen
	 */
	public boolean isExpired() {
		return !isValid();
	}

	/**
	 * Récupčre le fait que le booster est encore valide ou non
	 * 
	 * @return Un booléen
	 */
	public boolean isValid() {
		return expire > System.currentTimeMillis() || expire == -1;
	}

}
