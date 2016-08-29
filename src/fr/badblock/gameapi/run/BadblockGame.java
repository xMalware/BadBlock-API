package fr.badblock.gameapi.run;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.utils.i18n.Locale;
import fr.badblock.gameapi.utils.itemstack.ItemStackFactory;
import lombok.Getter;
import lombok.Setter;

@Getter
public enum BadblockGame {
	RUSH("rush", "Rush", GameAPI.getAPI().createItemStackFactory().type(Material.BED)), TOWER("tower", "Tower",
			GameAPI.getAPI().createItemStackFactory().type(Material.NETHER_FENCE)), SURVIVAL_GAMES("survivalgames",
					"SurvivalGames", GameAPI.getAPI().createItemStackFactory().type(Material.IRON_SWORD)), UHCSPEED(
							"uhcSpeed", "UHCSpeed",
							GameAPI.getAPI().createItemStackFactory().type(Material.GOLDEN_APPLE)), SPACE_BALLS(
									"spaceBalls", "SpaceBalls",
									GameAPI.getAPI().createItemStackFactory().type(Material.QUARTZ_ORE)), PEARLSWAR(
											"pearlsWar", "PearlsWar",
											GameAPI.getAPI().createItemStackFactory().type(Material.ENDER_PEARL));

	public static BadblockGame current;

	private final String internalGameName, displayGameName;
	private final ItemStackFactory itemStackFactory;

	@Setter
	private BadblockGameData gameData;

	BadblockGame(String internalGameName, String displayGameName, ItemStackFactory itemStackFactory) {
		itemStackFactory.displayName("gameitems." + internalGameName + ".displayname");
		itemStackFactory.lore("gameitems." + internalGameName + ".lore");

		this.internalGameName = internalGameName;
		this.displayGameName = displayGameName;
		this.itemStackFactory = itemStackFactory;
	}

	public ItemStack createItemStack(BadblockPlayer player) {
		return createItemStack(player.getPlayerData().getLocale());
	}

	public ItemStack createItemStack(Locale locale) {
		return itemStackFactory.clone().doWithI18n(locale).create(1);
	}

	public void use() {
		current = this;

		GameAPI.setGameName(displayGameName);
		GameAPI.setInternalGameName(internalGameName);
	}
}
