package fr.badblock.gameapi.command;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.block.Block;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.configuration.BadConfiguration;
import fr.badblock.gameapi.configuration.values.MapLocation;
import fr.badblock.gameapi.configuration.values.MapSelection;
import fr.badblock.gameapi.configuration.values.MapValue;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.players.BadblockPlayer.GamePermission;
import fr.badblock.gameapi.utils.i18n.TranslatableString;
import fr.badblock.gameapi.utils.i18n.Word.WordDeterminant;
import fr.badblock.gameapi.utils.i18n.messages.GameMessages;

public abstract class MapAbstractCommand extends AbstractCommand {
	private File mapFolder;

	public MapAbstractCommand(String command, TranslatableString usage, GamePermission permission, String[] aliases,
			File mapFolder) {
		super(command, usage, permission, aliases);
		this.mapFolder = mapFolder;

		if (!mapFolder.exists())
			mapFolder.mkdirs();
	}

	@SuppressWarnings("unchecked")
	public void changeValue(boolean addToList, String map, String sub, String key, MapValue<?> value) {
		File file = new File(mapFolder, map.toLowerCase() + ".json");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException unused) {
			}
		}

		BadConfiguration config = GameAPI.getAPI().loadConfiguration(file);
		BadConfiguration modify = sub == null ? config : config.getSection(sub.toLowerCase());

		if (addToList) {
			List<MapValue<?>> values = (List<MapValue<?>>) modify.getValueList(key, value.getClass());
			values.add(value);
			modify.setValueList(key, values);
		} else {
			modify.setValue(key, value);
		}

		config.save(file);
	}

	public void changeValue(String map, String sub, String key, List<MapValue<?>> value) {
		File file = new File(mapFolder, map.toLowerCase() + ".json");

		if (!file.exists()) {
			try {
				file.createNewFile();
			} catch (IOException unused) {
			}
		}

		BadConfiguration config = GameAPI.getAPI().loadConfiguration(file);
		BadConfiguration modify = sub == null ? config : config.getSection(sub.toLowerCase());

		modify.setValueList(key, value);
		config.save(file);
	}

	public void setLocation(boolean addToList, String map, String sub, String key, BadblockPlayer player) {
		changeValue(addToList, map, sub, key, new MapLocation(player.getLocation()));
	}

	public void setLookedBlock(boolean addToList, String map, String sub, String key, BadblockPlayer player,
			Material... allowedMaterials) {
		Block blockTarget = null;
		for (Block b : player.getLineOfSight((HashSet<Material>) null, 200)) {
			if (!b.getType().equals(Material.AIR)) {
				blockTarget = b;
				break;
			}
		}

		if (blockTarget == null) {
			player.sendTranslatedMessage("commands.nolookedblock");
		} else {
			boolean good = false;

			for (Material allowedMaterial : allowedMaterials) {
				if (allowedMaterial == blockTarget.getType()) {
					good = true;
				}
			}

			if (!good) {
				player.sendTranslatedMessage("commands.wronglookedblock",
						GameMessages.material(allowedMaterials[0], false, WordDeterminant.UNDEFINED));
			} else {
				changeValue(addToList, map, sub, key, new MapLocation(blockTarget.getLocation()));
			}

		}
	}

	public void setSelection(boolean addToList, String map, String sub, String key, BadblockPlayer player) {

		if (player.getSelection() == null) {
			player.sendTranslatedMessage("commands.noselection");
		} else {
			changeValue(addToList, map, sub, key, new MapSelection(player.getSelection()));
		}

	}

}
