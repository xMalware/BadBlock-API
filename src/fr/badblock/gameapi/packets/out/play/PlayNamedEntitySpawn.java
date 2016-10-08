package fr.badblock.gameapi.packets.out.play;

import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.Material;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import fr.badblock.gameapi.packets.watchers.WatcherEntity;

/**
 * Packet envoyé au joueur quand un autre joueur entre dans son champs de vision
 * 
 * @author LeLanN
 */
public interface PlayNamedEntitySpawn extends BadblockOutPacket {
	/**
	 * Récupère l'ID de l'entité
	 * 
	 * @return L'ID
	 */
	public int getEntityId();

	/**
	 * Récupère la position
	 * 
	 * @return La position
	 */
	public Location getLocation();

	/**
	 * Récupère l'UUID
	 * 
	 * @return L'UUID
	 */
	public UUID getUniqueId();
	
	/**
	 * Récupère le type d'objet dans la main
	 * @return Le type
	 */
	public Material getItemInHand();

	/**
	 * Récupère les watchers de l'entité
	 * 
	 * @return Les watchers
	 */
	public WatcherEntity getWatchers();

	/**
	 * Définit l'ID de l'entité
	 * 
	 * @param id
	 *            L'ID
	 * @return Le packet
	 */
	public PlayNamedEntitySpawn setEntityId(int id);

	/**
	 * Définit la position
	 * 
	 * @param location
	 *            La position
	 * @return Le packet
	 */
	public PlayNamedEntitySpawn setLocation(Location location);

	/**
	 * Définit l'UUID
	 * 
	 * @param uniqueId
	 *            L'UUID
	 * @return Le packet
	 */
	public PlayNamedEntitySpawn setUniqueId(UUID uniqueId);

	/**
	 * Définit le type d'objet dans la main
	 * 
	 * @param type
	 *            Le type
	 * @return Le packet
	 */
	public PlayNamedEntitySpawn setItemInHand(Material type);

	
	/**
	 * Définit les watchers de l'entité
	 * 
	 * @param watcher
	 *            Les watchers
	 * @return Le packet
	 */
	public PlayNamedEntitySpawn setWatchers(WatcherEntity watcher);

}
