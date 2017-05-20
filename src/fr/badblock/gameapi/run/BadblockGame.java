package fr.badblock.gameapi.run;

import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.utils.itemstack.ItemStackFactory;
import lombok.Getter;
import lombok.Setter;

@Getter
@SuppressWarnings("deprecation")
public enum BadblockGame {

	// PROD GAMES
	RUSH("rush", "Rush", "xMalware", createItemStackFactory(Material.BED)),
	TOWER("tower", "Tower", "LeLanN", createItemStackFactory(Material.NETHER_FENCE)),
	SURVIVAL_GAMES("survivalgames", "SurvivalGames", "LeLanN & xMalware", createItemStackFactory(Material.IRON_SWORD)),
	UHCSPEED("uhcSpeed", "UHCSpeed", "LeLanN", createItemStackFactory(Material.GOLDEN_APPLE)), 
	SPACE_BALLS("spaceBalls", "SpaceBalls", "LeLanN", createItemStackFactory(Material.QUARTZ_ORE)), 
	PEARLSWAR("pearlsWar", "PearlsWar", "LeLanN", createItemStackFactory(Material.ENDER_PEARL)),
	CTS("cts", "CaptureTheSheep", "Odanorr", createItemStackFactory(Material.WOOL).durability(DyeColor.GRAY.getWoolData())),
	PVPBOX("pvpbox", "PvPBox", "xMalware", createItemStackFactory(Material.DIAMOND_CHESTPLATE)),
	FREEBUILD("freebuild", "FreeBuild", "xMalware", createItemStackFactory(Material.BRICK)),
	BUILDCONTEST("buildcontest", "BuildContest", "archimede67 & Frekzz", createItemStackFactory(Material.SANDSTONE).fakeEnchantment()),
	// IN-DEV GAMES
	DAYZ("dayZ", "DayZ", "LeLanN", createItemStackFactory(Material.SKULL_ITEM).durability((byte)2)),
	SHOOTFLAG("shootflag", "ShootFlag", "xMalware", createItemStackFactory(Material.BLAZE_ROD).fakeEnchantment()),
	GUARDS("guards", "Guards", "xMalware", createItemStackFactory(Material.EMERALD).fakeEnchantment()),
	DOMINATION("domination", "Domination", "Odanorr", createItemStackFactory(Material.BLAZE_ROD).fakeEnchantment()),
	POINTOUT("pointout", "PointOut", "RedSpri", createItemStackFactory(Material.AIR).fakeEnchantment()),
	POTIONGAME("potiongame", "PotionGame", "Frekzz", createItemStackFactory(Material.POTION).fakeEnchantment());

	public static BadblockGame current;

	private final String internalGameName, developer, displayGameName;
	private final ItemStackFactory itemStackFactory;

	@Setter
	private BadblockGameData gameData;

	BadblockGame(String internalGameName, String displayGameName, String developer, ItemStackFactory itemStackFactory) {
		itemStackFactory.displayName("gameitems." + internalGameName + ".displayname");
		itemStackFactory.lore("gameitems." + internalGameName + ".lore");

		this.internalGameName = internalGameName;
		this.displayGameName = displayGameName;
		this.developer = developer;
		this.itemStackFactory = itemStackFactory;
	}

	public ItemStack createItemStack() {
		return itemStackFactory.clone().create(1);
	}

	public void use() {
		current = this;

		GameAPI.setGameName(displayGameName);
		GameAPI.setInternalGameName(internalGameName);
	}
	
	private static ItemStackFactory createItemStackFactory(Material material) {
		return GameAPI.getAPI().createItemStackFactory().type(material);
	}
	
}
