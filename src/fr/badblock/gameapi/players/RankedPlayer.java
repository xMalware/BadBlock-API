package fr.badblock.gameapi.players;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@Data
public class RankedPlayer
{

	private BadblockPlayer	player;
	private int				points;
	private int				totalRank;
	private int				monthRank;
	
}
