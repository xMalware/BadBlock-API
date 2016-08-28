package fr.badblock.example.gameapi;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.disguise.Disguise;
import fr.badblock.gameapi.disguise.FallingBlockDisguise;
import fr.badblock.gameapi.fakeentities.FakeEntity;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.utils.i18n.TranslatableString;
import lombok.Getter;
import lombok.Setter;

/**
 * The same as {@link FallingBlockDisguise}, already exist in API
 * 
 * @author LeLanN
 */
public class CustomDisguiseExample extends Disguise {
	@Getter
	@Setter
	private Material type;

	public CustomDisguiseExample(TranslatableString customName, boolean doWithScoreboard, boolean canSeeHimself,
			Material type) {
		super(EntityType.FALLING_BLOCK, customName, doWithScoreboard, canSeeHimself);

		this.type = type;
	}

	@Override
	public FakeEntity<?> createFakeEntity(BadblockPlayer player) {
		return GameAPI.getAPI().spawnFakeFallingBlock(player.getLocation(), type, (byte) 0);
	}
}
