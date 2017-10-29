package fr.badblock.gameapi.disguise;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.fakeentities.FakeEntity;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.utils.i18n.TranslatableString;
import lombok.Getter;
import lombok.Setter;

/**
 * Représente un déguisement en forme de block
 * 
 * @author LeLanN
 */
public class FallingBlockDisguise extends Disguise {
	@Getter
	@Setter
	private Material type;

	public FallingBlockDisguise(TranslatableString customName, boolean doWithScoreboard, boolean canSeeHimself,
			Material type) {
		super(EntityType.FALLING_BLOCK, customName, doWithScoreboard, canSeeHimself);

		this.type = type;
	}

	@Override
	public FakeEntity<?> createFakeEntity(BadblockPlayer player) {
		return GameAPI.getAPI().spawnFakeFallingBlock(player.getLocation(), type, (byte) 0);
	}
}
