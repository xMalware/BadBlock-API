package fr.badblock.gameapi.players.data.boosters;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Booster {

	private int    id;
	private double coinsMultiplier;
	private double xpMultiplier;
	private long   length;

}
