package fr.badblock.gameapi.fakeentities;

import org.bukkit.DyeColor;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Villager;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import fr.badblock.gameapi.GameAPI;
import fr.badblock.gameapi.configuration.values.MapLocation;
import fr.badblock.gameapi.configuration.values.MapValue;
import fr.badblock.gameapi.packets.out.play.PlayEntityStatus.EntityStatus;
import fr.badblock.gameapi.packets.watchers.WatcherAgeable;
import fr.badblock.gameapi.packets.watchers.WatcherCreeper;
import fr.badblock.gameapi.packets.watchers.WatcherEntity;
import fr.badblock.gameapi.packets.watchers.WatcherLivingEntity;
import fr.badblock.gameapi.packets.watchers.WatcherSheep;
import fr.badblock.gameapi.packets.watchers.WatcherSkeleton;
import fr.badblock.gameapi.packets.watchers.WatcherVillager;
import fr.badblock.gameapi.packets.watchers.WatcherZombie;
import fr.badblock.gameapi.players.BadblockPlayer;
import fr.badblock.gameapi.utils.entities.CreatureType;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Représente une entité qui n'est pas gérée côté serveur. Ne peut ętre qu'une
 * entité vivante.
 * 
 * @author LeLanN
 *
 * @param <T> Le watcher correspondant au type de l'entité gérée.
 */
public interface FakeEntity<T extends WatcherEntity> {
	@NoArgsConstructor@AllArgsConstructor
	public static class EntityConfig implements MapValue<FakeEntity<?>> {
		public EntityConfig(Location loc){
			this.location = new MapLocation(loc);
		}
		
		public MapLocation  		location		  = new MapLocation();
		public CreatureType 		creature		  = CreatureType.VILLAGER;
		
		public boolean 				isOnFire		  = false;
		public int					arrowsInBody	  = 0;
		
		public Villager.Profession	profession		  = Villager.Profession.LIBRARIAN;
		public DyeColor				color			  = DyeColor.RED;
		public boolean				isZombieVillager  = false;
		public boolean				isCreeperPowered  = false;
		public boolean				isWitherSkeleton  = false;
		public boolean			    isBaby			  = false;
		
		@Override
		public FakeEntity<?> getHandle() {
			return spawnFakeEntity(this);
		}
	}
	
	public static FakeEntity<?> spawnFakeEntity(EntityConfig config){
		EntityType ent = config.creature.bukkit();
		Location   loc = config.location.getHandle();
		
		FakeEntity<? extends WatcherLivingEntity> result = null;
		
		switch(config.creature){
			case CREEPER:
				FakeEntity<WatcherCreeper> creeper = GameAPI.getAPI().spawnFakeLivingEntity(loc, ent, WatcherCreeper.class);
				creeper.getWatchers().setPowered(config.isCreeperPowered);
				result = creeper;
			break;
			case CHICKEN:
			case COW:
			case HORSE:
			case OCELOT:
			case RABBIT:
			case WOLF:
				FakeEntity<WatcherAgeable> ageable = GameAPI.getAPI().spawnFakeLivingEntity(loc, ent, WatcherAgeable.class);
				ageable.getWatchers().setBaby(config.isBaby);
				result = ageable;
			break;
			case SHEEP:
				FakeEntity<WatcherSheep> sheep = GameAPI.getAPI().spawnFakeLivingEntity(loc, ent, WatcherSheep.class);
				sheep.getWatchers().setColor(config.color).setBaby(config.isBaby);
				result = sheep;
			break;
			case SKELETON:
				FakeEntity<WatcherSkeleton> skeleton = GameAPI.getAPI().spawnFakeLivingEntity(loc, ent, WatcherSkeleton.class);
				skeleton.getWatchers().setWither(config.isWitherSkeleton);
				result = skeleton;
			break;
			case VILLAGER:
				FakeEntity<WatcherVillager> villager = GameAPI.getAPI().spawnFakeLivingEntity(loc, ent, WatcherVillager.class);
				villager.getWatchers().setProfession(config.profession).setBaby(config.isBaby);
				result = villager;
			break;
			case ZOMBIE:
				FakeEntity<WatcherZombie> zombie = GameAPI.getAPI().spawnFakeLivingEntity(loc, ent, WatcherZombie.class);
				zombie.getWatchers().setVillager(config.isZombieVillager).setBaby(config.isBaby);
				result = zombie;
			break;
			default:
				result = GameAPI.getAPI().spawnFakeLivingEntity(loc, ent, WatcherLivingEntity.class);
			break;
		
		}
		
		result.getWatchers().setArrowsInEntity(config.arrowsInBody).setOnFire(config.isOnFire);
		return result;
	}
	
