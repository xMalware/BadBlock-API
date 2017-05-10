package fr.badblock.gameapi.minigame;

import fr.badblock.gameapi.BadblockPlugin;
import fr.badblock.gameapi.achievements.AchievementList;
import fr.badblock.gameapi.run.BadblockGame;

public abstract class BadBlockGamePlugin extends BadblockPlugin {

	/**
	 * When the game loads
	 */
	public abstract void onGameLoad();

	/**
	 * Load the game achievements
	 */
	public abstract void loadAchievements();

	/**
	 * BadBlockGame
	 * @return
	 */
	public abstract BadblockGame getGame();
	
	/**
	 * AchievementList
	 * @return
	 */
	public abstract AchievementList getAchievementList();
	
}
