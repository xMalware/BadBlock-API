package fr.badblock.gameapi.packets.in.play;

import fr.badblock.gameapi.packets.BadblockInPacket;
import fr.badblock.gameapi.packets.BadblockInPackets;
import fr.badblock.gameapi.utils.selections.Vector3f;
import lombok.Getter;

/**
 * Packet envoyé par le joueur lorsqu'il mine un block
 * 
 * @author LeLanN
 */
public interface PlayInBlockDig extends BadblockInPacket {
	/**
	 * Récupère l'action du joueur
	 * 
	 * @return L'action
	 */
	public BlockDigAction getAction();

	/**
	 * Récupère la position du block
	 * 
	 * @return La position
	 */
	public Vector3f getPosition();

	/**
	 * Récupère la face visée par le joueur
	 * 
	 * @return La face
	 */
	public BlockFace getBlockFace();

	@Override
	default BadblockInPackets getType() {
		return BadblockInPackets.PLAY_BLOCK_DIG;
	}

	/**
	 * Représente les différentes actions pour {@link PlayInBlockDig}.
	 * 
	 * @author LeLanN
	 */
	public enum BlockDigAction {
		STARTED_DIGGING(0), CANCELLED_DIGGING(1), FINISHED_DIGGING(2), DROP_ITEM_STACK(3), DROP_ITEM(
				4), SHOOWARROW_FINISHEATING(5);

		@Getter
		private int id;

		BlockDigAction(int id) {
			this.id = id;
		}

		public static BlockDigAction getById(int id) {
			for (BlockDigAction state : values())
				if (state.getId() == id)
					return state;
			return null;
		}
	}

	/**
	 * Représente les différentes face d'un block
	 * 
	 * @author LeLanN
	 */
	public enum BlockFace {
		Y_NEGATIF(0), Y_POSITIF(1), Z_NEGATIF(2), Z_POSITIF(3), X_NEGATIF(4), X_POSITIF(5);

		@Getter
		private int id;

		BlockFace(int id) {
			this.id = id;
		}

		public static BlockFace getById(int id) {
			for (BlockFace state : values())
				if (state.getId() == id)
					return state;
			return null;
		}
	}
}
