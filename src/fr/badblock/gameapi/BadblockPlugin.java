package fr.badblock.gameapi;

import org.bukkit.plugin.java.JavaPlugin;

import fr.badblock.gameapi.run.RunType;

/**
 * Classe commune à tous les plugins utilisant l'API (possède des méthodes permettant d'aller plus rapidement).
 * @author LeLanN
 */
public abstract class BadblockPlugin extends JavaPlugin {
	public GameAPI getAPI(){
		return GameAPI.getAPI();
	}
	
	public abstract void onEnable(RunType runType);
	
	@Override
	public final void onEnable(){
		onEnable(getAPI().getRunType());
	}
}