	/**
	 * Supprime l'entité si ce n'est pas fait et la supprime du cache (ne pourra
	 * plus ętre réutilisée).
	 */
	public void destroy();

	/**
	 * Récupčre la rotation de la tęte de l'entité
	 * 
	 * @return La position de la tęte
	 */
	public float getHeadYaw();

	/**
	 * L'entité de l'ID
	 * 
	 * @return L'ID
	 */
	public int getId();

	/**
	 * Récupčre la position du joueur
	 * 
	 * @return La position
	 */
	public Location getLocation();

	/**
	 * Récupčre le type de l'entité
	 * 
	 * @return Le type
	 */
	public EntityType getType();

	/**
	 * Récupčre la visibilité de l'entité
	 * 
	 * @return La visibilité
	 */
	public Visibility getVisibility();

	/**
	 * Récupčre les watchers de l'entité
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
	 * Tue (naturellement) l'entité. Autrement, męme effet que remove().
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
	 * Donne un statut ŕ l'entité
	 * 
	 * @param status
	 *            Le statut
	 */
	public void playStatus(EntityStatus status);

	/**
	 * Supprime l'entité. L'entité pour ętre respawn. Pour la supprimer de la
	 * mémoire utiliser {@link destroy}.
	 */
	public void remove();

	/**
	 * Add player in a view list
	 * @param list The list
	 * @param player The player
	 */
	public void addPlayer(EntityViewList list, BadblockPlayer player);
	
	/**
	 * Remove player from a view list
	 * @param list The list
	 * @param player The player
	 */
	public void removePlayer(EntityViewList list, BadblockPlayer player);
	
	/**
	 * Check if the player is in a view list
	 * @param list The list
	 * @param player The player
	 * @return Boolean
	 */
	public boolean isPlayerIn(EntityViewList list, BadblockPlayer player);
	
	/**
	 * Change une partie de l'équimement de l'entité
	 * 
	 * @param equipmentSlot Le slot
	 * @param itemstack L'item
	 */
	public void setEquipment(EquipmentSlot equipmentSlot, ItemStack itemstack);

	/**
	 * Modifie la rotation de la tęte de l'entité
	 * 
	 * @param yaw La position de la tęte
	 */
	public void setHeadYaw(float yaw);

	/**
	 * Définit la visibilité de l'entité
	 * 
	 * @param visibility La visibilité
	 */
	public FakeEntity<T> setVisibility(Visibility visibility);

	/**
	 * Téléporte l'entité
	 * 
	 * @param location *            La nouvelle position
	 */
	public void teleport(Location location);

	/**
	 * Update les watchers de l'entité (aprčs qu'ils aient été modifiée)
	 */
	public void updateWatchers();
	
	/**
	 * Check if the player can see the entity judging by the visibility and view lists
	 * @param player The player
	 * @return Boolean
	 */
	public default boolean canSeeEntity(BadblockPlayer player){
		switch(getVisibility()){
			case PLAYER: return isPlayerIn(EntityViewList.WHITELIST, player);
			case SERVER: return !isPlayerIn(EntityViewList.BLACKLIST, player);
		}
		
		return false;
	}
	
	public enum EntityViewList {
		/**
		 * Use with {@link Visibility#SERVER}
		 */
		BLACKLIST,
		/**
		 * Use with {@link Visibility#PLAYER}
		 */
		WHITELIST;
	}
	
	public enum Visibility {
		SERVER, PLAYER;
	}
}
