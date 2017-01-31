package fr.badblock.gameapi.players.data.boosters;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Booster {

	private int    id;
	private double coinsMultiplier;
	private double xpMultiplier;
	private double minRandomBadcoins;
	private double maxRandomBadcoins;
	private double minRandomXp;
	private double maxRandomXp;
	private double maxBadcoins;
	private double maxXP;
	private long   length;

}