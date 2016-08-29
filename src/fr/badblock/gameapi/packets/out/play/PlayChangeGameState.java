package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import lombok.Getter;

/**
 * Packet envoyé lorsque une donnée de jeu change.
 * 
 * @author LeLanN
 */
public interface PlayChangeGameState extends BadblockOutPacket {
	/**
	 * Représente les différentes choses pouvant être appelée avec
	 * {@link PlayChangeGameState}
	 * 
	 * @author LeLanN
	 */
	public static enum GameState {
		INVALID_BED(0), RAINING_END(1), RAINING_START(2),
		/**
		 * Valeur : valeur numérique du gamemode (0, 1, 2, 3)
		 */
		GAMEMODE_CHANGE(3), ENTER_CREDITS(4),
		/**
		 * Valeurs :
		 * <ul>
		 * <li>0 : montre l'image de démo</li>
		 * <li>101 : montre le message indiquant comment se déplacer</li>
		 * <li>102 : montre le message indiquant comment sauter
		 * <li>
		 * <li>103 : montre le message indiquant comment ouvrir l'inventaire
		 * <li>
		 * </ul>
		 */
		DEMO_MESSAGE(5), ARROW_HIT_PLAYER(6),
		/**
		 * La luminosité actuelle, entre 0 et 1 (0 = lumineux, 1 = sombre)
		 */
		FADE_VALUE(7),
		/**
		 * L'heure du monde en ticks (entre 0 et 24000)
		 */
		FADE_TIME(8), PLAY_MOB_APPEARANCE(10);

		public static GameState getFromId(int id) {
			for (GameState a : values())
				if (a.getValue() == id)
					return a;
			return null;
		}

		@Getter
		private final int value;

		GameState(int value) {
			this.value = value;
		}
	}

	/**
	 * Récupère ce qu'il faut changer
	 * 
	 * @return Ce qu'il faut changer
	 */
	public GameState getState();

	/**
	 * Récupère la valeur du gamestate. Voir {@link GameState} POur connaître
	 * les valeurs possibles (rien écrit = pas de valeur particulière).
	 * 
	 * @return La valeur
	 */
	public float getValue();

	/**
	 * Définit ce qu'il faut changer
	 * 
	 * @param state
	 *            Ce qu'il faut changer
	 * @return Le packet
	 */
	public PlayChangeGameState setState(GameState state);

	/**
	 * Définit la valeur du gamestate. Voir {@link GameState} POur connaître les
	 * valeurs possibles (rien écrit = pas de valeur particulière).
	 * 
	 * @param value
	 *            La valeur
	 * @return Le packet
	 */
	public PlayChangeGameState setValue(float value);
}
