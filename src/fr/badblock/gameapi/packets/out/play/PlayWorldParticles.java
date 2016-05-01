package fr.badblock.gameapi.packets.out.play;

import org.bukkit.Location;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import fr.badblock.gameapi.particles.ParticleEffect;

/**
 * Packet envoyant des particules au joueur
 * @author LeLanN
 */
public interface PlayWorldParticles extends BadblockOutPacket {
	/**
	 * Récupère la particule
	 * @return La particule
	 */
	public ParticleEffect getParticle();

	/**
	 * Définit la particule
	 * @param particle La particule
	 * @return Le packet
	 */
	public PlayWorldParticles setParticle(ParticleEffect particle);

	/**
	 * Récupère la position de la particule
	 * @return La position
	 */
	public Location getLocation();
	
	/**
	 * Définit la position de la particule
	 * @param location La position
	 * @return Le packet
	 */
	public PlayWorldParticles setLocation(Location location);
}
