package fr.badblock.gameapi;

import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Classe commune à tous les plugins utilisant l'API (possède des méthodes permettant d'aller plus rapidement).
 * @author LeLanN
 */
public abstract class BadblockPlugin extends JavaPlugin {
	public void registerListener(Listener listener){
		getServer().getPluginManager().registerEvents(listener, this);
	}
	
	public void registerCommand(String name, CommandExecutor command){
		getServer().getPluginCommand(name).setExecutor(command); 
	}
	
	public GameAPI getAPI(){
		return GameAPI.getAPI();
	}
}
