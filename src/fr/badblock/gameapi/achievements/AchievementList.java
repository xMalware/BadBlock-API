package fr.badblock.gameapi.achievements;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.google.common.collect.Maps;

public class AchievementList {
	private static Map<String, Map<String, PlayerAchievement>> achievements = Maps.newConcurrentMap();
	
	/*
	 * Tuer X personnes
	 */
	public static final PlayerAchievement RUSH_KILL_1 = addAchievement("rush", new PlayerAchievement("rush_kill_1", 10, 5, 10));
	public static final PlayerAchievement RUSH_KILL_2 = addAchievement("rush", new PlayerAchievement("rush_kill_2", 50, 25, 100));
	public static final PlayerAchievement RUSH_KILL_3 = addAchievement("rush", new PlayerAchievement("rush_kill_3", 250, 100, 1000));
	public static final PlayerAchievement RUSH_KILL_4 = addAchievement("rush", new PlayerAchievement("rush_kill_4", 500, 250, 10000));
	
	/*
	 * Casser X lits
	 */
	
	public static final PlayerAchievement RUSH_BED_1  = addAchievement("rush", new PlayerAchievement("rush_bed_1", 10, 5, 5));
	public static final PlayerAchievement RUSH_BED_2  = addAchievement("rush", new PlayerAchievement("rush_bed_2", 50, 25, 50));
	public static final PlayerAchievement RUSH_BED_3  = addAchievement("rush", new PlayerAchievement("rush_bed_3", 250, 100, 500));
	public static final PlayerAchievement RUSH_BED_4  = addAchievement("rush", new PlayerAchievement("rush_bed_4", 500, 250, 5000));

	/*
	 * Faire exploser X lits 
	 */
	public static final PlayerAchievement RUSH_EBED_1  = addAchievement("rush", new PlayerAchievement("rush_ebed_1", 10, 5, 5));
	public static final PlayerAchievement RUSH_EBED_2  = addAchievement("rush", new PlayerAchievement("rush_ebed_2", 50, 25, 50));
	public static final PlayerAchievement RUSH_EBED_3  = addAchievement("rush", new PlayerAchievement("rush_ebed_3", 250, 100, 500));
	public static final PlayerAchievement RUSH_EBED_4  = addAchievement("rush", new PlayerAchievement("rush_ebed_4", 500, 250, 5000));

	/*
	 * Gagner X parties
	 */
	public static final PlayerAchievement RUSH_WIN_1  = addAchievement("rush", new PlayerAchievement("rush_win_1", 10, 2, 1));
	public static final PlayerAchievement RUSH_WIN_2  = addAchievement("rush", new PlayerAchievement("rush_win_2", 50, 25, 100));
	public static final PlayerAchievement RUSH_WIN_3  = addAchievement("rush", new PlayerAchievement("rush_win_3", 250, 100, 1000));
	public static final PlayerAchievement RUSH_WIN_4  = addAchievement("rush", new PlayerAchievement("rush_win_4", 500, 250, 10000));

	/*
	 * Gagner X parties en moins de 10 minutes
	 */
	public static final PlayerAchievement RUSH_RUSHER_1  = addAchievement("rush", new PlayerAchievement("rush_rusher_1", 10, 5, 5));
	public static final PlayerAchievement RUSH_RUSHER_2  = addAchievement("rush", new PlayerAchievement("rush_rusher_2", 50, 25, 50));
	public static final PlayerAchievement RUSH_RUSHER_3  = addAchievement("rush", new PlayerAchievement("rush_rusher_3", 250, 100, 500));
	public static final PlayerAchievement RUSH_RUSHER_4  = addAchievement("rush", new PlayerAchievement("rush_rusher_4", 500, 250, 5000));
	
	/*
	 * Tuer 10 joueurs dans une même partie
	 */
	public static final PlayerAchievement RUSH_KILLER = addAchievement("rush", new PlayerAchievement("rush_killer", 100, 50, 10, true));
	/*
	 * Tuer 20 joueurs dans une même partie
	 */
	public static final PlayerAchievement RUSH_UKILLER = addAchievement("rush", new PlayerAchievement("rush_ukiller", 200, 100, 20, true));

	/*
	 * Tuer 15 à l'arc joueurs dans une même partie
	 */
	public static final PlayerAchievement RUSH_SHOOTER = addAchievement("rush", new PlayerAchievement("rush_shooter", 100, 50, 15, true));
	
	/*
	 * Ne frapper les adverseraires qu'à l'arc et faire 20 kills
	 */
	public static final PlayerAchievement RUSH_USHOOTER = addAchievement("rush", new PlayerAchievement("rush_ushooter", 250, 150, 20, true));
	
	/**
	 * Casser 3 lits dans une même partie
	 */
	public static final PlayerAchievement RUSH_BROKER = addAchievement("rush", new PlayerAchievement("rush_broker", 100, 50, 3, true));

	/**
	 * Exploser 3 lits dans une même partie
	 */
	public static final PlayerAchievement RUSH_EXPLODER = addAchievement("rush", new PlayerAchievement("rush_exploder", 150, 75, 3, true));

	
	private static <T extends PlayerAchievement> T addAchievement(String game, T achievement){
		if(!achievements.containsKey(game))
			achievements.put(game, Maps.newConcurrentMap());
		
		achievements.get(game).put(achievement.getName(), achievement);
		
		return achievement;
	}
	
	public static PlayerAchievement getGameAchievement(String game, String achievement){
		if(achievements.containsKey(game))
			return achievements.get(game).get(achievement);
		
		return null;
	}
	
	public static Collection<PlayerAchievement> getGameAchievements(String game){
		if(achievements.containsKey(game))
			return achievements.get(game).values();
		
		return new ArrayList<>();
	}
}
