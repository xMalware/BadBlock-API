package fr.badblock.gameapi.achievements;

import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

import org.bukkit.Material;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.players.data.PlayerAchievementState;
import fr.badblock.gameapi.run.BadblockGame;
import fr.badblock.gameapi.utils.itemstack.CustomInventory;
import fr.badblock.gameapi.utils.itemstack.ItemAction;

public class AchievementList {
	private Map<String, PlayerAchievement> achievements = new LinkedHashMap<>();

	private BadblockGame game;

	public AchievementList(BadblockGame game) {
		this.game = game;
	}

	public <T extends PlayerAchievement> T addAchievement(T achievement) {
		achievements.put(achievement.getName(), achievement);

		return achievement;
	}

	public CustomInventory createInventory(BadblockPlayer player, int canUseSize, int size) {
		CustomInventory inv = GameAPI.getAPI().createCustomInventory(size,
				player.getTranslatedMessage("achievements.inventory." + game.getInternalGameName())[0]);

		int line = 0;
		int column = 0;

		for (PlayerAchievement achievement : getAllAchievements()) {
			PlayerAchievementState state = player.getPlayerData().getAchievementState(achievement);

			boolean has = state.isSucceeds();
			int progress = (int) state.getProgress();

			if (has)
				progress = achievement.getNeededValue();

			Material type = achievement.isTemp() ? (has ? Material.EMERALD : Material.COAL)
					: (has ? Material.EMERALD_BLOCK : Material.COAL_BLOCK);

			inv.addClickableItem(line * 9 + column,
					GameAPI.getAPI().createItemStackFactory().type(type).doWithI18n(player.getPlayerData().getLocale())
							.displayName(achievement.getDisplayName()).lore(achievement.getDescription(progress))
							.asExtra(1).disallow(ItemAction.values())); // aucune
																		// action,
																		// juste
																		// lecture
																		// :o

			column++;

			if (column == 4) {
				line++;
				column = 0;
			} else if (column == 9) {
				line++;
				column = 5;
			}

			if (line == canUseSize) {

				if (column == 0) {
					line = 0;
					column = 5;
				} else
					break;

			}
		}

		return inv;
	}

	public Collection<PlayerAchievement> getAllAchievements() {
		return Collections.unmodifiableCollection(achievements.values());
	}

	public PlayerAchievement getGameAchievement(String achievement) {
		return achievements.get(achievement);
	}

	public void openInventory(BadblockPlayer player) {
		createInventory(player, 4, 4).openInventory(player);
	}
}
