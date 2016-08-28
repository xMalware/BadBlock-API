package fr.badblock.gameapi.packets.out.play;

import fr.badblock.gameapi.packets.BadblockOutPacket;
import lombok.Getter;

public interface PlayScoreboardObjective extends BadblockOutPacket {
	public String getObjectiveName();

	public PlayScoreboardObjective setObjectiveName(String objectiveName);

	public ObjectiveMode getMode();

	public PlayScoreboardObjective setMode(ObjectiveMode mode);

	public String getDisplayName();

	public PlayScoreboardObjective setDisplayName(String displayName);

	public ObjectiveType getObjectiveType();

	public PlayScoreboardObjective setObjectiveType(ObjectiveType type);

	public enum ObjectiveMode {
		CREATE((byte) 0), REMOVE((byte) 1), UPDATE((byte) 2);

		@Getter
		private byte data;

		ObjectiveMode(byte data) {
			this.data = data;
		}

		public static ObjectiveMode getByValue(byte value) {
			for (ObjectiveMode c : values())
				if (c.getData() == value)
					return c;
			return null;
		}
	}

	public enum ObjectiveType {
		INTEGER, HEARTS;
	}
}
