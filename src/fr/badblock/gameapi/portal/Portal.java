package fr.badblock.gameapi.portal;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Location;

import com.google.common.collect.Maps;

import fr.badblock.gameapi.configuration.values.MapLocation;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.players.BadblockPlayer.GamePermission;
import fr.badblock.gameapi.utils.general.TimeUnit;
import fr.badblock.gameapi.utils.selections.CuboidSelection;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Portal {
	private CuboidSelection portal;
	private String permission;
	private int cooldown;
	private String server;
	private MapLocation place;

	private PortalType type;

	private transient final Map<UUID, Long> cooldowns = Maps.newConcurrentMap();
	private transient File file;

	public Portal(CuboidSelection selection) {
		this.portal = selection;
		this.permission = GamePermission.PLAYER.getPermission();
		this.cooldown = 0;
		this.server = null;
		this.place = new MapLocation(selection.getCenter().getWorld().getSpawnLocation());
		this.type = PortalType.NORMAL_PORTAL;
	}

	public boolean canUsePortal(BadblockPlayer player, Location newLocation) {
		if (!portal.isInSelection(newLocation)) {
			return false;
		}

		if (cooldown > 0 && cooldowns.containsKey(player.getUniqueId())) {
			long time = cooldowns.get(player.getUniqueId());
			if (time < System.currentTimeMillis()) {
				cooldowns.remove(player.getUniqueId());
			} else {
				String delta = TimeUnit.MILLIS_SECOND.toFrench(time - System.currentTimeMillis(), TimeUnit.SECOND,
						TimeUnit.HOUR);
				player.sendTranslatedMessage("portals.cooldown", delta);
				return false;
			}

		}

		cooldowns.put(player.getUniqueId(), System.currentTimeMillis() + cooldown * 1000);

		if (!player.hasPermission(permission)) {
			player.sendTranslatedMessage("portals.permission");
			return false;
		}

		return true;
	}

	public void teleport(BadblockPlayer player) {
		if (type == PortalType.BUNGEE_PORTAL) {
			player.sendPlayer(server);
		} else if (type == PortalType.NORMAL_PORTAL) {
			player.teleport(place.getHandle());
		}
	}

	public static enum PortalType {
		BUNGEE_PORTAL, NORMAL_PORTAL;
	}
}
