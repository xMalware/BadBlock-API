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
	
	public void reward(BadblockPlayer player){
		PlayerData data = player.getPlayerData();
		
		int coins = coinsReward * data.getCoinsBonus();
		int xp    = xpReward    * data.getXpBonus();
		
		//TODO apply bonus on achievements reward ?
		
		data.addBadcoins(coins, true);
		data.addXp(xp, true);
		
		player.sendTranslatedMessage("achievements.unlocked", getDisplayName(), coins, xp);
	}
	
	public TranslatableString getDisplayName(){
		return new TranslatableString("achievements." + name + ".displayname");
	}
	
	public TranslatableString getDescription(int progress){
		return new TranslatableString("achievements." + name + ".description", neededValue, progress, xpReward, coinsReward);
	}
}
