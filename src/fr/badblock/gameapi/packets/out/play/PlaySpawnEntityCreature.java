package fr.badblock.gameapi.packets.out.play;

import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import fr.badblock.gameapi.packets.watchers.WatcherEntity;
import lombok.Getter;

/**
 * Packet envoyé lors du spawn d'une créature vivante (monster, animal, complex, ambient, ...).
 * @author LeLanN
 */
public interface PlaySpawnEntityCreature extends BadblockOutPacket {
	/**
	 * Récupère l'ID de l'entité
	 * @return L'ID
	 */
	public int getEntityId();
	
	/**
	 * Définit l'ID de l'entité
	 * @param id L'ID
	 * @return Le packet
	 */
	public PlaySpawnEntityCreature setEntityId(int id);
	
	/**
	 * Récupère le type de l'objet
	 * @return Le type
	 */
	public EntityType getType();
	
	/**
	 * Définit le type de l'objet
	 * @param type Le type
	 * @return Le packet
	 */
	public PlaySpawnEntityCreature setType(EntityType type);
	
	/**
	 * Récupère la position
	 * @return La position
	 */
	public Location getLocation();
	
	/**
	 * Définit la position
	 * @param location La position
	 * @return Le packet
	 */
	public PlaySpawnEntityCreature setLocation(Location location);
	
	/**
	 * Récupère la rotation de la tête (angle)
	 * @return La rotation
	 */
	public float getHeadRotation();
	
	/**
	 * Définit la rotation de la tête (angle)
	 * @param rotation
	 * @return Le packet
	 */
	public PlaySpawnEntityCreature setHeadRotation(float rotation);
	
	/**
	 * Récupère la vélocité
	 * @return La vélocité
	 */
	public Vector getVelocity();
	
	/**
	 * Définit la vélocité
	 * @param velocity La vélocité
	 * @return Le packet
	 */
	public PlaySpawnEntityCreature setVelocity(Vector velocity);
	
	/**
	 * Récupère les watchers de l'entité
	 * @return Les watchers
	 */
	public WatcherEntity getWatchers();
	
	/**
	 * Définit les watchers de l'entité
	 * @param watcher Les watchers
	 * @return Le packet
	 */
	public PlaySpawnEntityCreature setWatchers(WatcherEntity watcher);
	
	/**
	 * Représente les différents mobs spawnables avec {@link PlaySpawnEntityCreature}
	 * @author LeLanN
	 */
	public enum SpawnableCreatures {
		/**
		 * Un creeper
		 */
		CREEPER(50),
		/**
		 * Un squelette
		 */
		SKELETON(51),
		/**
		 * Une arraîgnée
		 */
		SPIDER(52),
		/**
		 * Un zombie géant
		 */
		GIANT_ZOMBIE(53),
		/**
		 * Un zombie
		 */
		ZOMBIE(54),
		/**
		 * Un slime
		 */
		SLIME(55),
		/**
		 * Un ghast
		 */
		GHAST(56),
		/**
		 * Un pigman
		 */
		PIGMAN(57),
		/**
		 * Un enderman
		 */
		ENDERMAN(58),
		/**
		 * Une arraîgnée des cavernes
		 */
		CAVE_SPIDER(59),
		/**
		 * Un silverfish
		 */
		SILVERFISH(60),
		/**
		 * Un blaze
		 */
		BLAZE(61),
		/**
		 * Un magma cube
		 */
		MAGMA_CUBE(62),
		/**
		 * Un enderdragon
		 */
		ENDER_DRAGON(63),
		/**
		 * Un wither
		 */
		WITHER(64),
		/**
		 * Une chauve-souris
		 */
		BAT(65),
		/**
		 * Une sorcière
		 */
		WITCH(66),
		/**
		 * Une endermite
		 */
		ENDERMITE(67),
		/**
		 * Un guardian
		 */
		GUARDIAN(68),
		/**
		 * Un cochon
		 */
		PIG(90),
		/**
		 * Un mouton
		 */
		SHEEP(91),
		/**
		 * Une vache
		 */
		COW(92),
		/**
		 * Un poulet
		 */
		CHICKEN(93),
		/**
		 * Un poulpe
		 */
		SQUID(94),
		/**
		 * Un loup
		 */
		WOLF(95),
		/**
		 * Une vache-champignon (champimeuh)
		 */
		MOOSHROOM(96),
		/**
		 * Un bonhomme de neighe
		 */
		SNOWMAN(97),
		/**
		 * Un ocelot
		 */
		OCELOT(98),
		/**
		 * Un golem de fer
		 */
		IRON_GOLEM(99),
		/**
		 * Un cheval
		 */
		HORSE(100),
		/**
		 * Un lapin
		 */
		RABBIT(101),
		/**
		 * Un villageois
		 */
		VILLAGER(120);
		
		@Getter private byte data;
		
		SpawnableCreatures(int data){
			this.data = (byte) data;
		}
		
		public static SpawnableCreatures getByValue(byte value){
			for(SpawnableCreatures c : values())
				if(c.getData() == value)
					return c;
			return null;
		}
	}
}
