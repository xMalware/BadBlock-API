package fr.badblock.gameapi.disguise;

import org.bukkit.entity.EntityType;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.fakeentities.FakeEntity;
import fr.badblock.gameapi.packets.watchers.WatcherEntity;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.utils.i18n.TranslatableString;
import lombok.Getter;

/**
 * Représente un déguisement
 * 
 * @author LeLanN
 */
@Getter
public class Disguise {
	protected EntityType entityType;
	protected TranslatableString customName;
	protected boolean doWithScoreboard;
	protected boolean canSeeHimself;

	/**
	 * Créé un nouveau déguisement
	 * 
	 * @param entityType
	 *            Le type d'entité
	 * @param customName
	 *            Le nom de l'entité (null = aucun)
	 * @param doWithScoreboard
	 *            Si le nom de l'entité doit être fait avec le scoreboard
	 * @param canSeeHimself
	 *            Si le joueur peut voir le déguisement
	 */
	public Disguise(EntityType entityType, TranslatableString customName, boolean doWithScoreboard,
			boolean canSeeHimself) {
		this.entityType = entityType;
		this.customName = customName;
		this.doWithScoreboard = doWithScoreboard;
		this.canSeeHimself = canSeeHimself;
	}

	/**
	 * Créé l'entité qui servivra de déguisement
	 * 
	 * @param location
	 *            La position de spawn
	 * @return L'entité
	 */
	public final FakeEntity<?> createEntity(BadblockPlayer player) {
		FakeEntity<?> entity = createFakeEntity(player);

		if (customName != null) {
			entity.getWatchers().setCustomNameVisible(true);
			entity.getWatchers().setCustomName(customName);
		} else if (doWithScoreboard) {
			TranslatableString customName = GameAPI.getAPI().getBadblockScoreboard().getUsedName(player);

			entity.getWatchers().setCustomNameVisible(customName != null);
			entity.getWatchers().setCustomName(customName);
		}

		return entity;
	}

	/**
	 * Créé l'entité, en interne. A override pour faire des entités customs
	 * 
	 * @param player
	 *            Le joueur
	 * @return L'entité
	 */
	public FakeEntity<?> createFakeEntity(BadblockPlayer player) {
		return GameAPI.getAPI().spawnFakeLivingEntity(player.getLocation(), entityType, WatcherEntity.class);
	}
}
