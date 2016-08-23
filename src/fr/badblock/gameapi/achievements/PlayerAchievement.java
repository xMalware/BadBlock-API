package fr.badblock.gameapi.achievements;

import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.players.data.PlayerData;
import fr.badblock.gameapi.utils.i18n.TranslatableString;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter@RequiredArgsConstructor
public class PlayerAchievement {
	private final String 			 name;
	private final int				 xpReward;
	private final int				 coinsReward;
	private final int 				 neededValue;

	private final boolean 			 temp;
	
	public PlayerAchievement(String name, int xpReward, int coinsReward, int neededValue){
		this(name, xpReward, coinsReward, neededValue, false);
	}
	/*
	 * 

		int coins  = BoosterUtil.getBoosted(player, BoostedValue.COINS, coinsReward);
		long xp    = BoosterUtil.getBoosted(player, BoostedValue.XP, xpReward);
	 */
	public void reward(BadblockPlayer player){
		PlayerData data = player.getPlayerData();
		
		data.addBadcoins(coinsReward, true);
		data.addXp(xpReward, true);
		
		player.sendTranslatedMessage("achievements.unlocked", getDisplayName(), coinsReward, xpReward);
	} 
	
	public TranslatableString getDisplayName(){
		return new TranslatableString("achievements." + name + ".displayname");
	}
	
	public TranslatableString getDescription(int progress){
		return new TranslatableString("achievements." + name + ".description", neededValue, progress, xpReward, coinsReward);
	}
}
