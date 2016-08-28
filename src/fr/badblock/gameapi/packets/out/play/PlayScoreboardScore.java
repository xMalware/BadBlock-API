package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import lombok.Getter;

public interface PlayScoreboardScore extends BadblockOutPacket {
	public enum ScoreMode {
		CHANGE((byte) 0), REMOVE((byte) 1);

		@Getter
		private byte data;

		ScoreMode(byte data) {
			this.data = data;
		}
	}

	public ScoreMode getMode();

	public String getObjectiveName();

	public int getScore();

	public String getScoreName();

	public PlayScoreboardScore setMode(ScoreMode mode);

	public PlayScoreboardScore setObjectiveName(String objectiveName);

	public PlayScoreboardScore setScore(int score);

	public PlayScoreboardScore setScoreName(String scoreName);
}