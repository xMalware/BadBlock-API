package fr.badblock.gameapi;

import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import fr.badblock.gameapi.game.GameState;

public abstract class BadListener implements Listener {
	public BadListener() {
		Bukkit.getPluginManager().registerEvents(this, GameAPI.getAPI());
	}

	protected boolean inGame() {
		return GameAPI.getAPI().getGameServer().getGameState() == GameState.RUNNING;
	}
}