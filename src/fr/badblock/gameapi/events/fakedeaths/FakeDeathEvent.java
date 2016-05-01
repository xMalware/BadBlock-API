package fr.badblock.gameapi.events.fakedeaths;

import org.bukkit.Location;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;

import fr.badblock.gameapi.utils.i18n.TranslatableString;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Les joueurs ne meurent jamais réellement avec l'API (pour leur éviter un temps de chargement désagréable).
 * Cet event permet au mini-jeux de savoir quand un joueur meurt, et permet de traiter en fonction.<br>
 * Cet event ne peut être appelé.
 * @author LeLanN
 */
@RequiredArgsConstructor
public abstract class FakeDeathEvent extends Event implements Cancellable {
	@Getter@Setter 
	private boolean  		   cancelled		 = false;
	@Getter@Setter 
	private int	    	       timeBeforeRespawn = 0;
	@Getter@Setter 
	private TranslatableString deathMessage	   	 = null;
	@Getter@Setter@NonNull 
	private Location 		   respawnPlace	     = null;
	@Getter@Setter 
	private boolean  		   lightning		 = false;
}
