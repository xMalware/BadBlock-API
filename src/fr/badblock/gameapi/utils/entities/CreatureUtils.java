package fr.badblock.gameapi.utils.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;

import fr.badblock.gameapi.utils.general.MathsUtils;
import fr.badblock.gameapi.utils.reflection.ReflectionUtils;

/**
 * Classe d'aide à la gestion des différentes créatures MineCraft.
 * 
 * @author LeLanN
 */
public class CreatureUtils {
	/**
	 * Fait spawn une entité.
	 * 
	 * @param location
	 *            La position du spawn
	 * @param clazz
	 *            Le type d'entité à faire spawn
	 */
	public static void spawn(Location location, Class<? extends Entity> clazz) {
		location.getWorld().spawn(location, clazz);
	}

	/**
	 * Recherche toutes les entités proche d'un certain point.
	 * 
	 * @param location
	 *            La position centrale
	 * @param radius
	 *            La distance maximum entre l'entité et la position
	 * 
	 * @return Les entités trouvées
	 */
	public static Collection<Entity> getNearbyEntities(Location location, double radius) {
		List<Entity> entities = new ArrayList<>();

		for (Entity e : location.getWorld().getEntities()) {
			if (e.getLocation().distance(location) <= radius) {
				entities.add(e);
			}
		}

		return entities;
	}

	/**
	 * Change le yaw et le pitch de la location pour regarder vers une autre
	 * 
	 * @param from
	 *            La location à changer
	 * @param to
	 *            La location à regarder
	 * @param aiming
	 *            La précision (100 = précision totale)
	 * @return La location changée
	 */
	public static Location lookAt(Location from, Location to, float aiming) {
		from = from.clone();

		double dx = to.getX() - from.getX();
		double dy = to.getY() - from.getY();
		double dz = to.getZ() - from.getZ();

		if (dx != 0) {
			from.setYaw((float) ((dx < 0 ? 1.5 : 0.5) * Math.PI));
			from.setYaw(from.getYaw() - (float) Math.atan(dz / dx));
		} else if (dz < 0) {
			from.setYaw((float) Math.PI);
		}

		double dxz = Math.sqrt(Math.pow(dx, 2) + Math.pow(dz, 2));

		from.setPitch((float) -Math.atan(dy / dxz));

		float yawAiming = 360 - (aiming * 360 / 100);
		if (yawAiming > 0)
			yawAiming = MathsUtils.integerRandomInclusive((int) yawAiming, (int) -yawAiming);

		float pitchAiming = 180 - (aiming * 180 / 100);
		if (pitchAiming > 0)
			pitchAiming = MathsUtils.integerRandomInclusive((int) pitchAiming, (int) -pitchAiming);

		from.setYaw(-from.getYaw() * 180f / (float) Math.PI + yawAiming);
		from.setPitch(from.getPitch() * 180f / (float) Math.PI + pitchAiming);

		return from;
	}

	/**
	 * Permet de récupérer une entité par son UUID.
	 * 
	 * @param world
	 *            Le monde où se trouve l'entité
	 * @param entityId
	 *            L'UUID de l'entité.
	 * @return L'entité si elle est trouvée (autrement, null).
	 */
	public static Entity getEntityByUUID(World world, UUID entityId) {
		for (Entity entity : world.getEntities()) {
			if (entity.getUniqueId().equals(entityId))
				return entity;
		}

		return null;
	}

	/**
	 * Recherche la version modifiée (par l'API) d'une créature. Si l'API n'a
	 * pas sa classe de register ou que l'entité n'est pas gérée, retourne null.
	 * 
	 * @param entity
	 *            L'entité.
	 * @return La version modifiée de la créature. Attention, peut être null !
	 */
	public static CustomCreature getAsCustom(Entity entity) {
		Object handler = ReflectionUtils.getHandle(entity);

		// System.out.println(handler);

		if (handler instanceof CustomCreature)
			return (CustomCreature) handler;
		return null;
	}
}
