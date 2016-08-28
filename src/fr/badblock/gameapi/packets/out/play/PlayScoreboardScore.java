package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import lombok.Getter;

public interface PlayScoreboardScore extends BadblockOutPacket {
	public String getScoreName();

	public PlayScoreboardScore setScoreName(String scoreName);

	public ScoreMode getMode();

	public PlayScoreboardScore setMode(ScoreMode mode);

	public String getObjectiveName();

	public PlayScoreboardScore setObjectiveName(String objectiveName);

	public int getScore();

	public PlayScoreboardScore setScore(int score);

	public enum ScoreMode {
		CHANGE((byte) 0), REMOVE((byte) 1);

		@Getter
		private byte data;

		ScoreMode(byte data) {
			this.data = data;
		}
	}
}