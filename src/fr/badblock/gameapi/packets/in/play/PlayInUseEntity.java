package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;
import fr.badblock.gameapi.utils.selections.Vector3f;
import lombok.Getter;

/**
 * Packet envoyé par le joueur lorsqu'il intéragit avec une entité
 * 
 * @author LeLanN
 */
public interface PlayInUseEntity extends BadblockInPacket {
	/**
	 * Représente les différentes actions pour {@link PlayInUseEntity}.
	 * 
	 * @author LeLanN
	 */
	public enum UseEntityAction {
		INTERACT(0), ATTACK(1), INTERACT_AT(2);

		public static UseEntityAction getById(int id) {
			for (UseEntityAction state : values())
				if (state.getId() == id)
					return state;
			return null;
		}

		@Getter
		private int id;

		UseEntityAction(int id) {
			this.id = id;
		}
	}

	/**
	 * Récupère l'action effectuée par le joueur
	 * 
	 * @return L'action
	 */
	public UseEntityAction getAction();

	/**
	 * Récupère l'action effectuée par le joueur
	 * 
	 * @return L'action
	 */
	public void setAction(UseEntityAction action);
	
	/**
	 * Récupère l'ID de l'entité visée
	 * 
	 * @return L'ID
	 */
	public int getEntityId();
	
	/**
	 * Récupère l'ID de l'entité visée
	 * 
	 * @return L'ID
	 */
	public void setEntityId(int id);

	/**
	 * Récupère la position du target (uniquement pour
	 * {@link UseEntityAction#INTERACT_AT})
	 * 
	 * @return La position
	 */
	public Vector3f getTargetPosition();

	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_USE_ENTITY;
	}
}
