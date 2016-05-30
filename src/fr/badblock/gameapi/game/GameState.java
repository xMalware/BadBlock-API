package fr.badblock.gameapi.game;

import lombok.Getter;

/**
 * Cette classe représente les différents statuts de la partie
 * @author LeLanN
 */
@Getter public enum GameState {
	WAITING(1),
	RUNNING(2),
	FINISHED(3),
	STOPPING(4);

	private final int id;

	private GameState(int id){
		this.id = id;
	}

	public static GameState getStatus(int id){
		for(final GameState status : values()){
			if(status.getId() == id)
				return status;
		}

		return null;
	}
}