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
	
	protected boolean afterGame(){
		GameState state = GameAPI.getAPI().getGameServer().getGameState();
		
		return state == GameState.RUNNING || state == GameState.FINISHED || state == GameState.STOPPING;
	}
	
	protected boolean beforeGame(){
		return GameAPI.getAPI().getGameServer().getGameState() == GameState.WAITING;
	}
}