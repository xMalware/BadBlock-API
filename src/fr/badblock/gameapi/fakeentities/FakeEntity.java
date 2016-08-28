package fr.badblock.gameapi.fakeentities;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import fr.badblock.gameapi.packets.out.play.PlayEntityStatus.EntityStatus;
import fr.badblock.gameapi.packets.watchers.WatcherEntity;
import fr.badblock.gameapi.players.BadblockPlayer;

/**
 * Représente une entité qui n'est pas gérée côté serveur. Ne peut être qu'une
 * entité vivante.
 * 
 * @author LeLanN
 *
 * @param <T>
 *            Le watcher correspondant au type de l'entité gérée.
 */
public interface FakeEntity<T extends WatcherEntity> {
	public enum Visibility {
		SERVER, PLAYER;
	}

	/**
	 * Supprime l'entité si ce n'est pas fait et la supprime du cache (ne pourra
	 * plus être réutilisée).
	 */
	public void destroy();

	/**
	 * Récupère la rotation de la tête de l'entité
	 * 
	 * @return La position de la tête
	 */
	public float getHeadYaw();

	/**
	 * L'entité de l'ID
	 * 
	 * @return L'ID
	 */
	public int getId();

	/**
	 * Récupère la position du joueur
	 * 
	 * @return La position
	 */
	public Location getLocation();

	/**
	 * Récupère le type de l'entité
	 * 
	 * @return Le type
	 */
	public EntityType getType();

	/**
	 * Récupère la visibilité de l'entité
	 * 
	 * @return La visibilité
	 */
	public Visibility getVisibility();

	/**
	 * Récupère les watchers de l'entité
	 * 
	 * @return Les watchers
	 */
	public T getWatchers();

	/**
	 * Vérifie si l'entité est remove.
	 * 
	 * @return Si elle est remove.
	 */
	public boolean isRemoved();

	/**
	 * Tue (naturellement) l'entité. Autrement, même effet que remove().
	 */
	public void kill();

	/**
	 * Déplace (en essayant de le faire naturellement) l'entité
	 * 
	 * @param location
	 *            La nouvelle position
	 */
	public void move(Location location);

	/**
	 * Donne un statut à l'entité
	 * 
	 * @param status
	 *            Le statut
	 */
	public void playStatus(EntityStatus status);

	/**
	 * Supprime l'entité. L'entité pour être respawn. Pour la supprimer de la
	 * mémoire utiliser {@link destroy}.
	 */
	public void remove();

	/**
	 * Supprime l'entité pour un seul joueur.
	 * 
	 * @param player
	 *            Le joueur
	 */
	public void remove(BadblockPlayer player);

	/**
	 * Si le joueur voit l'entité
	 * 
	 * @param player
	 *            Le joueur
	 * @return Si il la voit
	 */
	public boolean see(BadblockPlayer player);

	/**
	 * Change une partie de l'équimement de l'entité
	 * 
	 * @param equipmentSlot
	 *            Le slot
	 * @param itemstack
	 *            L'item
	 */
	public void setEquipment(EquipmentSlot equipmentSlot, ItemStack itemstack);

	/**
	 * Modifie la rotation de la tête de l'entité
	 * 
	 * @param yaw
	 *            La position de la tête
	 */
	public void setHeadYaw(float yaw);

	/**
	 * Définit la visibilité de l'entité
	 * 
	 * @param visibility
	 *            La visibilité
	 */
	public void setVisibility(Visibility visibility);

	/**
	 * Affiche l'entité à un joueur.
	 * 
	 * @param player
	 *            Le joueur
	 */
	public void show(BadblockPlayer player);

	/**
	 * Téléporte l'entité
	 * 
	 * @param location
	 *            La nouvelle position
	 */
	public void teleport(Location location);

	/**
	 * Update les watchers de l'entité (après qu'ils aient été modifiée)
	 */
	public void updateWatchers();
}
