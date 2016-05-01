package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import lombok.Getter;

/**
 * Le packet envoyé pour dire au client d'afficher un objectif déjà connu.
 * @author LeLanN
 */
public interface PlayScoreboardDisplayObjective extends BadblockOutPacket {
	/**
	 * Définit la position de l'objectif
	 * @param objectivePosition La position
	 * @return Le packet
	 */
	public PlayScoreboardDisplayObjective setObjectivePosition(ObjectivePosition objectivePosition);
	
	/**
	 * Définit le nom (interne) de l'objectif en question
	 * @param objectiveName Le nom
	 * @return Le packet
	 */
	public PlayScoreboardDisplayObjective setObjectiveName(String objectiveName);

	/**
	 * Représente les différentes positions possibles du scoreboard
	 * @author LeLanN
	 */
	public enum ObjectivePosition {
		/**
		 * Dans la tablist (à côté du pseudo du joueur), généralement la vie du joueur
		 */
		LLST((byte) 0),
		/**
		 * Le scoreboard en lui-même
		 */
		SIDEBAR((byte) 1),
		/**
		 * En dessous du pseudo du joueur
		 */
		BELOW_NAME((byte) 2);
		
		@Getter
		private byte data;
		
		ObjectivePosition(byte data){
			this.data = data;
		}
		
		public static ObjectivePosition getByValue(byte value){
			for(ObjectivePosition c : values())
				if(c.getData() == value)
					return c;
			return null;
		}
	}
}