package fr.badblock.example.gameapi;

import org.bukkit.entity.EntityType;

import fr.badblock.gameapi.disguise.Disguise;
import fr.badblock.gameapi.players.BadblockPlayer;

/**
 * Un exemple de l'utilisation des déguisements
 * 
 * @author LeLanN
 */
public class DisguiseExample {
	private BadblockPlayer player;
	private Disguise disguise;

	public DisguiseExample(BadblockPlayer player) {
		this.player = player;
		this.disguise = new Disguise(EntityType.ZOMBIE, null, true, true);
	}

	public void disguise() {
		player.disguise(disguise);
	}

	public void undisguise() {
		player.undisguise();
	}
}
