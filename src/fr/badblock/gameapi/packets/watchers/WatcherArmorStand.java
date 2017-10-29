package fr.badblock.gameapi.packets.watchers;

import fr.badblock.gameapi.utils.selections.Vector3f;
import lombok.Getter;

/**
 * Représente les watchers d'un armor stand
 * 
 * @author LeLanN
 */
public interface WatcherArmorStand extends WatcherLivingEntity {
	/**
	 * Représente les différentes propriétés possible pour une armor stand
	 * 
	 * @author LeLanN
	 */
	public enum ArmorStandFlag {
		/**
		 * L'armor stand est petite
		 */
		SMALL_ARMORSTAND(0x01),
		/**
		 * L'armor stand est soumise ŕ la gravité
		 */
		HAS_GRAVITY(0x02),
		/**
		 * L'armor stand a des bras
		 */
		HAS_ARMS(0x04),
		/**
		 * L'armor stand n'a pas de plateau en dessous
		 */
		REMOVE_BASEPLATE(0x08),
		/**
		 * L'armor stand est totalement invisible<br>
		 * Utile si l'on veut faire disparaître temporairement une armor stand.
		 */
		MARKER(0x10);

		@Getter
		private final int value;

		ArmorStandFlag(int value) {
			this.value = value;
		}
	}

	/**
	 * Ajoute une propriété ŕ l'armor stand
	 * 
	 * @param flag
	 *            La propriété
	 * @return Le watcher
	 */
	public WatcherArmorStand addFlag(ArmorStandFlag flag);

	/**
	 * Enlčve une propriété ŕ l'armor stand
	 * 
	 * @param flag
	 *            La propriété
	 * @return Le watcher
	 */
	public WatcherArmorStand removeFlag(ArmorStandFlag flag);

	/**
	 * Chhange la position du corps d'une armor stand
	 * 
	 * @param position
	 *            La position
	 * @return le watcher
	 */
	public WatcherArmorStand setBodyRotation(Vector3f position);

	/**
	 * Chhange la position de la tęte d'une armor stand
	 * 
	 * @param position
	 *            La position de la tęte
	 * @return le watcher
	 */
	public WatcherArmorStand setHeadRotation(Vector3f position);

	/**
	 * Chhange la position du brase gauche d'une armor stand
	 * 
	 * @param position
	 *            La position
	 * @return le watcher
	 */
	public WatcherArmorStand setLeftArmRotation(Vector3f position);

	/**
	 * Chhange la position de la jambe gauche d'une armor stand
	 * 
	 * @param position
	 *            La position
	 * @return le watcher
	 */
	public WatcherArmorStand setLeftLegRotation(Vector3f position);

	/**
	 * Chhange la position du bras droit d'une armor stand
	 * 
	 * @param position
	 *            La position
	 * @return le watcher
	 */
	public WatcherArmorStand setRightArmRotation(Vector3f position);

	/**
	 * Chhange la position de la jambe droite d'une armor stand
	 * 
	 * @param position
	 *            La position
	 * @return le watcher
	 */
	public WatcherArmorStand setRightLegRotation(Vector3f position);
}
